package entity;

public class Formula 
{
	String serviceId;
	String itemId;
	float quantity;
	
	public Formula() {}
	
	public Formula(String serviceId, String itemId, float quantity)
	{
		this.serviceId = serviceId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public String getServiceId() 
	{
		return this.serviceId;
	}

	public void setServiceId(String serviceId) 
	{
		this.serviceId = serviceId;
	}

	public String getItemId() 
	{
		return this.itemId;
	}

	public void setItemId(String itemId) 
	{
		this.itemId = itemId;
	}

	public float getQuantity() 
	{
		return this.quantity;
	}

	public void setQuantity(float quantity) 
	{
		this.quantity = quantity;
	}
}
