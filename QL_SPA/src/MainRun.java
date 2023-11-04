import javax.swing.JOptionPane;

import dao.ConnectionDao;
import graphics.GUI;

public class MainRun 
{
	public static void main(String args[])
	{
		GUI gui = new GUI("./icon_image/nen_spa.jpg");
		ConnectionDao connection = new ConnectionDao();
		if(!connection.openConnection())
		{
			int option = JOptionPane.showOptionDialog(null, "Chọn có nếu bạn muốn thoát phần mềm\nChọn không nếu bạn muốn tiếp tục mà không kết nối cơ sở dữ liệu", "HỆ THỐNG", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 0);
			if(option == 0)
			{
				if(connection.getCnn() != null)
				{
					connection.closeConnection();
				}
				gui.dispose();
			}
		}
	}
}
