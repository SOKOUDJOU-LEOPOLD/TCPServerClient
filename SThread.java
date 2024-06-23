import java.io.*;
import java.net.*;
import java.lang.Exception;


class SThread extends Thread {
	private Object[][] routingTable; // routing table
	private Socket clientSocket; // socket for communicating with a destination

	SThread(Object[][] table, Socket socket, int index) {
		this.routingTable = table;
		this.clientSocket = socket;
		// index in the routing table
		routingTable[index][0] = socket.getInetAddress().getHostAddress(); // IP addresses
		routingTable[index][1] = socket; // sockets for communication
	}

	public void run() {
		try {
			BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
			byte[] bytes = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(bytes)) != -1) {
				Socket serverSocket = (Socket) routingTable[0][1]; // assuming server is at index 0
				BufferedOutputStream out = new BufferedOutputStream(serverSocket.getOutputStream());
				out.write(bytes, 0, bytesRead);
				out.flush();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}