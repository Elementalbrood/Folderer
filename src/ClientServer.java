/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.util.LinkedList;
import java.io.*;

public class ClientServer{
	public static LinkedList<String> check=new LinkedList<String>();
	public static void main(String[] args) throws IOException{		
		Socket server = null;
		PrintWriter out= null;
		
        try {
            server = new Socket("James-PC", 4444);
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: Laptop.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Laptop.");
            System.exit(1);
        }
		
		Thread inputC=new Thread(new Client(server,check));
		inputC.start();
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String input;
		
		System.out.println("Ready:\n");
		while((input=stdIn.readLine())!=null){
			out.println(input);
			check.add(input);
			System.out.println("SENT TO S");
			if(input.equals("DISCONNECT"))
				break;
		}
		
		out.close();
		stdIn.close();
		server.close();
	}
}	
			