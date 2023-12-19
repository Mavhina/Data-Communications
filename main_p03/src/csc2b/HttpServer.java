package csc2b;
/**
 * 
 */


import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author mavhinamulisa
 * @version P03
 */
public class HttpServer {
	
	private ServerSocket serversocket;
	private boolean connected = false;
	
	/**
	 * Constructor
	 * @param port
	 */
	public HttpServer(int port) {
		try {
			
			//to connect to port 4321
			serversocket = new ServerSocket(port);
			connected = true;
			System.out.println("waiting for connection on port 4321s...");
		
		} catch (IOException e) {
			
			System.err.println("Error starting the server...");
		}
	}
	
	public void StartSever() {
		while(connected)
		{
			try {
				ClientHandler client = new ClientHandler(serversocket.accept());
				Thread thread = new Thread(client);
				thread.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
