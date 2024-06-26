package application.persistance.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "domanda")
public final class Domanda extends PojoAbstract{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Risposta> risposte = new ArrayList<>();
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Risposta> getRisposte() {
        return risposte;
    }

    public void setRisposte(List<Risposta> risposte) {
        this.risposte = risposte;
    }

    public Domanda() {}
    public Domanda(String text, List<Risposta> risposte) {
        this.text = text;
        this.risposte = risposte;
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Domanda{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", risposte=" + risposte +
                '}';
    }
}
