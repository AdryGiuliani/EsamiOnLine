package application.validation.chainsteps;

import application.persistance.pojos.Appello;
import application.persistance.pojos.Options;
import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;
import gen.javaproto.CompletedAppello;
import gen.javaproto.Credentials;

import java.util.HashMap;
import java.util.List;

public class CapsuleValidate extends AbstractCapsule{


    public CapsuleValidate(){
        payload=new HashMap<>(8);
    }

    public void setCredentials(Credentials credentials){
        insertObject(Utils.CAPSULE_KEY_CREDENZIALI, credentials);
    }
    public Credentials getCredentials(){
        return (Credentials) payload.get(Utils.CAPSULE_KEY_CREDENZIALI);
    }
    public void setStudent(Student student){
        insertObject(Utils.CAPSULE_KEY_STUDENTE, student);
    }
    public Student getStudent(){
        return (Student) payload.get(Utils.CAPSULE_KEY_STUDENTE);
    }
    public void setAppelloID(Long appelloid){
        insertObject(Utils.CAPSULE_KEY_APPELLOID, appelloid);
    }
    public Long getAppelloID(){
        return (Long) payload.get(Utils.CAPSULE_KEY_APPELLOID);
    }
    public void setAppelloCompletato(CompletedAppello res){
        insertObject(Utils.CAPSULE_KEY_RISULTATO, res);
    }


    public CompletedAppello getAppelloCompletato(){
        return (CompletedAppello) payload.get(Utils.CAPSULE_KEY_RISULTATO);
    }

    public List<Appello> getDisponibili(){
        return (List<Appello>) payload.get(Utils.CAPSULE_KEY_DISPONIBILI);
    }

    public void setDisponibili(List<Appello> disponibili){
        insertObject(Utils.CAPSULE_KEY_DISPONIBILI, disponibili);
    }

    public List<Appello> getall_appelli(){
        return (List<Appello>) payload.get(Utils.CAPSULE_KEY_ALLAPPELLI);
    }

    public Options getOptions(){return (Options) payload.get(Utils.CAPSULE_KEY_OPTIONS);}
    public void setOptions(Options options){
        insertObject(Utils.CAPSULE_KEY_OPTIONS, options);
    }

    public void setModAppello(Appello modAppello){
        insertObject(Utils.CAPSULE_KEY_APPELLO_CREATO, modAppello);
    }

    public Appello getAppelloCreato(){
        return (Appello) payload.get(Utils.CAPSULE_KEY_APPELLO_CREATO);
    }
    public void setAppelloCreato(Appello appelloCreato){
        insertObject(Utils.CAPSULE_KEY_APPELLO_CREATO, appelloCreato);
    }


    @Override
    public String toString() {
        return "CapsuleValidate{" +
                "payload=" + payload +";\n"+
                super.toString()+
                '}';
    }
}
