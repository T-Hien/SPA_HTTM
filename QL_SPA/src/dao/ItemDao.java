package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Item;

public class ItemDao 
{
	public static String checkQuantity()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String query = "SELECT MaSanPham FROM SanPham";
				ArrayList<Item> lst = new ArrayList<Item>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Item i = new Item();
					i.setItemId(rs.getString("MaSanPham").trim());
					lst.add(i);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					ItemDao.sortAcsByItemId(lst);
					return lst.get(lst.size()-1).getItemId();
				}
				else
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					return "";
				}
			}	
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Item getOneItemById(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM SanPham WHERE MaSanPham = ?";
				Item i = new Item();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				rs.next();
				i.setItemName(rs.getString("TenSanPham"));
				i.setQuantity(rs.getFloat("LuongTon"));
				i.setAmount(rs.getInt("SoLuongTon"));
				i.setImage(rs.getString("HinhAnh"));
				i.setItemQuantity(rs.getFloat("LuongSanPham"));
				i.setUnit(rs.getString("DonVi"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return i;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Item> getAllItem()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM SanPham";
				ArrayList<Item> lst = new ArrayList<Item>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Item i = new Item();
					i.setItemId(rs.getString("MaSanPham"));
					i.setItemName(rs.getString("TenSanPham"));
					i.setQuantity(rs.getFloat("LuongTon"));
					i.setAmount(rs.getInt("SoLuongTon"));
					i.setImage(rs.getString("HinhAnh"));
					i.setItemQuantity(rs.getFloat("LuongSanPham"));
					i.setUnit(rs.getString("DonVi"));
					lst.add(i);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ItemDao.sortAcsByItemId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy số lượng sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addItem(Item i)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO SanPham(MaSanPham,TenSanPham,LuongTon,SoLuongTon,HinhAnh,LuongSanPham,DonVi) VALUES(?,?,?,?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, i.getItemId().trim());
				pstm.setString(2, i.getItemName().trim());
				pstm.setFloat(3, i.getQuantity());
				pstm.setInt(4, i.getAmount());
				pstm.setString(5, i.getImage().trim());
				pstm.setFloat(6, i.getItemQuantity());
				pstm.setString(7, i.getUnit());
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
			JOptionPane.showOptionDialog(null, "Thêm sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateItem(Item i)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE SanPham SET TenSanPham = ?, LuongTon = ?*SoLuongTon, HinhAnh = ?, LuongSanPham = ?, DonVi = ? WHERE MaSanPham = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, i.getItemName().trim());
				pstm.setFloat(2, i.getItemQuantity());
				pstm.setString(3, i.getImage().trim());
				pstm.setFloat(4, i.getItemQuantity());
				pstm.setString(5, i.getUnit());
				pstm.setString(6, i.getItemId().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateQuantyAmount(Item i)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE SanPham SET LuongTon = ?, SoLuongTon = ? WHERE MaSanPham = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setFloat(1, i.getQuantity());
				pstm.setInt(2, i.getAmount());
				pstm.setString(3, i.getItemId().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Item> searchItem(String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM SanPham WHERE TenSanPham LIKE N'%" + info + "%'";
				ArrayList<Item> lst = new ArrayList<Item>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Item i = new Item();
					i.setItemId(rs.getString("MaSanPham"));
					i.setItemName(rs.getString("TenSanPham"));
					lst.add(i);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ItemDao.sortAcsByItemId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tìm sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByItemId(ArrayList<Item> lst) 
	{
		Comparator<Item> cpr = new Comparator<Item>() 
		{
			@Override
			public int compare(Item o1, Item o2) 
			{
				if (o1.getItemId().length() == o2.getItemId().length()) 
				{
					return o1.getItemId().compareTo(o2.getItemId());
				}
	            return o1.getItemId().length() - o2.getItemId().length();
			}
		};
		Collections.sort(lst, cpr);
	}
}
