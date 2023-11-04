package entity;

public class Revenue 
{
	public String id;
	public String staffName;
	public String dayCreated;
	public float value;
	
	public Revenue() {}
	
	public Revenue(String id, String staffName, String dayCreated, float value)
	{
		this.id = id;
		this.staffName = staffName;
		this.dayCreated = dayCreated;
		this.value = value;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getStaffName() 
	{
		return this.staffName;
	}

	public void setStaffName(String staffName) 
	{
		this.staffName = staffName;
	}

	public String getDayCreated() 
	{
		return this.dayCreated;
	}

	public void setDayCreated(String dayCreated) 
	{
		this.dayCreated = dayCreated;
	}

	public float getValue() 
	{
		return this.value;
	}

	public void setValue(float value) 
	{
		this.value = value;
	}
}
