package application.services;

import application.validation.*;
import application.validation.chainsteps.*;
import gen.javaproto.*;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class UserServices extends FrontendServicesGrpc.FrontendServicesImplBase implements Service{
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
        new SimpleDispatch(new AuthStep(true), new DisponibiliStep(), new DeadlineStep()).dispatch(c);
        Dto dt = assembler.assemble(c);
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
        new SimpleDispatch(new AuthStep(false), new DisponibiliStep()).dispatch(c);
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
        new SimpleDispatch(new AuthStep(false), new PrenotaStep(true)).dispatch(c);
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
        new SimpleDispatch(new AuthStep(true), new CancellaPrenotazioneStep()).dispatch(c);
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
        new SimpleDispatch(new AuthStep(true), new PartecipaStep()).dispatch(c);
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
        new SimpleDispatch(new AuthStep(false), new CompletaAppelloStep()).dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }

    @Override
    public void getFullAppello(AppelloID request, StreamObserver<Dto> responseObserver){
        CapsuleValidate c = new CapsuleValidate();
        parseMetadata(c);
        c.setAppelloID(request.getId());
        new SimpleDispatch(new AuthStep(false), new getFullAppStep()).dispatch(c);
        Dto dto = assembler.assemble(c);
        responseObserver.onNext(dto);
        responseObserver.onCompleted();
    }
}
