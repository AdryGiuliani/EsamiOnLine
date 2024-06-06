package application.persistance.pojos;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "studente")
public class Student implements Pojo {

    @Id
    @Column(name = "mat") //prende in automatico se coincide con il valore attributo
    private String mat;

    @Column(nullable = false)
    private String cf;
    @Column(nullable = false)
    private String codCorso;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "appello_prenotato",
            joinColumns = @JoinColumn(name = "studente_id"),
            inverseJoinColumns = @JoinColumn(name = "appello_mat")
    )
    private List<Appello> prenotazioni = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "appello_completato",
            joinColumns = @JoinColumn(name = "studente_mat"),
            inverseJoinColumns = @JoinColumn(name = "appello_id")
    )
    private List<Appello> completato = new LinkedList<>();

    public String getCodCorso() {
        return codCorso;
    }

    public void setCodCorso(String codCorso) {
        this.codCorso = codCorso;
    }

    public List<Appello> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Appello> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public List<Appello> getCompletato() {
        return completato;
    }

    public void setCompletato(List<Appello> completato) {
        this.completato = completato;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    @Override
    public String getId() {
        return this.mat;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mat='" + mat + '\'' +
                ", cf='" + cf + '\'' +
                '}';
    }
}
