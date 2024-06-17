package application;

import application.persistance.DBEsami;
import application.persistance.SessionCreator;
import application.persistance.pojos.*;
import application.services.CapsuleDtoAssembler;
import application.services.ServerEsamiOnLine;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.CapsuleValidate;
import gen.javaproto.Dto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {



    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        ServerEsamiOnLine.start();
//        DBEsami dbEsami = new DBEsami();
//        System.out.println(dbEsami.getDomande(1));
//
//        dbEsami.salvaRisultato("1111",1,20);
//        Student s = new Student();
//        s.setMat("3333");
//        s.setCf("AAAA");
//        s.setCodCorso("BBB");
//        dbEsami.update_or_add(s);
//        System.out.println(s);
//        //        DBEsami db = new DBEsami();
//        s = new Student();
//        s.setMat("1111");
//        s.setCf("ABC123");
//        s.setCodCorso("AAA");
//        dbEsami.salva(s);
//        ArrayList<Risposta>risp = new ArrayList<>();
//        risp.add(new Risposta(true,"patate"));
//        Domanda d = new Domanda("aaaaaaa", risp);
//
//        List<Domanda> dom = new ArrayList<>();
//        dom.add(d);
//        Appello app = new Appello();
//        app.setCodcorso("AAA");
//        app.setData_ora(new Date(System.currentTimeMillis()));
//        app.setDomande(dom);
//        app.setNome("pipo");
//        Appello app2 = new Appello();
//        app2.setCodcorso("AAA");
//        app2.setData_ora(new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(2)));
//        app2.setDomande(dom);
//        app2.setNome("pipo");
//        dbEsami.salva(app,app2);
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
//        Capsule c = new CapsuleValidate();
//        c.insertObject("a", app);
//        CapsuleDtoAssembler ass = new CapsuleDtoAssembler();
//        Dto dto = ass.assemble(c);
//        c = ass.disassemble(dto);
//        System.out.println(c);
//        dto= ass.assemble(c);
//        c=ass.disassemble(dto);
//        System.out.println(c);
//        Appello a = c.getObject("a", Appello.class);
//        System.out.println(a.getDomande().getFirst());
    }
}
