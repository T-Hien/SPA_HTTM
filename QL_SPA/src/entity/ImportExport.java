package entity;

public class ImportExport 
{	
	private String id;
	private float totalMoney;
	private String importExportDay;
	private String staffId;
	
	public ImportExport() {}
	
	public ImportExport(String id, float totalMoney, String importExportDay, String staffId)
	{
		this.id = id;
		this.totalMoney = totalMoney;
		this.importExportDay = importExportDay;
		this.staffId = staffId;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public float getTotalMoney() 
	{
		return this.totalMoney;
	}

	public void setTotalMoney(float totalMoney) 
	{
		this.totalMoney = totalMoney;
	}

	public String getImportExportDay() 
	{
		return this.importExportDay;
	}

	public void setImportExportDay(String importExportDay) 
	{
		this.importExportDay = importExportDay;
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
