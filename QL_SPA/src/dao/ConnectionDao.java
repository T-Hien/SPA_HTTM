package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionDao 
{
	private Connection cnn = null;
	private static String cnnStr = "jdbc:sqlserver://localhost:1443;databaseName=BARBERSHOP12;user=sa;password=352636;";
	
	public ConnectionDao() {}		
	public Connection getCnn() 
	{
		return cnn;
	}
	public void setCnn(Connection cnn) 
	{
		this.cnn = cnn;
	}
	public static String getCnnStr() 
	{
		return cnnStr;
	}
	public static void setCnnStr(String cnnStr)
	{
		ConnectionDao.cnnStr = cnnStr;
	}
	public boolean openConnection()
	{
		try
		{		
			try 
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} 
			catch(ClassNotFoundException e) 
			{
				JOptionPane.showOptionDialog(null, "Kết nối cơ sở dữ liệu thất bại !", "LỖI KẾT NỐI", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return false;
			}
			this.cnn = DriverManager.getConnection(ConnectionDao.cnnStr);
			if(this.cnn == null)
			{
				JOptionPane.showOptionDialog(null, "Kết nối cơ sở dữ liệu thất bại !", "LỖI KẾT NỐI", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kết nối cơ sở dữ liệu đã xảy ra lỗi\nLỗi: " + ex.getMessage() + " !", "LỖI KẾT NỐI", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	public boolean closeConnection()
	{
		try 
		{		
			this.cnn.close();
			return true;
		} 
		catch (SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Đóng cơ sở dữ liệu đã xảy ra lỗi\nLỗi: " + ex.getMessage() + " !", "LỖI KẾT NỐI", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
}