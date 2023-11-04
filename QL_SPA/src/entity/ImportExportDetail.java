package entity;

public class ImportExportDetail 
{
	private String id;
	private String itemId;
	private float money;
	private int amount;
	
	public ImportExportDetail() {}
	
	public ImportExportDetail(String id, String itemId, float money, int amount)
	{
		this.id = id;
		this.itemId = itemId;
		this.money = money;
		this.amount = amount;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getItemId() 
	{
		return this.itemId;
	}

	public void setItemId(String itemId) 
	{
		this.itemId = itemId;
	}

	public float getMoney() 
	{
		return this.money;
	}

	public void setMoney(float money) 
	{
		this.money = money;
	}

	public int getAmount() 
	{
		return this.amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}
}
