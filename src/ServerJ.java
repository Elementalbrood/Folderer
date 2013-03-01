<<<<<<< HEAD
/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerJ implements Runnable{
	public Queue<String> tasks;
	public Socket client;
	
	public Server(Socket s, Queue<String> t){
		client=s;
		tasks=t;
	}
	
	public void process(String t){
		return;
	}
	
	public void run(){
		PrintWriter out = null;
		String task="";
		try {
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: DH023-27.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: DH023-27.");
            System.exit(1);
        }
		
		while(!task.equals("DISCONNECT")){
			if(!tasks.isEmpty()){
				task=tasks.poll();
				process(task);
				out.println(task);
			}
		}
	}
}
=======

public class Server
{

}
>>>>>>> a0cc2d6552acf6f16e403484c88ef23eb82f69fd
