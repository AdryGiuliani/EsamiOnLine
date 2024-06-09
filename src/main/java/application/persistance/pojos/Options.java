package application.persistance.pojos;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "options")
public class Options extends PojoAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id = 0;

    //entro quanto tempo non ammettere prenotazioni e modifiche all'appello
    private long deadline_millis= TimeUnit.DAYS.toMillis(1);

    public void setId(int id) {
        this.id = id;
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
