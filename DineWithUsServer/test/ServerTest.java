package test;

import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;

import server.DineServerSocket;

public class ServerTest extends Thread{
	public void run(){
		long tid = Thread.currentThread().getId();
		if(tid == 10){
			try{
				ServerSocket server = new ServerSocket(256);
				DineServerSocket.dispatch(server);
			}
			catch(Exception e){
				System.err.println("Failed to open server socket");
				System.err.println(e.toString());
				System.exit(1);
			}
		}
		else{
			Socket server = null;
			String fromServer;
			try{
				server = new Socket("localhost", 256);
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
	            out.println("Profile me");
	            ObjectInputStream is = new ObjectInputStream(server.getInputStream());
	            Profile prof = (Profile) is.readObject();
	            System.out.println(prof.getFirstname());
	            server.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
	
	public static void main(String[] args){
		System.out.println("Testing \n");
		ServerTest testerA = new ServerTest();
		ServerTest testerB = new ServerTest();
		
		testerA.start();
		testerB.start();
		
		try{
			testerA.join();
			testerB.join();
		}
		catch(Exception E){
			System.out.println("wtf");
		}
	}
}
