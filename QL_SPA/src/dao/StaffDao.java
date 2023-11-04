package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Staff;

public class StaffDao 
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
				String query = "SELECT MaNhanVien FROM NhanVien";
				ArrayList<Staff> lst = new ArrayList<Staff>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Staff s = new Staff();
					s.setStaffId(rs.getString("MaNhanVien").trim());
					lst.add(s);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					StaffDao.sortAcsByStaffId(lst);
					return lst.get(lst.size()-1).getStaffId();
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
				String query = "IF EXISTS(SELECT Sdt FROM NhanVien WHERE Sdt = ?) SELECT 'Y' ELSE SELECT 'N'";
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
	
	public static String getManagerGmail(int role)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String query = "SELECT Gmail FROM NhanVien WHERE TenTaiKhoan = (SELECT TenTaiKhoan FROM TaiKhoan WHERE Quyen = ?)";
				String gmail;
				pstmt = connection.getCnn().prepareStatement(query);
				pstmt.setInt(1, role);
				rs = pstmt.executeQuery();
				rs.next();
				gmail = rs.getString("Gmail");
				pstmt.close();
				rs.close();
				connection.closeConnection();
				return gmail;
			}	
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin nhân viên thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Staff getOneStaffByAccount(String userName)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM NhanVien WHERE TenTaiKhoan = ?";
				Staff s = new Staff();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, userName.trim());
				rs = pstm.executeQuery();
				rs.next();
				s.setStaffId(rs.getString("MaNhanVien"));
				s.setSurname(rs.getString("Ho"));
				s.setName(rs.getString("Ten"));
				s.setSex(rs.getString("Phai"));
				s.setPhoneNumber(rs.getString("Sdt"));
				s.setId(rs.getString("Cccd"));
				s.setGmail(rs.getString("Gmail"));
				s.setBirthDate(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgaySinh")));
				s.setHomeTown(rs.getString("QueQuan"));
				s.setDayWork(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayVaoLam")));
				s.setAccount(rs.getString("TenTaiKhoan"));
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Staff getOneStaffById(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
				Staff s = new Staff();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				rs.next();
				s.setStaffId(rs.getString("MaNhanVien"));
				s.setSurname(rs.getString("Ho"));
				s.setName(rs.getString("Ten"));
				s.setSex(rs.getString("Phai"));
				s.setPhoneNumber(rs.getString("Sdt"));
				s.setId(rs.getString("Cccd"));
				s.setGmail(rs.getString("Gmail"));
				s.setBirthDate(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgaySinh")));
				s.setHomeTown(rs.getString("QueQuan"));
				s.setDayWork(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayVaoLam")));
				s.setAccount(rs.getString("TenTaiKhoan"));
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin cá nhân thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static int getAllStaff()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM TaiKhoan WHERE Quyen = 2";
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
			JOptionPane.showOptionDialog(null, "Lấy số lượng nhân viên thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 2;
		}
	}
	
	public static boolean addStaff(Staff s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO NhanVien(MaNhanVien,Ho,Ten,Phai,Sdt,Cccd,Gmail,NgaySinh,QueQuan,NgayVaoLam,TenTaiKhoan) VALUES(?,?,?,?,?,?,?,?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getStaffId().trim());
				pstm.setString(2, s.getSurname().trim());
				pstm.setString(3, s.getName().trim());
				pstm.setString(4, s.getSex().trim());
				pstm.setString(5, s.getPhoneNumber().trim());
				pstm.setString(6, s.getId().trim());
				pstm.setString(7, s.getGmail().trim());
				pstm.setString(8, s.getBirthDate().trim());
				pstm.setString(9, s.getHomeTown().trim());
				pstm.setString(10, s.getDayWork().trim());
				pstm.setString(11, s.getAccount().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm nhân viên thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}	
	
	public static boolean updateStaff(Staff s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE NhanVien SET Ho = ?, Ten = ?, Phai = ?, Sdt = ?, Cccd = ?, Gmail = ?, NgaySinh = ?, QueQuan = ?, NgayVaoLam = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getSurname().trim());
				pstm.setString(2, s.getName().trim());
				pstm.setString(3, s.getSex().trim());
				pstm.setString(4, s.getPhoneNumber().trim());
				pstm.setString(5, s.getId().trim());
				pstm.setString(6, s.getGmail().trim());
				pstm.setString(7, s.getBirthDate().trim());
				pstm.setString(8, s.getHomeTown().trim());
				pstm.setString(9, s.getDayWork().trim());
				pstm.setString(10, s.getAccount().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật thông tin nhân viên thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateManagerPersonal(Staff s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE NhanVien SET Ho = ?, Ten = ?, Phai = ?, Sdt = ?, Cccd = ?, Gmail = ?, NgaySinh = ?, QueQuan = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getSurname().trim());
				pstm.setString(2, s.getName().trim());
				pstm.setString(3, s.getSex().trim());
				pstm.setString(4, s.getPhoneNumber().trim());
				pstm.setString(5, s.getId().trim());
				pstm.setString(6, s.getGmail().trim());
				pstm.setString(7, s.getBirthDate().trim());
				pstm.setString(8, s.getHomeTown().trim());
				pstm.setString(9, s.getAccount().trim());
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
	
	public static boolean updateStaffPersonal(Staff s)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE NhanVien SET Sdt = ?, Gmail = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, s.getPhoneNumber().trim());
				pstm.setString(2, s.getGmail().trim());
				pstm.setString(3, s.getAccount().trim());
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
	
	public static void sortAcsByStaffId(ArrayList<Staff> lst) 
	{
		Comparator<Staff> cpr = new Comparator<Staff>() 
		{
			@Override
			public int compare(Staff o1, Staff o2) 
			{
				if (o1.getStaffId().length() == o2.getStaffId().length()) 
				{
					return o1.getStaffId().compareTo(o2.getStaffId());
				}
	            return o1.getStaffId().length() - o2.getStaffId().length();
			}
		};
		Collections.sort(lst, cpr);
	}
}
