import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Domanda;
import application.persistance.pojos.Risposta;
import application.persistance.pojos.Student;
import application.services.CapsuleDtoAssembler;
import application.services.ServerEsamiOnLine;
import application.validation.chainsteps.CapsuleValidate;
import gen.javaproto.AppelloID;
import gen.javaproto.Credentials;
import gen.javaproto.Dto;
import gen.javaproto.FrontendServicesGrpc;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BackendTest {

    public static boolean setupDone = false;
    public static FrontendServicesGrpc.FrontendServicesBlockingStub stub;
    public static CapsuleDtoAssembler assembler;
    public static long id1= 13;//necessario aggiornare id dopo la prima esecuzione
    public static long id2= 14;
    public static long id3= 17;
    public static AppelloID partecipabile_non_prenotabile = AppelloID.newBuilder().setId(id1).build();
    public static AppelloID non_partecipabile_prenotabile = AppelloID.newBuilder().setId(id2).build();
    public static AppelloID non_partecipabile_non_prenotabile = AppelloID.newBuilder().setId(id3).build();

    @Before
    public void setUp() throws Exception {
        if(!setupDone){
            setupDone=true;
            String target = "localhost:" + ServerEsamiOnLine.port;
            ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
            stub = FrontendServicesGrpc.newBlockingStub(channel);
            assembler = new CapsuleDtoAssembler();
            DBEsami db = new DBEsami();
            ArrayList<Risposta> risp = new ArrayList<>();
            risp.add(new Risposta(true,"42"));
            Domanda d = new Domanda("?", risp);
            List<Domanda> dom = new ArrayList<>();
            dom.add(d);
            Appello app = new Appello();
            app.setId(id1);
            app.setCodcorso("c1");
            app.setData_ora(new Date(System.currentTimeMillis()));
            app.setDomande(dom);
            app.setNome("app1");
            Appello app2 = new Appello();
            app2.setId(id2);
            app2.setCodcorso("c1");
            app2.setData_ora(new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(2)));
            app2.setDomande(dom);
            app2.setNome("app2");
            Appello app3 = new Appello();
            app3.setId(id3);
            app3.setCodcorso("c1");
            app3.setData_ora(new Date(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(2)));
            app3.setDomande(dom);
            app3.setNome("app3");
            db.update_or_add(app);
            db.update_or_add(app2);
            db.update_or_add(app3);
            Student s = new Student();
            s.setMat("mat1");
            s.setCf("cf1");
            s.setCodCorso("c1");
            ArrayList<Appello> prenotati = new ArrayList<>();
            prenotati.add(app3);
            prenotati.add(app2);
            prenotati.add(app);
            s.setPrenotazioni(prenotati);
            db.update_or_add(s);
            s = new Student();
            s.setMat("mat2");
            s.setCf("cf2");
            s.setCodCorso("c2");
            db.update_or_add(s);



//
            new Thread(() -> {
                try {
                    ServerEsamiOnLine.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    @Test
    public void login_ok() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).login(cred);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertTrue( "login dovrebbe non presentare errori> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void login_missing_cred() {
        Dto dto = stub.login(null);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertEquals( "login non effettuato> " + cap.getException(),-1, cap.getStatus());
    }

    @Test
    public void login_wrong_cred() {
        Credentials cred = Credentials.newBuilder().setMat("xxxxx").setCf("xxxxxx").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).login(cred);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "login dovrebbe non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void prenotaz_corsi_diversi() {
        Credentials cred = Credentials.newBuilder().setMat("mat2").setCf("cf2").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).prenota(non_partecipabile_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "prenotazione non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void prenotaz_in_ritardo() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).prenota(partecipabile_non_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "prenotazione non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void prenotaz_ok() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        stub.withCallCredentials(authCredentials).cancella(non_partecipabile_prenotabile);
        Dto dto = stub.withCallCredentials(authCredentials).prenota(non_partecipabile_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertTrue( "prenotazione effettuata> "+cap.getException(), cap.getStatus()>=0);
        stub.withCallCredentials(authCredentials).cancella(non_partecipabile_prenotabile);
    }

    @Test
    public void partecipazione_non_prenotato() {
        Credentials cred = Credentials.newBuilder().setMat("mat2").setCf("cf2").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).partecipa(partecipabile_non_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "prenotazione non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void partecipazione_ritardo() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).prenota(non_partecipabile_non_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "prenotazione non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void partecipazione_ok() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).partecipa(partecipabile_non_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertTrue( "prenotazione non effettuato> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void canc_prenotazione_ok() {
        Credentials cred = Credentials.newBuilder().setMat("mat1").setCf("cf1").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).cancella(non_partecipabile_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertTrue( "> "+cap.getException(), cap.getStatus()>=0);
    }

    @Test
    public void canc_prenotazione_inesistente() {
        Credentials cred = Credentials.newBuilder().setMat("mat2").setCf("cf2").build();
        AuthCredentials authCredentials = new AuthCredentials(cred);
        Dto dto = stub.withCallCredentials(authCredentials).cancella(non_partecipabile_prenotabile);
        CapsuleValidate cap = assembler.disassembleValidate(dto);
        assertFalse( "> "+cap.getException(), cap.getStatus()>=0);
    }
}
