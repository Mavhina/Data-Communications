import csc2b.HttpServer;

/**
 * 
 */

/**
 * @author mavhinamulisa
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HttpServer server = new HttpServer(4321);
		server.StartSever();

	}

}
