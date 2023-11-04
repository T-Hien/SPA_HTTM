package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Statistics;

public class StatisticsDao 
{
	public static int totalYear;
	public static int totalBilledService;
	public static int mostBilledService;
	
	public static Statistics getOverallFinance()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "";
				Statistics s = new Statistics();
				query = "SELECT COUNT(*) FROM PhieuNhapXuat WHERE MaPhieuNhapXuat LIKE '%PN%'";
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				rs.next();
				s.setImportQuantity(rs.getInt(1));
				query = "SELECT COUNT(*) FROM PhieuNhapXuat WHERE MaPhieuNhapXuat LIKE '%PX%'";
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				rs.next();
				s.setExportQuantity(rs.getInt(1));
				query = "SELECT COUNT(*) FROM HoaDon";
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				rs.next();
				s.setReceiptQuantity(rs.getInt(1));
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
			JOptionPane.showOptionDialog(null, "Lấy số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Statistics> getIndividualFinance()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM DoanhThuNhanVien";
				ArrayList<Statistics> lst = new ArrayList<Statistics>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Statistics s = new Statistics();
					s.setStaffName(rs.getString("HoTen"));
					s.setIndividualIncome(rs.getFloat("TongTien") / (float) 1000000);
					lst.add(s);
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
			JOptionPane.showOptionDialog(null, "Lấy số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Statistics> getServiceReceiptByYear()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT * FROM HoaDonDIchVuTheoNam";
				ArrayList<Statistics> lst = new ArrayList<Statistics>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Statistics s = new Statistics();
					s.setServiceName(rs.getString("TenDichVu"));
					s.setReceiptByYearQuantity(rs.getInt("SoLuong"));
					s.setYear(rs.getInt("Nam"));
					lst.add(s);
				}
				StatisticsDao.getServiceBilledAndYearAvailable();
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
			JOptionPane.showOptionDialog(null, "Lấy số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean getServiceBilledAndYearAvailable()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT COUNT(*) OVER() AS SoLuong, 'DV' AS Loai FROM ChiTietHoaDon GROUP BY MaDichVu UNION SELECT COUNT(*) OVER(), 'N' FROM HoaDon GROUP BY YEAR(NgayLap)";
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					if(rs.getString("Loai").equals("DV"))
					{
						StatisticsDao.totalBilledService = rs.getInt("SoLuong");
					}
					else
					{
						StatisticsDao.totalYear = rs.getInt("SoLuong");
					}
				}
				if(StatisticsDao.totalBilledService == 0)
				{
					StatisticsDao.totalYear = 0;
				}
				else if(StatisticsDao.totalYear == 0)
				{
					StatisticsDao.totalYear = StatisticsDao.totalBilledService;
				}
				rs.close();
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
			JOptionPane.showOptionDialog(null, "Lấy số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static void sortAcsByServiceName(ArrayList<Statistics> lst) 
	{
		Comparator<Statistics> cpr = new Comparator<Statistics>() 
		{
			@Override
			public int compare(Statistics o1, Statistics o2) 
			{
				return o1.getServiceName().compareTo(o2.getServiceName());
			}
		};
		Collections.sort(lst, cpr);
	}
	
	public static void sortDescByServiceName(ArrayList<Statistics> lst) 
	{
		Comparator<Statistics> cpr = new Comparator<Statistics>() 
		{
			@Override
			public int compare(Statistics o1, Statistics o2) 
			{
				return o2.getServiceName().compareTo(o1.getServiceName());
			}
		};
		Collections.sort(lst, cpr);
	}
	
	public static void sortAcsByYear(ArrayList<Statistics> lst) 
	{
		Comparator<Statistics> cpr = new Comparator<Statistics>() 
		{
			@Override
			public int compare(Statistics o1, Statistics o2) 
			{
				return String.valueOf(o1.getYear()).compareTo(String.valueOf(o2.getYear()));
			}
		};
		Collections.sort(lst, cpr);
	}
	
	public static void sortDescByYear(ArrayList<Statistics> lst) 
	{
		Comparator<Statistics> cpr = new Comparator<Statistics>() 
		{
			@Override
			public int compare(Statistics o1, Statistics o2) 
			{
				return String.valueOf(o2.getYear()).compareTo(String.valueOf(o1.getYear()));
			}
		};
		Collections.sort(lst, cpr);
	}
}
