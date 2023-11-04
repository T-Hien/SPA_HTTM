package entity;

public class Service 
{
	private String id;
	private String name;
	private float price;
	private String staffId;
	
	public Service() {}
	
	public Service(String id, String name, float price, String staffId)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.staffId = staffId;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public float getPrice() 
	{
		return this.price;
	}

	public void setPrice(float price) 
	{
		this.price = price;
	}

	public String getStaffId() 
	{
		return this.staffId;
	}

	public void setStaffId(String staffId) 
	{
		this.staffId = staffId;
	}
}
