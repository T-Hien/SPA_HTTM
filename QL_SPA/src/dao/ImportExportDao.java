package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.ImportExport;

public class ImportExportDao 
{
	public static String checkQuantity(String type)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String query = "SELECT MaPhieuNhapXuat FROM PhieuNhapXuat WHERE MaPhieuNhapXuat LIKE '%" + type + "%'";
				ArrayList<ImportExport> lst = new ArrayList<ImportExport>();
				pstmt = connection.getCnn().prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					ImportExport ie = new ImportExport();
					ie.setId(rs.getString("MaPhieuNhapXuat").trim());
					lst.add(ie);
				}
				if(lst.size() != 0)
				{
					pstmt.close();
					rs.close();
					connection.closeConnection();
					ImportExportDao.sortAcsByImportExportId(lst);
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
			JOptionPane.showOptionDialog(null, "Kiểm tra thông tin phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<ImportExport> getAllByType(String type)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaPhieuNhapXuat, ThanhTien, NgayNhapXuat, Ho + ' ' + Ten AS HoTen FROM PhieuNhapXuat, NhanVien  WHERE MaPhieuNhapXuat LIKE '%" + type + "%' AND PhieuNhapXuat.MaNhanVien = NhanVien.MaNhanVien";
				ArrayList<ImportExport> lst = new ArrayList<ImportExport>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ImportExport ie = new ImportExport();
					ie.setId(rs.getString("MaPhieuNhapXuat"));
					ie.setTotalMoney(rs.getFloat("ThanhTien"));
					ie.setImportExportDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayNhapXuat")));
					ie.setStaffId(rs.getString("HoTen"));
					lst.add(ie);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ImportExportDao.sortAcsByImportExportId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<ImportExport> getAllByTypeAndStaffId(String type, String staffId)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaPhieuNhapXuat, ThanhTien, NgayNhapXuat, Ho + ' ' + Ten AS HoTen FROM PhieuNhapXuat, NhanVien  WHERE MaPhieuNhapXuat LIKE '%" + type + "%' AND PhieuNhapXuat.MaNhanVien = ? AND PhieuNhapXuat.MaNhanVien = NhanVien.MaNhanVien";
				ArrayList<ImportExport> lst = new ArrayList<ImportExport>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, staffId.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ImportExport ie = new ImportExport();
					ie.setId(rs.getString("MaPhieuNhapXuat"));
					ie.setTotalMoney(rs.getFloat("ThanhTien"));
					ie.setImportExportDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayNhapXuat")));
					ie.setStaffId(rs.getString("HoTen"));
					lst.add(ie);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ImportExportDao.sortAcsByImportExportId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy thông tin phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addImportExport(ImportExport ie)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO PhieuNhapXuat(MaPhieuNhapXuat,ThanhTien,NgayNhapXuat,MaNhanVien) VALUES(?,?,?,?)";	
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, ie.getId().trim());
				pstm.setFloat(2, ie.getTotalMoney());
				pstm.setString(3, ie.getImportExportDay().trim());
				pstm.setString(4, ie.getStaffId().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<ImportExport> searchImportExport(String type, String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT MaPhieuNhapXuat, ThanhTien, NgayNhapXuat, Ho + ' ' + Ten AS HoTen FROM PhieuNhapXuat, NhanVien  WHERE MaPhieuNhapXuat LIKE '%" + type + "%' AND Ho + ' ' + Ten LIKE N'%" + info + "%' AND PhieuNhapXuat.MaNhanVien = NhanVien.MaNhanVien";
				ArrayList<ImportExport> lst = new ArrayList<ImportExport>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ImportExport ie = new ImportExport();
					ie.setId(rs.getString("MaPhieuNhapXuat"));
					ie.setTotalMoney(rs.getFloat("ThanhTien"));
					ie.setImportExportDay(new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayNhapXuat")));
					ie.setStaffId(rs.getString("HoTen"));
					lst.add(ie);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				ImportExportDao.sortAcsByImportExportId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Tìm phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static void sortAcsByImportExportId(ArrayList<ImportExport> lst) 
	{
		Comparator<ImportExport> cpr = new Comparator<ImportExport>() 
		{
			@Override
			public int compare(ImportExport o1, ImportExport o2) 
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
