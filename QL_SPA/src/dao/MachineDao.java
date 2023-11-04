package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

import entity.Machine;

public class MachineDao {
	
	public static ArrayList<Machine> getAllMachine()
	{
		try
		{	
			ConnectionDao connection = new ConnectionDao();
			if(connection.openConnection())
			{
				PreparedStatement pstm = null;
				ResultSet rs = null;
				//String query = "SELECT * FROM HocMay";
				String query = "SELECT * FROM HocMay WHERE Nhan = 'nhap'";
				ArrayList<Machine> lst = new ArrayList<Machine>();
				pstm = connection.getCnn().prepareStatement(query);
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Machine i = new Machine();
					i.setItemIdM(rs.getString("MaSanPham"));
					i.setItemNameM(rs.getString("TenSanPham"));
					i.setTime(rs.getInt("ThoiGianNhap"));
					i.setAmountM(rs.getInt("SoLuongTon"));
					i.setAmountImport(rs.getInt("SoLuongNhap"));
					i.setLaBel(rs.getString("Nhan"));

					lst.add(i);
				}
				rs.close();
				pstm.close();
				connection.closeConnection();
				MachineDao.sortAcsByMachineId(lst);
				return lst;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException ex)
		{
			JOptionPane.showOptionDialog(null, "Lấy số lượng sản phẩm thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return null;
		}
	}
	
	public static ArrayList<Machine> importProducts() {
	    ArrayList<Machine> lst = new ArrayList<Machine>();

	    try {
	        ConnectionDao connection = new ConnectionDao();
	        if (connection.openConnection()) {
	            CallableStatement pstm = null;
	            ResultSet rs = null;
	            String query = "{call Nhap_GiaSP}";

	            pstm = connection.getCnn().prepareCall(query);
	            rs = pstm.executeQuery();

	            while (rs.next()) {
	                Machine m = new Machine();
	                m.setItemIdM(rs.getString("MaSanPham"));
	                m.setItemNameM(rs.getString("TenSanPham")); // Assuming you have a setter method for the item name
	                m.setMoneyImport(rs.getFloat("GiaNhap")); // Assuming you have a setter method for the quantity
	                m.setAmountM(rs.getInt("LuongNhap")); // Assuming you have a setter method for the price
	                lst.add(m);
	            }

	            rs.close();
	            pstm.close();
	            connection.closeConnection();
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Tìm số liệu thất bại !\nLỗi: " + ex.getMessage() + " !", "LỖI TRUY VẤN THÔNG TIN", JOptionPane.ERROR_MESSAGE);
	    }
	    
	    return lst;
	}

	
	

	public static void sortAcsByMachineId(ArrayList<Machine> lst) 
	{
		Comparator<Machine> cpr = new Comparator<Machine>() 
		{
			@Override
			public int compare(Machine o1, Machine o2) 
			{
				if (o1.getItemIdM().length() == o2.getItemIdM().length()) 
				{
					return o1.getItemIdM().compareTo(o2.getItemIdM());
				}
	            return o1.getItemIdM().length() - o2.getItemIdM().length();
			}
		};
		Collections.sort(lst, cpr);
	}

}
