/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class ClientServer{
	
	public static void main(String[] args) throws IOException{		
		Socket server = null;
		PrintWriter out;
		
        try {
            server = new Socket("James-PC", 4444);
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: DH023-27.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: DH023-27.");
            System.exit(1);
        }
		
		Thread input=new Thread(new Client(server));
        String task;
		input.start();
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String input;
		
		System.out.println("Ready:\n");
		while((input=stdIn.getline())!=null){
			out.println(input);
			if(input.equals("DISCONNECT");
				break;
			input.check.add(input);
		}
		
        in.close();
		stdIn.close();
        qSocket.close();
		return;
	}
}	
			