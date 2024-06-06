package application.persistance;

import application.persistance.pojos.Pojo;

public class DBEsami implements Database{
    @Override
    public boolean salva(Pojo obj) {
        return false;
    }

    @Override
    public Pojo carica(Pojo obj, Object id) {
        return null;
    }

    @Override
    public boolean delete(Pojo id) {
        return false;
    }

    @Override
    public boolean update_or_add(Pojo obj) {
        return false;
    }
}
