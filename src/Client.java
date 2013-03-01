/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class Client implements Runnable{
	public LinkedList check=new LinkedList();
	public Queue<String> tasks=new LinkedList<String>();
	public Socket server=null;
	
	public Client(Socket s){
	server=s;
	}
	
	public void run()
	{	PrintWriter out= null;
		BufferedReader in = null;
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
		try {
			resp=in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while((resp=in.readLine())!=null){
			try {
				resp=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
