package application.persistance;


import application.TestDB;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static application.persistance.util.Utils.*;
import static application.persistance.util.Utils.DB_PWD;

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
