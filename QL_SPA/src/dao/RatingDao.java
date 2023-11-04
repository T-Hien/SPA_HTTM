package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Rating;

public class RatingDao 
{
	public static String checkServiceBilled(String customerId, String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String query = "SELECT HoaDon.MaHoaDon FROM HoaDon JOIN ChiTietHoaDon ON HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon AND MaKhachHang = ? AND MaDichVu = ?";
				String check = "";
				pstmt = connection.getCnn().prepareStatement(query);
				pstmt.setString(1, customerId.trim());
				pstmt.setString(2, serviceId.trim());
				rs = pstmt.executeQuery();
				if(rs.next()) { check = rs.getString(1); }
				rs.close();
				pstmt.close();
				connection.closeConnection();
				return check;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin đánh giá thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static Rating getOneRating(String serviceId, String receiptId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT * FROM DanhGia WHERE MaDichVu = ? AND MaHoaDon = ?) SELECT * FROM DanhGia WHERE MaDichVu = ? AND MaHoaDon = ? ELSE SELECT 'N'";
				Rating r = null;
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				pstm.setString(2, receiptId.trim());
				pstm.setString(3, serviceId.trim());
				pstm.setString(4, receiptId.trim());
				rs = pstm.executeQuery();
				rs.next();
				if(!rs.getString(1).equalsIgnoreCase("N"))
				{
					r = new Rating();
					r.setServiceId(rs.getString("MaDichVu"));
					r.setReceiptId(rs.getString("MaHoaDon"));
					r.setRating(rs.getInt("DanhGia"));
					r.setComment(rs.getString("BinhLuan"));
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				return r;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin đánh giá thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Rating> getServiceRating(String serviceId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "IF EXISTS(SELECT * FROM DanhGia WHERE MaDichVu = ?) SELECT * FROM DanhGia WHERE MaDichVu = ? ELSE SELECT 'N'";
				ArrayList<Rating> lst = new ArrayList<Rating>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, serviceId.trim());
				pstm.setString(2, serviceId.trim());
				rs = pstm.executeQuery();
				rs.next();
				if(!rs.getString(1).equalsIgnoreCase("N"))
				{
					Rating r1 = new Rating(rs.getString("MaDichVu"), rs.getString("MaHoaDon"), rs.getInt("DanhGia"), rs.getString("BinhLuan"));
					lst.add(r1);
					while(rs.next())
					{
						Rating r = new Rating(rs.getString("MaDichVu"), rs.getString("MaHoaDon"), rs.getInt("DanhGia"), rs.getString("BinhLuan"));
						lst.add(r);
					}
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin đánh giá thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	
	public static String getRatingCustomerId(String receiptId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaKhachHang FROM HoaDon WHERE MaHoaDon = ?";
				String customerId = "";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, receiptId.trim());
				rs = pstm.executeQuery();
				rs.next();
				customerId = rs.getString(1);
				rs.close();
				pstm.close();
				connection.closeConnection();
				return customerId;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin khách hàng thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addRating(Rating r)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO DanhGia(MaDichVu,MaHoaDon,DanhGia,BinhLuan) VALUES(?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, r.getServiceId().trim());
				pstm.setString(2, r.getReceiptId().trim());
				pstm.setInt(3, r.getRating());
				pstm.setString(4, r.getComment().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm đánh giá thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean updateRating(Rating r)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE DanhGia SET DanhGia = ?, BinhLuan = ? WHERE MaDichVu = ? AND MaHoaDon = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, r.getRating());
				pstm.setString(2, r.getComment().trim());
				pstm.setString(3, r.getServiceId().trim());
				pstm.setString(4, r.getReceiptId().trim());
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
			JOptionPane.showOptionDialog(null, "Cập nhật đánh giá thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
}
