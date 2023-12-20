import csc2b.acsse.gui.smtpPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author mavhinamulisa
 *
 */
public class Main extends Application{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("SMTP client");
		
		smtpPane pane = new smtpPane();		
		Scene sc = new Scene(pane,850,450);
				
		primaryStage.setScene(sc);	
		primaryStage.show();
	}
	
	

}