/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class Client	implements Runnable throws IOException{
	public static LinkedList check=new LinkedList();
	public Queue<String> tasks=new LinkedList<String>();
	public Socket server=null;
	
	public Client(Socket s){
	server=s;
	tasks=t;
	}
	
	public void run()
	{	PrintWriter out;
		BufferedReader in;
		String resp;
		
		try {
            out = new PrintWriter(server.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: DH023-27.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: DH023-27.");
            System.exit(1);
        }
		
		while(true){
			resp=in.getline();
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
		
		in.close();
		out.close();
		server.close();
	}
}
