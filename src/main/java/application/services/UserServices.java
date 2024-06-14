package application.services;

import application.validation.*;
import application.validation.chainsteps.CapsuleValidate;
import gen.javaproto.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class UserServices extends FrontendServicesGrpc.FrontendServicesImplBase{

    private final CapsuleDtoAssembler assembler = new CapsuleDtoAssembler();
    public UserServices() {}

    /**
     * <pre>
     * Login function
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void login(Credentials request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        System.out.println(MyAuthInterceptor.USER_IDENTITY.get());
        c.setCredentials(request);
        new LoginDispatcher().dispatch(c);
        Dto dt = new CapsuleDtoAssembler().assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * Logout
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void logout(Vuoto request, StreamObserver<GenericResponse> responseObserver) {
        super.logout(request, responseObserver);
    }

    /**
     * <pre>
     * get degli appelli disponibili per l'utente indicato
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getDisponibili(Vuoto request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        System.out.println(c.getCredentials());
        new DisponibiliDispatcher().dispatch(c);
        if (c.getStatus()>0)
            System.out.println("disponibili recuperati");
        Dto dt = assembler.assemble(c);
        responseObserver.onNext(dt);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * prenota un appello, restituisce la lista aggiornata se è cambiata
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void prenota(AppelloID request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        c.setAppelloID(request.getId());
        new PrenotazioneDispatch().dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * cancella prenotazione
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void cancella(AppelloID request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        c.setAppelloID(request.getId());
        new CancellaDispatch().dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * conferma partecipazione appello
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void partecipa(AppelloID request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        c.setAppelloID(request.getId());
        new PartecipaDispatch().dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }

    /**
     * <pre>
     * conclusione appello dall'utente
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void concludi(CompletedAppello request, StreamObserver<Dto> responseObserver) {
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        c.setAppelloCompletato(request);
        new ConcludiDispatch().dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }

    private void parseMetadata(CapsuleValidate c){
        c.setCredentials(MyAuthInterceptor.USER_IDENTITY.get());
    }
}
