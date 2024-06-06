package application.persistance.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studente")
public class Student implements Pojo {

    @Id
    @Column(name = "mat") //prende in automatico se coincide con il valore attributo
    private String mat;
    private String cf;

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
