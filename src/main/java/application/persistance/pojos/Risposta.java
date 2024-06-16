package application.persistance.pojos;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public final class Risposta implements Serializable {

    private boolean corretta = false;
    private String text;

    public boolean isCorretta() {
        return corretta;
    }

    public void setCorretta(boolean corretta) {
        this.corretta = corretta;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Risposta(boolean corretta, String text) {
        this.corretta = corretta;
        this.text = text;
    }
    public Risposta() {}

    @Override
    public String toString() {
        return "Risposta{" +
                "corretta=" + corretta +
                ", text='" + text + '\'' +
                '}';
    }
}
