package entity;

public class Statistics 
{
	private String staffName;
	private String serviceName;
	private int importQuantity;
	private int exportQuantity;
	private int receiptQuantity;
	private int receiptByYearQuantity;
	private int year;
	private float individualIncome;

	public Statistics() {}

	public String getStaffName() 
	{
		return this.staffName;
	}

	public void setStaffName(String staffName) 
	{
		this.staffName = staffName;
	}
	
	public int getImportQuantity() 
	{
		return this.importQuantity;
	}

	public void setImportQuantity(int importQuantity) 
	{
		this.importQuantity = importQuantity;
	}

	public int getExportQuantity() 
	{
		return this.exportQuantity;
	}

	public void setExportQuantity(int exportQuantity) 
	{
		this.exportQuantity = exportQuantity;
	}

	public int getReceiptQuantity() 
	{
		return this.receiptQuantity;
	}

	public void setReceiptQuantity(int receiptQuantity) 
	{
		this.receiptQuantity = receiptQuantity;
	}
	
	public float getIndividualIncome() 
	{
		return this.individualIncome;
	}

	public void setIndividualIncome(float individualIncome) 
	{
		this.individualIncome = individualIncome;
	}
	
	public String getServiceName() 
	{
		return this.serviceName;
	}

	public void setServiceName(String serviceName) 
	{
		this.serviceName = serviceName;
	}

	public int getReceiptByYearQuantity() 
	{
		return this.receiptByYearQuantity;
	}

	public void setReceiptByYearQuantity(int receiptByYearQuantity) 
	{
		this.receiptByYearQuantity = receiptByYearQuantity;
	}

	public int getYear() 
	{
		return this.year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}
}
