package entity;

public class Discount 
{
	private int discountLevel;
	private float moneyLevel;
	private String staffId;
	
	public Discount() {}
	
	public Discount(int discountLevel, float moneyLevel, String staffId)
	{
		this.discountLevel = discountLevel;
		this.moneyLevel = moneyLevel;
		this.staffId = staffId;
	}

	public int getDiscountLevel() 
	{
		return this.discountLevel;
	}

	public void setDiscountLevel(int discountLevel) 
	{
		this.discountLevel = discountLevel;
	}

	public float getMoneyLevel() 
	{
		return this.moneyLevel;
	}

	public void setMoneyLevel(float moneyLevel) 
	{
		this.moneyLevel = moneyLevel;
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
