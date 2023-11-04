package entity;

public class Receipt
{
	private String receiptId;
	private String staffId;
	private String customerId;
	private int discountLevel;
	private String receiptDay;
	private float totalMoney;
	
	public Receipt() {}
	
	public Receipt(String receiptId, String staffId, String customerId, int discountLevel, String receiptDay, float totalMoney)
	{
		this.receiptId = receiptId;
		this.staffId = staffId;
		this.customerId = customerId;
		this.discountLevel = discountLevel;
		this.receiptDay = receiptDay;
		this.totalMoney = totalMoney;
	}

	public String getReceiptId() 
	{
		return this.receiptId;
	}

	public void setReceiptId(String receiptId) 
	{
		this.receiptId = receiptId;
	}

	public String getStaffId() 
	{
		return this.staffId;
	}

	public void setStaffId(String staffId) 
	{
		this.staffId = staffId;
	}

	public String getCustomerId() 
	{
		return this.customerId;
	}

	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}

	public int getDiscountLevel() 
	{
		return this.discountLevel;
	}

	public void setDiscountLevel(int discountLevel) 
	{
		this.discountLevel = discountLevel;
	}

	public String getReceiptDay() 
	{
		return this.receiptDay;
	}

	public void setReceiptDay(String receiptDay) 
	{
		this.receiptDay = receiptDay;
	}

	public float getTotalMoney()
	{
		return this.totalMoney;
	}

	public void setTotalMoney(float totalMoney) 
	{
		this.totalMoney = totalMoney;
	}
}
