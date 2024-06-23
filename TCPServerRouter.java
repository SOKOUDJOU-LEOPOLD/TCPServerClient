import java.io.*;
import java.net.*;

public class TCPServerRouter {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null; // server socket for accepting connections
        Object[][] routingTable = new Object[10][2]; // routing table
        int index = 0; // index in the routing table

        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("Router is Listening on port: 5555.");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5555.");
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = serverSocket.accept();
            SThread t = new SThread(routingTable, clientSocket, index); // creates a thread with a random port
            t.start(); // starts the thread
            index++; // increments the index
            System.out.println("Router connected with Client/Server: " + clientSocket.getInetAddress().getHostAddress());
        }
    }
}
