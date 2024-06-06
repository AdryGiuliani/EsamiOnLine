package application.persistance.pojos;

import jakarta.persistence.Entity;

/**
 * Interface for pojo object in database, if implemented remember to apply the correct Entity annotations
 */
public interface Pojo {
    public Object getId();

}
