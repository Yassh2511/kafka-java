package origin.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args){
        System.out.println("Logs:");
    ServerSocket serverSocket;
    Socket clientSocket = null;
    int port = 9092;
    try{
        serverSocket = new ServerSocket(port);
        // Since the tester restarts your program quite often, setting SO_REUSEADDR
        // ensures that we don't run into 'Address already in use' errors
        serverSocket.setReuseAddress(true);
        // Wait for connection from client.
        clientSocket = serverSocket.accept();
        // sending correlation_id to the client
        // correlation id is something which is 32 bit
        // here sending direct correlation id only which is 7
        // so first 0000 is for message size =0 and next 0007 this 4 bytes is for correlation id
        clientSocket.getOutputStream().write(new byte[]{0,0,0,0,0,0,0,7});
    } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
    } finally {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
}

