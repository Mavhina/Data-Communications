package csc2b;
/**
 * 
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author mavhinamulisa
 * @version P03
 */
public class ClientHandler implements Runnable{
	
	private Socket connection;
	
	/**
	 * A constructor
	 * @param accept
	 */
	public ClientHandler(Socket accept) {
		connection = accept;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		DataOutputStream dos = null; 
		
		try {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			dos = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
			
			String Line = br.readLine();
			System.out.println("client request: " + Line);
			
			StringTokenizer tokens = new StringTokenizer(Line);
			
			int notokens = tokens.countTokens();
			System.out.println("No of tokens:" + notokens);
			
			String TypeofRequest = tokens.nextToken();
			String filename = tokens.nextToken().substring(1); 
			
			
			String filepath = "";
			
			//to check if it contains the following word and if it does it then add the .html at the front
			if(filename.contains("Afrikaans") || filename.contains("Zulu") || filename.contains("ZuluWithImage"))
			{
				filepath = "data/" + filename + ".html"; 
			}
			else
			{
				filepath = "data/" + filename; 
			}
			
			//For debugging
			System.out.println("name "+ filename);
			System.out.println("filepath: " + filepath);
			System.out.println("No of tokens:" + notokens);
			
			
			File file = new File(filepath);
			//check if its a GET request
			if(TypeofRequest.equals("GET"))
			{
				//Check first if the file exist
				if(file.exists())
				{
					//checking if it have all the necessary request
					if(notokens != 3)
					{
						errorhandle(dos,500,"Incorrect GET request");
					}
					else
					{
						
						dos.writeBytes("HTTP/1.1 200 OK \r\n");
						dos.writeBytes("Connection: close \r\n");
						
						if(filename.endsWith(".html"))
						{
							dos.writeBytes("Content-Type: text/html\r\n");
						}
						else if(filename.endsWith(".jpg"))
						{
							dos.writeBytes(("Content-Type: image/jpeg\r\n"));
						}
						
						dos.writeBytes("Content-Length:"+ file.length()+"\r\n");
						dos.writeBytes("\r\n");
						
						try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
							byte[] buffer = new byte[1024];
							int n =0;
							
							while((n = bis.read(buffer))>0)
							{ 
								dos.write(buffer,0,n);
							}
							
							bis.close();
							dos.writeBytes("\r\n");
							dos.flush();
						}
					}
					
				}
				else   //if a file does not exist
				{
					errorhandle(dos,404,"File Not Found");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * a function to handle the error code
	 * @param dos
	 * @param statusCode
	 * @param content
	 */
	private void errorhandle(DataOutputStream dos,int statusCode, String content)
	{
		String contentsMSG = "<html><head> <tittle> ERROR MESSAGE </tittle></head>";
		contentsMSG += "<body> message: " +  content + " </body></hmtl>";
		String statusText = " ";
		
		//to display necessary code error
		switch(statusCode)
		{
		case 404:
			statusText = "404 Not Found";
			break;
		case 500:
			statusText = "500 Server Error";
			break;
		}

			try {
				dos.writeBytes("HTTP/1.1 " + statusCode + " " + statusText + "\r\n");
				dos.writeBytes("Content-Type: text/plain\r\n");
				dos.writeBytes("Content-Length: " + contentsMSG.length() + "\r\n");
				dos.writeBytes("Connection: close\r\n");
		                
				dos.writeBytes("\r\n"); 
				dos.writeBytes(contentsMSG);
				dos.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
