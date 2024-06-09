package application.services;

import application.persistance.DBEsami;
import application.validation.Capsule;
import application.validation.CapsuleValidate;
import application.validation.LoginDispatcher;
import com.google.protobuf.ByteString;
import gen.javaproto.*;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UserServices extends FrontendServicesGrpc.FrontendServicesImplBase{

    private DBEsami db;
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
        c.setCredentials(request);
        new LoginDispatcher().dispatch(c);
        byte[] dto = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(c);
            dto = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Dto dt = Dto.newBuilder().setUnparsedDto(ByteString.copyFrom(dto)).build();
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
        super.getDisponibili(request, responseObserver);
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
        super.prenota(request, responseObserver);
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
        super.cancella(request, responseObserver);
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
        super.partecipa(request, responseObserver);
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
        super.concludi(request, responseObserver);
    }
}
