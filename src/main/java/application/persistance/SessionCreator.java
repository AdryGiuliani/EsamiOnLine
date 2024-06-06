package application.persistance;


import application.TestDB;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static application.persistance.Utils.*;
import static application.persistance.Utils.DB_PWD;

//pattern singleton implementato tramite Bill Pugh Singleton
public class SessionCreator {
    private final SessionFactory sf;
    public Session getSession(){
        return sf.openSession();
    }
    private SessionCreator(){
        if (!TestDB.databaseExists(DB_NAME, DB_URL, DB_USER, DB_PWD)){
            TestDB.createDatabase(DB_NAME, DB_URL, DB_USER, DB_PWD);
        }
        Configuration config  = new Configuration();
        config.configure("hibernate.cgf.xml");
        this.sf = config.buildSessionFactory();
    };
    private static class SessionCreatorHelper{
        private static final SessionCreator instance = new SessionCreator();
    }
    public static SessionCreator getInstance(){
        return SessionCreatorHelper.instance;
    }
}
