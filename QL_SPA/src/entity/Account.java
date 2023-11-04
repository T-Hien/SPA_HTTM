package entity;

public class Account 
{
	private String account;
	private String password;
	private int role;
	private int status = 1;
	
	public Account() {}
	
	public Account(String account, String password, int role, int status)
	{
		this.account = account;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public String getAccount() 
	{
		return this.account;
	}

	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getRole() 
	{
		return this.role;
	}

	public void setRole(int role) 
	{
		this.role = role;
	}

	public int getStatus() 
	{
		return status;
	}

	public void setStatus(int status) 
	{
		this.status = status;
	}
}
