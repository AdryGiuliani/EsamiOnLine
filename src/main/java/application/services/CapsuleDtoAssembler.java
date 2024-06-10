package application.services;

import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.CapsuleValidate;
import com.google.protobuf.ByteString;
import gen.javaproto.Dto;

import java.io.*;

public class CapsuleDtoAssembler implements Assembler<Dto,Capsule>{
    public CapsuleDtoAssembler(){}
    @Override
    public Dto assemble(Capsule object) {
        ByteString bs;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bs =ByteString.copyFrom(baos.toByteArray());
            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Dto dto = Dto.newBuilder().setUnparsedDto(bs).build();
        return dto;
    }

    @Override
    public Capsule disassemble(Dto obj) {
        Capsule res = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(obj.getUnparsedDto().toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            res = (Capsule) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return res;
    }

    public CapsuleValidate disassembleValidate(Dto obj) {
        return (CapsuleValidate) disassemble(obj);
    }
}
