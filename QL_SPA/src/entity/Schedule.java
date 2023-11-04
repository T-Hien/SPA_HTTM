package entity;

public class Schedule 
{
	private String scheduleId;
	private String customerId;
	private String serviceId;
	private String date;
	private String time;
	private int status;
	private String staffId;
	
	public Schedule() {}
	
	public Schedule(String scheduleId, String customerId, String serviceId, String date, String time, int status, String staffId)
	{
		this.scheduleId = scheduleId;
		this.customerId = customerId;
		this.serviceId = serviceId;
		this.date = date;
		this.time = time;
		this.status = status;
		this.staffId = staffId;
	}

	public String getScheduleId() 
	{
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public String getCustomerId() 
	{
		return this.customerId;
	}

	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}

	public String getServiceId() 
	{
		return this.serviceId;
	}

	public void setServiceId(String serviceId) 
	{
		this.serviceId = serviceId;
	}

	public String getDate() 
	{
		return this.date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public String getTime() 
	{
		return this.time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}

	public int getStatus() 
	{
		return this.status;
	}

	public void setStatus(int status) 
	{
		this.status = status;
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
