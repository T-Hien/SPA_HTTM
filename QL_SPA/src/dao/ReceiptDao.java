package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Receipt;

public class ReceiptDao 
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
				String query = "SELECT MaHoaDon FROM HoaDon";
				ArrayList<Receipt> lst = new ArrayList<Receipt>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Receipt r = new Receipt();
					r.setReceiptId(rs.getString("MaHoaDon").trim());
					lst.add(r);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					ReceiptDao.sortAcsByReceiptId(lst);
					return lst.get(lst.size()-1).getReceiptId();
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
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Receipt> getAllReceipt()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaHoaDon, Ho + ' ' + Ten AS HoTen, MaKhachHang, MucUuDai, NgayLap, ThanhTien FROM HoaDon, NhanVien WHERE HoaDon.MaNhanVien = NhanVien.MaNhanVien";
				ArrayList<Receipt> lst = new ArrayList<Receipt>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Receipt r = new Receipt();
					r.setReceiptId(rs.getString("MaHoaDon"));
					r.setStaffId(rs.getString("HoTen"));
					r.setCustomerId(rs.getString("MaKhachHang"));
					r.setDiscountLevel(rs.getInt("MucUuDai"));
					r.setReceiptDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")));
					r.setTotalMoney(rs.getFloat("ThanhTien"));
					lst.add(r);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ReceiptDao.sortAcsByReceiptId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Receipt> getAllReceiptByStaffId(String staffId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaHoaDon, Ho + ' ' + Ten AS HoTen, MaKhachHang, MucUuDai, NgayLap, ThanhTien FROM HoaDon, NhanVien WHERE HoaDon.MaNhanVien = NhanVien.MaNhanVien AND HoaDon.MaNhanVien = ?";
				ArrayList<Receipt> lst = new ArrayList<Receipt>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, staffId.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Receipt r = new Receipt();
					r.setReceiptId(rs.getString("MaHoaDon"));
					r.setStaffId(rs.getString("HoTen"));
					r.setCustomerId(rs.getString("MaKhachHang"));
					r.setDiscountLevel(rs.getInt("MucUuDai"));
					r.setReceiptDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")));
					r.setTotalMoney(rs.getFloat("ThanhTien"));
					lst.add(r);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ReceiptDao.sortAcsByReceiptId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Receipt> getAllReceiptByCustomerId(String customerId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaHoaDon, Ho + ' ' + Ten AS HoTen, MaKhachHang, MucUuDai, NgayLap, ThanhTien FROM HoaDon, NhanVien WHERE HoaDon.MaNhanVien = NhanVien.MaNhanVien AND HoaDon.MaKhachHang = ?";
				ArrayList<Receipt> lst = new ArrayList<Receipt>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, customerId.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Receipt r = new Receipt();
					r.setReceiptId(rs.getString("MaHoaDon"));
					r.setStaffId(rs.getString("HoTen"));
					r.setCustomerId(rs.getString("MaKhachHang"));
					r.setDiscountLevel(rs.getInt("MucUuDai"));
					r.setReceiptDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")));
					r.setTotalMoney(rs.getFloat("ThanhTien"));
					lst.add(r);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ReceiptDao.sortAcsByReceiptId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addReceipt(Receipt r)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO HoaDon(MaHoaDon,MaNhanVien,MaKhachHang,MucUuDai,NgayLap,ThanhTien) VALUES(?,?,?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, r.getReceiptId().trim());
				pstm.setString(2, r.getStaffId().trim());
				pstm.setString(3, r.getCustomerId());
				pstm.setInt(4, r.getDiscountLevel());
				pstm.setString(5, r.getReceiptDay().trim());
				pstm.setFloat(6, r.getTotalMoney());
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
			JOptionPane.showOptionDialog(null, "Thêm hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Receipt> searchReceipt(String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaHoaDon, Ho + ' ' + Ten AS HoTen, HoaDon.MaKhachHang, MucUuDai, NgayLap, ThanhTien FROM HoaDon, NhanVien WHERE HoaDon.MaNhanVien = NhanVien.MaNhanVien AND Ho + ' ' + Ten LIKE N'%" + info + "%'";
				ArrayList<Receipt> lst = new ArrayList<Receipt>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Receipt r = new Receipt();
					r.setReceiptId(rs.getString("MaHoaDon"));
					r.setStaffId(rs.getString("HoTen"));
					r.setCustomerId(rs.getString("MaKhachHang"));
					r.setDiscountLevel(rs.getInt("MucUuDai"));
					r.setReceiptDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")));
					r.setTotalMoney(rs.getFloat("ThanhTien"));
					lst.add(r);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ReceiptDao.sortAcsByReceiptId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tìm hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByReceiptId(ArrayList<Receipt> lst) 
	{
		Comparator<Receipt> cpr = new Comparator<Receipt>() 
		{
			@Override
			public int compare(Receipt o1, Receipt o2) 
			{
				if (o1.getReceiptId().length() == o2.getReceiptId().length()) 
				{
					return o1.getReceiptId().compareTo(o2.getReceiptId());
				}
	            return o1.getReceiptId().length() - o2.getReceiptId().length();
			}
		};
		Collections.sort(lst, cpr);
	}
}
