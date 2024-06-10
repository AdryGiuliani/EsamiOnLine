package application.validation.chainsteps;

import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;
import gen.javaproto.Credentials;

import java.util.HashMap;

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
    public void setAppelloCompletato(Risultato res){
        insertObject(Utils.CAPSULE_KEY_RISULTATO, res);
    }
    public Risultato getAppelloCompletato(){
        return (Risultato) payload.get(Utils.CAPSULE_KEY_RISULTATO);
    }


    @Override
    public String toString() {
        return "CapsuleValidate{" +
                "payload=" + payload +";\n"+
                super.toString()+
                '}';
    }
}
