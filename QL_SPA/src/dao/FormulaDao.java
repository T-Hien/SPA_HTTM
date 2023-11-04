package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Formula;

public class FormulaDao 
{
	public static ArrayList<Formula> formulaList = new ArrayList<Formula>();;
	public static void checkServiceExist(String serviceId, int amount)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT * FROM CongThuc WHERE MaDichVu = ?) SELECT 'Y' ELSE SELECT 'N'";
				String check = "";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				rs = pstm.executeQuery();
				rs.next();
				check = rs.getString(1);
				if(check.equals("Y"))
				{
					query = "SELECT * FROM CongThuc WHERE MaDichVu = ?";
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, serviceId.trim());
					for(int i=0; i<amount; i++)
					{
						rs = pstm.executeQuery();
						while(rs.next())
						{
							Formula f = new Formula();
							f.setItemId(rs.getString("MaSanPham"));
							f.setQuantity(rs.getFloat("Luong"));
							FormulaDao.formulaList.add(f);
						}
					}
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
			}
			else
			{
				return;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin công thức thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return;
		}
	}
	
	public static ArrayList<Formula> getServiceFormula(String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM CongThuc WHERE MaDichVu = ?";
				ArrayList<Formula> lst = new ArrayList<Formula>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Formula f = new Formula(null, rs.getString("MaSanPham"), rs.getFloat("Luong"));
					lst.add(f);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin công thức thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addFormula(ArrayList<Formula> lst)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO CongThuc(MaDichVu,MaSanPham,Luong) VALUES(?,?,?)";	
				for(Formula f : lst)
				{
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, f.getServiceId().trim());
					pstm.setString(2, f.getItemId().trim());
					pstm.setFloat(3, f.getQuantity());
					pstm.executeUpdate();
				}
				pstm.close();
				connection.closeConnection();
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Thêm công thức thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateFormula(ArrayList<Formula> lst, String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "DELETE FROM CongThuc WHERE MaDichVu = ?";;
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				pstm.executeUpdate();
				if(lst.size() != 0)
				{
					query = "INSERT INTO CongThuc(MaDichVu,MaSanPham,Luong) VALUES(?,?,?)";
					for(Formula f : lst)
					{
						pstm = connection.getCnn().prepareStatement(query);
						pstm.setString(1, f.getServiceId().trim());
						pstm.setString(2, f.getItemId().trim());
						pstm.setFloat(3, f.getQuantity());
						pstm.executeUpdate();
					}
				}
				pstm.close();
				connection.closeConnection();
				return true;
			}	
			else
			{
				return false;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Cập nhật công thức thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean deleteFormula(String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query =  "DELETE FROM CongThuc WHERE MaDichVu = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				pstm.executeUpdate();
				pstm.close();
				connection.closeConnection();
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Xóa công thức thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
}	
