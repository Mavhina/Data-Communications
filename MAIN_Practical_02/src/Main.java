
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cscb2.server.ChatBotServer;

/**
 * @author MAVHINA
 * @version P02
 */
public class Main {
	
	private static ServerSocket serversocket;
	private final static int PORT = 8888;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Server attempting to bind to port: " + PORT);
			serversocket = new ServerSocket(PORT);
			System.out.println("Ready for incoming connections...");
			
			do
			{
				Socket clientSocket =serversocket.accept();
				
				//Calling the fuction 
				ChatBotServer chatbot = new ChatBotServer(clientSocket);
				chatbot.queries();
				
			}while(true);
		} catch (IOException e) {
			System.err.println("Unable to bind to port: " + PORT);
		}
	}

}
