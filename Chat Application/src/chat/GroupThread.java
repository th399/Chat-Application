package chat;

import java.io.*;
import java.net.*;
import utils.Login;

public class GroupThread extends Thread{
	private Socket socket;
	private GroupChat server;
	private PrintWriter writer;
	
	public GroupThread(Socket socket, GroupChat server) 
	{
        this.socket = socket;
        this.server = server;
    }
	
	public void run()
	{
		System.out.println("User"+ Thread.currentThread().getName() + "   signing On");
		try
		{
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output,true);

			printGroup();
			String message = "";
			String serverMessage;
			String name = Login.currentUser.getName();
			server.announce("User connected: "+ name, this);
			
			while (!message.equals("quit"))
			{
				message = reader.readLine();
				serverMessage = "<" + name +">" + message;
				server.announce(serverMessage,this);
			}
			server.removeUser(name,this);
			socket.close();
			
			serverMessage = name + "quitted";
			server.announce(serverMessage, this);
		} catch (IOException e)
		{
			System.out.println("Error at user: " + e.getMessage());
		}
	}
	
	void printGroup() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUsernameSet());
        } else {
            writer.println("No other users connected");
        }
    }
	
	void sendMessage(String message) 
	{
		server.addMessagetoLog(message);
        writer.println(message);
    }
}
