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
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static application.persistance.SessionCreator.*;

public class DBEsami implements Database{

    public List<Appello> getDisponibili(String mat) {
        Student s = new Student();
        this.carica(s,mat);
        Options opt = new Options();
        this.carica(opt, 0);
        Timestamp deadline = new Timestamp(System.currentTimeMillis()+opt.getDeadline_millis());
        List<Appello> disponibili = new ArrayList<>();
        try (EntityManager em = getEMfactory().createEntityManager()) {
            EntityTransaction t = em.getTransaction();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Appello> cquery = cb.createQuery(Appello.class);
            Root<Appello> app_root = cquery.from(Appello.class);
            Predicate checkAppelloCorso = cb.equal(app_root.get("codcorso"), s.getCodCorso());
            Predicate checkdata = cb.lessThanOrEqualTo(app_root.get("dataora"),deadline);
            //se sono presenti altri check di disponibilità (es propedeuticità) possono essere aggiunti qui
            cquery.select(app_root).where(checkAppelloCorso).where(checkdata);
            Query q = em.createQuery(cquery);
            t.commit();
            disponibili = q.getResultList();
        }
        disponibili.removeAll(s.getPrenotazioni());
        for (Risultato r : s.getCompletato())
            disponibili.remove(r.getCompleted_appello());
        return disponibili;
    }

}
