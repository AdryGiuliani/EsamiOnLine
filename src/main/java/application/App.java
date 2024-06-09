package application;

import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.pojos.*;
import application.validation.Capsule;
import application.validation.CapsuleValidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class App {



    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
//        ObjectMapper om = new ObjectMapper();
//        String json = om.writeValueAsString(app);
//        FileOutputStream fo = new FileOutputStream("testfile");
//        ObjectOutputStream oos = new ObjectOutputStream(fo);
//        oos.writeObject(app);
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testfile"));
//        Appello app2 = (Appello) ois.readObject();
//        System.out.println(json);
//        Appello appels = om.readValue(json, Appello.class);
//        System.out.println(app.getDomande().getFirst());
//        System.out.println(app2.getDomande().getFirst());
//        System.out.println(appels.getDomande().getFirst());
        Capsule c = new CapsuleValidate();
        c.insertObject("a", app);
        Appello a = c.getObject("a", Appello.class);
        System.out.println(a.getDomande().getFirst());
    }
}
