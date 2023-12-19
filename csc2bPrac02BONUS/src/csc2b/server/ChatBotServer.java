/**
 * 
 */
package csc2b.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * @author MAVHINA
 * @version P02
 */
public class ChatBotServer implements Runnable {
	
	private Socket connection;
	
	/**
	 * The constructor
	 * @param connection
	 */
	public ChatBotServer(Socket connection){
		this.connection = connection;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				PrintWriter out = new PrintWriter(connection.getOutputStream(),true);
				
				int ResponseNo = 0;
				int QuestionAskedNo = 0;
				
				out.println("HELLO - you may make 4 requests and I'll try to detect your language");
				boolean startsession = false;
				String Message = " ";
				
				/**
				 * while loop
				 */
				while((QuestionAskedNo < 4) && (!Message.equals("DONE")))
				{
					Message = in.readLine();
					StringTokenizer tokens = new StringTokenizer(Message);
					String InstructionSent = tokens.nextToken();
					
					System.out.println("Instruction" + InstructionSent);
					if(InstructionSent.equals("START"))
					{
						startsession = true;
						System.out.println("Client Has started the seesion");
						out.println("REQUEST or DONE");
					}
					else if(InstructionSent.equals("REQUEST") && startsession)
					{
						//to count the response and question asked
						ResponseNo += 1;
						QuestionAskedNo += 1;
						
						String Question = " ";
						while(tokens.hasMoreTokens())
						{
							Question += tokens.nextToken() + " ";
						}
						System.out.println("Question asked:" + Question);
						
						if(Question.contains("ngiyabonga") || Question.contains("mina"))
						{
							out.println("0" + ResponseNo + " I detect some Zulu here.");
						}
						else if(Question.startsWith("Is"))
						{
							Random r = new Random();
							int chooseAnswer = r.nextInt(3);
							switch(chooseAnswer)
							{
							case 0:
								out.println("0" + ResponseNo + " Anglais?");
								break;
							case 1:
								out.println("0" + ResponseNo + " English?");
								break;
							case 2:
								out.println("0" + ResponseNo + " Maybe Afrikaans?");
								break;
							}
						}
						else if(Question.contains("Dumela"))
						{
							out.println("0" +ResponseNo + " I greet you in Sotho!");
						}
						else
						{
							Random r = new Random();
							int chooseAnswer = r.nextInt(3);
							switch(chooseAnswer)
							{
							case 0:
								out.println("0" + ResponseNo + " Howzit");
								break;
							case 1:
								out.println("0" + ResponseNo + " I'm still learning");
								break;
							case 2:
								out.println("0" + ResponseNo + " No idea");
								break;
							}
						}
						System.out.println("Questions asked: " + QuestionAskedNo);
						
					}
					else if(!Message.equals("DONE"))
					{
						out.println("You entered the incorrect command");
					}
				}
				if(Message.equals("DONE"))
				{
					out.println("0" + ResponseNo + " GOOD BYE - [" + QuestionAskedNo + "] quaries answered");
				}
				else
				{
					out.println("ANSWERED 4 REQUESTS HAPPY I COULD HELP - " + QuestionAskedNo + " Question answered");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//To close the connections
			finally
			{
				if(connection != null)
				{
					try {
						connection.close();
					} catch (IOException e) {
						System.err.println("Couldnt close the connection");
					}
				}
			}
		
		
	}

}
