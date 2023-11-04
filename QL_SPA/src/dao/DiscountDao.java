package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Discount;

public class DiscountDao 
{
	public static int checkDiscountLevelExist(int discountLevel)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT * FROM UuDai WHERE MucUuDai = ?) SELECT 'Y' ELSE SELECT 'N'";
				String check = "";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, discountLevel);
				rs = pstm.executeQuery();
				rs.next();
				check = rs.getString(1);
				rs.close();
				pstm.close();
				connection.closeConnection();
				if(check.equals("Y"))
				{
					return 1;
				}
				else
				{
					return 2;
				}
			}
			else
			{
				return 0;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 0;
		}
	}
	
	public static ArrayList<Discount> getAllDiscount()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM UuDai";
				ArrayList<Discount> lst = new ArrayList<Discount>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Discount d = new Discount();
					d.setDiscountLevel(rs.getInt("MucUuDai"));
					d.setMoneyLevel(rs.getFloat("MucTien"));
					d.setStaffId(rs.getString("MaNhanVien").trim());
					lst.add(d);
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
			JOptionPane.showOptionDialog(null, "Lấy số lượng ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Discount getOneDiscountByLevel(int level)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM UuDai WHERE MucUuDai = ?";
				Discount d = new Discount();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, level);
				rs = pstm.executeQuery();
				rs.next();
				d.setDiscountLevel(rs.getInt("MucUuDai"));
				d.setMoneyLevel(rs.getFloat("MucTien"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return d;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addDiscount(Discount d)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO UuDai(MucUuDai,MucTien,MaNhanVien) VALUES(?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, d.getDiscountLevel());
				pstm.setFloat(2, d.getMoneyLevel());
				pstm.setString(3, d.getStaffId().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateDiscount(Discount d)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE UuDai SET MucTien = ? WHERE MucUuDai = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setFloat(1, d.getMoneyLevel());
				pstm.setInt(2, d.getDiscountLevel());
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
			JOptionPane.showOptionDialog(null, "Cập nhật ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean deleteDiscount(int level)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query =  "DELETE FROM UuDai WHERE MucUuDai = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, level);
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
			JOptionPane.showOptionDialog(null, "Xóa ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Discount> searchDiscount(int info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM UuDai WHERE MucUuDai LIKE N'%" + info + "%'";
				ArrayList<Discount> lst = new ArrayList<Discount>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Discount d = new Discount();
					d.setDiscountLevel(rs.getInt("MucUuDai"));
					d.setMoneyLevel(rs.getFloat("MucTien"));
					lst.add(d);
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
			JOptionPane.showOptionDialog(null, "Tìm ưu đãi thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByMoneyLevel(ArrayList<Discount> lst) 
	{
		Comparator<Discount> cpr = new Comparator<Discount>() 
		{
			@Override
			public int compare(Discount o1, Discount o2) 
			{
				return String.valueOf(o1.getMoneyLevel()).compareTo(String.valueOf(o2.getMoneyLevel()));
			}
		};
		Collections.sort(lst, cpr);
	}
	
	public static void sortDescByMoneyLevel(ArrayList<Discount> lst) 
	{
		Comparator<Discount> cpr = new Comparator<Discount>() 
		{
			@Override
			public int compare(Discount o1, Discount o2) 
			{
				return String.valueOf(o2.getMoneyLevel()).compareTo(String.valueOf(o1.getMoneyLevel()));
			}
		};
		Collections.sort(lst, cpr);
	}
}
