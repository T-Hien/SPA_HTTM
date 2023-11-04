package entity;

public class ReceiptDetail 
{
	private String receiptId;
	private String serviceId;
	private float money;
	int amount;
	
	public ReceiptDetail() {}
	
	public ReceiptDetail(String receiptId, String serviceId, float money, int amount) 
	{
		this.receiptId = receiptId;
		this.serviceId = serviceId;
		this.money = money;
		this.amount = amount;
	}

	public String getReceiptId() 
	{
		return this.receiptId;
	}

	public void setReceiptId(String receiptId) 
	{
		this.receiptId = receiptId;
	}

	public String getServiceId() 
	{
		return this.serviceId;
	}

	public void setServiceId(String serviceId) 
	{
		this.serviceId = serviceId;
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
