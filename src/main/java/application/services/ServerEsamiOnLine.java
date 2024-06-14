package application.services;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;

public class ServerEsamiOnLine {
    public static int port = 50000;

        public static void start() throws IOException, InterruptedException {
            Server server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                    .addService(new UserServices())
                    .intercept(new MyAuthInterceptor())
                    .build()
                    .start();
            System.out.println("Server started, listening on port " + port);
            server.awaitTermination();
        }

}
