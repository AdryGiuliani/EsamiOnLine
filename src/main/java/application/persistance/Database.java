package application.persistance;

import application.persistance.pojos.Pojo;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface Database {

    SessionCreator sc = SessionCreator.getInstance();
    public boolean salva(Pojo obj){
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        
        session.load(student, "231127" );
        session.merge(student);
        System.out.println(student);
        tx.commit();
        session.close();

    };
    public Pojo carica(Pojo obj, Object id);
    public boolean delete(Pojo id);
    public boolean update_or_add(Pojo obj);
}
