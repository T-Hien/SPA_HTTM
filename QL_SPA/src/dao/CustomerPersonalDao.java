package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.CustomerPersonal;

public class CustomerPersonalDao 
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
				String query = "SELECT MaKhachHang FROM KhachHang";
				ArrayList<CustomerPersonal> lst = new ArrayList<CustomerPersonal>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					CustomerPersonal cp = new CustomerPersonal();
					cp.setId(rs.getString("MaKhachHang").trim());
					lst.add(cp);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					CustomerPersonalDao.sortAcsByCustomerId(lst);
					return lst.get(lst.size()-1).getId();
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
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin khách hàng thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean checkDuplicatePhoneNumberRegister(String phoneNumber)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT Sdt FROM KhachHang WHERE Sdt = ?) SELECT 'Y' ELSE SELECT 'N'";
				String check = "";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, phoneNumber.trim());
				rs = pstm.executeQuery();
				rs.next();
				check = rs.getString(1);
				if(check.equals("N"))
				{
					rs.close();
					pstm.close();
					connection.closeConnection();
					return true;
				}
				else
				{
					rs.close();
					pstm.close();
					connection.closeConnection();
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static CustomerPersonal getOnePersonalByAccount(String userName)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM KhachHang WHERE TenTaiKhoan = ?";
				CustomerPersonal cp = new CustomerPersonal();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, userName.trim());
				rs = pstm.executeQuery();
				rs.next();
				cp.setId(rs.getString("MaKhachHang"));
				cp.setSurname(rs.getString("Ho"));
				cp.setName(rs.getString("Ten"));
				cp.setGmail(rs.getString("Gmail"));
				cp.setPhoneNumber(rs.getString("Sdt"));
				cp.setAccount(rs.getString("TenTaiKhoan"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return cp;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static CustomerPersonal getOnePersonalById(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM KhachHang WHERE MaKhachHang = ?";
				CustomerPersonal cp = new CustomerPersonal();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				rs.next();
				cp.setId(rs.getString("MaKhachHang"));
				cp.setSurname(rs.getString("Ho"));
				cp.setName(rs.getString("Ten"));
				cp.setGmail(rs.getString("Gmail"));
				cp.setPhoneNumber(rs.getString("Sdt"));
				cp.setAccount(rs.getString("TenTaiKhoan"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return cp;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static int getTotalCount()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM TaiKhoan WHERE Quyen = 3";
				int count = 0;
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					count++;
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				return count;
			}
			else
			{
				return 2;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy số lượng khách hàng thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 2;
		}
	}
	
	public static ArrayList<CustomerPersonal> getAllCustomer()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaKhachHang, Ho, Ten FROM KhachHang";
				ArrayList<CustomerPersonal> lst = new ArrayList<CustomerPersonal>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					CustomerPersonal cp = new CustomerPersonal();
					cp.setId(rs.getString("MaKhachHang"));
					cp.setSurname(rs.getString("Ho"));
					cp.setName(rs.getString("Ten"));
					lst.add(cp);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				CustomerPersonalDao.sortAcsByCustomerId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy số lượng khách hàng thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addCustomerPersonal(CustomerPersonal cp)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO KhachHang(MaKhachHang,Ho,Ten,Gmail,Sdt,TenTaiKhoan) VALUES(?,?,?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, cp.getId().trim());
				pstm.setString(2, cp.getSurname().trim());
				pstm.setString(3, cp.getName().trim());
				pstm.setString(4, cp.getGmail().trim());
				pstm.setString(5, cp.getPhoneNumber().trim());
				pstm.setString(6, cp.getAccount().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateCustomerPersonal(CustomerPersonal cp)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE KhachHang SET Ho = ?, Ten = ?, Gmail = ?, Sdt = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, cp.getSurname().trim());
				pstm.setString(2, cp.getName().trim());
				pstm.setString(3, cp.getGmail().trim());
				pstm.setString(4, cp.getPhoneNumber().trim());
				pstm.setString(5, cp.getAccount().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static void sortAcsByCustomerId(ArrayList<CustomerPersonal> lst) 
	{
		Comparator<CustomerPersonal> cpr = new Comparator<CustomerPersonal>() 
		{
			@Override
			public int compare(CustomerPersonal o1, CustomerPersonal o2) 
			{
				if (o1.getId().length() == o2.getId().length()) 
				{
					return o1.getId().compareTo(o2.getId());
				}
	            return o1.getId().length() - o2.getId().length();
			}
		};
		Collections.sort(lst, cpr);
	}
}
