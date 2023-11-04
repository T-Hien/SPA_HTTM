package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Account;

public class AccountDao 
{
	private static String convertByteToHex(byte[] input)
	{
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0; i<input.length; i++)
		{
			stringBuffer.append(Integer.toString((input[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
	
	private static String getMD5(String password)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			return convertByteToHex(md.digest(password.getBytes()));
		}
		catch(NoSuchAlgorithmException ex)
		{
			JOptionPane.showOptionDialog(null, "Xử lý dữ liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static int checkAccount(String userName, String password, Account account)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT * FROM TaiKhoan WHERE TenTaiKhoan = ?) SELECT MatKhau FROM TaiKhoan WHERE TenTaiKhoan = ? ELSE SELECT 'N'";
				String check = "";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, userName.trim());
				pstm.setString(2, userName.trim());
				rs = pstm.executeQuery();
				rs.next();
				check = rs.getString(1);
				if(check.equals("N"))
				{
					rs.close();
					pstm.close();
					connection.closeConnection();
					return 2;
				}
				if(check.equals(AccountDao.getMD5(password.trim())))
				{
					query = "SELECT * FROM TaiKhoan WHERE TenTaiKhoan = ?";
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, userName.trim());
					rs = pstm.executeQuery();
					rs.next();
					if(rs.getInt("TrangThai") == 0)
					{
						rs.close();
						pstm.close();
						connection.closeConnection();
						return 4;
					}
					account.setAccount(rs.getString("TenTaiKhoan"));
					account.setPassword(rs.getString("MatKhau"));
					account.setRole(rs.getInt("Quyen"));
					rs.close();
					pstm.close();
					connection.closeConnection();
					return 1;
				}
				else
				{
					rs.close();
					pstm.close();
					connection.closeConnection();
					return 3;
				}
			}
			else
			{
				return 0;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Đăng nhập thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 0;
		}
	}
	
	public static String getResetGmail(String account)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT Gmail FROM NhanVien WHERE TenTaiKhoan = ?) SELECT Gmail FROM NhanVien WHERE TenTaiKhoan = ? ELSE SELECT 'N'";
				String gmail = "";
				pstmt = connection.getCnn().prepareStatement(query);
				pstmt.setString(1, account.trim());
				pstmt.setString(2, account.trim());
				rs = pstmt.executeQuery();
				rs.next();
				if(rs.getString(1).equals("N"))
				{
					query = "SELECT Gmail FROM KhachHang WHERE TenTaiKhoan = ?";
					pstmt = connection.getCnn().prepareStatement(query);
					pstmt.setString(1, account.trim());
					rs = pstmt.executeQuery();
					rs.next();
				}
				gmail = rs.getString(1);
				pstmt.close();
				rs.close();
				connection.closeConnection();
				return gmail;
			}	
			else
			{
				return "";
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tên tài khoản không tồn tại hoặc đã xảy ra lỗi !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return "";
		}
	}
	
	public static boolean checkDuplicatePhoneNumberUpdate(String phoneNumber, String userName, int role)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "";
				String check = "";
				if(role == 1 || role == 2)
				{
					query = "IF EXISTS(SELECT * FROM NhanVien WHERE Sdt = ? AND TenTaiKhoan != ?) SELECT 'Y' ELSE SELECT 'N'";
				}
				else
				{
					query = "IF EXISTS(SELECT * FROM KhachHang WHERE Sdt = ? AND TenTaiKhoan != ?) SELECT 'Y' ELSE SELECT 'N'";
				}
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, phoneNumber.trim());
				pstm.setString(2, userName.trim());
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
	
	public static ArrayList<Account> getAllAccount(int quyen)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM TaiKhoan WHERE Quyen = ?";
				ArrayList<Account> lst = new ArrayList<Account>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, quyen);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Account a = new Account(rs.getString("TenTaiKhoan"), null, -1, rs.getInt("TrangThai"));
					lst.add(a);
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin tài khoản thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static int createAccount(Account account)
	{
		try
		{		
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String check = "";
				String checkQuery = "IF EXISTS(SELECT * FROM TaiKhoan WHERE TenTaiKhoan = ?) SELECT 'Y' ELSE SELECT 'N'";
				String query = "INSERT INTO TaiKhoan(TenTaiKhoan,MatKhau,Quyen,TrangThai) VALUES(?,?,?,?)";
				pstm = connection.getCnn().prepareStatement(checkQuery);
				pstm.setString(1, account.getAccount().trim());
				rs = pstm.executeQuery();	
				rs.next();
				check = rs.getString(1);
				if(check.equals("N"))
				{
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, account.getAccount().trim());
					pstm.setString(2, AccountDao.getMD5(account.getPassword().trim()));
					pstm.setInt(3, account.getRole());
					pstm.setInt(4, account.getStatus());
					pstm.executeUpdate();
					pstm.close();
					rs.close();
					connection.closeConnection();
					return 1;
				}
				else
				{
					pstm.close();
					rs.close();
					connection.closeConnection();
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
			JOptionPane.showOptionDialog(null, "Tạo tài khoản thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return 0;
		}
	}
	
	public static boolean updateAccount(String userName, String password)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, AccountDao.getMD5(password.trim()));
				pstm.setString(2, userName.trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật mật khẩu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean deleteAccount(String username)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query =  "DELETE FROM TaiKhoan WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, username.trim());
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
			JOptionPane.showOptionDialog(null, "Xóa tài khoản thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Account> searchAccount(String info, int role)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "";
				ArrayList<Account> lst = new ArrayList<Account>();
				if(role == 2)
				{
					query = "SELECT TaiKhoan.TenTaiKhoan, TrangThai FROM TaiKhoan, NhanVien WHERE Ho + ' ' + Ten LIKE N'%" + info + "%' AND TaiKhoan.TenTaiKhoan = NhanVien.TenTaiKhoan AND Quyen != 1";
				}
				else 
				{
					query = "SELECT TaiKhoan.TenTaiKhoan, TrangThai FROM TaiKhoan, KhachHang WHERE Ho + ' ' + Ten LIKE N'%" + info + "%' AND TaiKhoan.TenTaiKhoan = KhachHang.TenTaiKhoan";
				}
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Account a = new Account(rs.getString("TenTaiKhoan"), null, -1, rs.getInt("TrangThai"));
					lst.add(a);
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
			JOptionPane.showOptionDialog(null, "Tìm tài khoản thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean updateStatus(String userName, int status)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE TaiKhoan SET TrangThai = ? WHERE TenTaiKhoan = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, status);
				pstm.setString(2, userName.trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật trạng thái thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
}
