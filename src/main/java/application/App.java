package application;

import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.SessionCreator;
import application.persistance.pojos.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

import static application.persistance.Utils.*;
import static java.lang.System.exit;

public class App {



    public static void main(String[] args) {
        Database db = new DBEsami();
        Student s = new Student();
        s.setMat("1111");
        s.setCf("ABC123");
        s.setCodCorso("AAA");
        ArrayList<Risposta>risp = new ArrayList<>();
        risp.add(new Risposta(true,"patate"));
        Domanda d = new Domanda("aaaaaaa", risp);

        List<Domanda> dom = new ArrayList<>();
        dom.add(d);

        Appello app = new Appello();
        app.setCodCorso("AAA");
        app.setData_ora(new Date(System.currentTimeMillis()));
        app.setDomande(dom);
        app.setNome("pipo");


        db.salva(app,d);
    }
}
