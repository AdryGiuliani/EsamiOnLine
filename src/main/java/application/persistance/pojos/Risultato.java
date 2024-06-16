package application.persistance.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "risultato")
public class Risultato extends PojoAbstract{

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appello_id")
    private Appello completed_appello = new Appello();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studente_mat")
    private Student studente = new Student();

    private int punteggio;
    private boolean superato=false;

    public Student getStudente() {
        return studente;
    }

    public void setStudente(Student studente) {
        this.studente = studente;
    }

    public Object getId() {
        return id;
    }

    public boolean isSuperato() {
        return superato;
    }

    public void setSuperato(boolean superato) {
        this.superato = superato;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Appello getCompleted_appello() {
        return completed_appello;
    }

    public void setCompleted_appello(Appello completed_appello) {
        this.completed_appello = completed_appello;
    }


    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public String toString() {
        return "Risultato{" +
                "studenti=" + studente +
                ", punteggio=" + punteggio +
                ", superato=" + superato +
                '}';
    }
}
