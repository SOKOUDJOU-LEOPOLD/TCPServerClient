import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        // Variables for setting up connection and communication
        Socket clientSocket = null; // socket to connect with ServerRouter
        BufferedOutputStream sendTo = null; // for writing to ServerRouter
        BufferedInputStream readFrom = null; // for reading form ServerRouter
        InetAddress addr = InetAddress.getLocalHost();
        String host = addr.getHostAddress(); // Client machine's IP
        String routerName = "localhost"; // ServerRouter host name
        int SockNum = 5555; // port number
        FileInputStream out = null;

        // Tries to connect to the ServerRouter
        try {
            clientSocket = new Socket(routerName, SockNum);
            sendTo = new BufferedOutputStream(clientSocket.getOutputStream());
            readFrom = new BufferedInputStream(clientSocket.getInputStream());
            File file = new File("C:\\Users\\atako\\OneDrive\\Documents\\School-tools\\KSU-Classes\\Parallel Distributed System\\part-2\\datacamp.mp4");
            out = new FileInputStream(file);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about router: " + routerName);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + routerName);
            System.exit(1);
        }


        byte[] bytes = new byte[1024];
        int bytesRead;
        while((bytesRead = out.read(bytes)) != -1) {
            sendTo.write(bytes, 0, bytesRead);
        }

        System.out.println("File has been uploaded successfully!!");


        //closing connection
        sendTo.close();
        readFrom.close();
        out.close();
        clientSocket.close();


    }
}
