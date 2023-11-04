package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.ImportExportDetail;

public class ImportExportDetailDao 
{
	public static ArrayList<ImportExportDetail> getAllById(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT CTNhapXuat.MaSanPham, TenSanPham, GiaNhapXuat, SoLuong FROM CTNhapXuat, SanPham WHERE CTNhapXuat.MaSanPham = SanPham.MaSanPham AND MaPhieuNhapXuat = ?";
				ArrayList<ImportExportDetail> lst = new ArrayList<ImportExportDetail>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ImportExportDetail ied = new ImportExportDetail();
					ied.setId(rs.getString("MaSanPham"));
					ied.setItemId(rs.getString("TenSanPham"));
					ied.setMoney(rs.getFloat("GiaNhapXuat"));
					ied.setAmount(rs.getInt("SoLuong"));
					lst.add(ied);
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin chi tiết phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addImportExportDetail(ArrayList<ImportExportDetail> lst, int type)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO CTNhapXuat(MaPhieuNhapXuat,MaSanPham,GiaNhapXuat,SoLuong) VALUES(?,?,?,?)";	
				String subQueryOne = "";
				String subQueryTwo = "";
				for(ImportExportDetail ied : lst)
				{
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, ied.getId().trim());
					pstm.setString(2, ied.getItemId().trim());
					pstm.setFloat(3, ied.getMoney());
					pstm.setInt(4, ied.getAmount());
					pstm.executeUpdate();
					if(type == 0)
					{
						subQueryOne = "UPDATE SanPham SET SoLuongTon = SoLuongTon + ? WHERE MaSanPham = ?";
					}
					else if(type == 1)
					{
						subQueryOne = "UPDATE SanPham SET SoLuongTon = SoLuongTon - ? WHERE MaSanPham = ?";
					}
					pstm = connection.getCnn().prepareStatement(subQueryOne);
					pstm.setInt(1, ied.getAmount());
					pstm.setString(2, ied.getItemId().trim());
					pstm.executeUpdate();
					if(type == 0)
					{
						subQueryTwo = "UPDATE SanPham SET LuongTon = LuongTon + (LuongSanPham*?) WHERE MaSanPham = ?";
					}
					else if(type == 1)
					{
						subQueryTwo = "UPDATE SanPham SET LuongTon = LuongTon - (LuongSanPham*?) WHERE MaSanPham = ?";
					}
					pstm = connection.getCnn().prepareStatement(subQueryTwo);
					pstm.setInt(1, ied.getAmount());
					pstm.setString(2, ied.getItemId().trim());
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
			JOptionPane.showOptionDialog(null, "Thêm chi tiết phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<ImportExportDetail> searchImportExportDetail(String id, String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT CTNhapXuat.MaSanPham, TenSanPham, GiaNhapXuat, SoLuong FROM CTNhapXuat, SanPham WHERE CTNhapXuat.MaSanPham = SanPham.MaSanPham AND MaPhieuNhapXuat = ? AND TenSanPham LIKE N'%" + info + "%'";
				ArrayList<ImportExportDetail> lst = new ArrayList<ImportExportDetail>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ImportExportDetail ied = new ImportExportDetail();
					ied.setId(rs.getString("MaSanPham"));
					ied.setItemId(rs.getString("TenSanPham"));
					ied.setMoney(rs.getFloat("GiaNhapXuat"));
					ied.setAmount(rs.getInt("SoLuong"));
					lst.add(ied);
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
			JOptionPane.showOptionDialog(null, "Tìm chi tiết phiếu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
}
