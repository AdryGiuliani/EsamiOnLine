package application.persistance.pojos;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static application.persistance.util.Utils.KEY_OPZIONI;

@Entity
@Table(name = "options")
public class Options extends PojoAbstract {

    @Id
    private String id = KEY_OPZIONI;

    //entro quanto tempo non ammettere prenotazioni e modifiche all'appello
    private long deadline_millis= TimeUnit.DAYS.toMillis(1);

    private String admin_usrn= "admin";
    private String admin_pwd = "0000";

    public String getAdmin_usrn() {
        return admin_usrn;
    }

    public void setAdmin_usrn(String admin_usrn) {
        this.admin_usrn = admin_usrn;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    private int minVoto = 18;

    public int getMinVoto() {
        return minVoto;
    }

    public void setMinVoto(int minVoto) {
        this.minVoto = minVoto;
    }

    public long getDeadline_millis() {
        return deadline_millis;
    }

    public void setDeadline_millis(long deadline_millis) {
        this.deadline_millis = deadline_millis;
    }

    @Override
    public Object getId() {
        return id;
    }
}
