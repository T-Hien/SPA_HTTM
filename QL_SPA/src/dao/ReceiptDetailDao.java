package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.ReceiptDetail;

public class ReceiptDetailDao 
{
	public static ArrayList<ReceiptDetail> getAllById(String id)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT ChiTietHoaDon.MaDichVu, TenDichVu, GiaTien, SoLuong FROM ChiTietHoaDon, DichVu WHERE ChiTietHoaDon.MaDichVu = DichVu.MaDichVu AND MaHoaDon = ?";
				ArrayList<ReceiptDetail> lst = new ArrayList<ReceiptDetail>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ReceiptDetail rd = new ReceiptDetail();
					rd.setReceiptId(rs.getString("MaDichVu"));
					rd.setServiceId(rs.getString("TenDichVu"));
					rd.setMoney(rs.getFloat("GiaTien"));
					rd.setAmount(rs.getInt("SoLuong"));
					lst.add(rd);
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
			JOptionPane.showOptionDialog(null, "Lấy thông tin chi tiết hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static boolean addReceiptDetail(ArrayList<ReceiptDetail> lst)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				String query = "INSERT INTO ChiTietHoaDon(MaHoaDon,MaDichVu,GiaTien,SoLuong) VALUES(?,?,?,?)";	
				for(ReceiptDetail rd : lst)
				{
					pstm = connection.getCnn().prepareStatement(query);
					pstm.setString(1, rd.getReceiptId().trim());
					pstm.setString(2, rd.getServiceId().trim());
					pstm.setFloat(3, rd.getMoney());
					pstm.setInt(4, rd.getAmount());
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
			JOptionPane.showOptionDialog(null, "Thêm chi tiết hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI CẬP NHẬT THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public static ArrayList<ReceiptDetail> searchReceiptDetail(String id, String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				String query = "SELECT ChiTietHoaDon.MaDichVu, TenDichVu, GiaTien FROM ChiTietHoaDon, DichVu WHERE ChiTietHoaDon.MaDichVu = DichVu.MaDichVu AND MaHoaDon = ? AND TenDichVu LIKE N'%" + info + "%'";
				ArrayList<ReceiptDetail> lst = new ArrayList<ReceiptDetail>();
				pstm = connection.getCnn().prepareStatement(query);
				pstm.setString(1, id.trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					ReceiptDetail rd = new ReceiptDetail();
					rd.setReceiptId(rs.getString("MaDichVu"));
					rd.setServiceId(rs.getString("TenDichVu"));
					rd.setMoney(rs.getFloat("GiaTien"));
					lst.add(rd);
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
			JOptionPane.showOptionDialog(null, "Tìm chi tiết hóa đơn thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
}
