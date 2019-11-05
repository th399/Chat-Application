package data;

import java.util.*;

public class UserDB
{
	//<Name,Account>
	private static final Map<String, Account> db = new HashMap<String, Account>();
	
	public static void addUser(String userName, char[] password)
	{
		Account user = new Account(userName,password);
		db.put(userName,user);
	}
	
	public static Account findUser(String userName, char[] pw)
	{
		Account user = db.get(userName);
		if (user != null && user.getPassword().equals(pw))
		{
			return user;
		}
		else return null;
	}
	
	public static boolean findUser(String userName)
	{
		Account user = db.get(userName);
		if (user != null) return true; else return false;
	}
	
	public static void print()
	{
		db.entrySet().forEach(entry->{
		    System.out.println(entry.getKey() + " " + entry.getValue());  
		 });
	}
	
	public void clear()
	{
		db.clear();
	}
}