package application.persistance.pojos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public final class Risposta implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Risposta risposta)) return false;
        return Objects.equals(text, risposta.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(text);
    }

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
