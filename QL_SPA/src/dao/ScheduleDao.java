package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Schedule;

public class ScheduleDao 
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
				String query = "SELECT * FROM LichHen";
				ArrayList<Schedule> lst = new ArrayList<Schedule>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Schedule s = new Schedule(rs.getString("MaLichHen"), null, null, null, null, -1, null);
					lst.add(s);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					ScheduleDao.sortAcsByScheduleId(lst);
					return lst.get(lst.size()-1).getScheduleId();
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
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}	
	
	public static ArrayList<Schedule> getCustomerSchedule(String customerId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaLichHen, TenDichVu, Ngay, Gio, TrangThai FROM LichHen, DichVu WHERE LichHen.MaDichVu = DichVu.MaDichVu AND MaKhachHang = ? AND TrangThai != 2";
				ArrayList<Schedule> lst = new ArrayList<Schedule>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, customerId);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Schedule s = new Schedule(rs.getString("MaLichHen"), null, rs.getString("TenDichVu"), new SimpleDateFormat("d-M-yyyy").format(rs.getDate("Ngay")), rs.getString("Gio"), rs.getInt("TrangThai"), null);
					lst.add(s);
				}
				ScheduleDao.sortAcsByScheduleId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Schedule> getScheduleByStatus(int status)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaLichHen, MaKhachHang, TenDichVu, Ngay, Gio, TrangThai, LichHen.MaNhanVien FROM LichHen, DichVu WHERE LichHen.MaDichVu = DichVu.MaDichVu AND TrangThai = ?";
				ArrayList<Schedule> lst = new ArrayList<Schedule>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, status);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Schedule s = new Schedule(rs.getString("MaLichHen"), rs.getString("MaKhachHang"), rs.getString("TenDichVu"), new SimpleDateFormat("d/M/yyyy").format(rs.getDate("Ngay")), rs.getString("Gio"), rs.getInt("TrangThai"), rs.getString("MaNhanVien"));
					lst.add(s);
				}
				ScheduleDao.sortAcsByScheduleId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean bookSchedule(ArrayList<Schedule> lst)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO LichHen(MaLichHen,MaKhachHang,MaDichVu,Ngay,Gio,TrangThai) VALUES(?,?,?,?,?,?)";	
				for(Schedule s : lst)
				{
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, ScheduleDao.checkQuantity().trim().isEmpty() ? "LH0" : "LH" + String.valueOf(Integer.parseInt(ScheduleDao.checkQuantity().trim().substring(2, ScheduleDao.checkQuantity().trim().length()))+1));
					pstm.setString(2, s.getCustomerId());
					pstm.setString(3, s.getServiceId());
					pstm.setString(4, s.getDate());
					pstm.setString(5, s.getTime());
					pstm.setInt(6, s.getStatus());
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
			JOptionPane.showOptionDialog(null, "Đặt lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean confirmSchedule(String scheduleId, String staffId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE LichHen SET TrangThai = 1, MaNhanVien = ? WHERE MaLichHen = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, staffId);
				pstm.setString(2, scheduleId.trim());
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
			JOptionPane.showOptionDialog(null, "Hủy lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static boolean cancelSchedule(String scheduleId, String staffId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "UPDATE LichHen SET TrangThai = 2, MaNhanVien = ? WHERE MaLichHen = ?";
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, staffId);
				pstm.setString(2, scheduleId.trim());
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
			JOptionPane.showOptionDialog(null, "Hủy lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<Schedule> searchCustomerSchedule(int status, String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaLichHen, LichHen.MaKhachHang, TenDichVu, Ngay, Gio, TrangThai, LichHen.MaNhanVien FROM LichHen, DichVu, KhachHang WHERE LichHen.MaDichVu = DichVu.MaDichVu AND TrangThai = ? AND Ho + ' ' + Ten LIKE N'%" + info + "%' AND KhachHang.MaKhachHang = LichHen.MaKhachHang";
				ArrayList<Schedule> lst = new ArrayList<Schedule>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setInt(1, status);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Schedule s = new Schedule(rs.getString("MaLichHen"), rs.getString("MaKhachHang"), rs.getString("TenDichVu"), new SimpleDateFormat("d/M/yyyy").format(rs.getDate("Ngay")), rs.getString("Gio"), rs.getInt("TrangThai"), rs.getString("MaNhanVien"));
					lst.add(s);
				}
				ScheduleDao.sortAcsByScheduleId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tìm lịch hẹn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByScheduleId(ArrayList<Schedule> lst) 
	{
		Comparator<Schedule> cpr = new Comparator<Schedule>() 
		{
			@Override
			public int compare(Schedule o1, Schedule o2) 
			{
				if (o1.getScheduleId().length() == o2.getScheduleId().length()) 
				{
					return o1.getScheduleId().compareTo(o2.getScheduleId());
				}
	            return o1.getScheduleId().length() - o2.getScheduleId().length();
			}
		};
		Collections.sort(lst, cpr);
	}
}
