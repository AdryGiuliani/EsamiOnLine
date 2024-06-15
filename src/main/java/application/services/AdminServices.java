package application.services;

import application.validation.GetAllDispatcher;
import application.validation.LoginDispatcher;
import application.validation.RimuoviDispatcher;
import application.validation.chainsteps.CapsuleValidate;
import gen.javaproto.*;
import io.grpc.stub.StreamObserver;

public class AdminServices extends AdminServicesGrpc.AdminServicesImplBase implements Service{

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void login(Credentials request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        c.setCredentials(request);
        new GetAllDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void rimuovi(AppelloID request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new RimuoviDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void aggiungi(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new AggiungiDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void modifica(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new RimuoviDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void getAllAppelli(Vuoto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new GetAllDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void getOptions(Vuoto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new RimuoviDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void setOptions(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        new RimuoviDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }
}
