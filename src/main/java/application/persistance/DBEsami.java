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
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static application.persistance.SessionCreator.*;
import static application.persistance.util.Utils.KEY_OPZIONI;

public class DBEsami implements Database{

    public List<Appello> getDisponibili(String mat) {
        Options opt = this.carica(Options.class, KEY_OPZIONI );
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
}
