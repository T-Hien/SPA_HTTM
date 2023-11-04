package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Revenue;

public class RevenueDao 
{
	public static ArrayList<Revenue> getRevenue(int month, int year)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				CallableStatement pstm = null;
				ResultSet rs = null;
				String query = "{call TinhDoanhThu (?, ?)}";
				ArrayList<Revenue> lst = new ArrayList<Revenue>();
				pstm = connection.getCnn().prepareCall(query);
				pstm.setInt(1, month);
				pstm.setInt(2, year);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Revenue r = new Revenue(rs.getString("Ma"), rs.getString("TenNhanVien"), new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")), rs.getFloat("ThanhTien"));
					lst.add(r);
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
	
	public static ArrayList<Revenue> findStaffRevenue(int month, int year, String info)
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				CallableStatement pstm = null;
				ResultSet rs = null;
				String query = "{call TinhDoanhThu (?, ?)}";
				ArrayList<Revenue> lst = new ArrayList<Revenue>();
				pstm = connection.getCnn().prepareCall(query);
				pstm.setInt(1, month);
				pstm.setInt(2, year);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					if(rs.getString("TenNhanVien").toLowerCase().trim().contains(info.toLowerCase().trim()))
					{
						Revenue r = new Revenue(rs.getString("Ma"), rs.getString("TenNhanVien"), new SimpleDateFormat("d/M/yyyy").format(rs.getDate("NgayLap")), rs.getFloat("ThanhTien"));
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
			JOptionPane.showOptionDialog(null, "Tìm số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
}
