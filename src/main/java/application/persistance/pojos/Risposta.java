package application.persistance.pojos;

import jakarta.persistence.*;

@Embeddable
public final class Risposta{

    private boolean corretta = false;
    private String text;

    public Risposta(boolean corretta, String text) {
        this.corretta = corretta;
        this.text = text;
    }
    public Risposta() {}
}
