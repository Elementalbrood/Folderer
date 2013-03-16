/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class Client implements Runnable{
	public static LinkedList<String> check;
	public Queue<String> tasks=new LinkedList<String>();
	public Socket server=null;
	public PrintWriter out;
	BufferedReader in;
	
	public Client(Socket s, LinkedList<String> c) throws IOException{
	server=s;
	check=c;
    in = new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void run()
	{	String resp;
		
		try{
			while((resp=in.readLine())!=null){
				System.out.println("RESPONSE");
				if(!check.isEmpty()){
					if(check.contains(resp)){
						System.out.printf("Recieved confirmation of %s",resp);
						check.remove(resp);
					}
					else{
						System.out.printf("Recieved unexpected response");
						System.exit(1);
					}
				}
				
				if(resp.equals("DISCONNECT"))
					break;
			}
		} catch(IOException e){
			System.err.println("Error");
		}
		
		try{
			in.close();
			server.close();
		} catch(IOException e){
			System.err.println("Error");
		}
	}
}
