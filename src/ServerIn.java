/*Author: James Reinholdt
Date: 1/26/2013
---------------------------------*/
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerIn{
	public static Queue<String> tasks=new LinkedList<String>();
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
		
		Socket client = null;
		try {
			client = serverSocket.accept();
		} catch(IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		
		PrintWriter out=new PrintWriter(client.getOutputStream(), true);
		BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		String input;
		
		while ((input = in.readLine())!=null) {
			tasks.add(input);
			out.println(tasks.poll());
			if(input.equals("DISCONNECT"))
				break;
        }
		
		out.close();
        in.close();
        client.close();
        serverSocket.close();
	}
}