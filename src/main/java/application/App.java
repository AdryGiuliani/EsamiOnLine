package application;

import application.persistance.SessionCreator;
import application.persistance.pojos.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

import static application.persistance.Utils.*;
import static java.lang.System.exit;

public class App {



    public static void main(String[] args) {


        Student student = new Student();
        SessionCreator sc = SessionCreator.getInstance();
        Session session = sc.getSession();
        Transaction tx = session.beginTransaction();
        session.load(student, "231127" );
        session.merge(student);
        System.out.println(student);
        tx.commit();
        session.close();



    }
}
