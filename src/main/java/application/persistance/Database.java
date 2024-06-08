package application.persistance;

import application.persistance.pojos.Pojo;
import jakarta.persistence.RollbackException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.util.Collection;

public interface Database {

    SessionCreator sc = SessionCreator.getInstance();
    default boolean salva(Pojo... coll){
        boolean status = true;
        try {
            Session session = sc.getSession();
            Transaction tx = session.beginTransaction();
            for (Pojo obj :coll){
                session.persist(obj);
            }
            tx.commit();
            session.close();
        }catch (ConstraintViolationException e){
            System.out.println(e.getMessage());
            status = false;
        }
        return status;

    };

    /**
     * carica l'oggetto con il dato Id e del tipo di obj dentro obj stesso, ritorna false se la
     * query non è stata eseguita, assegna invece obj = null se non è stato trovato
     * @param obj
     * @param id
     * @return
     */
    default boolean carica(Pojo obj, Object id){
        boolean status = true;
        try {
            Session session = sc.getSession();
            Transaction tx = session.beginTransaction();
            obj = session.get(obj.getClass(), id);
            tx.commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    };
    default boolean delete( Pojo obj){
        boolean status = true;
        try {
            Session session = sc.getSession();
            Transaction tx = session.beginTransaction();
            session.remove(obj);
            tx.commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    };

    default boolean delete(Pojo placeholder, Object id){
        boolean status = true;
        try {
            Session session = sc.getSession();
            Transaction tx = session.beginTransaction();
            session.load(placeholder, id);
            session.remove(placeholder);
            tx.commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }
    default boolean update_or_add(Pojo obj){
        boolean status = true;
        try {
            Session session = sc.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(obj);
            tx.commit();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }
    default boolean esiste(Pojo placeholder ,Object id){
        this.carica(placeholder,id);
        return placeholder!=null;
    }
}
