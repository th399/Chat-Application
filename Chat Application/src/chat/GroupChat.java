package chat;

import java.io.*;
import java.net.*;
import java.util.*;

import data.ChatLog;

public class GroupChat {
	private int port;
	private Set<String> usernameSet = new HashSet<>();
	private Set<GroupThread> userThreads = new HashSet<>();
	private String name;
	private ChatLog log;
	
	public GroupChat(int port)
	{
		this.port = port;
	}
	
	public void exe()
	{
		try (ServerSocket server = new ServerSocket(port))
		{
			System.out.println("Server started");
			while(true)
			{
				Socket socket = server.accept();
				GroupThread newUser = new GroupThread(socket, this);
				addUser(newUser.getName(),newUser);
				newUser.start();
			}
		} catch (IOException ex) 
		{
            System.out.println("Error in the server: " + ex.getMessage());
        }
	}
	
	void addUser(String userName, GroupThread user)
	{
		userThreads.add(user);
		usernameSet.add(userName);
	}
	
	void removeUser(String userName, GroupThread user) 
	{
		boolean check = usernameSet.remove(userName);
		if (check)
		{
			userThreads.remove(user);
		}
		else
		{
			
		}
	}
	Set<String> getUsernameSet()
	{
		return usernameSet;
	}
	
	boolean hasUsers()
	{
		return !usernameSet.isEmpty();
	}
	
	void announce(String message, GroupThread user)
	{
		addMessagetoLog(message);
		for (GroupThread anotherUser : userThreads)
		{
			if (anotherUser != user)
			{
				anotherUser.sendMessage(message);
			}
		}
	}
	public String getName()
	{
		return name;
	}
	
	public void addMessagetoLog(String message)
	{
		log.addMessage(message);
	}
}
