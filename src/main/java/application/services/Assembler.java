package application.services;

import java.io.Serializable;

public interface Assembler <T extends Serializable,M extends Serializable> {
    public  T assemble(M object);
    public  M disassemble(T obj);
}
