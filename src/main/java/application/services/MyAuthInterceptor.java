package application.services;

import gen.javaproto.Credentials;
import io.grpc.*;

import java.io.*;

public class MyAuthInterceptor implements ServerInterceptor {
    public static final Context.Key<Credentials> USER_IDENTITY = Context.key("identity"); // "identity" is just for debugging
    public static final Metadata.Key<Credentials> KEY_CREDENTIALS = Metadata.Key.of("credenziali-bin", new CredentialMarshaller());

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        // You need to implement validateIdentity
        Credentials c = headers.get(KEY_CREDENTIALS);
        Context context = Context.current().withValue(USER_IDENTITY, c);
        return Contexts.interceptCall(context, call, headers, next);
    }
}

class CredentialMarshaller implements Metadata.BinaryMarshaller<Credentials>{

    /**
     * Serialize a metadata value to bytes.
     *
     * @param value to serialize
     * @return serialized version of value
     */
    @Override
    public byte[] toBytes(Credentials value) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try(ObjectOutputStream oos = new ObjectOutputStream(bos); ) {
            oos.writeObject(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();
    }

    /**
     * Parse a serialized metadata value from bytes.
     *
     * @param serialized value of metadata to parse
     * @return a parsed instance of type T
     */
    @Override
    public Credentials parseBytes(byte[] serialized) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serialized);
        Credentials c = null;
        try(ObjectInputStream ois = new ObjectInputStream(bis)) {
            c = (Credentials) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored) {}
        return c;
    }

}

