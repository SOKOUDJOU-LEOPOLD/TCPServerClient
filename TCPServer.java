import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {

        // Variables for setting up connection and communication
        Socket routerSocket = null; // socket to connect with ServerRouter
        BufferedOutputStream sendTo = null; // for writing to ServerRouter
        BufferedInputStream readFrom = null; // for reading form ServerRouter
        InetAddress addr = InetAddress.getLocalHost();
        String host = addr.getHostAddress(); // Server machine's IP
        String routerName = "localhost"; // ServerRouter host name
        int SockNum = 5555; // port number

        // Tries to connect to the ServerRouter
        try {
            routerSocket = new Socket(routerName, SockNum);
            sendTo = new BufferedOutputStream(routerSocket.getOutputStream());
            readFrom = new BufferedInputStream(routerSocket.getInputStream());
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about router: " + routerName);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + routerName);
            System.exit(1);
        }

        // Variables for message passing
//         String fromServer; // messages sent to ServerRouter
//         String fromClient; // messages received from ServerRouter
// 			String address ="10.5.3.196"; // destination IP (Client)
//
//			// Communication process (initial sends/receives)
//			sendTo.write(address);// initial send (IP of the destination Client)
//			fromClient = readFrom.readLine();// initial receive from router (verification of connection)
//			System.out.println("ServerRouter: " + fromClient);

        // Communication while loop
        String fileName = "C:\\Users\\atako\\OneDrive\\Documents\\School-tools\\KSU-Classes\\Parallel Distributed System\\part-2\\response" + ".mp4";
        File file = new File(fileName);
        FileOutputStream out = new FileOutputStream(file);
        long t0, t1;
        byte[] bytes = new byte[1024];
        int bytesRead;
        t0 = System.currentTimeMillis();
        while((bytesRead = readFrom.read(bytes)) != -1) {
            out.write(bytes, 0, bytesRead);
            System.out.println(bytesRead);
            if (bytesRead < 1024) {
                System.out.println("File downloaded successfully!!");
                break;
            }
        }
        t1 = System.currentTimeMillis();
        System.out.printf("The elapsed time is %d milliseconds", t1 - t0);


        // closing connections
        sendTo.close();
        readFrom.close();
        out.close();
        routerSocket.close();
    }
}
