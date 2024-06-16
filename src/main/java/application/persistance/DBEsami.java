package application.persistance;

import application.persistance.pojos.*;
import application.persistance.util.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static application.persistance.SessionCreator.*;
import static application.persistance.util.Utils.KEY_OPZIONI;

public class DBEsami implements Database{

    public Options caricaOpzioni(){
        Options opt = this.carica(Options.class, KEY_OPZIONI);
        if (opt == null) {
            opt = new Options();
            this.salva(opt);
        }
        return opt;
    }

    public List<Appello> getDisponibili(String mat) {
        Options opt = caricaOpzioni();
        Timestamp deadline = new Timestamp(System.currentTimeMillis()+opt.getDeadline_millis());
        List<Appello> disponibili = new ArrayList<>();
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, mat);
        if (s == null) {
            tx.commit();
            session.close();
            return disponibili;
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Appello> cquery = cb.createQuery(Appello.class);
        Root<Appello> app_root = cquery.from(Appello.class);
        Predicate checkAppelloCorso = cb.equal(app_root.get("codcorso"), s.getCodCorso());
        Predicate checkdata = cb.greaterThanOrEqualTo(app_root.get("data_ora"), deadline);
        Predicate disponibile = cb.and(checkAppelloCorso, checkdata);
        //se sono presenti altri check di disponibilità (es propedeuticità) possono essere aggiunti qui
        cquery.select(app_root).where(disponibile);
        Query q = session.createQuery(cquery);
        disponibili = q.getResultList();
        disponibili.removeAll(s.getPrenotazioni());
        for (Risultato r : s.getCompletato())
            if (r.isSuperato()){
                disponibili.removeIf(app -> app.equalsSoft(r.getCompleted_appello()));
            }
        tx.commit();
        session.close();
        return disponibili;
    }


    public void prenota(String mat, Long idAppello) {
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, mat);
        Appello appello = session.get(Appello.class, idAppello);
        s.getPrenotazioni().add(appello);
        session.merge(s);
        tx.commit();
        session.close();
    }

    public void salvaRisultato(String mat, long idAppello, int punteggio) {
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, mat);
        Appello appello = session.get(Appello.class, idAppello);
        Risultato result = new Risultato();
        Options options = caricaOpzioni();
        result.setSuperato(punteggio>= options.getMinVoto());
        result.setPunteggio(punteggio);
        result.setCompleted_appello(appello);
        result.setStudente(s);
        s.getCompletato().add(result);
        tx.commit();
        session.close();
    }

    public List<Risultato> getRisultati(long idAppello){
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Appello appello = session.get(Appello.class, idAppello);
        List<Risultato> res = appello.getRisultati();
        session.close();
        return res;
    }

    /*
    public Collection<Student> getPrenotazioni(long idAppello) {
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Appello appello = session.get(Appello.class, idAppello);
        Collection<Student> res = appello.getStudentiPrenotati();
        tx.commit();
        session.close();
        return res;
    }
*/
    public List<Domanda> getDomande(long idAppello) {
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        Appello appello = session.get(Appello.class, idAppello);
        List<Domanda> res = appello.getDomande();
        tx.commit();
        session.close();
        return res;
    }

    public List<Appello> getAllAppelli(){
        List<Appello> res = new ArrayList<>();
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Appello> cquery = cb.createQuery(Appello.class);
        Root<Appello> app_root = cquery.from(Appello.class);
        cquery.select(app_root);
        Query q = session.createQuery(cquery);
        res = q.getResultList();
        res.sort((o1, o2) -> o2.getData_ora().compareTo(o1.getData_ora()));
        for (Appello a : res){ //purtroppo necessario per mantenere lazy loading su pojo Appelli
            System.out.println(a.getDomande());
            System.out.println(a.getRisultati());
        }
        tx.commit();
        session.close();
        return res;
    }



}
