package application.services;

import application.validation.chainsteps.CapsuleValidate;

public interface Service {
    final CapsuleDtoAssembler assembler = new CapsuleDtoAssembler();
    default void parseMetadata(CapsuleValidate c){
        c.setCredentials(MyAuthInterceptor.USER_IDENTITY.get());
    }
}
