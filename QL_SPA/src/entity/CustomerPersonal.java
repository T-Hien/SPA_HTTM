package entity;

public class CustomerPersonal 
{
	private String id;
	private String surname;
	private String name;
	private String gmail;
	private String phoneNumber;
	private String account;
	
	public CustomerPersonal() {}
	
	public CustomerPersonal(String id, String surname, String name, String gmail, String phoneNumber, String account)
	{
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.gmail = gmail;
		this.phoneNumber = phoneNumber;
		this.account = account;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getSurname() 
	{
		return this.surname;
	}

	public void setSurname(String surname) 
	{
		this.surname = surname;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getGmail() 
	{
		return this.gmail;
	}

	public void setGmail(String gmail) 
	{
		this.gmail = gmail;
	}

	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getAccount() 
	{
		return this.account;
	}

	public void setAccount(String account) 
	{
		this.account = account;
	}
}
