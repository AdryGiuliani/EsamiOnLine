package application.services;
import application.persistance.DBEsami;
import gen.javaproto.*;
import io.grpc.stub.StreamObserver;

public class ServizioUtente extends FrontendServicesGrpc.FrontendServicesImplBase{

    private DBEsami db;
    private
    ServizioUtente(){

    }
    /**
     * <pre>
     * Login function
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void login(Credentials request, StreamObserver<AllData> responseObserver) {
        String mat = request.getMat();
        String cf = request.getCf();
        AllData response = AllData.newBuilder()
        if (verificaCredenziali(mat))

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
    public void getDisponibili(Vuoto request, StreamObserver<ListaAppelli> responseObserver) {
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
    public void prenota(AppelloID request, StreamObserver<ListaAppelli> responseObserver) {
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
    public void cancella(AppelloID request, StreamObserver<GenericResponse> responseObserver) {
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
    public void partecipa(AppelloID request, StreamObserver<User> responseObserver) {
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
    public void concludi(CompletedAppello request, StreamObserver<GenericResponse> responseObserver) {
        super.concludi(request, responseObserver);
    }
}
