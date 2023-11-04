package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Service;

public class ServiceDao 
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
				String query = "SELECT MaDichVu FROM DichVu";
				ArrayList<Service> lst = new ArrayList<Service>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Service s = new Service(rs.getString("MaDichVu"), null, -1, null);
					lst.add(s);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					ServiceDao.sortAcsByServiceId(lst);
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
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Service getOneService(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM DichVu WHERE MaDichVu = ?";
				Service s = new Service();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				rs.next();
				s.setId(rs.getString("MaDichVu"));
				s.setName(rs.getString("TenDichVu"));
				s.setPrice(rs.getFloat("Gia"));
				s.setStaffId(rs.getString("MaNhanVien"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return s;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Service> getAllService()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM DichVu";
				ArrayList<Service> lst = new ArrayList<Service>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Service s = new Service(rs.getString("MaDichVu"), rs.getString("TenDichVu"), rs.getFloat("Gia"), rs.getString("MaNhanVien"));
					lst.add(s);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ServiceDao.sortAcsByServiceId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy số lượng dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static String getIdByName(String name)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM DichVu WHERE TenDichVu = ?";
				Service s = new Service();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, name.trim());
				rs = pstm.executeQuery();
				rs.next();
				s.setId(rs.getString("MaDichVu"));
				rs.close();
				pstm.close();
				connection.closeConnection();
				return s.getId().trim();
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy mã dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addService(Service s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO DichVu(MaDichVu,TenDichVu,Gia,MaNhanVien) VALUES(?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getId().trim());
				pstm.setString(2, s.getName().trim());
				pstm.setFloat(3, s.getPrice());
				pstm.setString(4, s.getStaffId().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateService(Service s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE DichVu SET TenDichVu = ?, Gia = ? WHERE MaDichVu = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getName().trim());
				pstm.setFloat(2, s.getPrice());
				pstm.setString(3, s.getId().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static int checkReceiptExist(String serviceId)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String check = "";
				String query = "IF EXISTS(SELECT * FROM ChiTietHoaDon WHERE MaDichVu = ?) SELECT 'Y' ELSE SELECT 'N'";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				rs = pstm.executeQuery();	
				rs.next();
				check = rs.getString(1);
				pstm.close();
				rs.close();
				connection.closeConnection();
				if(check.equals("N"))
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
				return 2;
			}		
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra ràng buộc thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 2;
		}
	}
	
	public static int checkScheduleExist(String serviceId)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String check = "";
				String query = "IF EXISTS(SELECT * FROM LichHen WHERE MaDichVu = ?) SELECT 'Y' ELSE SELECT 'N'";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				rs = pstm.executeQuery();	
				rs.next();
				check = rs.getString(1);
				pstm.close();
				rs.close();
				connection.closeConnection();
				if(check.equals("N"))
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
				return 2;
			}		
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra ràng buộc thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 2;
		}
	}
	
	public static int checkRatingExist(String serviceId)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String check = "";
				String query = "IF EXISTS(SELECT * FROM DanhGia WHERE MaDichVu = ?) SELECT 'Y' ELSE SELECT 'N'";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				rs = pstm.executeQuery();	
				rs.next();
				check = rs.getString(1);
				pstm.close();
				rs.close();
				connection.closeConnection();
				if(check.equals("N"))
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
				return 2;
			}		
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra ràng buộc thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 2;
		}
	}
	
	public static boolean deleteService(String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query =  "DELETE FROM DichVu WHERE MaDichVu = ?";
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
			JOptionPane.showOptionDialog(null, "Xóa dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Service> searchService(String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM DichVu WHERE TenDichVu LIKE N'%" + info + "%'";
				ArrayList<Service> lst = new ArrayList<Service>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Service s = new Service(rs.getString("MaDichVu"), rs.getString("TenDichVu"), rs.getFloat("Gia"), rs.getString("MaNhanVien"));
					lst.add(s);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ServiceDao.sortAcsByServiceId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tìm dịch vụ thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByServiceId(ArrayList<Service> lst) 
	{
		Comparator<Service> cpr = new Comparator<Service>() 
		{
			@Override
			public int compare(Service o1, Service o2) 
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
