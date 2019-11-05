package data;

public class Account
{
	private String userName;
	private char[] password;
	public Account(){}
	
	public Account(String userName,char[] password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public String getName()
	{
		return userName;
	}
	
	public void setName(String name)
	{
		userName = name;
	}

	public char[] getPassword()
	{
		return password;
	}
	
	public void setPassword(char[] password)
	{
		this.password = password;
	}
}
