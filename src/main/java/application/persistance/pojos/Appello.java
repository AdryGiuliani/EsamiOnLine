package application.persistance.pojos;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "appello",
        uniqueConstraints = {
        @UniqueConstraint(name = "unicoAppello", columnNames = {"nome","codcorso","data_ora"})
        })
public class Appello extends PojoAbstract{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appello appello)) return false;
        return id == appello.id;
    }

    public boolean equalsSoft(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appello appello)) return false;
        return nome.equals(appello.nome) && codcorso.equals(appello.codcorso);
    }

    @Override
    public String toString() {
        return "Appello{" +
                "nome='" + nome + '\'' +
                ", codcorso='" + codcorso + '\'' +
                ", data_ora=" + data_ora +
                '}';
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    private String codcorso;
    @Column(nullable = false)
    private int durata_minuti=60;
    @Column(nullable = false)
    private int tempo_domanda_sec=300;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_ora;


    @OneToMany(mappedBy = "completed_appello")
    private List<Risultato> risultati = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn
    private List<Domanda> domande = new ArrayList<>();

    @Override
    public Object getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodcorso() {
        return codcorso;
    }

    public void setCodcorso(String codCorso) {
        this.codcorso = codCorso;
    }

    public int getDurata_minuti() {
        return durata_minuti;
    }

    public void setDurata_minuti(int durata_minuti) {
        this.durata_minuti = durata_minuti;
    }

    public Date getData_ora() {
        return data_ora;
    }

    public void setData_ora(Date data_ora) {
        this.data_ora = data_ora;
    }

    public List<Domanda> getDomande() {
        return domande;
    }

    public void setDomande(List<Domanda> domande) {
        this.domande = domande;
    }

    public void setId(long id) {
        this.id = id;
    }
    public int getTempo_domanda_sec() {
        return tempo_domanda_sec;
    }

    public void setTempo_domanda_sec(int tempo_domanda_sec) {
        this.tempo_domanda_sec = tempo_domanda_sec;
    }

    public List<Risultato> getRisultati() {
        return risultati;
    }

    public void setRisultati(List<Risultato> risultati) {
        this.risultati = risultati;
    }
}
