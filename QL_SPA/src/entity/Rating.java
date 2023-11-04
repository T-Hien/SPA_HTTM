package entity;

public class Rating 
{
	private String serviceId;
	private String receiptId;
	private int rating;
	private String comment;
	
	public Rating() {}
	
	public Rating(String serviceId, String receiptId, int rating, String comment) 
	{
		this.serviceId = serviceId;
		this.receiptId = receiptId;
		this.rating = rating;
		this.comment = comment;
	}

	public String getServiceId() 
	{
		return this.serviceId;
	}

	public void setServiceId(String serviceId) 
	{
		this.serviceId = serviceId;
	}

	public String getReceiptId() 
	{
		return this.receiptId;
	}

	public void setReceiptId(String receiptId) 
	{
		this.receiptId = receiptId;
	}

	public int getRating() 
	{
		return this.rating;
	}

	public void setRating(int rating) 
	{
		this.rating = rating;
	}

	public String getComment() 
	{
		return this.comment;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}
}
