package application.services;

import application.validation.GetAllDispatcher;
import application.validation.RimuoviDispatcher;
import application.validation.SimpleDispatch;
import application.validation.chainsteps.*;
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
        new SimpleDispatch(new AdminAuthStep(), new GetAllStep(), new OptionStep(OptionStep.OP.READ)).dispatch(c);
        Dto dt = assembler.assemble(c);
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
        c.setAppelloID(request.getId());
        new SimpleDispatch(new AdminAuthStep(),new CheckTimeStep(), new RemoveAppelloStep()).dispatch(c);
        Dto dt = assembler.assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request is required the insertion of the new  Appello inside the dto
     * @param responseObserver
     */
    @Override
    public void aggiungi(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = assembler.disassembleValidate(request);
        parseMetadata(c);
        new SimpleDispatch(new AdminAuthStep(), new AddOrUpdateAppelloStep()).dispatch(c);
        Dto dt = assembler.assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request is required the insertion of the new modified Appello inside the dto
     * @param responseObserver
     */
    @Override
    public void modifica(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = assembler.disassembleValidate(request);
        parseMetadata(c);
        c.setAppelloID((Long) c.getAppelloCreato().getId());
        new SimpleDispatch(new AdminAuthStep(),new CheckTimeStep(), new AddOrUpdateAppelloStep()).dispatch(c);
        Dto dt = assembler.assemble(c);
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
        new SimpleDispatch(new AdminAuthStep(), new GetAllStep()).dispatch(c);
        Dto dt = assembler.assemble(c);
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
        new SimpleDispatch(new AdminAuthStep(), new OptionStep(OptionStep.OP.READ)).dispatch(c);
        Dto dt = assembler.assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * @param request is required the insertion of Options object inside the dto
     * @param responseObserver
     */
    @Override
    public void setOptions(Dto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = assembler.disassembleValidate(request);
        parseMetadata(c);
        new SimpleDispatch(new AdminAuthStep(), new OptionStep(OptionStep.OP.UPDATE)).dispatch(c);
        Dto dt = assembler.assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }
}
