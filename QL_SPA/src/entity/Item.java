package entity;

public class Item 
{
	private String itemId;
	private String itemName;
	private float quantity;
	private int amount;
	private String image;
	private float itemQuantity;
	private String unit;
	
	public Item() {}
	
	public Item(String itemId, String itemName, float quantity, int amount, String image, float itemQuantity, String unit)
	{
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
		this.image = image;
		this.itemQuantity = itemQuantity;
		this.unit = unit;
	}

	public String getItemId() 
	{
		return this.itemId;
	}

	public void setItemId(String itemId) 
	{
		this.itemId = itemId;
	}

	public String getItemName() 
	{
		return this.itemName;
	}

	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public float getQuantity() 
	{
		return this.quantity;
	}

	public void setQuantity(float quantity) 
	{
		this.quantity = quantity;
	}
	
	public int getAmount() 
	{
		return this.amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	public String getImage() 
	{
		return this.image;
	}

	public void setImage(String image) 
	{
		this.image = image;
	}
	
	public float getItemQuantity() 
	{
		return this.itemQuantity;
	}

	public void setItemQuantity(float itemQuantity) 
	{
		this.itemQuantity = itemQuantity;
	}
	
	public String getUnit() 
	{
		return this.unit;
	}

	public void setUnit(String unit) 
	{
		this.unit = unit;
	}
}
