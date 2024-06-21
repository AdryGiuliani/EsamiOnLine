package application.services;

import application.validation.chainsteps.CapsuleValidate;

public interface Service {
    CapsuleDtoAssembler assembler = new CapsuleDtoAssembler();
    default void parseMetadata(CapsuleValidate c){
        c.setCredentials(MyAuthInterceptor.USER_IDENTITY.get());
    }
}
