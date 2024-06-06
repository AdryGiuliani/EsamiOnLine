package application.persistance.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "domanda")
public final class Domanda implements Pojo{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @ElementCollection
    private List<Risposta> risposte = new ArrayList<>();

    public Domanda() {}
    public Domanda(String text, List<Risposta> risposte) {
        this.text = text;
        this.risposte = risposte;
    }

    @Override
    public Object getId() {
        return id;
    }
}
