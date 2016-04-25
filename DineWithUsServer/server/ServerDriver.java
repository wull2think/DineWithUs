//S16 18-641 Java Smartphone Design
//Hui Jun Tay (htay)
//Project 1

package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerDriver {
	//set up and build server object for testing
	public static void main(String[] args) {
		ServerSocket server = null;

		try{
			server = new ServerSocket(256);
			DineServerSocket.dispatch(server);
		}
		catch(Exception e){
			System.err.println("Failed to open server socket");
			System.err.println(e.toString());
			System.exit(1);
		}
	}
}
