package entity;

public class Staff 
{
	private String staffId;
	private String surname;
	private String name;
	private String sex;
	private String phoneNumber;
	private String id;
	private String gmail;
	private String birthDate;
	private String homeTown;
	private String dayWork;
	private String account;
	
	public Staff() {}
	
	public Staff( String staffId, String surname, String name, String sex, String phoneNumber, String id, String gmail, String birthDate, String homeTown, String dayWork, String account)
	{
		this.staffId = staffId;
		this.surname = surname; 
		this.name = name;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.gmail = gmail;
		this.birthDate = birthDate;
		this.homeTown = homeTown;
		this.dayWork = dayWork;
		this.account = account;
	}

	public String getStaffId() 
	{
		return this.staffId;
	}

	public void setStaffId(String staffId) 
	{
		this.staffId = staffId;
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

	public String getSex() 
	{
		return this.sex;
	}

	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getGmail() 
	{
		return this.gmail;
	}

	public void setGmail(String gmail) 
	{
		this.gmail = gmail;
	}

	public String getBirthDate() 
	{
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) 
	{
		this.birthDate = birthDate;
	}

	public String getHomeTown() 
	{
		return this.homeTown;
	}

	public void setHomeTown(String homeTown) 
	{
		this.homeTown = homeTown;
	}

	public String getDayWork() 
	{
		return this.dayWork;
	}

	public void setDayWork(String dayWork) 
	{
		this.dayWork = dayWork;
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
