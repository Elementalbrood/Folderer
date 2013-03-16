/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerJ implements Runnable{
	public Queue<String> tasks;
	public Socket client;
	public PrintWriter out;
	
	public ServerJ(Socket s, Queue<String> t) throws IOException{
		client=s;
		tasks=t;
		out = new PrintWriter(client.getOutputStream(), true);
	}
	
	public void process(String t){
		return;
	}
	
	public void run(){
		String task="";
		
		while(!task.trim().equalsIgnoreCase("DISCONNECT")){
			if(!tasks.isEmpty()){
				System.out.println("Task Recieved");
				task=tasks.poll();
				process(task);
				out.println(task);
				System.out.println("Sent");
			}
		}
	}
}