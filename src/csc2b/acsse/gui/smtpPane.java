/**
 * 
 */
package csc2b.acsse.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author MAVHINA
 * @version P01
 */
public class smtpPane extends GridPane{
	
	//Global Variables
	private Socket mySocket = null;
	private PrintWriter pw = null;
	private BufferedReader in = null; 
	
	Label lblhostName = new Label("Host Name: ");
	Label lblstatus = new Label("Connection Status: ");
	Label lblportNo = new Label("Port Number: ");
	Label lblSender = new Label("From: ");
	Label lblReicever = new Label("To: ");
	Label lblMessage = new Label("Message: ");
	Label lblMailStatus = new Label("Mail status: ");
	
	TextField txtHostName = new TextField();
	TextField txtstatus = new TextField();
	TextField txtportNo = new TextField();
	TextField txtSender = new TextField();
	TextField txtReicever = new TextField();
	TextField txtMailStatus = new TextField();
	
	TextArea emailContent = new TextArea();
	
	Button btnlSend = new Button("Send Mail");
	Button btnConnect = new Button("Connect");
	String inputhost = txtHostName.getText();
	
	/**
	 * A Constructor
	 */
	public smtpPane() {
		setUpGrid();
		CheckConnection();
		SendMessage();	
	}
	
	/**
	 * a void fuction to set up the gridPane and label it
	 */
	private void setUpGrid() {	
		setHgap(10); 
	    setVgap(10);
	    setPadding(new Insets(5, 5, 5, 5));
	  
		add(lblhostName, 0, 0);
		add(txtHostName, 1, 0);
		add(lblportNo, 2, 0);
		add(txtportNo, 3, 0);
		btnConnect.setPrefWidth(200);
		add(btnConnect, 1, 1);
		add(lblstatus, 0, 2);
		add(txtstatus, 1, 2);
		add(lblSender, 0, 3);
		add(txtSender, 1, 3);
		add(lblReicever, 0, 4);
		add(txtReicever, 1, 4); 
		add(lblMessage, 0, 5); 
		add(emailContent,1,5); 
		add(btnlSend, 1, 6);
		add(lblMailStatus, 0, 7);
		add(txtMailStatus, 1, 7);
	}
	
	/**
	 * a void fuction to check for connection
	 */
	private void CheckConnection() {
		
		//A button for checking the connection
		btnConnect.setOnAction(e -> {
			try {
				int intPortValue = Integer.parseInt(txtportNo.getText());
				mySocket = new Socket(inputhost,intPortValue);
				
				if(mySocket.isConnected()) {
					txtstatus.setText("Connected Successfully");
				}
			} catch (UnknownHostException e1) {
				txtstatus.setText("Unable to connect to host ");
				
			} catch (IOException e1) {
				txtstatus.setText("Could not connect");
			}
		});
	}
	
	/**
	 * a void function for sending the email
	 */
	private void SendMessage() {
		
		//a button to send the email
		btnlSend.setOnAction(e -> {
			
			//getting the input from the user
			String inputEmailSender = txtSender.getText();
			String inputEmailReceiver = txtReicever.getText();
			String inputMessageCont = emailContent.getText();
			
			try {
				
				pw = new PrintWriter(mySocket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
				
				readResponse(in);
				writemessage(pw,"HELO Papercut");
				readResponse(in);
				
				writemessage(pw,"MAIL FROM: <" + inputEmailSender + ">");
				readResponse(in);
				
				writemessage(pw,"RCPT TO: <" + inputEmailReceiver + ">");
				readResponse(in);
				
				writemessage(pw,"DATA");
				readResponse(in);
				
				writemessage(pw,"From: " + inputEmailSender);
				writemessage(pw,"To: " + inputEmailReceiver);
				writemessage(pw,"Subject: CSC2B");
				writemessage(pw," ");
				
				writemessage(pw,inputMessageCont);
				writemessage(pw,".");
				readResponse(in); 
				
				writemessage(pw,"QUIT");
				readResponse(in);
				
				txtMailStatus.setText("Mail sent successfully");
				
			} catch (IOException e1) {
				txtMailStatus.setText("Could not send");
			}
			finally {
				if(mySocket != null) {
					try {
						//Close the SMTP session.
						mySocket.close();
						pw.close();
						in.close();
					} catch (IOException e1) {
						txtstatus.setText("Can not cloe");
					}
				}
			}
		});	
	}
	
	/**
	 * 
	 * @param pw
	 * @param msg
	 */
	private static void writemessage(PrintWriter pw, String msg) {
		pw.println(msg);
	}
	
	/**
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static String readResponse(BufferedReader in) throws IOException {
		String responseline = in.readLine();
		System.out.println(responseline);
		return responseline;
	} 

}
