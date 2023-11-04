package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartPanel;

import dao.AccountDao;
import dao.CustomerPersonalDao;
import dao.DiscountDao;
import dao.FormulaDao;
import dao.ImportExportDao;
import dao.ImportExportDetailDao;
import dao.ItemDao;
import dao.MachineDao;
import dao.RatingDao;
import dao.ReceiptDao;
import dao.ReceiptDetailDao;
import dao.RevenueDao;
import dao.ScheduleDao;
import dao.ServiceDao;
import dao.StaffDao;
import dao.StatisticsDao;
import entity.Account;
import entity.CustomerPersonal;
import entity.Discount;
import entity.Formula;
import entity.ImportExport;
import entity.ImportExportDetail;
import entity.Item;
import entity.Machine;
import entity.Rating;
import entity.Receipt;
import entity.ReceiptDetail;
import entity.Revenue;
import entity.Schedule;
import entity.Service;
import entity.Staff;
import entity.Statistics;

public class GUI extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = -7595259713652365158L;
	private int role = -1;
	private int menu = -1;
	private NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	protected static String managementIcon1 = null;
	protected static String managementIcon2 = null;
	private String sign = "đăâêôơưáắấéếíóốớúứýàằầèềìòồờùừỳảẳẩẻểỉỏổởủửỷãẵẫẽễĩõỗỡũữỹạặậẹệịọộợụự";
	private String acc = "";
	private String pwd = "";
	private String tempId = "";
	private String dateNow = "";
	private ArrayList<Item> items;
	//THÊM
	private ArrayList<Machine> machines;
	private ArrayList<Service> services;
	private ArrayList<JPanel> keepTrack = new ArrayList<JPanel>();
	private ArrayList<JComponent> comps = new ArrayList<JComponent>();
	private JComponent comp;
	protected AnimationPanel managementWindow = new AnimationPanel();
	protected JPanel signupWindow = new JPanel();
	protected JPanel resetMailWindow = new JPanel();
	protected JPanel managementSection = new JPanel();
	protected JPanel managementPersonal = new JPanel();
	protected JPanel managementDisplay = new JPanel();
	protected JPanel mainWindow = new JPanel();
	protected JPanel serviceWindow = new JPanel();
	protected JPanel scheduledWindow = new JPanel();
	protected JPanel personalInfoWindow = new JPanel();
	protected JPanel historyWindow = new JPanel();
	protected JPanel ratingWindow = new JPanel();
	protected JPanel rating = new JPanel();
	protected JLabel line1 = new JLabel();
	private JLabel managerFullName = new JLabel();
	private JLabel managerGmail = new JLabel();
	private JLabel managerPhoneNumber = new JLabel();
	private JLabel title = new JLabel();
	private JLabel signupAccount = new JLabel();
	private JLabel resetPassword = new JLabel();
	private JLabel personalAccount = new JLabel();
	private JLabel searchIcon = new JLabel();
	private JLabel tempTitle = new JLabel();
	private JLabel upManagementTitle = new JLabel();
	private JLabel downManagementTitle = new JLabel();
	private JLabel managementPickerInfo = new JLabel();
	private JLabel customerId = new JLabel();
	private JLabel customerName = new JLabel();
	private JTextField userName = new JTextField();
	private JTextField password = new JTextField();
	protected JTextField newUserName = new JTextField();
	protected JTextField restoreUserName = new JTextField();
	protected JTextField surname = new JTextField();
	protected JTextField name = new JTextField();
	protected JTextField mail = new JTextField();
	protected JTextField phoneNumber = new JTextField();
	protected JTextField search = new RoundJTextField(30);
	protected JTextField updateSurname = new JTextField();
	protected JTextField updateName = new JTextField();
	protected JTextField updateMail = new JTextField();
	protected JTextField updatePhoneNumber = new JTextField();
	protected JTextField infoOne = new RoundJTextField(30);
	protected JTextField infoTwo = new RoundJTextField(30);
	protected JTextField infoThree = new RoundJTextField(30);
	protected JTextField infoFour = new RoundJTextField(30);
	protected JTextField infoFive = new RoundJTextField(30);
	protected JTextField infoSix = new RoundJTextField(30);
	protected JTextField infoSeven = new RoundJTextField(30);
	protected JTextField infoEight = new RoundJTextField(30);
	protected JTextField infoNine = new RoundJTextField(30);
	protected JTextField infoTen = new RoundJTextField(30);
	protected JTextField infoEleven = new RoundJTextField(30);
	//THÊM
	protected JTextField infoTwelve = new RoundJTextField(30);
	
	
	protected JTextField personalSurname = new JTextField();
	protected JTextField personalName = new JTextField();
	protected JTextField personalSex = new JTextField();
	protected JTextField personalId = new JTextField();
	protected JTextField personalBirthDate = new JTextField();
	protected JTextField personalHomeTown = new JTextField();
	protected JTextField personalGmail = new JTextField();
	protected JTextField personalPhoneNumber = new JTextField();
	protected JPasswordField personalPassword = new JPasswordField();
	protected JPasswordField personalNewPassword = new JPasswordField();
	protected JPasswordField personalConfirmPassword = new JPasswordField();
	protected JPasswordField passwordHolder = new JPasswordField();
	protected JPasswordField newPassword = new JPasswordField();
	protected JPasswordField updatePassword = new JPasswordField();
	protected JPasswordField updateNewPassword = new JPasswordField();
	protected JPasswordField updateConfirmPassword = new JPasswordField();
	private JButton login = new JButton();
	private JButton signup = new JButton();
	private JButton signupGoBack = new JButton();
	private JButton resetMail = new JButton();
	private JButton resetMailGoBack = new JButton();
	private JButton updateManagement = new JButton();
	private JButton changePasswordManagement = new JButton();
	private JButton staff = new JButton();
	private JButton customer = new JButton();
	private JButton item = new JButton();
	private JButton	service = new JButton();
	private JButton schedule = new JButton();
	private JButton billing = new JButton();
	private JButton revenue = new JButton();
	private JButton statistics = new JButton();
	private JButton managementAdd = new JButton();
	private JButton managementUpdate = new JButton();
	private JButton managementOne = new JButton();
	private JButton managementTwo = new JButton();
	private JButton tempButton = new JButton();
	private JButton managementGoBack = new JButton();
	private JButton customerService = new JButton();
	private JButton customerScheduled = new JButton();
	private JButton customerPersonalInfo = new JButton();
	private JButton customerHistory = new JButton();
	private JButton customerRating = new JButton();
	private JButton customerGoback = new JButton();
	private JButton headerGoBack = new JButton();
	private JButton serviceAdd = new JButton();
	private JButton serviceConfirm = new JButton();
	private JButton updatePersonalInfo = new JButton();
	private JButton updateAccount = new JButton();
	private JButton ratingConfirm = new JButton();
	
	//THÊM
	private JButton notify = new JButton();
	private JComboBox<String> servicePricePicker = new JComboBox<String>();
	private JComboBox<String> servicePicker = new JComboBox<String>();
	private JComboBox<String> timePicker = new JComboBox<String>();
	private JComboBox<String> managementPickerOne = new JComboBox<String>();
	private JComboBox<String> managementPickerTwo = new JComboBox<String>();
	private JTextArea comment = new JTextArea();
	private JDatePickerImpl datePicker;
	private JTable data;
	private JScrollPane managementData;
	
	public GUI(String background)
	{
		Staff s = StaffDao.getOneStaffByAccount("sa");
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read(new File(background));
		} 
		catch (IOException e) 
		{
			JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi !\nLỗi: " + e.getMessage() + " !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			this.dispose();
		} 
		this.setTitle("JOLENE SPA");
		this.setIconImage(new ImageIcon("./icon_image/icon.png").getImage());
		this.setSize(1285, 685);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setContentPane(new Background(image));
		this.setUp();
		this.showLogin(s.getSurname() + " " + s.getName(), s.getGmail(), s.getPhoneNumber());
		this.showSignup();
		this.showResetMail();
		this.showManagement();
		this.showMain();
		this.showService();
		this.showScheduled();
		this.showPersonalInfo();
		this.showHistory();
		this.showRating();
		this.setVisible(true);
	}
	
	public boolean checkAllFilled(JTextField[] lst)
	{
		for(JTextField tf : lst)
		{
			if(tf.getText().trim().isEmpty() || tf.getForeground() == Color.gray) return false;
		}
		return true;
	}
	
	public boolean checkOnlyLetter(JTextField tf)
	{
		boolean check = false;
		for(int i=0; i<tf.getText().trim().length(); i++)
		{
			for(int j=0; j<this.sign.length()-1; j++)
			{
				if(this.sign.charAt(j) == tf.getText().trim().charAt(i) || this.sign.toUpperCase().charAt(j) == tf.getText().trim().charAt(i))
				{
					check = true;
					break;
				}
				check = false;
			}
			if(check) continue;
			if(!((tf.getText().trim().charAt(i) >= 'a' && tf.getText().trim().charAt(i) <= 'z') || (tf.getText().trim().charAt(i) >= 'A' && tf.getText().trim().charAt(i) <= 'Z') || tf.getText().trim().charAt(i) == ' ')) return false;
		}
		return true;
	}
	
	public boolean checkOnlyNumber(JTextField tf)
	{
		for(int i=0; i<tf.getText().trim().length(); i++)
		{
			if(!(tf.getText().trim().charAt(i) >= '0' && tf.getText().trim().charAt(i) <= '9')) return false;
		}
		return true;
	}
	
	public boolean checkValidGmail(JTextField tf)
	{
		boolean result = true;
		try 
		{
			InternetAddress emailAddr = new InternetAddress(tf.getText().trim());
			emailAddr.validate();
		} 
		catch (AddressException ex) 
		{
	      result = false;
		}
		return result;
	}
	
	public boolean checkFloat(JTextField tf)
	{
		for(int i=0; i<tf.getText().trim().length(); i++)
		{
			if(!(tf.getText().trim().charAt(i) >= '0' && tf.getText().trim().charAt(i) <= '9' || tf.getText().trim().charAt(i) == '.')) return false;
		}
		return true;
	}
	
	public String checkItemAmountIllegal(String id, int amount)
	{
		this.items = ItemDao.getAllItem();
		for(Item i : this.items)
		{
			if(i.getItemId().equals(id)) if(amount > i.getAmount()) return i.getItemName().trim();
		}
		return "";
	}
	
	public ArrayList<String> splitDate(String date)
	{
		ArrayList<String> splitDate = new ArrayList<String>();
		String temp = "";
		for(int i=0; i<date.trim().length(); i++)
		{
			if(!(date.trim().charAt(i) == '/')) temp = temp + date.trim().charAt(i);
			else 
			{
				splitDate.add(temp);
				temp = "";
			}
		}
		splitDate.add(temp);
		return splitDate;
	}
	
	public void setUp()
	{
		Properties p = new Properties();
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		this.headerGoBack.setIcon(new ImageIcon("./icon_image/header.png"));
		this.headerGoBack.setBorder(BorderFactory.createRaisedBevelBorder());
		this.headerGoBack.setBackground(new Color(0x69b5b8));
		this.headerGoBack.setFocusable(false);
		this.headerGoBack.addActionListener(this);
		this.headerGoBack.setBounds(0, 1, 100, 49);
		this.tempButton.setToolTipText("Chọn hình ảnh");
		this.tempButton.setIcon(new ImageIcon("./icon_image/plus.png"));
		this.tempButton.setBorder(null);
		this.tempButton.setBackground(new Color(0xf5f7f7));
		this.tempButton.setFocusable(false);
		this.tempButton.setContentAreaFilled(false);
		this.tempButton.addActionListener(this);
		this.tempButton.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = tempTitle.getBounds();
		        tempButton.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));	
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
	}
	
	public void moveScreen(JPanel first, JPanel second, String direction)
	{
		if(first == null && second == null) return;
		int move = 0;
		second.setVisible(true);
		second.setBounds(1285, 0, 1285, 685);
		while(move < 1285)
		{
			if(move < 1200)
			{
				if(direction.equalsIgnoreCase("LEFT"))
				{
					first.setLocation(first.getLocation().x-600, first.getLocation().y);
					second.setLocation(second.getLocation().x-600, second.getLocation().y);
				}
				else if(direction.equalsIgnoreCase("RIGHT"))
				{
					first.setLocation(first.getLocation().x+600, first.getLocation().y);
					second.setLocation(second.getLocation().x+600, second.getLocation().y);
				}
				move = move + 600;
			}
			else
			{
				if(direction.equalsIgnoreCase("LEFT"))
				{
					first.setLocation(first.getLocation().x-85, first.getLocation().y);
					second.setLocation(second.getLocation().x-85, second.getLocation().y);
				}
				else if(direction.equalsIgnoreCase("RIGHT"))
				{
					first.setLocation(first.getLocation().x+85, first.getLocation().y);
					second.setLocation(second.getLocation().x+85, second.getLocation().y);
				}
				move = move + 85;
			}
			try 
			{
			    Thread.sleep(20);
			} 
			catch (InterruptedException ie) 
			{
			    Thread.currentThread().interrupt();
			}
		}
	}
	
	public void resetTemplate()
	{
		if(this.managementData != null)
		{
			this.serviceWindow.remove(this.managementData);
			this.scheduledWindow.remove(this.managementData);
			this.historyWindow.remove(this.managementData);
		}
		this.datePicker.getModel().setValue(null);
		this.timePicker.setSelectedIndex(-1);
		this.servicePicker.setSelectedIndex(-1);
		for(StarRating star: StarRating.stars)
		{
			star.clicked = false;
			star.star.setIcon(new ImageIcon("./icon_image/unrated.png"));
		}
		this.userName.setText("Tài khoản");
		this.password.setText("Mật khẩu");
		this.passwordHolder.setText("");
		this.comment.setText("");
		for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
		{
			int count = 0;
			if(customMouseListener.originalClickType == 1) customMouseListener.clickType = customMouseListener.originalClickType;
			for(CustomMouseListener subCustomMouseListener : CustomMouseListener.comps)
			{
				if(subCustomMouseListener.clickType == 4)
				{
					JTextField temp = (JTextField) subCustomMouseListener.comp;
					temp.setForeground(Color.gray);
					temp.setVisible(true);
					if(count == 0) temp.setText("Mật khẩu");
					else if(count == 1) temp.setText("Mật khẩu cũ");
					else if(count == 2) temp.setText("Mật khẩu mới");
					else temp.setText("Xác nhận mật khẩu mới");
					subCustomMouseListener.pwd.setText("");
					subCustomMouseListener.pwd.setVisible(false);
					count++;
				}
				else if(subCustomMouseListener.originalClickType == 5)
				{
					subCustomMouseListener.clickType = 5;
					customMouseListener.frame.managementPersonal.setVisible(true);
					customMouseListener.frame.line1.setVisible(true);
				}
			}
		}
	}
	
	public void refreshInfo(int menu, String hint)
	{
		this.menu = menu;
		this.search.setText("Tìm kiếm");
		this.search.setForeground(Color.gray);
		this.search.setToolTipText(hint);
		for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
		{
			if(customMouseListener.originalClickType == 1) customMouseListener.clickType = customMouseListener.originalClickType;
		}
		this.resetInfo();
	}
	
	public void resetInfo()
	{
		if(this.comp != null) this.managementDisplay.remove(this.comp);
		for(JComponent c : this.comps)
		{
			this.managementDisplay.remove(c);
		}
		this.comps.clear();
		this.infoOne.setVisible(false);
		this.infoTwo.setVisible(false);
		this.infoThree.setVisible(false);
		this.infoFour.setVisible(false);
		this.infoFive.setVisible(false);
		this.infoSix.setVisible(false);
		this.infoSeven.setVisible(false);
		this.infoEight.setVisible(false);
		this.infoNine.setVisible(false);
		this.infoTen.setVisible(false);
		this.infoEleven.setVisible(false);
		//THÊM
		this.infoTwelve.setVisible(false);
	}
	
	public void createCustomerPicker(JComboBox<String> cbb)
	{
		ArrayList<CustomerPersonal> lst = CustomerPersonalDao.getAllCustomer();	
		for(CustomerPersonal s : lst)
		{
			cbb.addItem(s.getId() + " - " + s.getSurname().trim() + " " + s.getName().trim());
		}
	}
	
	public void createItemPicker(JComboBox<String> cbb)
	{
		ArrayList<Item> lst = ItemDao.getAllItem();	
		for(Item i : lst)
		{
			cbb.addItem(i.getItemName().trim());
		}
	}
	
	public void createServicePricePicker(JComboBox<String> cbb)
	{
		ArrayList<Service> lst = ServiceDao.getAllService();	
		this.services = new ArrayList<Service>();
		for(Service s : lst)
		{
			cbb.addItem("Tên: " + s.getName().trim() + " - Giá: " + this.numberFormat.format(s.getPrice()));
			this.services.add(s);
		}
	}
	
	public void createServicePicker(JComboBox<String> cbb)
	{
		ArrayList<Service> lst = ServiceDao.getAllService();	
		for(Service s : lst)
		{
			cbb.addItem(s.getName().trim());
		}
	}
	
	
	public void createTimePicker(JComboBox<String> cbb)
	{
		String[] minutes = new String[] {"00", "30"};
		for(int i=7; i<22; i++)
		{
			for(int j=0; j<minutes.length; j++)
			{
				cbb.addItem(String.valueOf(i) + ":" + minutes[j]);
			}
		}
	}
	
	public void createMonthPicker(JComboBox<String> cbb)
	{
		for(int i=1; i<=12; i++)
		{
			cbb.addItem(String.valueOf(i));
		}
	}
	
	public void createYearPicker(JComboBox<String> cbb)
	{
		for(int i=2020; i<=Calendar.getInstance().get(Calendar.YEAR); i++)
		{
			cbb.addItem(String.valueOf(i));
		}
	}
	
	public void createIconTable(JPanel panel, String[][] rows, String[] columns, String icon1, String icon2, int textPosition, Boolean setHeader, int fontSize, int rowHeight, Color borderColor, Color selectColor, int x, int y, int width, int height, Boolean isShowGrid, Boolean isSelected)
	{
		this.data = new JTable();
		JTableHeader tableHeader = this.data.getTableHeader();
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		this.data.setModel(new DefaultTableModel(rows, columns) 
		{
			private static final long serialVersionUID = -6507730735173809287L;
			public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return false;
            }
        });
		TableActionEvent event = new TableActionEvent() 
		{
            @Override
            public void onFirstAction(int row) 
            {
            	if(menu == 1)
        		{
            		AccountDao.updateStatus(data.getValueAt(row, 0).toString().trim(), 1);
        			refreshAccount(AccountDao.getAllAccount(2), 2);
        		}
        		else if(menu == 2)
        		{
        			AccountDao.updateStatus(data.getValueAt(row, 0).toString().trim(), 1);
        			refreshAccount(AccountDao.getAllAccount(3), 3);
        		}
        		else if(menu == 3)
        		{
        			refreshInfo(32, "");
        			refreshUpdateItem(ItemDao.getOneItemById(data.getValueAt(row, 0).toString().trim()));
        			managementTwo.setText("Quay lại");
        		}
        		else if(menu == 4)
        		{
        			Service s = ServiceDao.getOneService(data.getValueAt(row, 0).toString().trim());
        			ArrayList<Formula> lst = FormulaDao.getServiceFormula(data.getValueAt(row, 0).toString().trim());
        			String name = s.getName().trim();
        			String price = String.valueOf(numberFormat.format(s.getPrice()).substring(0, String.valueOf(numberFormat.format(s.getPrice())).length()-2)).replace(".", "");
        			menu = 42;
        			tempId = data.getValueAt(row, 0).toString().trim();
    				refreshUpdateServiceFormula(lst, name, price, new Color[] {null, Color.black, Color.black, null, null, Color.gray}, false);
        			managementOne.setText("Thêm sản phẩm");
    				managementTwo.setText("Quay lại");
        		}
        		else if(menu == 33)
        		{
        			tempId = data.getValueAt(row, 0).toString().trim();
        			refreshInfo(333, "");
        			refreshImportExportDetail(ImportExportDetailDao.getAllById(tempId));
        		}
        		else if(menu == 5)
        		{
        			ScheduleDao.confirmSchedule(data.getValueAt(row, 0).toString().trim(), StaffDao.getOneStaffByAccount(acc.trim()).getStaffId().trim());
        			refreshSchedule(ScheduleDao.getScheduleByStatus(managementPickerTwo.getSelectedIndex()), managementPickerTwo.getSelectedIndex());
        		}
        		else if(menu == 6)
        		{
        			tempId = data.getValueAt(row, 0).toString().trim();
        			managementTwo.setText("Quay lại");
        			refreshInfo(61, "Tìm tên dịch vụ");
        			refreshReceiptDetail(ReceiptDetailDao.getAllById(tempId));
        		}
        		else if(menu == 63)
        		{
        			menu = 632;
        			String money = String.valueOf(numberFormat.format(DiscountDao.getOneDiscountByLevel(Integer.parseInt(data.getValueAt(row, 0).toString().replace("%", ""))).getMoneyLevel()));
        			refreshUpdateDiscount(money.substring(0, money.length()-2).replace(".", ""));
        		}
            }

            @Override
            public void onSecondAction(int row) 
            {
            	if(role == 3)
            	{
            		if(menu == 9)
            		{
            			int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn bỏ lịch hẹn này ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
        				if(choice == 0)
        				{
        					if(ScheduleDao.cancelSchedule(data.getValueAt(row, 0).toString().trim(), null))
                    		{
                    			if(data.isEditing()) data.getCellEditor().stopCellEditing();
                                DefaultTableModel model = (DefaultTableModel) data.getModel();
                                model.removeRow(row);
                    		}
        				}
            		}
            		else if(menu == 10)
            		{
            			int choice = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn hủy lịch hẹn này ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
        				if(choice == 0)
        				{
        					if(ScheduleDao.cancelSchedule(data.getValueAt(row, 0).toString().trim(), null))
                    		{
                    			if(data.isEditing()) data.getCellEditor().stopCellEditing();
                                DefaultTableModel model = (DefaultTableModel) data.getModel();
                                model.removeRow(row);
                    		}
        				}
            		}
    				return;
            	}
            	if(menu == 1)
        		{
            		int choice = JOptionPane.showOptionDialog(null, "Có chắc chắn muốn khóa tài khoản này ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
    				if(choice == 0)
    				{
    					AccountDao.updateStatus(data.getValueAt(row, 0).toString().trim(), 0);
            			refreshAccount(AccountDao.getAllAccount(2), 2);
    				}
        		}
        		else if(menu == 2)
        		{
        			int choice = JOptionPane.showOptionDialog(null, "Có chắc chắn muốn khóa tài khoản này ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
    				if(choice == 0)
    				{
    					AccountDao.updateStatus(data.getValueAt(row, 0).toString().trim(), 0);
            			refreshAccount(AccountDao.getAllAccount(3), 3);
    				}
        		}
        		else if(menu == 4)
        		{
            		int choice = JOptionPane.showOptionDialog(null, "Có chắc chắn muốn xóa ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
    				if(choice == 0)
    				{
            			if(ServiceDao.checkReceiptExist(data.getValueAt(row, 0).toString().trim()) == 2)
            			{
            				JOptionPane.showOptionDialog(null, "Không thể xóa dịch vụ này vì đã được lập hóa đơn !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
    						return;
            			}
            			if(ServiceDao.checkScheduleExist(data.getValueAt(row, 0).toString().trim()) == 2)
            			{
            				JOptionPane.showOptionDialog(null, "Không thể xóa dịch vụ này vì đã được lập lịch hẹn !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
    						return;
            			}
            			if(ServiceDao.checkRatingExist(data.getValueAt(row, 0).toString().trim()) == 2)
            			{
            				JOptionPane.showOptionDialog(null, "Không thể xóa dịch vụ này vì đã có khách hàng đánh giá !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
    						return;
            			}
    					if(!FormulaDao.deleteFormula(data.getValueAt(row, 0).toString().trim())) return;
    					if(!ServiceDao.deleteService(data.getValueAt(row, 0).toString().trim())) return;
    					if(data.isEditing()) data.getCellEditor().stopCellEditing();
                        DefaultTableModel model = (DefaultTableModel) data.getModel();
                        model.removeRow(row);
    					JOptionPane.showOptionDialog(null, "Xóa dịch vụ thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
    				}
        		}
        		else if(menu == 331 || menu == 332 || menu == 41 || menu == 42 || menu == 62 || menu==101)
            	{
            		DefaultTableModel model = (DefaultTableModel) data.getModel();
                    model.removeRow(row);
            	}
        		else if(menu == 5)
        		{
        			ScheduleDao.cancelSchedule(data.getValueAt(row, 0).toString().trim(), StaffDao.getOneStaffByAccount(acc.trim()).getStaffId().trim());
        			refreshSchedule(ScheduleDao.getScheduleByStatus(managementPickerTwo.getSelectedIndex()), managementPickerTwo.getSelectedIndex());
        		}
        		else if(menu == 63)
        		{
        			int choice = JOptionPane.showOptionDialog(null, "Có chắc chắn muốn xóa ?", "XÁC NHẬN XÓA DỮ LIỆU", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Có", "Không"}, 1);
    				if(choice == 0)
    				{
    					if(DiscountDao.deleteDiscount(Integer.parseInt(data.getValueAt(row, 0).toString().trim().replace("%", ""))))
    					{
    						DefaultTableModel model = (DefaultTableModel) data.getModel();
                            model.removeRow(row);
        					JOptionPane.showOptionDialog(null, "Xóa ưu đãi thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
    					}
    				}
        		}
            }
        };
		GUI.managementIcon1 = icon1;
		GUI.managementIcon2 = icon2;
		this.data.setModel(new DefaultTableModel(rows, columns) 
		{
			private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return false || columnIndex == columns.length-1;
            }
        });
		rightRenderer.setHorizontalAlignment(textPosition);
		for(int i=0; i<this.data.getColumnCount(); i++)
		{
			this.data.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
	    }
		if(!setHeader) this.data.setTableHeader(null);
		this.data.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		this.data.setSelectionBackground(selectColor);
		this.data.setShowGrid(isShowGrid);
		this.data.setFocusable(isSelected);
		this.data.setOpaque(false);
		this.data.setRowHeight(rowHeight);
		this.data.getColumnModel().getColumn(columns.length-1).setCellRenderer(new TableActionCellRender());
		this.data.getColumnModel().getColumn(columns.length-1).setCellEditor(new TableActionCellEditor(event));
		this.data.setAutoCreateRowSorter(true);
		this.data.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
	        public void valueChanged(ListSelectionEvent event) 
	        {
            	try
            	{
            		if(role == 3) return;
            		if(menu == 1)
                	{
                		Staff s = StaffDao.getOneStaffByAccount(data.getValueAt(data.getSelectedRow(), 0).toString().trim());
        				managementOne.setText("Sửa thông tin");
        				managementOne.setVisible(true);
                		infoOne.setText(" Mã nhân viên: " + s.getStaffId() + " ");
                		infoOne.setVisible(true);
                		infoTwo.setText(" Họ: " + s.getSurname() + " ");
                		infoTwo.setVisible(true);
                		infoThree.setText(" Tên: " + s.getName() + " ");
                		infoThree.setVisible(true);
                		infoFour.setText(" Ngày sinh: " + s.getBirthDate() + " ");
                		infoFour.setVisible(true);
                		infoFive.setText(" Gmail: " + s.getGmail() + " ");
                		infoFive.setVisible(true);
                		infoSix.setText(" Số điện thoại: " + s.getPhoneNumber() + " ");
                		infoSix.setVisible(true);
                		infoSeven.setText(" Phái: " + s.getSex() + " ");
                		infoSeven.setVisible(true);
                		infoEight.setText(" Cmnd/Cccd: " + s.getId() + " ");
                		infoEight.setVisible(true);
                		infoNine.setText(" Quê quán: " + s.getHomeTown() + " ");
                		infoNine.setVisible(true);
                		infoTen.setText(" Ngày vào làm: " + s.getDayWork() + " ");
                		infoTen.setVisible(true);
                	}
                	else if(menu == 2)
                	{
                		CustomerPersonal cp = CustomerPersonalDao.getOnePersonalByAccount(data.getValueAt(data.getSelectedRow(), 0).toString().trim());
                		infoOne.setText(" Mã khách hàng: " + cp.getId() + " ");
                		infoOne.setVisible(true);
                		infoTwo.setText(" Họ: " + cp.getSurname() + " ");
                		infoTwo.setVisible(true);
                		infoThree.setText(" Tên: " + cp.getName() + " ");
                		infoThree.setVisible(true);
                		infoFive.setText(" Gmail: " + cp.getGmail() + " ");
                		infoFive.setVisible(true);
                		infoSix.setText(" Số điện thoại: " + cp.getPhoneNumber() + " ");
                		infoSix.setVisible(true);
                	}
                	else if(menu == 3)
                	{
                		Item i = ItemDao.getOneItemById(data.getValueAt(data.getSelectedRow(), 0).toString().trim()); 
                		JPanel temp = new JPanel();
                		JLabel temp2 = new JLabel();
                		if(comp != null) managementDisplay.remove(comp);
                		try 
                		{
                			if(!i.getImage().trim().isEmpty())
                    		{
                				temp.setBounds(810, 10, 150, 150);
        						temp2.setIcon(new ImageIcon(ImageIO.read(new File(i.getImage().trim())).getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
        						temp2.setBounds(0, 0, 150, 150);
                    		}
                			else
                			{
                				temp.setBounds(810, 10, 150, 150);
        						temp2.setIcon(new ImageIcon(ImageIO.read(new File("./icon_image/unavailable.png")).getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
        						temp2.setBounds(0, 0, 140, 140);
                			}
    					} 
                		catch (IOException e) 
                		{
                			JOptionPane.showOptionDialog(null, "Tải hình ảnh thất bại !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
                			temp.setBounds(810, 10, 150, 150);
    						temp2.setIcon(null);
    						temp2.setBounds(0, 0, 150, 150);
                		}
                		finally
        				{
        					temp.add(temp2);
            				temp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.gray, Color.lightGray));
            				comp = temp;
                    		managementDisplay.add(temp);
        				}
                	}
                	else if(menu == 5 || menu == 6)
                	{
                		if(data.getValueAt(data.getSelectedRow(), 1) == null)
                		{
                			refreshInfo(menu, "");
                			infoFive.setText(" Không có thông tin của khách hàng ");
                    		infoFive.setVisible(true);
                    		return;
                		}
                		CustomerPersonal cp = CustomerPersonalDao.getOnePersonalById(data.getValueAt(data.getSelectedRow(), 1).toString().trim());
                		infoOne.setText(" Mã khách hàng: " + cp.getId() + " ");
                		infoOne.setVisible(true);
                		infoTwo.setText(" Họ: " + cp.getSurname() + " ");
                		infoTwo.setVisible(true);
                		infoThree.setText(" Tên: " + cp.getName() + " ");
                		infoThree.setVisible(true);
                		infoFive.setText(" Gmail: " + cp.getGmail() + " ");
                		infoFive.setVisible(true);
                		infoSix.setText(" Số điện thoại: " + cp.getPhoneNumber() + " ");
                		infoSix.setVisible(true);
                	}
            	}
            	catch(IndexOutOfBoundsException e)
            	{
            		return;
            	}
            }
	    });
		tableHeader.setFont(new Font("Consolas", Font.BOLD, 15));
		tableHeader.setForeground(new Color(0x8cfad4));
		tableHeader.setBackground(new Color(0x429e86));
		this.managementData = new JScrollPane(this.data);
		this.managementData.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		this.managementData.getViewport().setBackground(Color.white);
		this.managementData.setBounds(x, y, width, height);
		panel.add(this.managementData);
	}
	
	public void createNonIconTable(JPanel panel, String[][] rows, String[] columns, int textPosition, Boolean setHeader, int fontSize, int rowHeight, Color borderColor, Color selectColor, int x, int y, int width, int height, Boolean isShowGrid, Boolean isSelected)
	{
		this.data = new JTable();
		JTableHeader tableHeader = this.data.getTableHeader();
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		this.data.setModel(new DefaultTableModel(rows, columns) 
		{
			private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return false;
            }
        });
		rightRenderer.setHorizontalAlignment(textPosition);
		for(int i=0; i<this.data.getColumnCount(); i++)
		{
			this.data.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
	    }
		if(!setHeader) this.data.setTableHeader(null);
		this.data.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		this.data.setSelectionBackground(selectColor);
		this.data.setShowGrid(isShowGrid);
		this.data.setFocusable(isSelected);
		this.data.setOpaque(false);
		this.data.setRowHeight(rowHeight);
		this.data.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
	        public void valueChanged(ListSelectionEvent event) 
	        { 
	        	if(menu == 2)
            	{
            		CustomerPersonal cp = CustomerPersonalDao.getOnePersonalByAccount(data.getValueAt(data.getSelectedRow(), 0).toString().trim());
            		infoOne.setText(" Mã khách hàng: " + cp.getId() + " ");
            		infoOne.setVisible(true);
            		infoTwo.setText(" Họ: " + cp.getSurname() + " ");
            		infoTwo.setVisible(true);
            		infoThree.setText(" Tên: " + cp.getName() + " ");
            		infoThree.setVisible(true);
            		infoFive.setText(" Gmail: " + cp.getGmail() + " ");
            		infoFive.setVisible(true);
            		infoSix.setText(" Số điện thoại: " + cp.getPhoneNumber() + " ");
            		infoSix.setVisible(true);
            	}
	        	else if(menu == 3)
            	{
            		Item i = ItemDao.getOneItemById(data.getValueAt(data.getSelectedRow(), 0).toString().trim()); 
            		JPanel temp = new JPanel();
            		JLabel temp2 = new JLabel();
            		if(comp != null) managementDisplay.remove(comp);
            		try 
            		{
            			if(!i.getImage().trim().isEmpty())
                		{
            				temp.setBounds(810, 10, 150, 150);
    						temp2.setIcon(new ImageIcon(ImageIO.read(new File(i.getImage().trim())).getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
    						temp2.setBounds(0, 0, 150, 150);
                		}
            			else
            			{
            				temp.setBounds(810, 10, 150, 150);
    						temp2.setIcon(new ImageIcon(ImageIO.read(new File("./icon_image/unavailable.png")).getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
    						temp2.setBounds(0, 0, 140, 140);
            			}
					} 
            		catch (IOException e) 
            		{
            			JOptionPane.showOptionDialog(null, "Tải hình ảnh thất bại !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
              			temp.setBounds(810, 10, 150, 150);
						temp2.setIcon(null);
						temp2.setBounds(0, 0, 150, 150);
					}
    				finally
    				{
    					temp.add(temp2);
        				temp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.gray, Color.lightGray));
        				comp = temp;
                		managementDisplay.add(temp);
    				}
            	}
	        	else if(menu == 4)
            	{
            		tempId = data.getValueAt(data.getSelectedRow(), 0).toString();
            		managementOne.setText("Xem đánh giá");
            		managementOne.setVisible(true);
            	}
	        	else if(menu == 43)
	        	{
	        		CustomerPersonal cp = CustomerPersonalDao.getOnePersonalById(RatingDao.getRatingCustomerId(data.getValueAt(data.getSelectedRow(), 0).toString().trim()).trim());
            		infoOne.setText(" Mã khách hàng: " + cp.getId() + " ");
            		infoOne.setVisible(true);
            		infoTwo.setText(" Họ: " + cp.getSurname() + " ");
            		infoTwo.setVisible(true);
            		infoThree.setText(" Tên: " + cp.getName() + " ");
            		infoThree.setVisible(true);
            		infoFive.setText(" Gmail: " + cp.getGmail() + " ");
            		infoFive.setVisible(true);
            		infoSix.setText(" Số điện thoại: " + cp.getPhoneNumber() + " ");
            		infoSix.setVisible(true);
	        	}
	        	else if(menu == 5)
            	{
            		if(role == 1 || role == 2)
            		{
            			CustomerPersonal cp = CustomerPersonalDao.getOnePersonalById(data.getValueAt(data.getSelectedRow(), 1).toString().trim());
                		infoOne.setText(" Mã khách hàng: " + cp.getId() + " ");
                		infoOne.setVisible(true);
                		infoTwo.setText(" Họ: " + cp.getSurname() + " ");
                		infoTwo.setVisible(true);
                		infoThree.setText(" Tên: " + cp.getName() + " ");
                		infoThree.setVisible(true);
                		infoFive.setText(" Gmail: " + cp.getGmail() + " ");
                		infoFive.setVisible(true);
                		infoSix.setText(" Số điện thoại: " + cp.getPhoneNumber() + " ");
                		infoSix.setVisible(true);
            		}
            	}
            }
	    });
		tableHeader.setFont(new Font("Consolas", Font.BOLD, 15));
		tableHeader.setBorder(BorderFactory.createLineBorder(new Color(0xede0dd)));
		tableHeader.setForeground(new Color(0x8cfad4));
		tableHeader.setBackground(new Color(0x429e86));
		this.managementData = new JScrollPane(this.data);
		this.managementData.setBorder(BorderFactory.createLineBorder(borderColor, 2));
		this.managementData.getViewport().setBackground(Color.white);
		this.managementData.setBounds(x, y, width, height);
		panel.add(this.managementData);
	}
	
	public String getServiceId(String serviceName)
	{
		for(Service s : this.services)
		{
			if(s.getName().trim().equalsIgnoreCase(serviceName.trim()))
			{
				return s.getId().trim();
			}
		}
		return "";
	}
	
	public void dispayLogin(Boolean isDisplayed)
	{
		if(isDisplayed)
		{
			Staff s = StaffDao.getOneStaffByAccount("sa");
			this.managerFullName.setText(s.getSurname().trim() + " " + s.getName().trim() + " (Họ tên quản lý)");
			this.managerPhoneNumber.setText(s.getPhoneNumber().trim() + " (Số điện thoại liên hệ)");
			this.managerGmail.setText(s.getGmail().trim() + " (Gmail cá nhân)");
		}
		this.managerFullName.setVisible(isDisplayed);
		this.managerPhoneNumber.setVisible(isDisplayed);
		this.managerGmail.setVisible(isDisplayed);
		this.title.setVisible(isDisplayed);
		this.userName.setVisible(isDisplayed);
		this.userName.setForeground(new Color(0x55515c));
		this.password.setVisible(isDisplayed);
		this.password.setForeground(new Color(0x55515c));
		this.passwordHolder.setVisible(false);
		this.login.setVisible(isDisplayed);
		this.signupAccount.setVisible(isDisplayed);
		this.resetPassword.setVisible(isDisplayed);
	}
	
	//GHI CHU
	public void displayAll(Boolean[] states)
	{
		if(this.managementData != null) this.managementDisplay.remove(this.managementData);
		this.searchIcon.setVisible(states[0]);
		this.search.setVisible(states[1]);
		this.managementAdd.setVisible(states[2]);
		this.managementUpdate.setVisible(states[3]);
		this.managementOne.setVisible(states[4]);
		this.managementTwo.setVisible(states[5]);
		this.managementPickerInfo.setVisible(states[6]);
		this.managementPickerOne.setVisible(states[7]);
		this.managementPickerTwo.setVisible(states[8]);
		this.upManagementTitle.setVisible(states[9]);
		this.downManagementTitle.setVisible(states[10]);
	}
	
	public void showAll(String[] Info)
	{
		this.upManagementTitle.setText(Info[0]);
		this.downManagementTitle.setText(Info[1]);
	}
	
	public void placeAll(int[][] coordinate)
	{
		this.upManagementTitle.setBounds(coordinate[0][0], coordinate[0][1],coordinate[0][2], coordinate[0][3]);
		this.downManagementTitle.setBounds(coordinate[1][0], coordinate[1][1],coordinate[1][2], coordinate[1][3]);
	}
	
	public void showLogin(String fullName, String gmail, String phoneNumber)
	{
		this.managerFullName.setText(fullName + " (Họ tên quản lý)");
		this.managerFullName.setIcon(new ImageIcon("./icon_image/Briefcase.png"));
		this.managerFullName.setIconTextGap(15);
		this.managerFullName.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		this.managerFullName.setHorizontalAlignment(JLabel.LEFT);
		this.managerFullName.setVerticalAlignment(JLabel.CENTER);
		this.managerFullName.setForeground(new Color(0x55515c));
		this.managerFullName.setBounds(850, 30, 500, 24);
		this.managerPhoneNumber.setText(phoneNumber + " (Số điện thoại liên hệ)");
		this.managerPhoneNumber.setIcon(new ImageIcon("./icon_image/Dial.png"));
		this.managerPhoneNumber.setIconTextGap(15);
		this.managerPhoneNumber.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		this.managerPhoneNumber.setHorizontalAlignment(JLabel.LEFT);
		this.managerPhoneNumber.setVerticalAlignment(JLabel.CENTER);
		this.managerPhoneNumber.setForeground(new Color(0x55515c));
		this.managerPhoneNumber.setBounds(850, 55, 500, 24);
		this.managerGmail.setText(gmail + " (Gmail cá nhân)");
		this.managerGmail.setIcon(new ImageIcon("./icon_image/Bubble.png"));
		this.managerGmail.setIconTextGap(15);
		this.managerGmail.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		this.managerGmail.setHorizontalAlignment(JLabel.LEFT);
		this.managerGmail.setVerticalAlignment(JLabel.CENTER);
		this.managerGmail.setForeground(new Color(0x55515c));
		this.managerGmail.setBounds(850, 80, 500, 24);
		this.title.setText("ĐĂNG NHẬP TÀI KHOẢN");
		this.title.setIcon(new ImageIcon("./icon_image/login.png"));
		this.title.setIconTextGap(3);
		this.title.setVerticalTextPosition(JLabel.BOTTOM);
		this.title.setFont(new Font("Courier New", Font.BOLD, 20));
		this.title.setVerticalTextPosition(JLabel.CENTER);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setVerticalAlignment(JLabel.BOTTOM);
		this.title.setForeground(new Color(0x000000));
		this.title.setBounds(460, 450, 350, 40);
		this.userName.setText("Tài khoản");
		this.userName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.userName.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.darkGray));
		this.userName.setForeground(new Color(0x55515c));
		this.userName.setOpaque(false);
		this.userName.addMouseListener(new CustomMouseListener(this.userName, null, this, null, null, new Color(0x55515c), 1, 1));
		this.userName.addKeyListener(this);
		this.userName.setBounds(510, 505, 255, 30);
		this.password.setText("Mật khẩu");
		this.password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.password.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.darkGray));
		this.password.setForeground(new Color(0x55515c));
		this.password.setOpaque(false);
		this.password.addMouseListener(new CustomMouseListener(this.password, this.passwordHolder, this, null, null, null, 1, 1));
		this.password.addKeyListener(this);
		this.password.setBounds(510, 540, 255, 30);
		this.passwordHolder.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.passwordHolder.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.darkGray));
		this.passwordHolder.setOpaque(false);
		this.passwordHolder.addKeyListener(this);
		this.passwordHolder.setFocusTraversalKeysEnabled(false);
		this.passwordHolder.setVisible(false);
		this.passwordHolder.setBounds(510, 540, 255, 30);
		this.login.setText("ĐĂNG NHẬP");
		this.login.setFont(new Font("Consolas", Font.BOLD, 15));
		this.login.setBorder(BorderFactory.createRaisedBevelBorder());
		this.login.setForeground(new Color(0x111111));
		this.login.setBackground(new Color(0x51cfa3));
		this.login.setFocusable(false);
		this.login.addActionListener(this);
		this.login.setBounds(588, 580, 100, 30);
		this.signupAccount.setText("<HTML><u>Đăng ký tài khoản</u></HTML>");
		this.signupAccount.setFont(new Font("Consolas", Font.BOLD, 14));
		this.signupAccount.setHorizontalAlignment(JLabel.LEFT);
		this.signupAccount.setVerticalAlignment(JLabel.BOTTOM);
		this.signupAccount.setForeground(new Color(0x990e45));
		this.signupAccount.addMouseListener(new CustomMouseListener(this.signupAccount, null, this, Color.black, new Color(0x990e45), new Color(0x5e6cc4), 2, 2));
		this.signupAccount.setBounds(570, 605, 150, 30);		
		this.add(this.managerFullName);
		this.add(this.managerGmail);
		this.add(this.managerPhoneNumber);
		this.add(this.title);
		this.add(this.userName);
		this.add(this.password);
		this.add(this.passwordHolder);
		this.add(this.login);
		this.add(this.signupAccount);
	}
	
	public void showSignup()
	{
		JPanel signupFrame = new JPanel();
		JLabel title = new JLabel();
		JLabel subTitle = new JLabel();
		JLabel icon = new JLabel();
		JTextField[] inputs = new JTextField[] {this.newUserName, this.surname, this.name, this.mail, this.phoneNumber, this.newPassword};
		String[] components = new String[] {"Tên tài khoản", "Họ", "Tên", "Gmail", "Số điện thoại", "Mật khẩu"};
		String[] icons = new String[] {"./icon_image/account.png", "./icon_image/surname.png", "./icon_image/name.png", "./icon_image/mail.png", "./icon_image/phonenumber.png", "./icon_image/password.png"};
		int height = 95;
		this.signupWindow.setLayout(null);
		this.signupWindow.setBackground(new Color(0xc5dbcc));
		this.signupWindow.setBounds(0, 0, 1285, 685);
		this.signupWindow.setVisible(false);
		signupFrame.setLayout(null);
		signupFrame.setBackground(Color.white);
		signupFrame.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x69b5b8)));
		signupFrame.setBounds(485, 190, 300, 440);
		title.setText(" ĐĂNG KÝ TÀI KHOẢN");
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(0, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		subTitle.setText("Tạo tài khoản");
		subTitle.setIcon(new ImageIcon("./icon_image/signup.png"));
		subTitle.setIconTextGap(10);
		subTitle.setVerticalTextPosition(JLabel.BOTTOM);
		subTitle.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setVerticalAlignment(JLabel.BOTTOM);
		subTitle.setForeground(new Color(0x5e85bd));
		subTitle.setBounds(50, 20, 190, 50);
		signupFrame.add(subTitle);
		for(int i=0; i<inputs.length; i++)
		{
			JLabel container = new JLabel();
			container.setIcon(new ImageIcon(icons[i]));
			container.setBorder(new RoundedBorder(30));
			container.setBounds(20, height, 260, 40);
			if(i == inputs.length-1)
			{
				JTextField temp = new JTextField();
				temp.setText(components[i]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setBorder(BorderFactory.createLineBorder(Color.white));
				temp.setForeground(Color.gray);
				temp.addMouseListener(new CustomMouseListener(temp, (JPasswordField) inputs[i], this, null, null, Color.black, 4, 4));
				temp.addKeyListener(this);
				temp.setBounds(80, height+5, 190, 30);
				inputs[i].setFont(new Font("Times New Roman", Font.PLAIN, 15));
				inputs[i].setBorder(BorderFactory.createLineBorder(Color.white));
				inputs[i].addMouseListener(new CustomMouseListener(inputs[i], null, this, null, null, Color.black, 1, 1));
				inputs[i].addKeyListener(this);
				inputs[i].setBounds(80, height+5, 190, 30);
				inputs[i].setVisible(false);
				signupFrame.add(container);
				signupFrame.add(temp);
				signupFrame.add(inputs[i]);
				height = height + 50;
				break;
			}
			inputs[i].setText(components[i]);
			inputs[i].setFont(new Font("Times New Roman", Font.PLAIN, 15));
			inputs[i].setBorder(BorderFactory.createLineBorder(Color.white));
			inputs[i].setForeground(Color.gray);
			inputs[i].addMouseListener(new CustomMouseListener(inputs[i], null, this, null, null, Color.black, 1, 1));
			inputs[i].addKeyListener(this);
			inputs[i].setBounds(80, height+5, 190, 30);
			signupFrame.add(container);
			signupFrame.add(inputs[i]);
			height = height + 50;
		}
		this.signup.setText("ĐĂNG KÝ");
		this.signup.setFont(new Font("Consolas", Font.BOLD, 15));
		this.signup.setBorder(BorderFactory.createRaisedBevelBorder());
		this.signup.setForeground(Color.white);
		this.signup.setBackground(new Color(0x69b5b8));
		this.signup.setFocusable(false);
		this.signup.addActionListener(this);
		this.signup.setBounds(30, height, 100, 30);
		this.signupGoBack.setText("QUAY LẠI");
		this.signupGoBack.setFont(new Font("Consolas", Font.BOLD, 15));
		this.signupGoBack.setBorder(BorderFactory.createRaisedBevelBorder());
		this.signupGoBack.setForeground(Color.white);
		this.signupGoBack.setBackground(new Color(0x69b5b8));
		this.signupGoBack.setFocusable(false);
		this.signupGoBack.addActionListener(this);
		this.signupGoBack.setBounds(170, height, 100, 30);
		signupFrame.add(this.signup);
		signupFrame.add(this.signupGoBack);
		this.signupWindow.add(signupFrame);
		this.signupWindow.add(title);
		this.signupWindow.add(icon);
		this.add(this.signupWindow);
	}
	
	public void showResetMail()
	{
		JPanel resetMailFrame = new JPanel();
		JLabel title = new JLabel();
		JLabel subTitle = new JLabel();
		JLabel icon = new JLabel();
		JLabel container = new JLabel();
		this.resetMailWindow.setLayout(null);
		this.resetMailWindow.setBackground(new Color(0xc5dbcc));
		this.resetMailWindow.setBounds(0, 0, 1285, 685);
		this.resetMailWindow.setVisible(false);
		resetMailFrame.setLayout(null);
		resetMailFrame.setBackground(Color.white);
		resetMailFrame.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x69b5b8)));
		resetMailFrame.setBounds(485, 210, 300, 165);
		title.setText(" HỒI PHỤC MẬT KHẨU");
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(0, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		subTitle.setText("Xác thực tài khoản");
		subTitle.setIcon(new ImageIcon("./icon_image/resetpassword.png"));
		subTitle.setIconTextGap(10);
		subTitle.setVerticalTextPosition(JLabel.BOTTOM);
		subTitle.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setVerticalAlignment(JLabel.BOTTOM);
		subTitle.setForeground(new Color(0x5e85bd));
		subTitle.setBounds(25, 10, 250, 40);
		container.setIcon(new ImageIcon("./icon_image/account.png"));
		container.setBorder(new RoundedBorder(30));
		container.setBounds(20, 70, 260, 40);
		this.restoreUserName.setText("Tên tài khoản");
		this.restoreUserName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.restoreUserName.setBorder(BorderFactory.createLineBorder(Color.white));
		this.restoreUserName.setForeground(Color.gray);
		this.restoreUserName.addMouseListener(new CustomMouseListener(this.restoreUserName, null, this, null, null, Color.black, 1, 1));
		this.restoreUserName.addKeyListener(this);
		this.restoreUserName.setBounds(80, 75, 190, 30);
		this.resetMail.setText("HỒI PHỤC");
		this.resetMail.setFont(new Font("Consolas", Font.BOLD, 15));
		this.resetMail.setBorder(BorderFactory.createRaisedBevelBorder());
		this.resetMail.setForeground(Color.white);
		this.resetMail.setBackground(new Color(0x69b5b8));
		this.resetMail.setFocusable(false);
		this.resetMail.addActionListener(this);
		this.resetMail.setBounds(30, 125, 100, 30);
		this.resetMailGoBack.setText("QUAY LẠI");
		this.resetMailGoBack.setFont(new Font("Consolas", Font.BOLD, 15));
		this.resetMailGoBack.setBorder(BorderFactory.createRaisedBevelBorder());
		this.resetMailGoBack.setForeground(Color.white);
		this.resetMailGoBack.setBackground(new Color(0x69b5b8));
		this.resetMailGoBack.setFocusable(false);
		this.resetMailGoBack.addActionListener(this);
		this.resetMailGoBack.setBounds(170, 125, 100, 30);
		resetMailFrame.add(subTitle);
		resetMailFrame.add(container);
		resetMailFrame.add(this.restoreUserName);
		resetMailFrame.add(this.resetMail);
		resetMailFrame.add(this.resetMailGoBack);
		this.resetMailWindow.add(resetMailFrame);
		this.resetMailWindow.add(title);
		this.resetMailWindow.add(icon);
		this.add(this.resetMailWindow);
	}
	
	public void showManagement()
	{
		JPanel headerDisplay = new JPanel();
		JPanel tempHeaderDisplay = new JPanel();
		JPanel tempDisplay = new JPanel();
		JPanel bannerContainer1 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JPanel bannerContainer2 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JPanel bannerContainer3 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JPanel tempBannerContainer1 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JPanel tempBannerContainer2 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JPanel tempBannerContainer3 = new SubjectIntro(new ImageIcon("./icon_image/nam12.png").getImage());
		JLabel title = new JLabel();
		JLabel tempTitle = new JLabel();
		JLabel line2 = new JLabel();
		JLabel icon = new JLabel();
		JLabel managementTitle = new JLabel();
		JLabel personalInformation = new JLabel();
		JLabel contactInformation = new JLabel();
		JLabel accountInformation = new JLabel();
		JLabel surname = new JLabel();
		JLabel name = new JLabel();
		JLabel sex = new JLabel();
		JLabel id = new JLabel();
		JLabel birthDate = new JLabel();
		JLabel homeTown = new JLabel();
		JLabel gmail = new JLabel();
		JLabel phoneNumber = new JLabel();
		JLabel password = new JLabel();
		JLabel newPassword = new JLabel();
		JLabel newPasswordConfirm = new JLabel();
		JButton[] inputs = new JButton[] {this.staff, this.customer, this.item, this.service, this.schedule, this.billing, this.revenue, this.statistics, this.managementGoBack,this.notify};
		String[] headers = new String[] {"Tài khoản & thông tin cá nhân", "Quản lý & cập nhật", "Theo dõi & thanh toán", "Tổng kết & báo cáo", "Khác"};
		String[] components = new String[] {"Nhân viên", "Khách hàng", "Sản phẩm", "Dịch vụ", "Lịch hẹn", "Hóa đơn", "Doanh thu", "Thống kê", "Thoát","Thông báo"};
		String[] icons = new String[] {"./icon_image/staff.png", "./icon_image/customer.png", "./icon_image/item.png", "./icon_image/services.png", "./icon_image/billing.png", "./icon_image/discount.png", "./icon_image/revenue.png", "./icon_image/statistics.png", "./icon_image/exit.png","./icon_image/message.png"};
		int[] coordinates = new int[] {50, 90, 170, 210, 290, 330, 410, 450, 515,550};
		int height = 20;
		this.managementWindow.addComponents(new JComponent[] {headerDisplay, tempHeaderDisplay}, 1285);
		this.managementWindow.setLayout(null);
		this.managementWindow.setBackground(new Color(0xe5dded));
		this.managementWindow.setVisible(false);
		this.managementWindow.setBounds(0, 0, 1285, 685);
		this.managementSection.setLayout(null);
		this.managementSection.setBackground(new Color(0x38388c));
		this.managementSection.setVisible(false);
		this.managementSection.setBounds(0, 50, 305, 635);
		this.managementPersonal.setLayout(null);
		this.managementPersonal.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.lightGray));
		this.managementPersonal.setBackground(new Color(0xf0dedd));
		this.managementPersonal.setBounds(0, 114, 305, 571);
		this.managementDisplay.setLayout(null);
		this.managementDisplay.setBackground(Color.white);
		this.managementDisplay.setBounds(305, 114, 980, 571);
		headerDisplay.setLayout(null);
		headerDisplay.setBackground(new Color(0xed966d));
		headerDisplay.setBounds(285, 56, 975, 50);
		tempHeaderDisplay.setLayout(null);
		tempHeaderDisplay.setBackground(new Color(0xed966d));
		tempHeaderDisplay.setBounds(-1000, 56, 975, 50);
		tempDisplay.setBackground(Color.white);
		tempDisplay.setBounds(0, 114, 285, 571);
		bannerContainer1.setLayout(null);
		bannerContainer1.setBounds(75, 0, 203, 50);
		bannerContainer2.setLayout(null);
		bannerContainer2.setBounds(388, 0, 203, 50);
		bannerContainer3.setLayout(null);
		bannerContainer3.setBounds(701, 0, 203, 50);
		tempBannerContainer1.setLayout(null);
		tempBannerContainer1.setBounds(75, 0, 203, 50);
		tempBannerContainer2.setLayout(null);
		tempBannerContainer2.setBounds(388, 0, 203, 50);
		tempBannerContainer3.setLayout(null);
		tempBannerContainer3.setBounds(701, 0, 203, 50);
		title.setText("QUẢN LÝ NGHIỆP VỤ");
		title.setIcon(new ImageIcon("./icon_image/menu.png"));
		title.setIconTextGap(10);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x196dc2));
		title.setOpaque(true);
		title.addMouseListener(new CustomMouseListener(title, null, this, null, null, null, 5, 5));
		title.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = title.getBounds();
		        title.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
		title.setBounds(0, 0, 240, 50);
		tempTitle.setBackground(new Color(0x196dc2));
		tempTitle.setOpaque(true);
		tempTitle.setBounds(240, 0, 1045, 50);
		this.tempTitle = title;
		this.line1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x196dc2)));
		this.line1.setBounds(0, 112, 305, 2);
		line2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x196dc2)));
		line2.setBounds(305, 112, 980, 2);
		icon.setIcon(new ImageIcon("./icon_image/manager.png"));
		icon.setHorizontalAlignment(JLabel.LEFT);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(10, 1, 40, 40);
		managementTitle.setText("JOLENE SPA");
		managementTitle.setFont(new Font("Edwardian Script ITC", Font.BOLD, 30));
		managementTitle.setHorizontalAlignment(JLabel.LEFT);
		managementTitle.setVerticalAlignment(JLabel.CENTER);
		managementTitle.setForeground(new Color(0x185f94));
		managementTitle.setBounds(60, 0, 250, 45);
		this.personalAccount.setFont(new Font("Consolas", Font.BOLD, 12));
		this.personalAccount.setHorizontalAlignment(JLabel.LEFT);
		this.personalAccount.setVerticalAlignment(JLabel.CENTER);
		this.personalAccount.setForeground(Color.black);
		this.personalAccount.setBackground(this.managementPersonal.getBackground());
		this.personalAccount.setOpaque(true);
		this.personalAccount.setBounds(18, 35, 165, 20);
		personalInformation.setText(" Thông tin cá nhân");
		personalInformation.setFont(new Font("CONSOLAS", Font.BOLD, 17));
		personalInformation.setHorizontalAlignment(JLabel.LEFT);
		personalInformation.setVerticalAlignment(JLabel.BOTTOM);
		personalInformation.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		personalInformation.setForeground(new Color(0xe85179));
		personalInformation.setBounds(0, 25, 305, 50);
		contactInformation.setText(" Thông tin liên hệ");
		contactInformation.setFont(new Font("CONSOLAS", Font.BOLD, 17));
		contactInformation.setHorizontalAlignment(JLabel.LEFT);
		contactInformation.setVerticalAlignment(JLabel.BOTTOM);
		contactInformation.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		contactInformation.setForeground(new Color(0xe85179));
		contactInformation.setBounds(0, 245, 305, 50);
		accountInformation.setText(" Quản lý tài khoản");
		accountInformation.setFont(new Font("CONSOLAS", Font.BOLD, 17));
		accountInformation.setHorizontalAlignment(JLabel.LEFT);
		accountInformation.setVerticalAlignment(JLabel.BOTTOM);
		accountInformation.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		accountInformation.setForeground(new Color(0xe85179));
		accountInformation.setBounds(0, 345, 305, 50);
		surname.setText("Họ:");
		surname.setIcon(new ImageIcon("./icon_image/pi.png"));
		surname.setIconTextGap(15);
		surname.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		surname.setHorizontalAlignment(JLabel.LEFT);
		surname.setVerticalAlignment(JLabel.CENTER);
		surname.setForeground(new Color(0x706f6c));
		surname.setBounds(10, 80, 60, 30);
		name.setText("Tên:");
		name.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		name.setHorizontalAlignment(JLabel.LEFT);
		name.setVerticalAlignment(JLabel.CENTER);
		name.setForeground(new Color(0x706f6c));
		name.setBounds(40, 110, 35, 30);
		sex.setText("Phái:");
		sex.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		sex.setHorizontalAlignment(JLabel.LEFT);
		sex.setVerticalAlignment(JLabel.CENTER);
		sex.setForeground(new Color(0x706f6c));
		sex.setBounds(40, 140, 40, 30);
		id.setText("CMND/CCCD:");
		id.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		id.setHorizontalAlignment(JLabel.LEFT);
		id.setVerticalAlignment(JLabel.CENTER);
		id.setForeground(new Color(0x706f6c));
		id.setBounds(40, 170, 100, 30);
		birthDate.setText("Ngày sinh:");
		birthDate.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		birthDate.setHorizontalAlignment(JLabel.LEFT);
		birthDate.setVerticalAlignment(JLabel.CENTER);
		birthDate.setForeground(new Color(0x706f6c));
		birthDate.setBounds(40, 200, 100, 30);
		homeTown.setText("Quê quán:");
		homeTown.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		homeTown.setHorizontalAlignment(JLabel.LEFT);
		homeTown.setVerticalAlignment(JLabel.CENTER);
		homeTown.setForeground(new Color(0x706f6c));
		homeTown.setBounds(40, 230, 100, 30);
		gmail.setText("Gmail:");
		gmail.setIcon(new ImageIcon("./icon_image/ci.png"));
		gmail.setIconTextGap(15);
		gmail.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		gmail.setHorizontalAlignment(JLabel.LEFT);
		gmail.setVerticalAlignment(JLabel.CENTER);
		gmail.setForeground(new Color(0x706f6c));
		gmail.setBounds(10, 300, 80, 30);
		phoneNumber.setText("Số điện thoại:");
		phoneNumber.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		phoneNumber.setHorizontalAlignment(JLabel.LEFT);
		phoneNumber.setVerticalAlignment(JLabel.CENTER);
		phoneNumber.setForeground(new Color(0x706f6c));
		phoneNumber.setBounds(40, 330, 115, 30);
		password.setText("Mật khẩu:");
		password.setIcon(new ImageIcon("./icon_image/ai.png"));
		password.setIconTextGap(15);
		password.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		password.setHorizontalAlignment(JLabel.LEFT);
		password.setVerticalAlignment(JLabel.CENTER);
		password.setForeground(new Color(0x706f6c));
		password.setBounds(10, 400, 150, 30);
		newPassword.setText("Mật khẩu mới:");
		newPassword.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		newPassword.setHorizontalAlignment(JLabel.LEFT);
		newPassword.setVerticalAlignment(JLabel.CENTER);
		newPassword.setForeground(new Color(0x706f6c));
		newPassword.setBounds(40, 430, 150, 30);
		newPasswordConfirm.setText("Xác nhận lại:");
		newPasswordConfirm.setFont(new Font("CONSOLAS", Font.BOLD, 14));
		newPasswordConfirm.setHorizontalAlignment(JLabel.LEFT);
		newPasswordConfirm.setVerticalAlignment(JLabel.CENTER);
		newPasswordConfirm.setForeground(new Color(0x706f6c));
		newPasswordConfirm.setBounds(40, 460, 150, 30);
		this.personalSurname.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalSurname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalSurname.setForeground(Color.black);
		this.personalSurname.setBackground(this.managementPersonal.getBackground());
		this.personalSurname.setDisabledTextColor(Color.black);
		this.personalSurname.setBounds(135, 85, 165, 20);
		this.personalName.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalName.setForeground(Color.black);
		this.personalName.setBackground(this.managementPersonal.getBackground());
		this.personalName.setDisabledTextColor(Color.black);
		this.personalName.setBounds(135, 115, 165, 20);
		this.personalSex.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalSex.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalSex.setForeground(Color.black);
		this.personalSex.setBackground(this.managementPersonal.getBackground());
		this.personalSex.setDisabledTextColor(Color.black);
		this.personalSex.setBounds(135, 145, 165, 20);
		this.personalId.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalId.setForeground(Color.black);
		this.personalId.setBackground(this.managementPersonal.getBackground());
		this.personalId.setDisabledTextColor(Color.black);
		this.personalId.setBounds(135, 175, 165, 20);
		this.personalBirthDate.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalBirthDate.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalBirthDate.setForeground(Color.black);
		this.personalBirthDate.setBackground(this.managementPersonal.getBackground());
		this.personalBirthDate.setDisabledTextColor(Color.black);
		this.personalBirthDate.setBounds(135, 205, 165, 20);
		this.personalHomeTown.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalHomeTown.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalHomeTown.setForeground(Color.black);
		this.personalHomeTown.setBackground(this.managementPersonal.getBackground());
		this.personalHomeTown.setDisabledTextColor(Color.black);
		this.personalHomeTown.setBounds(135, 235, 165, 20);
		this.personalGmail.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalGmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalGmail.setForeground(Color.black);
		this.personalGmail.setBackground(this.managementPersonal.getBackground());
		this.personalGmail.setBounds(95, 305, 205, 20);
		this.personalPhoneNumber.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalPhoneNumber.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalPhoneNumber.setForeground(Color.black);
		this.personalPhoneNumber.setBackground(this.managementPersonal.getBackground());
		this.personalPhoneNumber.setBounds(155, 335, 145, 20);
		this.personalPassword.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalPassword.setForeground(Color.black);
		this.personalPassword.setBackground(this.managementPersonal.getBackground());
		this.personalPassword.setBounds(160, 405, 140, 20);
		this.personalNewPassword.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalNewPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalNewPassword.setForeground(Color.black);
		this.personalNewPassword.setBackground(this.managementPersonal.getBackground());
		this.personalNewPassword.setBounds(160, 435, 140, 20);
		this.personalConfirmPassword.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.personalConfirmPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		this.personalConfirmPassword.setForeground(Color.black);
		this.personalConfirmPassword.setBackground(this.managementPersonal.getBackground());
		this.personalConfirmPassword.setBounds(160, 465, 140, 20);
		this.updateManagement.setText("CẬP NHẬT");
		this.updateManagement.setFont(new Font("Consolas", Font.BOLD, 12));
		this.updateManagement.setBorder(BorderFactory.createRaisedBevelBorder());
		this.updateManagement.setForeground(Color.black);
		this.updateManagement.setBackground(new Color(0xe0d9c3));
		this.updateManagement.setFocusable(false);
		this.updateManagement.addActionListener(this);
		this.updateManagement.setBounds(50, 495, 80, 30);
		this.changePasswordManagement.setText("ĐỔI MẬT KHẨU");
		this.changePasswordManagement.setFont(new Font("Consolas", Font.BOLD, 12));
		this.changePasswordManagement.setBorder(BorderFactory.createRaisedBevelBorder());
		this.changePasswordManagement.setForeground(Color.black);
		this.changePasswordManagement.setBackground(new Color(0xe0d9c3));
		this.changePasswordManagement.setFocusable(false);
		this.changePasswordManagement.addActionListener(this);
		this.changePasswordManagement.setBounds(177, 495, 100, 30);
		this.searchIcon.setIcon(new ImageIcon("./icon_image/search.png"));
		this.searchIcon.setHorizontalAlignment(JLabel.LEFT);
		this.searchIcon.setVerticalAlignment(JLabel.CENTER);
		this.searchIcon.setVisible(false);
		this.searchIcon.setBounds(5, 13, 30, 30);
		this.search.setText("Tìm kiếm");
		this.search.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.search.setBorder(BorderFactory.createEtchedBorder(Color.black, Color.darkGray));
		this.search.setForeground(Color.gray);
		this.search.addMouseListener(new CustomMouseListener(this.search, null, this, null, null, Color.black, 1, 1));
		this.search.addKeyListener(this);
		this.search.setVisible(false);
		this.search.setBounds(35, 13, 320, 30);
		this.managementPickerInfo.setText("Chọn tháng/năm:");
		this.managementPickerInfo.setFont(new Font("Consolas", Font.BOLD, 13));
		this.managementPickerInfo.setHorizontalAlignment(JLabel.LEFT);
		this.managementPickerInfo.setVerticalAlignment(JLabel.CENTER);
		this.managementPickerInfo.setForeground(Color.black);
		this.managementPickerInfo.setVisible(false);
		this.managementPickerInfo.setBounds(5, 51, 150, 25);
		this.managementPickerOne.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		this.managementPickerOne.setBackground(Color.white);
		this.managementPickerOne.setEditable(false);
		this.managementPickerOne.addActionListener(this);
		this.managementPickerOne.setVisible(false);
		this.managementPickerOne.setBounds(150, 48, 80, 25);
		this.managementPickerTwo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		this.managementPickerTwo.setBackground(Color.white);
		this.managementPickerTwo.setEditable(false);
		this.managementPickerTwo.setFocusable(false);
		this.managementPickerTwo.addActionListener(this);
		this.managementPickerTwo.setVisible(false);
		this.managementPickerTwo.setBounds(235, 48, 120, 25);
		this.managementAdd.setText("Thêm");
		this.managementAdd.setFont(new Font("Consolas", Font.BOLD, 15));
		this.managementAdd.setBorder(new RoundedBorder(30));
		this.managementAdd.setForeground(new Color(0x05f509));
		this.managementAdd.setBackground(Color.white);
		this.managementAdd.setFocusable(false);
		this.managementAdd.setContentAreaFilled(false);
		this.managementAdd.addActionListener(this);
		this.managementAdd.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = title.getBounds();
		        managementAdd.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
		    }

		    
		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
		this.managementAdd.setVisible(false);
		this.managementAdd.setBounds(595, 13, 175, 30);
		this.managementUpdate.setText("Cập nhật");
		this.managementUpdate.setFont(new Font("Consolas", Font.BOLD, 15));
		this.managementUpdate.setBorder(new RoundedBorder(30));
		this.managementUpdate.setForeground(new Color(0xf21198));
		this.managementUpdate.setBackground(Color.white);
		this.managementUpdate.setFocusable(false);
		this.managementUpdate.setContentAreaFilled(false);
		this.managementUpdate.addActionListener(this);
		this.managementUpdate.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = title.getBounds();
		        managementUpdate.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
		this.managementUpdate.setVisible(false);
		this.managementUpdate.setBounds(780, 13, 175, 30);
		this.managementOne.setFont(new Font("Consolas", Font.BOLD, 15));
		this.managementOne.setBorder(new RoundedBorder(30));
		this.managementOne.setForeground(new Color(0x9579a8));
		this.managementOne.setBackground(Color.white);
		this.managementOne.setFocusable(false);
		this.managementOne.setContentAreaFilled(false);
		this.managementOne.addActionListener(this);
		this.managementOne.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = title.getBounds();
		        managementOne.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
		this.managementOne.setVisible(false);
		this.managementOne.setBounds(595, 47, 175, 30);
		this.managementTwo.setFont(new Font("Consolas", Font.BOLD, 15));
		this.managementTwo.setBorder(new RoundedBorder(30));
		this.managementTwo.setForeground(new Color(0x5d6bba));
		this.managementTwo.setBackground(Color.white);
		this.managementTwo.setFocusable(false);
		this.managementTwo.setContentAreaFilled(false);
		this.managementTwo.addActionListener(this);
		this.managementTwo.addMouseMotionListener(new MouseMotionListener() 
		{
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        final int x = e.getX();
		        final int y = e.getY();
		        final Rectangle cellBounds = title.getBounds();
		        managementTwo.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {}
		});
		this.managementTwo.setVisible(false);
		this.managementTwo.setBounds(780, 47, 175, 30);
		for(int i=0; i<5; i++)
		{
			JLabel sectionHeader = new JLabel();
			JLabel line = new JLabel();
			sectionHeader.setText(headers[i]);
			sectionHeader.setFont(new Font("CONSOLAS", Font.BOLD, 15));
			sectionHeader.setHorizontalAlignment(JLabel.LEFT);
			sectionHeader.setVerticalAlignment(JLabel.CENTER);
			sectionHeader.setForeground(new Color(0xb3c2ff));
			sectionHeader.setBounds(10, height, 270, 30);
			line.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xdedede)));
			line.setBounds(0, height+110, 285, 1);
			this.managementSection.add(sectionHeader);
			this.managementSection.add(line);
			height = height + 120;
			
		}
		for(int i=0; i<inputs.length; i++)
		{
			JButton temp = (JButton) inputs[i];
			inputs[i].setText(components[i]);
			inputs[i].setIcon(new ImageIcon(icons[i]));
			inputs[i].setIconTextGap(10);
			inputs[i].setFont(new Font("Consolas", Font.BOLD, 13));
			inputs[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
			inputs[i].setHorizontalAlignment(JButton.LEFT);
			inputs[i].setForeground(Color.white);
			inputs[i].setBackground(new Color(0x38388c));
			inputs[i].setFocusable(false);
			inputs[i].setContentAreaFilled(false);
			inputs[i].addActionListener(this);
			temp.addMouseMotionListener(new MouseMotionListener() 
			{
			    @Override
			    public void mouseMoved(MouseEvent e) 
			    {
			        final int x = e.getX();
			        final int y = e.getY();
			        final Rectangle cellBounds = title.getBounds();
			        temp.setCursor(cellBounds != null && cellBounds.contains(x, y) ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
			    }

			    @Override
			    public void mouseDragged(MouseEvent e) {}
			});
			inputs[i].setBounds(40, coordinates[i], 220, 30);
			this.managementSection.add(inputs[i]);
		}
		this.infoOne.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoOne.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoOne.setBackground(new Color(0xdee3e3));
		this.infoOne.setForeground(Color.black);
		this.infoOne.setEnabled(false);
		this.infoOne.setDisabledTextColor(Color.black);
		this.infoOne.setVisible(false);
		this.infoOne.setBounds(5, 420, 245, 30);
		this.infoTwo.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoTwo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoTwo.setBackground(new Color(0xdee3e3));
		this.infoTwo.setForeground(Color.black);
		this.infoTwo.setEnabled(false);
		this.infoTwo.setDisabledTextColor(Color.black);
		this.infoTwo.setVisible(false);
		this.infoTwo.setBounds(255, 420, 245, 30);
		this.infoThree.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoThree.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoThree.setBackground(new Color(0xdee3e3));
		this.infoThree.setForeground(Color.black);
		this.infoThree.setEnabled(false);
		this.infoThree.setDisabledTextColor(Color.black);
		this.infoThree.setVisible(false);
		this.infoThree.setBounds(505, 420, 245, 30);
		this.infoFour.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoFour.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoFour.setBackground(new Color(0xdee3e3));
		this.infoFour.setForeground(Color.black);
		this.infoFour.setEnabled(false);
		this.infoFour.setDisabledTextColor(Color.black);
		this.infoFour.setVisible(false);
		this.infoFour.setBounds(755, 420, 208, 30);
		this.infoFive.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoFive.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoFive.setBackground(new Color(0xdee3e3));
		this.infoFive.setForeground(Color.black);
		this.infoFive.setEnabled(false);
		this.infoFive.setDisabledTextColor(Color.black);
		this.infoFive.setVisible(false);
		this.infoFive.setBounds(5, 460, 495, 30);
		this.infoSix.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoSix.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoSix.setBackground(new Color(0xdee3e3));
		this.infoSix.setForeground(Color.black);
		this.infoSix.setEnabled(false);
		this.infoSix.setDisabledTextColor(Color.black);
		this.infoSix.setVisible(false);
		this.infoSix.setBounds(505, 460, 245, 30);
		this.infoSeven.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoSeven.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoSeven.setBackground(new Color(0xdee3e3));
		this.infoSeven.setForeground(Color.black);
		this.infoSeven.setEnabled(false);
		this.infoSeven.setDisabledTextColor(Color.black);
		this.infoSeven.setVisible(false);
		this.infoSeven.setBounds(755, 460, 208, 30);
		this.infoEight.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoEight.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoEight.setBackground(new Color(0xdee3e3));
		this.infoEight.setForeground(Color.black);
		this.infoEight.setEnabled(false);
		this.infoEight.setDisabledTextColor(Color.black);
		this.infoEight.setVisible(false);
		this.infoEight.setBounds(5, 500, 245, 30);
		this.infoNine.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoNine.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoNine.setBackground(new Color(0xdee3e3));
		this.infoNine.setForeground(Color.black);
		this.infoNine.setEnabled(false);
		this.infoNine.setDisabledTextColor(Color.black);
		this.infoNine.setVisible(false);
		this.infoNine.setBounds(255, 500, 245, 30);
		this.infoTen.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoTen.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoTen.setBackground(new Color(0xdee3e3));
		this.infoTen.setForeground(Color.black);
		this.infoTen.setEnabled(false);
		this.infoTen.setDisabledTextColor(Color.black);
		this.infoTen.setVisible(false);
		this.infoTen.setBounds(505, 500, 245, 30);
		this.infoEleven.setFont(new Font("Consolas", Font.BOLD, 15));
		this.infoEleven.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		this.infoEleven.setBackground(new Color(0xdee3e3));
		this.infoEleven.setForeground(Color.black);
		this.infoEleven.setEnabled(false);
		this.infoEleven.setDisabledTextColor(Color.black);
		this.infoEleven.setVisible(false);
		this.infoEleven.setBounds(755, 500, 208, 30);
		this.upManagementTitle.setFont(new Font("CONSOLAS", Font.BOLD, 18));
		this.upManagementTitle.setHorizontalAlignment(JLabel.CENTER);
		this.upManagementTitle.setVerticalAlignment(JLabel.CENTER);
		this.upManagementTitle.setForeground(new Color(0x2f8799));
		this.upManagementTitle.setVisible(false);
		this.downManagementTitle.setFont(new Font("CONSOLAS", Font.BOLD, 18));
		this.downManagementTitle.setHorizontalAlignment(JLabel.CENTER);
		this.downManagementTitle.setVerticalAlignment(JLabel.CENTER);
		this.downManagementTitle.setForeground(new Color(0x2f8799));
		this.downManagementTitle.setVisible(false);
		headerDisplay.add(bannerContainer1);
		headerDisplay.add(bannerContainer2);
		headerDisplay.add(bannerContainer3);
		tempHeaderDisplay.add(tempBannerContainer1);
		tempHeaderDisplay.add(tempBannerContainer2);
		tempHeaderDisplay.add(tempBannerContainer3);
		this.managementPersonal.add(icon);
		this.managementPersonal.add(managementTitle);
		this.managementPersonal.add(this.personalAccount);
		this.managementPersonal.add(personalInformation);
		this.managementPersonal.add(contactInformation);
		this.managementPersonal.add(accountInformation);
		this.managementPersonal.add(surname);
		this.managementPersonal.add(name);
		this.managementPersonal.add(sex);
		this.managementPersonal.add(id);
		this.managementPersonal.add(birthDate);
		this.managementPersonal.add(homeTown);
		this.managementPersonal.add(gmail);
		this.managementPersonal.add(phoneNumber);
		this.managementPersonal.add(password);
		this.managementPersonal.add(newPassword);
		this.managementPersonal.add(newPasswordConfirm);
		this.managementPersonal.add(this.personalSurname);
		this.managementPersonal.add(this.personalName);
		this.managementPersonal.add(this.personalSex);
		this.managementPersonal.add(this.personalId);
		this.managementPersonal.add(this.personalBirthDate);
		this.managementPersonal.add(this.personalHomeTown);
		this.managementPersonal.add(this.personalGmail);
		this.managementPersonal.add(this.personalPhoneNumber);
		this.managementPersonal.add(this.personalPassword);
		this.managementPersonal.add(this.personalNewPassword);
		this.managementPersonal.add(this.personalConfirmPassword);
		this.managementPersonal.add(this.updateManagement);
		this.managementPersonal.add(this.changePasswordManagement);
		this.managementDisplay.add(this.searchIcon);
		this.managementDisplay.add(this.search);
		this.managementDisplay.add(this.managementPickerInfo);
		this.managementDisplay.add(this.managementPickerOne);
		this.managementDisplay.add(this.managementPickerTwo);
		this.managementDisplay.add(this.managementAdd);
		this.managementDisplay.add(this.managementUpdate);
		this.managementDisplay.add(this.managementOne);
		this.managementDisplay.add(this.managementTwo);
		this.managementDisplay.add(this.upManagementTitle);
		this.managementDisplay.add(this.downManagementTitle);
		this.managementDisplay.add(this.infoOne);
		this.managementDisplay.add(this.infoTwo);
		this.managementDisplay.add(this.infoThree);
		this.managementDisplay.add(this.infoFour);
		this.managementDisplay.add(this.infoFive);
		this.managementDisplay.add(this.infoSix);
		this.managementDisplay.add(this.infoSeven);
		this.managementDisplay.add(this.infoEight);
		this.managementDisplay.add(this.infoNine);
		this.managementDisplay.add(this.infoTen);
		this.managementDisplay.add(this.infoEleven);
		this.managementWindow.add(title);
		this.managementWindow.add(tempTitle);
		this.managementWindow.add(this.line1);
		this.managementWindow.add(line2);
		this.managementWindow.add(this.managementSection);
		this.managementWindow.add(this.managementPersonal);
		this.managementWindow.add(headerDisplay);
		this.managementWindow.add(tempHeaderDisplay);
		this.managementWindow.add(tempDisplay);
		this.managementWindow.add(this.managementDisplay);
		this.add(this.managementWindow);
	}
	
	public void showMain()
	{
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		JLabel subTitle = new JLabel();
		JButton[] inputs = new JButton[] {this.customerService, this.customerScheduled, this.customerPersonalInfo, this.customerHistory, this.customerRating, this.customerGoback};
		String[] components = new String[] {"ĐẶT LỊCH", "XEM LỊCH ĐÃ ĐẶT", "THÔNG TIN CÁ NHÂN", "LỊCH SỬ HÓA ĐƠN", "ĐÁNH GIÁ, GÓP Ý", "ĐĂNG XUẤT"};
		String[] icons = new String[] {"./icon_image/service.png", "./icon_image/view.png", "./icon_image/profile.png", "./icon_image/history.png", "./icon_image/comment.png", "./icon_image/logout.png"};
		int height = 280;
		this.mainWindow.setLayout(null);
		this.mainWindow.setBackground(new Color(0xede0dd));
		this.mainWindow.setBounds(0, 0, 1285, 685);
		this.mainWindow.setVisible(false);
		title.setText(" QUẢN LÝ DANH MỤC CỦA BẠN");
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(0, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		subTitle.setText("Danh mục của tôi");
		subTitle.setFont(new Font("CONSOLAS", Font.BOLD, 22));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setVerticalAlignment(JLabel.CENTER);
		subTitle.setForeground(new Color(0x5e85bd));
		subTitle.setBounds(534, 240, 210, 30);
		for(int i=0; i<inputs.length; i=i+2)
		{
			inputs[i].setText(components[i]);
			inputs[i].setIcon(new ImageIcon(icons[i]));
			inputs[i].setIconTextGap(10);
			inputs[i].setFont(new Font("Consolas", Font.BOLD, 20));
			inputs[i].setVerticalTextPosition(JButton.BOTTOM);
			inputs[i].setBorder(BorderFactory.createRaisedBevelBorder());
			inputs[i].setForeground(Color.white);
			inputs[i].setBackground(new Color(0x69b5b8));
			inputs[i].setFocusable(false);
			inputs[i].addActionListener(this);
			inputs[i].setBounds(380, height+10, 260, 50);
			inputs[i+1].setText(components[i+1]);
			inputs[i+1].setIcon(new ImageIcon(icons[i+1]));
			inputs[i+1].setIconTextGap(10);
			inputs[i+1].setFont(new Font("Consolas", Font.BOLD, 20));
			inputs[i+1].setVerticalTextPosition(JButton.BOTTOM);
			inputs[i+1].setBorder(BorderFactory.createRaisedBevelBorder());
			inputs[i+1].setForeground(Color.white);
			inputs[i+1].setBackground(new Color(0x69b5b8));
			inputs[i+1].setFocusable(false);
			inputs[i+1].addActionListener(this);
			inputs[i+1].setBounds(650, height+10, 260, 50);
			this.mainWindow.add(inputs[i]);
			this.mainWindow.add(inputs[i+1]);
			height = height + 70;
		}
		this.mainWindow.add(title);
		this.mainWindow.add(icon);
		this.mainWindow.add(subTitle);
		this.add(this.mainWindow);
	}
	
	public void showService()
	{
		JPanel scheduledFrame = new JPanel();
		JPanel scheduledInfo = new JPanel();
		AnimationPanel infoOne = new AnimationPanel();
		AnimationPanel infoTwo = new AnimationPanel();
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		JLabel scheduleDetail = new JLabel();
		JLabel scheduleList = new JLabel();
		JLabel customerId = new JLabel();
		JLabel customerName = new JLabel();
		JLabel servicePricePicker = new JLabel();
		JLabel dayPicker = new JLabel();
		JLabel timePicker = new JLabel();
		JLabel showSchedule = new JLabel();
		JLabel showScheduleTemp = new JLabel();
		JLabel showScheduled = new JLabel();
		JLabel showScheduledTemp = new JLabel();
		this.serviceWindow.setLayout(null);
		this.serviceWindow.setBackground(new Color(0xede0dd));
		this.serviceWindow.setBounds(0, 0, 1285, 685);
		this.serviceWindow.setVisible(false);
		scheduledFrame.setLayout(null);
		scheduledFrame.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0x69b5b8)));
		scheduledFrame.setBackground(Color.white);
		scheduledFrame.setBounds(20, 227, 560, 330);
		scheduledInfo.setLayout(null);
		scheduledInfo.setBorder(new RoundedBorder(30));
		scheduledInfo.setBackground(Color.white);
		scheduledInfo.setBounds(20, 20, 520, 290);
		infoOne.addComponents(new JComponent[] {showSchedule, showScheduleTemp}, 590);
		infoOne.setLayout(null);
		infoOne.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		infoOne.setBackground(new Color(0xe7dfe8));
		infoOne.setBounds(20, 577, 560, 60);
		infoTwo.addComponents(new JComponent[] {showScheduled, showScheduledTemp}, 590);
		infoTwo.setLayout(null);
		infoTwo.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		infoTwo.setBackground(new Color(0xe7dfe8));
		infoTwo.setBounds(602, 577, 650, 60);
		title.setText(" CHỌN DỊCH VỤ MÀ BẠN CẦN");
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(100, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		scheduleDetail.setText("Chi tiết đặt lịch");
		scheduleDetail.setFont(new Font("Consolas", Font.BOLD, 18));
		scheduleDetail.setHorizontalAlignment(JLabel.CENTER);
		scheduleDetail.setVerticalAlignment(JLabel.CENTER);
		scheduleDetail.setForeground(new Color(0x5e85bd));
		scheduleDetail.setBounds(150, 190, 300, 30);
		scheduleList.setText("Danh sách lịch hẹn");
		scheduleList.setFont(new Font("Consolas", Font.BOLD, 18));
		scheduleList.setHorizontalAlignment(JLabel.CENTER);
		scheduleList.setVerticalAlignment(JLabel.CENTER);
		scheduleList.setForeground(new Color(0x5e85bd));
		scheduleList.setBounds(785, 190, 300, 30);
		customerId.setText("Mã khách hàng:");
		customerId.setFont(new Font("Consolas", Font.BOLD, 15));
		customerId.setHorizontalAlignment(JLabel.LEFT);
		customerId.setVerticalAlignment(JLabel.CENTER);
		customerId.setForeground(new Color(0xff7d7d));
		customerId.setBounds(10, 12, 150, 30);
		customerName.setText("Họ và tên khách hàng:");
		customerName.setFont(new Font("Consolas", Font.BOLD, 15));
		customerName.setHorizontalAlignment(JLabel.LEFT);
		customerName.setVerticalAlignment(JLabel.CENTER);
		customerName.setForeground(new Color(0xff7d7d));
		customerName.setBounds(10, 53, 250, 30);
		servicePricePicker.setText("Chọn dịch vụ:");
		servicePricePicker.setFont(new Font("Consolas", Font.BOLD, 15));
		servicePricePicker.setHorizontalAlignment(JLabel.LEFT);
		servicePricePicker.setVerticalAlignment(JLabel.CENTER);
		servicePricePicker.setForeground(new Color(0xff7d7d));
		servicePricePicker.setBounds(10, 94, 150, 30);
		timePicker.setText("Chọn giờ làm:");
		timePicker.setFont(new Font("Consolas", Font.BOLD, 15));
		timePicker.setHorizontalAlignment(JLabel.LEFT);
		timePicker.setVerticalAlignment(JLabel.CENTER);
		timePicker.setForeground(new Color(0xff7d7d));
		timePicker.setBounds(10, 135, 150, 30);
		dayPicker.setText("Chọn ngày làm:");
		dayPicker.setFont(new Font("Consolas", Font.BOLD, 15));
		dayPicker.setHorizontalAlignment(JLabel.LEFT);
		dayPicker.setVerticalAlignment(JLabel.CENTER);
		dayPicker.setForeground(new Color(0xff7d7d));
		dayPicker.setBounds(10, 176, 150, 30);
		this.customerId.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.customerId.setHorizontalAlignment(JLabel.LEFT);
		this.customerId.setVerticalAlignment(JLabel.CENTER);
		this.customerId.setForeground(Color.black);
		this.customerId.setBounds(250, 12, 150, 30);
		this.customerName.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.customerName.setHorizontalAlignment(JLabel.LEFT);
		this.customerName.setVerticalAlignment(JLabel.CENTER);
		this.customerName.setForeground(Color.black);
		this.customerName.setBounds(250, 53, 150, 30);
		this.createServicePricePicker(this.servicePricePicker);
		this.servicePricePicker.setSelectedIndex(-1);
		this.servicePricePicker.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.servicePricePicker.setBackground(new Color(0xf0eeeb));
		this.servicePricePicker.setEditable(false);
		this.servicePricePicker.setFocusable(false);
		this.servicePricePicker.setBounds(250, 94, 250, 26);
		this.createTimePicker(this.timePicker);
		this.timePicker.setSelectedIndex(-1);
		this.timePicker.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.timePicker.setBackground(new Color(0xf0eeeb));
		this.timePicker.setEditable(false);
		this.timePicker.setFocusable(false);
		this.timePicker.setBounds(250, 133, 250, 26);
		this.datePicker.setBounds(250, 171, 250, 26);
		this.serviceAdd.setText("THÊM LỊCH HẸN");
		this.serviceAdd.setFont(new Font("Consolas", Font.BOLD, 15));
		this.serviceAdd.setVerticalAlignment(JButton.BOTTOM);
		this.serviceAdd.setBorder(BorderFactory.createRaisedBevelBorder());
		this.serviceAdd.setForeground(Color.white);
		this.serviceAdd.setBackground(new Color(0x69b5b8));
		this.serviceAdd.setFocusable(false);
		this.serviceAdd.addActionListener(this);
		this.serviceAdd.setBounds(302, 240, 145, 30);
		this.serviceConfirm.setText("ĐẶT TOÀN BỘ");
		this.serviceConfirm.setFont(new Font("Consolas", Font.BOLD, 15));
		this.serviceConfirm.setVerticalAlignment(JButton.BOTTOM);
		this.serviceConfirm.setBorder(BorderFactory.createRaisedBevelBorder());
		this.serviceConfirm.setForeground(Color.white);
		this.serviceConfirm.setBackground(new Color(0x69b5b8));
		this.serviceConfirm.setFocusable(false);
		this.serviceConfirm.addActionListener(this);
		this.serviceConfirm.setBounds(55, 240, 130, 30);
		showSchedule.setText("Chọn dịch vụ bạn mong muốn làm phía trên nhé");
		showSchedule.setFont(new Font("Courier New", Font.PLAIN, 17));
		showSchedule.setHorizontalAlignment(JLabel.LEFT);
		showSchedule.setVerticalAlignment(JLabel.CENTER);
		showSchedule.setForeground(new Color(0xcf2d6b));
		showSchedule.setBounds(60, 10, 480, 40);
		showScheduleTemp.setText("Chọn dịch vụ bạn mong muốn làm phía trên nhé");
		showScheduleTemp.setFont(new Font("Courier New", Font.PLAIN, 17));
		showScheduleTemp.setHorizontalAlignment(JLabel.LEFT);
		showScheduleTemp.setVerticalAlignment(JLabel.CENTER);
		showScheduleTemp.setForeground(new Color(0xcf2d6b));
		showScheduleTemp.setBounds(-550, 10, 480, 40);
		showScheduled.setText("Bạn kiểm tra kỹ thông tin trước khi đặt nhé");
		showScheduled.setFont(new Font("Courier New", Font.PLAIN, 17));
		showScheduled.setHorizontalAlignment(JLabel.LEFT);
		showScheduled.setVerticalAlignment(JLabel.CENTER);
		showScheduled.setForeground(new Color(0xcf2d6b));
		showScheduled.setBounds(60, 10, 580, 40);
		showScheduledTemp.setText("Bạn kiểm tra kỹ thông tin trước khi đặt nhé");
		showScheduledTemp.setFont(new Font("Courier New", Font.PLAIN, 17));
		showScheduledTemp.setHorizontalAlignment(JLabel.LEFT);
		showScheduledTemp.setVerticalAlignment(JLabel.CENTER);
		showScheduledTemp.setForeground(new Color(0xcf2d6b));
		showScheduledTemp.setBounds(-545, 10, 580, 40);
		scheduledInfo.add(customerId);
		scheduledInfo.add(customerName);
		scheduledInfo.add(servicePricePicker);
		scheduledInfo.add(timePicker);
		scheduledInfo.add(dayPicker);
		scheduledInfo.add(this.customerId);
		scheduledInfo.add(this.customerName);
		scheduledInfo.add(this.servicePricePicker);
		scheduledInfo.add(this.timePicker);
		scheduledInfo.add(this.datePicker);
		scheduledInfo.add(this.serviceAdd);
		scheduledInfo.add(this.serviceConfirm);
		infoOne.add(showSchedule);
		infoOne.add(showScheduleTemp);
		infoTwo.add(showScheduled);
		infoTwo.add(showScheduledTemp);
		scheduledFrame.add(scheduledInfo);
		this.serviceWindow.add(scheduledFrame);
		this.serviceWindow.add(title);
		this.serviceWindow.add(icon);
		this.serviceWindow.add(scheduleDetail);
		this.serviceWindow.add(scheduleList);
		this.serviceWindow.add(infoOne);
		this.serviceWindow.add(infoTwo);
		this.add(this.serviceWindow);
	}
	
	public void showScheduled()
	{
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		String[] components = new String[] {"Mã lịch hẹn", "Dịch vụ", "Ngày", "Giờ", "Trạng thái", "Hủy đặt"};
		int[] coordinates = new int[] {55, 262, 470, 679, 889, 1101};
		this.scheduledWindow.setLayout(null);
		this.scheduledWindow.setBackground(new Color(0xede0dd));
		this.scheduledWindow.setBounds(0, 0, 1285, 685);
		this.scheduledWindow.setVisible(false);
		title.setText(" THEO DÕI LỊCH HẸN ĐĂ ĐẶT");
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(100, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		for(int i=0; i<components.length; i++)
		{
			JLabel temp = new JLabel();
			temp.setText(components[i]);
			temp.setFont(new Font("Consolas", Font.BOLD, 18));
			temp.setHorizontalAlignment(JLabel.CENTER);
			temp.setVerticalAlignment(JLabel.CENTER);
			temp.setForeground(new Color(0x5e85bd));
			temp.setBounds(coordinates[i], 210, 120, 30);
			this.scheduledWindow.add(temp);
		}
		this.scheduledWindow.add(title);
		this.scheduledWindow.add(icon);
		this.add(this.scheduledWindow);
	}
	
	public void showPersonalInfo()
	{
		JPanel personalInfoFrame = new JPanel();
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		JLabel subTitle = new JLabel();
		JTextField[] inputs = new JTextField[] {this.updateSurname, this.updateName, this.updateMail, this.updatePhoneNumber, this.updatePassword, this.updateNewPassword, this.updateConfirmPassword};
		String[] components = new String[] {"Họ", "Tên", "Gmail", "Số điện thoại", "Mật khẩu cũ", "Mật khẩu mới", "Xác nhận mật khẩu mới"};
		String[] icons = new String[] {"./icon_image/surname.png", "./icon_image/name.png", "./icon_image/mail.png", "./icon_image/phonenumber.png", "./icon_image/password.png", null, null};
		int height = 30;
		this.personalInfoWindow.setLayout(null);
		this.personalInfoWindow.setBackground(new Color(0xede0dd));
		this.personalInfoWindow.setBounds(0, 0, 1285, 685);
		this.personalInfoWindow.setVisible(false);
		personalInfoFrame.setLayout(null);
		personalInfoFrame.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x69b5b8)));
		personalInfoFrame.setBackground(Color.white);
		personalInfoFrame.setBounds(500, 270, 300, 350);
		title.setText(" QUẢN LÝ THÔNG TIN CÁ NHÂN");
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(100, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		subTitle.setText("Sửa thông tin cá nhân");
		subTitle.setFont(new Font("CONSOLAS", Font.BOLD, 22));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setVerticalAlignment(JLabel.CENTER);
		subTitle.setForeground(new Color(0x5e85bd));
		subTitle.setBounds(519, 220, 260, 30);
		this.updatePersonalInfo.setText("CẬP NHẬT");
		this.updatePersonalInfo.setFont(new Font("Consolas", Font.BOLD, 15));
		this.updatePersonalInfo.setBorder(BorderFactory.createRaisedBevelBorder());
		this.updatePersonalInfo.setForeground(Color.white);
		this.updatePersonalInfo.setBackground(new Color(0x69b5b8));
		this.updatePersonalInfo.setFocusable(false);
		this.updatePersonalInfo.addActionListener(this);
		this.updatePersonalInfo.setBounds(50, 310, 100, 30);
		this.updateAccount.setText("ĐỔI MẬT KHẨU");
		this.updateAccount.setFont(new Font("Consolas", Font.BOLD, 15));
		this.updateAccount.setBorder(BorderFactory.createRaisedBevelBorder());
		this.updateAccount.setForeground(Color.white);
		this.updateAccount.setBackground(new Color(0x69b5b8));
		this.updateAccount.setFocusable(false);
		this.updateAccount.addActionListener(this);
		this.updateAccount.setBounds(155, 310, 115, 30);
		for(int i=0; i<inputs.length; i++)
		{
			JLabel container = new JLabel();
			container.setIcon(new ImageIcon(icons[i]));
			container.setBounds(20, height, 40, 30);
			if(i >= inputs.length-3)
			{
				JTextField temp = new JTextField();
				temp.setText(components[i]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
				temp.setForeground(Color.gray);
				temp.addMouseListener(new CustomMouseListener(temp, (JPasswordField) inputs[i], this, null, null, null, 4, 4));
				temp.addKeyListener(this);
				temp.setBounds(50, height, 220, 30);
				inputs[i].setFont(new Font("Times New Roman", Font.PLAIN, 15));
				inputs[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
				inputs[i].setBounds(50, height, 220, 30);
				inputs[i].setVisible(false);
				personalInfoFrame.add(temp);
			}
			else
			{
				inputs[i].setText(components[i]);
				inputs[i].setFont(new Font("Times New Roman", Font.PLAIN, 15));
				inputs[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
				inputs[i].setForeground(Color.black);
				inputs[i].setBounds(50, height, 220, 30);
			}
			personalInfoFrame.add(container);
			personalInfoFrame.add(inputs[i]);
			height = height + 40;
		}
		personalInfoFrame.add(this.updatePersonalInfo);
		personalInfoFrame.add(this.updateAccount);
		this.personalInfoWindow.add(title);
		this.personalInfoWindow.add(icon);
		this.personalInfoWindow.add(subTitle);
		this.personalInfoWindow.add(personalInfoFrame);
		this.add(this.personalInfoWindow);
	}
	
	public void showHistory()
	{
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		String[] components = new String[] {"Mã hóa đơn", "Mã khách hàng", "Phí gốc", "Mức ưu đãi áp dụng", "Phí thanh toán", "Nhân viên lập", "Ngày lập"};
		int[] coordinates = new int[] {-26, 150, 331, 508, 688, 872, 1053};
		this.historyWindow.setLayout(null);
		this.historyWindow.setBackground(new Color(0xede0dd));
		this.historyWindow.setBounds(0, 0, 1285, 685);
		this.historyWindow.setVisible(false);
		title.setText(" XEM LẠI HÓA ĐƠN ĐÃ LÀM");
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(100, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		for(int i=0; i<components.length; i++)
		{
			JLabel temp = new JLabel();
			temp.setText(components[i]);
			temp.setFont(new Font("Consolas", Font.BOLD, 18));
			temp.setHorizontalAlignment(JLabel.CENTER);
			temp.setVerticalAlignment(JLabel.CENTER);
			temp.setForeground(new Color(0x5e85bd));
			temp.setBounds(coordinates[i], 210, 250, 30);
			this.historyWindow.add(temp);
		}
		this.historyWindow.add(title);
		this.historyWindow.add(icon);
		this.add(this.historyWindow);
	}
	
	public void showRating()
	{
		JPanel ratingFrame = new JPanel();
		JLabel title = new JLabel();
		JLabel icon = new JLabel();
		JLabel subTitle = new JLabel();
		JLabel serviceInfo = new JLabel();
		JLabel ratingInfo = new JLabel();
		JLabel commentInfo = new JLabel();
		int starWidth = 0;
		this.ratingWindow.setLayout(null);
		this.ratingWindow.setBackground(new Color(0xede0dd));
		this.ratingWindow.setBounds(0, 0, 1285, 685);
		this.ratingWindow.setVisible(false);
		ratingFrame.setLayout(null);
		ratingFrame.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0x69b5b8)));
		ratingFrame.setBackground(Color.white);
		ratingFrame.setBounds(400, 270, 470, 330);
		this.rating.setLayout(null);
		this.rating.setBorder(BorderFactory.createLineBorder(Color.white));
		this.rating.setBackground(Color.white);
		this.rating.setBounds(200, 50, 260, 50);
		title.setText(" ĐÁNH GIÁ CÙNG VỚI GÓP Ý");
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.LEFT);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setForeground(Color.black);
		title.setBackground(new Color(0x69b5b8));
		title.setOpaque(true);
		title.setBounds(100, 0, 1285, 50);
		icon.setIcon(new ImageIcon("./icon_image/icon.png"));
		icon.setHorizontalAlignment(JLabel.CENTER);
		icon.setVerticalAlignment(JLabel.CENTER);
		icon.setBounds(598, 100, 80, 80);
		subTitle.setText("Thêm đánh giá và góp ý của bạn");
		subTitle.setFont(new Font("CONSOLAS", Font.BOLD, 22));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setVerticalAlignment(JLabel.CENTER);
		subTitle.setForeground(new Color(0x5e85bd));
		subTitle.setBounds(449, 220, 370, 30);
		serviceInfo.setText("Chọn loại dịch vụ:");
		serviceInfo.setFont(new Font("CONSOLAS", Font.PLAIN, 15));
		serviceInfo.setHorizontalAlignment(JLabel.LEFT);
		serviceInfo.setVerticalAlignment(JLabel.CENTER);
		serviceInfo.setForeground(Color.black);
		serviceInfo.setBounds(10, 20, 180, 30);
		ratingInfo.setText("Đánh giá của bạn:");
		ratingInfo.setFont(new Font("CONSOLAS", Font.PLAIN, 15));
		ratingInfo.setHorizontalAlignment(JLabel.LEFT);
		ratingInfo.setVerticalAlignment(JLabel.CENTER);
		ratingInfo.setForeground(Color.black);
		ratingInfo.setBounds(10, 60, 150, 30);
		commentInfo.setText("Góp ý của bạn:");
		commentInfo.setFont(new Font("CONSOLAS", Font.PLAIN, 15));
		commentInfo.setHorizontalAlignment(JLabel.LEFT);
		commentInfo.setVerticalAlignment(JLabel.CENTER);
		commentInfo.setForeground(Color.black);
		commentInfo.setBounds(10, 100, 150, 30);
		this.createServicePicker(this.servicePicker);
		this.servicePicker.setSelectedIndex(-1);
		this.servicePicker.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.servicePicker.setForeground(new Color(0x4b5f6b));
		this.servicePicker.setBackground(new Color(0xe4eff7));
		this.servicePicker.setEditable(false);
		this.servicePicker.setFocusable(false);
		this.servicePicker.addActionListener(this);
		this.servicePicker.setBounds(200, 20, 250, 30);
		this.comment.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.comment.setBorder(new RoundedBorder(30));
		this.comment.setLineWrap(true);
		this.comment.setBounds(10, 140, 450, 180);
		this.ratingConfirm.setText("XÁC NHẬN");
		this.ratingConfirm.setFont(new Font("Consolas", Font.BOLD, 15));
		this.ratingConfirm.setBorder(BorderFactory.createRaisedBevelBorder());
		this.ratingConfirm.setForeground(Color.white);
		this.ratingConfirm.setBackground(new Color(0x69b5b8));
		this.ratingConfirm.setFocusable(false);
		this.ratingConfirm.addActionListener(this);
		this.ratingConfirm.setBounds(590, 610, 100, 30);
		for(int i=0; i<5; i++)
		{
			JLabel container = new JLabel();
			container.setBounds(starWidth, 0, 50, 40);
			container.addMouseListener(new StarRating(container));
			this.rating.add(container);
			starWidth = starWidth + 55;
		}
		ratingFrame.add(serviceInfo);
		ratingFrame.add(ratingInfo);
		ratingFrame.add(commentInfo);
		ratingFrame.add(this.servicePicker);
		ratingFrame.add(this.rating);
		ratingFrame.add(this.comment);
		this.ratingWindow.add(this.ratingConfirm);
		this.ratingWindow.add(title);
		this.ratingWindow.add(icon);
		this.ratingWindow.add(subTitle);
		this.ratingWindow.add(ratingFrame);
		this.add(this.ratingWindow);
	}
	
	public void refreshPersonal()
	{
		Staff s = StaffDao.getOneStaffByAccount(this.acc.trim());
		if(s != null)
		{
			this.personalAccount.setText(s.getAccount().trim());
			this.personalSurname.setText(s.getSurname().trim());
			this.personalName.setText(s.getName().trim());
			this.personalSex.setText(s.getSex().trim());
			this.personalId.setText(s.getId().trim());
			this.personalBirthDate.setText(s.getBirthDate().trim());
			this.personalHomeTown.setText(s.getHomeTown().trim());
			this.personalGmail.setText(s.getGmail());
			this.personalPhoneNumber.setText(s.getPhoneNumber().trim());
			this.personalPassword.setText("");
			this.personalNewPassword.setText("");
			this.personalConfirmPassword.setText("");
			if(this.role == 1)
			{
				this.personalSurname.setEnabled(true);
				this.personalName.setEnabled(true);
				this.personalSex.setEnabled(true);
				this.personalId.setEnabled(true);
				this.personalBirthDate.setEnabled(true);
				this.personalHomeTown.setEnabled(true);
			}
			else if(this.role == 2)
			{
				this.personalSurname.setEnabled(false);
				this.personalName.setEnabled(false);
				this.personalSex.setEnabled(false);
				this.personalId.setEnabled(false);
				this.personalBirthDate.setEnabled(false);
				this.personalHomeTown.setEnabled(false);
			}
		}
	}
	
	public void refreshAccount(ArrayList<Account> lst, int role)
	{
		String[][] accountList = new String[lst.size()][2];
		this.placeAll(new int[][] {{337, 48, 300, 30}, {330, 380, 300, 30}});
		for(Account a : lst)
		{
			accountList[lst.indexOf(a)][0] = a.getAccount();
			accountList[lst.indexOf(a)][1] = a.getStatus() == 0 ?  "Đã khóa" : "Đang hoạt động";
		}
		if(role == 2 || role == 3 && this.role == 2)
		{
			if(role == 2)
			{
				this.showAll(new String[] {"TÀI KHOẢN NHÂN VIÊN", "THÔNG TIN CÁ NHÂN"});
				this.displayAll(new Boolean[] {true, true, true, false, false, false, false, false, false, true, true});
			}
			else
			{
				this.showAll(new String[] {"TÀI KHOẢN KHÁCH HÀNG", "THÔNG TIN CÁ NHÂN"});
				this.displayAll(new Boolean[] {true, true, false, false, false, false, false, false, false, true, true});
			}
			this.createIconTable(this.managementDisplay, accountList, new String[]{"Tên tài khoản", "Trạng thái", "Mở/khóa"}, "./icon_image/confirm.png", "./icon_image/lock.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
		else
		{
			this.showAll(new String[] {"TÀI KHOẢN KHÁCH HÀNG", "THÔNG TIN CÁ NHÂN"});
			this.displayAll(new Boolean[] {true, true, false, false, false, false, false, false, false, true, true});
			this.createNonIconTable(this.managementDisplay, accountList, new String[]{"Tên tài khoản", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
	}
	
	public void refreshAddStaff()
	{
		String[] infoList = new String[] {"Tên tài khoản", "Mật khẩu", "Họ", "Tên", "Phái:",  "Ngày sinh:", "Ngày vào làm:", "Số điện thoại", "Cccd/Cmnd", "Gmail", "Quê quán"};
		int height = 88;
		for(int i=0; i<11; i++)
		{
			if(i == 1)
			{
				JTextField temp1 = new JTextField();
				JPasswordField temp2 = new JPasswordField();
				temp1.setText(infoList[i]);
				temp1.setForeground(Color.gray);
				temp1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp1.addMouseListener(new CustomMouseListener(temp1, temp2, this, null, null, null, 4, 4));
				temp1.addKeyListener(this);
				temp1.setBounds(297, height, 350, 30);
				temp2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp2.setBounds(297, height, 350, 30);
				temp2.setVisible(false);
				this.comp = temp1;
				this.managementDisplay.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp2);
			}
			else if(i < 4 || i > 6)
			{
				JTextField temp = new JTextField();
				temp.setText(infoList[i]);
				temp.setForeground(Color.gray);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
				temp.addKeyListener(this);
				temp.setBounds(297, height, 350, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if(i == 4)
			{
				JComboBox<String> temp1 = new JComboBox<String>(new String[] {"Nam", "Nữ"});
				JLabel temp2 = new JLabel();
				temp1.setSelectedIndex(-1);
				temp1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp1.setEditable(false);
				temp1.setFocusable(false);
				temp1.setBounds(537, height, 110, 30);
				temp2.setText(infoList[i]);
				temp2.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp2.setHorizontalAlignment(JLabel.LEFT);
				temp2.setVerticalAlignment(JLabel.CENTER);
				temp2.setBounds(297, height, 110, 30);
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			else
			{
				JLabel temp1 = new JLabel();
				JDatePickerImpl temp2;
				Properties p = new Properties();
				UtilDateModel model = new UtilDateModel();
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
				temp1.setText(infoList[i]);
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(297, height, 150, 30);
				temp2 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				temp2.setBounds(537, height, 110, 30);
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			height = height + 40;
		}
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
		this.showAll(new String[] {"ĐIỀN THÔNG TIN NHÂN VIÊN MỚI", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
	}
	
	public void refreshUpdateStaff(String userName)
	{
		Staff s = StaffDao.getOneStaffByAccount(userName.trim());
		ArrayList<String> lst = new ArrayList<String>();
		ArrayList<String> birthDate = this.splitDate(s.getBirthDate().trim());
		ArrayList<String> dayWork = this.splitDate(s.getDayWork().trim());
		lst.add(s.getSurname().trim());
		lst.add(s.getName().trim());
		lst.add(s.getSex().trim());
		lst.add("Ngày sinh:");
		lst.add("Ngày vào làm:");
		lst.add(s.getPhoneNumber().trim());
		lst.add(s.getId().trim());
		lst.add(s.getGmail().trim());
		lst.add(s.getHomeTown().trim());
		int height = 88;
		for(int i=0; i<9; i++)
		{
			if(i < 2 || i > 4)
			{
				JTextField temp = new JTextField();
				temp.setText(lst.get(i));
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setBounds(297, height, 350, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if (i == 2)
			{
				JComboBox<String> temp1 = new JComboBox<String>(new String[] {"Nam", "Nữ"});
				JLabel temp2 = new JLabel();
				temp1.setSelectedIndex(lst.get(i).equalsIgnoreCase("Nam") ? 0 : 1);
				temp1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp1.setEditable(false);
				temp1.setFocusable(false);
				temp1.setBounds(537, height, 110, 30);
				temp2.setText("Phái:");
				temp2.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp2.setHorizontalAlignment(JLabel.LEFT);
				temp2.setVerticalAlignment(JLabel.CENTER);
				temp2.setBounds(297, height, 110, 30);
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			else
			{
				JLabel temp1 = new JLabel();
				JDatePickerImpl temp2;
				Properties p = new Properties();
				UtilDateModel model = new UtilDateModel();
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
				temp1.setText(lst.get(i));
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(297, height, 150, 30);
				temp2 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				if(i == 3) temp2.getModel().setDate(Integer.parseInt(birthDate.get(2)), Integer.parseInt(birthDate.get(1))-1, Integer.parseInt(birthDate.get(0)));
				else if(i == 4) temp2.getModel().setDate(Integer.parseInt(dayWork.get(2)), Integer.parseInt(dayWork.get(1))-1, Integer.parseInt(dayWork.get(0)));
				temp2.getModel().setSelected(true);
				temp2.setBounds(537, height, 110, 30);
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			height = height + 40;
		}
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
		this.showAll(new String[] {"CHỈNH SỬA THÔNG TIN", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
	}
	
	public void refreshItem(ArrayList<Item> lst)
	{
		String[][] itemList  = new String[lst.size()][6];
		this.showAll(new String[] {"SẢN PHẨM", "HÌNH ẢNH SẢN PHẨM"});
		this.placeAll(new int[][] {{337, 140, 300, 30}, {0, 0, 0, 0}});
		for(Item i : lst)
		{
			itemList[lst.indexOf(i)][0] = i.getItemId();
			itemList[lst.indexOf(i)][1] = i.getItemName();
			itemList[lst.indexOf(i)][2] = i.getUnit();
			itemList[lst.indexOf(i)][3] = String.valueOf(i.getItemQuantity());
			itemList[lst.indexOf(i)][4] = String.valueOf(i.getAmount());
			itemList[lst.indexOf(i)][5] = String.valueOf(i.getQuantity());
		}
		if(this.role == 1)
		{
			this.displayAll(new Boolean[] {true, true, false, false, true, false, false, false, false, true, false});
			this.createNonIconTable(this.managementDisplay, itemList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị", "Dung tích", "Số lượng tồn", "Dung tích tồn"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 170, 958, 360, true, true);
		}
		else if(this.role == 2)
		{
			this.displayAll(new Boolean[] {true, true, true, false, true, false, false, false, false, true, false});
			this.createIconTable(this.managementDisplay, itemList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị", "Dung tích", "Số lượng tồn", "Dung tích tồn", "Sửa"}, "./icon_image/edit.png", null, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 170, 958, 360, true, true);
		}
	}
	
	//THÊM
	//CHỨC NĂNG THÔNG BÁO
	public void refreshMachine(ArrayList<Machine> lst)
	{
		String[][] itemList  = new String[lst.size()][5];
		this.showAll(new String[] {"ĐỀ XUẤT NHẬP HÀNG", ""});
		this.placeAll(new int[][] {{332, 48, 300, 30}, {330, 380, 300, 30}});
		//this.placeAll(new int[][] {{337, 140, 300, 30}, {0, 0, 0, 0}});

		for(Machine i : lst)
		{
			itemList[lst.indexOf(i)][0] = i.getItemNameM();
			itemList[lst.indexOf(i)][1] = String.valueOf(i.getAmountM());
			itemList[lst.indexOf(i)][2] = String.valueOf(i.getAmountImport());
			itemList[lst.indexOf(i)][3] = String.valueOf(i.getTime());
			//itemList[lst.indexOf(i)][4] = i.getLaBel();
			
			if (i.getLaBel() != null && "nhap".equalsIgnoreCase(i.getLaBel().trim())) {
			    itemList[lst.indexOf(i)][4] = "Nhập";
			}
			
		}
		if(this.role == 1)
		{
			this.displayAll(new Boolean[] {true, true, false, false, false, false, false, false, false, true, false});
			this.createNonIconTable(this.managementDisplay, itemList, new String[]{"Tên sản phẩm","Số lượng tồn","Số lượng nhâp trước","Thời gian nhập", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
			//this.createNonIconTable(this.managementDisplay, itemList, new String[]{"Tên sản phẩm","Số lượng tồn","Số lượng nhâp trước","Thời gian nhập", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 170, 958, 360, true, true);
		}
		else if(this.role == 2)
		{
			this.displayAll(new Boolean[] {true, true, false, false, true, false, false, false, false, true, false});
			//this.createNonIconTable(this.managementDisplay,itemList, new String[]{"Tên sản phẩm","Số lượng tồn","Số lượng nhâp trước","Thời gian nhập", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);

			this.createNonIconTable(this.managementDisplay, itemList, new String[]{"Tên sản phẩm","Số lượng tồn","Số lượng nhâp trước","Thời gian nhập", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
			}
	}
	public void refreshAddItem()
	{
		String[] infoList = new String[] {"Hình ảnh sản phẩm:", "Tên sản phẩm", "Dung tích (nếu có)", "Đơn vị dung tích (nếu có)"};
		int height = 198;
		this.comp = new JTextField();
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
		this.showAll(new String[] {"ĐIỀN THÔNG TIN SẢN PHẨM MỚI", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		for(int i=0; i<4; i++)
		{
			if(i == 0)
			{
				JLabel temp1 = new JLabel();
				JPanel temp2 = new JPanel();
				temp1.setText(infoList[i]);
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(297, 138, 170, 30);
				temp2.setLayout(new GridBagLayout());
				temp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.gray, Color.lightGray));
				temp2.setBackground(new Color(0xf5f7f7));
				temp2.setBounds(495, 78, 150, 150);
				this.tempButton.setVisible(true);
				temp2.add(this.tempButton, new GridBagConstraints());
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			else
			{
				JTextField temp = new JTextField();
				temp.setText(infoList[i]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
				temp.setForeground(Color.gray);
				temp.addKeyListener(this);
				temp.setBounds(297, height, 350, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			height = height + 40;
		}
	}
	
	public void refreshUpdateItem(Item i)
	{
		String[] infoList = new String[] {"Chọn ảnh mới(nếu cần):", i.getItemName().trim()};
		int height = 198;
		this.comp = new JTextField();
		((JTextField) this.comp).setText(i.getImage());
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
		this.showAll(new String[] {"CHỈNH SỬA THÔNG TIN SẢN PHẨM", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		for(int j=0; j<2; j++)
		{
			if(j == 0)
			{
				JLabel temp1 = new JLabel();
				JPanel temp2 = new JPanel();
				temp1.setText(infoList[j]);
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(297, 138, 200, 30);
				temp2.setLayout(new GridBagLayout());
				temp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.gray, Color.lightGray));
				temp2.setBackground(new Color(0xf5f7f7));
				temp2.setBounds(495, 78, 150, 150);
				this.tempButton.setVisible(true);
				temp2.add(this.tempButton, new GridBagConstraints());
				this.comps.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp1);
				this.managementDisplay.add(temp2);
			}
			else
			{
				JTextField temp = new JTextField();
				temp.setText(infoList[j]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setForeground(Color.black);
				temp.setBounds(297, height, 350, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			height = height + 40;
		}
	}
	
	public void refreshImportExport(ArrayList<ImportExport> lst)
	{
		String[][] importExportList = new String[lst.size()][4];
		this.showAll(new String[] {"PHIẾU NHẬP VÀ XUẤT", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		if(this.role == 1)
		{
			for(ImportExport ie : lst)
			{
				importExportList[lst.indexOf(ie)][0] = ie.getId();
				importExportList[lst.indexOf(ie)][1] = String.valueOf(this.numberFormat.format(ie.getTotalMoney()));
				importExportList[lst.indexOf(ie)][2] = ie.getImportExportDay();
				importExportList[lst.indexOf(ie)][3] = ie.getStaffId();
			}
			this.displayAll(new Boolean[] {true, true, false, false, false, true, true, false, true, true, false});
			this.createIconTable(this.managementDisplay, importExportList, new String[]{"Mã phiếu", "Tổng trị giá", "Ngày lập", "Nhân viên lập", "Xem chi tiết"}, "./icon_image/eye.png", null, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
		else if(this.role == 2)
		{
			for(ImportExport ie : lst)
			{
				importExportList[lst.indexOf(ie)][0] = ie.getId();
				importExportList[lst.indexOf(ie)][1] = String.valueOf(this.numberFormat.format(ie.getTotalMoney()));
				importExportList[lst.indexOf(ie)][2] = ie.getImportExportDay();
			}
			this.displayAll(new Boolean[] {false, false, false, false, true, true, true, false, true, true, false});
			this.createIconTable(this.managementDisplay, importExportList, new String[]{"Mã phiếu", "Tổng trị giá", "Ngày lập", "Xem chi tiết"}, "./icon_image/eye.png", null, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
	}
	
	public void refreshImportExportDetail(ArrayList<ImportExportDetail> lst)
	{
		String[][] importExportDetailList = new String[lst.size()][4];
		String type = this.managementPickerTwo.getSelectedIndex() == 0 ? "nhập" : "xuất";
		for(ImportExportDetail ied : lst)
		{
			importExportDetailList[lst.indexOf(ied)][0] = ied.getId();
			importExportDetailList[lst.indexOf(ied)][1] = ied.getItemId();
			importExportDetailList[lst.indexOf(ied)][2] = String.valueOf(this.numberFormat.format(ied.getMoney()));
			importExportDetailList[lst.indexOf(ied)][3] = String.valueOf(ied.getAmount());
		}
		this.showAll(new String[] {"CHI TIẾT PHIẾU", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		this.displayAll(new Boolean[] {true, true, false, false, false, true, false, false, false, true, false});
		this.createNonIconTable(this.managementDisplay, importExportDetailList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá " + type, "Số lượng " + type}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
	}
	
	public void refreshAddImportExport()
	{
		String[][] tempList = new String[0][5];
		String[] info = new String[] {"Chọn sản phẩm", "Giá", "Số lượng" }; 
		int[] coordinates = new int[] {5, 420, 700};
		int[] heights = new int[] {420, 420, 420};
		this.displayAll(new Boolean[] {false, false, false, true, true, true, true, false, true, true, true});
		this.showAll(new String[] {"CHI TIẾT PHIẾU", "THÔNG TIN SẢN PHẨM"});
		this.placeAll(new int[][] {{335, 48, 300, 30}, {335, 380, 300, 30}});
		for(int i=0; i<3; i++)
		{
			if(i != 0)
			{
				JTextField temp = new JTextField();
				temp.setText(info[i]);
				temp.setForeground(Color.gray);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
				temp.addKeyListener(this);
				temp.setBounds(coordinates[i], heights[i], 240, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if(i == 0)
			{
				JLabel temp1 = new JLabel();
				JComboBox<String> temp2 = new JComboBox<String>();
				temp1.setText("Chọn sản phẩm:");
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(coordinates[i], heights[i], 130, 30);
				this.createItemPicker(temp2);
				temp2.setSelectedIndex(-1);
				temp2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp2.setBackground(Color.white);
				temp2.setEditable(false);
				temp2.addActionListener(this);
				temp2.setBounds(140, 420, 240, 30);
				this.comps.add(temp1);
				this.managementDisplay.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp2);
			}
		}
		this.createIconTable(this.managementDisplay, tempList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Bỏ sản phẩm"}, null, "./icon_image/remove.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	//THÊM
	public void refreshAddImportExportMachine(ArrayList<Machine> lst)
	{
		//String[][] tempList  = new String[lst.size()][5];
		String[][] tempList = new String[0][5];
		String[] info = new String[] {"Chọn sản phẩm", "Giá", "Số lượng" }; 
		int[] coordinates = new int[] {5, 420, 700};
		int[] heights = new int[] {420, 420, 420};
		this.displayAll(new Boolean[] {false, false, false, true, true, true, true, false, true, true, true});
		this.showAll(new String[] {"CHI TIẾT PHIẾU", "THÔNG TIN SẢN PHẨM"});
		this.placeAll(new int[][] {{335, 48, 300, 30}, {335, 380, 300, 30}});
		

		for(int i=0; i<3; i++)
		{
			if(i != 0)
			{
				JTextField temp = new JTextField();
				temp.setText(info[i]);
				temp.setForeground(Color.gray);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
				temp.addKeyListener(this);
				temp.setBounds(coordinates[i], heights[i], 240, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if(i == 0)
			{
				JLabel temp1 = new JLabel();
				JComboBox<String> temp2 = new JComboBox<String>();
				temp1.setText("Chọn sản phẩm:");
				temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp1.setHorizontalAlignment(JLabel.LEFT);
				temp1.setVerticalAlignment(JLabel.CENTER);
				temp1.setBounds(coordinates[i], heights[i], 130, 30);
				this.createItemPicker(temp2);
				temp2.setSelectedIndex(-1);
				temp2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp2.setBackground(Color.white);
				temp2.setEditable(false);
				temp2.addActionListener(this);
				temp2.setBounds(140, 420, 240, 30);
				this.comps.add(temp1);
				this.managementDisplay.add(temp1);
				this.comps.add(temp2);
				this.managementDisplay.add(temp2);
			}
		}
		this.createIconTable(this.managementDisplay, tempList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Bỏ sản phẩm"}, null, "./icon_image/remove.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		DefaultTableModel model = (DefaultTableModel) this.data.getModel();			
		ArrayList<Machine> machines = MachineDao.importProducts();
		for(Machine m : machines)
		{
			
			String id = m.getItemIdM();
			String name = m.getItemNameM();
			String price = String.valueOf(m.getMoneyImport());
			String amount = String.valueOf(m.getAmountM());	
			model.addRow(new String[]{id, name, price, amount});
			
		}
		model.fireTableDataChanged();
	}
	
	public void refreshService(ArrayList<Service> lst)
	{
		String[][] serviceList = new String[lst.size()][5];
		this.showAll(new String[] {"DỊCH VỤ", ""});
		this.placeAll(new int[][] {{332, 48, 300, 30}, {330, 380, 300, 30}});
		for(Service s : lst)
		{
			Staff staff = StaffDao.getOneStaffById(s.getStaffId().trim());
			serviceList[lst.indexOf(s)][0] = s.getId();
			serviceList[lst.indexOf(s)][1] = s.getName();
			serviceList[lst.indexOf(s)][2] = String.valueOf(this.numberFormat.format(s.getPrice()));
			serviceList[lst.indexOf(s)][3] = staff.getSurname() + " " + staff.getName();
		}
		if(this.role == 1)
		{
			this.displayAll(new Boolean[] {true, true, false, false, false, false, false, false, false, true, false});
			this.createNonIconTable(this.managementDisplay, serviceList, new String[]{"Mã dịch vụ", "Tên dịch vụ", "Giá", "Nhân viên thêm"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
		else if(this.role == 2)
		{
			this.displayAll(new Boolean[] {true, true, true, false, false, false, false, false, false, true, false});
			this.createIconTable(this.managementDisplay, serviceList, new String[]{"Mã dịch vụ", "Tên dịch vụ", "Giá", "Nhân viên thêm", "Sửa/Xóa"}, "./icon_image/edit.png", "./icon_image/delete.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
	}
	public void refreshNotify(ArrayList<Service> lst)
	{
		String[][] accountList = new String[lst.size()][2];
		this.placeAll(new int[][] {{337, 48, 300, 30}, {330, 380, 300, 30}});
		
		this.showAll(new String[] {"ĐỀ XUẤT NHẬP HÀNG", ""});
		this.displayAll(new Boolean[] {false, false, false, false, false, false, false, false, false, false, false});
		this.createNonIconTable(this.managementDisplay, accountList, new String[]{"Tên sản phẩm","Số lượng tồn","Số lượng nhâp trước","Thời gian nhập", "Trạng thái"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	public void refreshAddServiceFormula(String info1, String info2, Color[] colorList, Boolean check)
	{
		String[][] tempList = new String[0][4];
		String[] info = new String[] {"Thông tin dịch vụ:", info1, info2, "Thông tin sản phẩm sử dụng kèm:", "", "Lượng sử dụng"}; 
		int[] coordinates = new int[] {5, 310, 570, 5, 310, 570};
		int[] heights = new int[] {420, 420, 420, 480, 480, 480};
		this.displayAll(new Boolean[] {false, false, false, true, true, true, false, false, false, true, true});
		this.showAll(new String[] {"SẢN PHẨM SỬ DỤNG KÈM", "THÔNG TIN DỊCH VỤ"});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		for(int i=0; i<6; i++)
		{
			if(i == 0 || i == 3)
			{
				JLabel temp = new JLabel();
				temp.setText(info[i]);
				temp.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp.setHorizontalAlignment(JLabel.LEFT);
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setBounds(coordinates[i], heights[i], 300, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if(i != 4)
			{
				JTextField temp = new JTextField();
				temp.setText(info[i]);
				temp.setForeground(colorList[i]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				if(i < 3 && check || i > 3)
				{
					temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
					temp.addKeyListener(this);
				}
				temp.setBounds(coordinates[i], heights[i], 250, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else
			{
				JComboBox<String> temp = new JComboBox<String>();
				this.createItemPicker(temp);
				temp.setSelectedIndex(-1);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setBackground(Color.white);
				temp.setEditable(false);
				temp.addActionListener(this);
				temp.setBounds(coordinates[i], heights[i], 250, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
		}
		this.createIconTable(this.managementDisplay, tempList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Lượng", "Đơn vị tính", "Bỏ sản phẩm"}, null, "./icon_image/remove.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	public void refreshUpdateServiceFormula(ArrayList<Formula> lst, String info1, String info2, Color[] colorList, Boolean check)
	{
		String[][] formulaList = new String[lst.size()][4];
		String[] info = new String[] {"Thông tin dịch vụ:", info1, info2, "Thông tin sản phẩm sử dụng kèm:", "", "Lượng sử dụng"}; 
		int[] coordinates = new int[] {5, 310, 570, 5, 310, 570};
		int[] heights = new int[] {420, 420, 420, 480, 480, 480};
		this.displayAll(new Boolean[] {false, false, false, true, true, true, false, false, false, true, true});
		this.showAll(new String[] {"SẢN PHẨM SỬ DỤNG KÈM", "THÔNG TIN DỊCH VỤ"});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {325, 380, 300, 30}});
		for(Formula f : lst)
		{
			formulaList[lst.indexOf(f)][0] = f.getItemId();
			formulaList[lst.indexOf(f)][1] = ItemDao.getOneItemById(f.getItemId()).getItemName();
			formulaList[lst.indexOf(f)][2] = String.valueOf(f.getQuantity());
			formulaList[lst.indexOf(f)][3] = ItemDao.getOneItemById(f.getItemId()).getUnit();
		}
		for(int i=0; i<6; i++)
		{
			if(i == 0 || i == 3)
			{
				JLabel temp = new JLabel();
				temp.setText(info[i]);
				temp.setFont(new Font("Consolas", Font.PLAIN, 15));
				temp.setHorizontalAlignment(JLabel.LEFT);
				temp.setVerticalAlignment(JLabel.CENTER);
				temp.setBounds(coordinates[i], heights[i], 300, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else if(i != 4)
			{
				JTextField temp = new JTextField();
				temp.setText(info[i]);
				temp.setForeground(colorList[i]);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				if(i < 2 && check || i > 2)
				{
					temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
					temp.addKeyListener(this);
				}
				temp.setBounds(coordinates[i], heights[i], 250, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
			else
			{
				JComboBox<String> temp = new JComboBox<String>();
				this.createItemPicker(temp);
				temp.setSelectedIndex(-1);
				temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp.setBackground(Color.white);
				temp.setEditable(false);
				temp.addActionListener(this);
				temp.setBounds(coordinates[i], heights[i], 250, 30);
				this.comps.add(temp);
				this.managementDisplay.add(temp);
			}
		}
		this.createIconTable(this.managementDisplay, formulaList, new String[]{"Mã sản phẩm", "Tên sản phẩm", "Lượng", "Đơn vị tính", "Bỏ sản phẩm"}, null, "./icon_image/remove.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	public void refreshServiceRating(ArrayList<Rating> lst)
	{
		String[][] ratingList = new String[lst.size()][3];
		this.displayAll(new Boolean[] {true, true, false, false, false, true, false, false, false, true, true});
		this.showAll(new String[] {"ĐÁNH GIÁ", "THÔNG TIN KHÁCH HÀNG"});
		this.placeAll(new int[][] {{335, 48, 300, 30}, {340, 380, 300, 30}});
		for(Rating r : lst)
		{
			ratingList[lst.indexOf(r)][0] = r.getReceiptId();
			ratingList[lst.indexOf(r)][1] = String.valueOf(r.getRating()) + " sao";
			ratingList[lst.indexOf(r)][2] = r.getComment();
		}
		this.createNonIconTable(this.managementDisplay, ratingList, new String[]{"Mã hóa đơn", "Đánh giá", "Góp ý của khách hàng"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	public void refreshSchedule(ArrayList<Schedule> lst, int status)
	{
		String[][] scheduleList = new String[lst.size()][6];
		this.displayAll(new Boolean[] {true, true, false, false, false, false, true, false, true, true, true});
		this.showAll(new String[] {"LỊCH HẸN", "THÔNG TIN KHÁCH HÀNG"});
		this.placeAll(new int[][] {{331, 48, 300, 30}, {330, 380, 300, 30}});
		if(status == 0)
		{
			for(Schedule s : lst)
			{
				scheduleList[lst.indexOf(s)][0] = s.getScheduleId();
				scheduleList[lst.indexOf(s)][1] = s.getCustomerId();
				scheduleList[lst.indexOf(s)][2] = s.getServiceId();
				scheduleList[lst.indexOf(s)][3] = s.getDate();
				scheduleList[lst.indexOf(s)][4] = s.getTime();
			}
			if(this.role == 1) this.createNonIconTable(this.managementDisplay, scheduleList, new String[]{"Mã lịch hẹn", "Mã khách hàng", "Tên dịch vụ", "Ngày", "Giờ"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
			else if(this.role == 2) this.createIconTable(this.managementDisplay, scheduleList, new String[]{"Mã lịch hẹn", "Mã khách hàng", "Tên dịch vụ", "Ngày", "Giờ", "Xác nhận/hủy"}, "./icon_image/confirm.png", "./icon_image/lock.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
		else
		{
			String statusInfo = status == 1 ? "Nhân viên xác nhận" : "Người hủy";
			String surname = "";
			String name = "";
			for(Schedule s : lst)
			{
				scheduleList[lst.indexOf(s)][0] = s.getScheduleId();
				scheduleList[lst.indexOf(s)][1] = s.getCustomerId();
				scheduleList[lst.indexOf(s)][2] = s.getServiceId();
				scheduleList[lst.indexOf(s)][3] = s.getDate();
				scheduleList[lst.indexOf(s)][4] = s.getTime();
				if(s.getStaffId() != null)
				{
					Staff temp = StaffDao.getOneStaffById(s.getStaffId());
					surname = temp.getSurname().trim();
					name = temp.getName().trim();
					scheduleList[lst.indexOf(s)][5] = surname + " " + name;
				}
				else scheduleList[lst.indexOf(s)][5] = "Khách hàng tự hủy";
				
			}
			this.createNonIconTable(this.managementDisplay, scheduleList, new String[]{"Mã lịch hẹn", "Mã khách hàng", "Tên dịch vụ", "Ngày", "Giờ", statusInfo}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
	}
	
	public void refreshReceipt(ArrayList<Receipt> lst)
	{
		String[][] receiptList = new String[lst.size()][6];
		this.showAll(new String[] {"HÓA ĐƠN", "THÔNG TIN KHÁCH HÀNG"});
		this.placeAll(new int[][] {{331, 48, 300, 30}, {330, 380, 300, 30}});
		if(this.role == 1)
		{
			for(Receipt r : lst)
			{
				receiptList[lst.indexOf(r)][0] = r.getReceiptId();
				receiptList[lst.indexOf(r)][1] = r.getCustomerId();
				receiptList[lst.indexOf(r)][2] = String.valueOf(r.getDiscountLevel()) + "%";
				receiptList[lst.indexOf(r)][3] = r.getStaffId();
				receiptList[lst.indexOf(r)][4] = r.getReceiptDay();
				receiptList[lst.indexOf(r)][5] = String.valueOf(this.numberFormat.format(r.getTotalMoney()));
			}
			this.displayAll(new Boolean[] {true, true, false, false, true, false, false, false, false, true, true});
			this.createIconTable(this.managementDisplay, receiptList, new String[]{"Mã hóa đơn", "Mã khách hàng", "Ưu đãi áp dụng", "Nhân viên lập", "Ngày lập", "Tổng trị giá", "Xem chi tiết"}, "./icon_image/eye.png", null, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
		else if(this.role == 2)
		{
			for(Receipt r : lst)
			{
				receiptList[lst.indexOf(r)][0] = r.getReceiptId();
				receiptList[lst.indexOf(r)][1] = r.getCustomerId();
				receiptList[lst.indexOf(r)][2] = String.valueOf(r.getDiscountLevel()) + "%";
				receiptList[lst.indexOf(r)][3] = r.getReceiptDay();
				receiptList[lst.indexOf(r)][4] = String.valueOf(this.numberFormat.format(r.getTotalMoney()));
			}
			this.displayAll(new Boolean[] {false, false, true, false, true, false, false, false, false, true, true});
			this.createIconTable(this.managementDisplay, receiptList, new String[]{"Mã hóa đơn", "Mã khách hàng", "Ưu đãi", "Ngày lập", "Tổng trị giá", "Xem chi tiết"}, "./icon_image/eye.png", null, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
		}
	}
	
	public void refreshReceiptDetail(ArrayList<ReceiptDetail> lst)
	{
		String[][] receiptDetailList = new String[lst.size()][4];
		for(ReceiptDetail rd : lst)
		{
			receiptDetailList[lst.indexOf(rd)][0] = rd.getReceiptId();
			receiptDetailList[lst.indexOf(rd)][1] = rd.getServiceId();
			receiptDetailList[lst.indexOf(rd)][2] = String.valueOf(this.numberFormat.format(rd.getMoney()));
			receiptDetailList[lst.indexOf(rd)][3] = String.valueOf(rd.getAmount());
		}
		this.showAll(new String[] {"CHI TIẾT HÓA ĐƠN", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		this.displayAll(new Boolean[] {true, true, false, false, false, true, false, false, false, true, false});
		this.createNonIconTable(this.managementDisplay, receiptDetailList, new String[]{"Mã dịch vụ", "Tên dịch vụ", "Phí dịch vụ", "Số lượng"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
	}
	
	public void refreshAddReceipt()
	{
		String[][] tempList = new String[0][4];
		String[] info = new String[] {"Chọn khách hàng:", "Chọn dịch vụ:", "Số lượng:"}; 
		int[] coordinates = new int[] {5, 5, 5};
		int[] heights = new int[] {410, 455, 500};
		this.displayAll(new Boolean[] {false, false, false, true, true, true, false, false, false, true, true});
		this.showAll(new String[] {"CHI TIẾT HÓA ĐƠN", "THÔNG TIN HÓA ĐƠN"});
		this.placeAll(new int[][] {{330, 48, 300, 30}, {331, 380, 300, 30}});
		for(int i=0; i<3; i++)
		{
			JLabel temp1 = new JLabel();
			temp1.setText(info[i]);
			temp1.setFont(new Font("Consolas", Font.PLAIN, 15));
			temp1.setHorizontalAlignment(JLabel.LEFT);
			temp1.setVerticalAlignment(JLabel.CENTER);
			temp1.setBounds(coordinates[i], heights[i], 150, 30);
			this.comps.add(temp1);
			this.managementDisplay.add(temp1);
			if(i < 2)
			{
				JComboBox<String> temp2 = new JComboBox<String>();
				if(i == 0) this.createCustomerPicker(temp2);
				else this.createServicePicker(temp2);
				temp2.setSelectedIndex(-1);
				temp2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp2.setBackground(Color.white);
				temp2.setEditable(false);
				temp2.setBounds(coordinates[i]+150, heights[i], 220, 30);
				this.comps.add(temp2);
				this.managementDisplay.add(temp2);
			}
			else
			{
				JSpinner temp2 = new JSpinner();
				temp2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				temp2.setBackground(Color.white);
				temp2.setValue(1);
				temp2.setEditor(new JSpinner.DefaultEditor(temp2));
				((SpinnerNumberModel) temp2.getModel()).setMinimum(1);
				temp2.setBounds(coordinates[i]+150, heights[i], 220, 30);
				this.comps.add(temp2);
				this.managementDisplay.add(temp2);

			}
		}
		this.createIconTable(this.managementDisplay, tempList, new String[]{"Mã dịch vụ", "Tên dịch vụ", "Phí dịch vụ", "Số lượng thanh toán", "Bỏ dịch vụ"}, null, "./icon_image/remove.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 300, true, true);
	}
	
	public void refreshDiscount(ArrayList<Discount> lst)
	{
		String[][] discountList = new String[lst.size()][3];
		this.showAll(new String[] {"ƯU ĐÃI", ""});
		this.placeAll(new int[][] {{340, 48, 300, 30}, {320, 380, 300, 30}});
		for(Discount d : lst)
		{
			String fullName = StaffDao.getOneStaffById(d.getStaffId().trim()).getSurname() + " " + StaffDao.getOneStaffById(d.getStaffId().trim()).getName();
			discountList[lst.indexOf(d)][0] = String.valueOf(d.getDiscountLevel()) + "%";
			discountList[lst.indexOf(d)][1] = String.valueOf(this.numberFormat.format(d.getMoneyLevel()));
			discountList[lst.indexOf(d)][2] = String.valueOf(fullName);
		}
		if(this.role == 1)
		{
			this.displayAll(new Boolean[] {true, true, false, false, false, true, false, false, false, true, true});
			this.createNonIconTable(this.managementDisplay, discountList, new String[]{"Mức ưu đãi", "Mức tiền áp dụng", "Nhân viên thêm"}, SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
		else if(this.role == 2)
		{
			this.displayAll(new Boolean[] {true, true, true, false, false, true, false, false, false, true, true});
			this.createIconTable(this.managementDisplay, discountList, new String[]{"Mức ưu đãi", "Mức tiền áp dụng", "Nhân viên thêm", "Sửa/Xóa"}, "./icon_image/edit.png", "./icon_image/delete.png", SwingConstants.CENTER, true, 13, 35, new Color(0x9eb8b8), new Color(0xabb4f5), 5, 81, 958, 450, true, true);
		}
	}
	
	public void refreshAddDiscount()
	{	
		String[] infoList = new String[] {"Mức ưu đãi", "Mức tiền"};
		int height = 88;
		for(int i=0; i<2; i++)
		{
			JTextField temp = new JTextField();
			temp.setText(infoList[i]);
			temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			temp.addMouseListener(new CustomMouseListener(temp, null, this, null, null, Color.black, 1, 1));
			temp.setForeground(Color.gray);
			temp.addKeyListener(this);
			temp.setBounds(350, height, 240, 30);
			this.comps.add(temp);
			this.managementDisplay.add(temp);
			height = height + 40;
		}
		this.showAll(new String[] {"ĐIỀN THÔNG TIN ƯU ĐÃI MỚI", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
	}
	
	public void refreshUpdateDiscount(String moneyLevel)
	{	
		JTextField temp = new JTextField();
		temp.setText(moneyLevel);
		temp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		temp.setForeground(Color.black);
		temp.setBounds(350, 88, 240, 30);
		this.comps.add(temp);
		this.managementDisplay.add(temp);
		this.showAll(new String[] {"CHỈNH SỬA THÔNG TIN ƯU ĐÃI", ""});
		this.placeAll(new int[][] {{320, 48, 300, 30}, {315, 380, 300, 30}});
		this.displayAll(new Boolean[] {false, false, false, true, false, true, false, false, false, true, false});
	}
	
	public void refreshRevenue(ArrayList<Revenue> lst)
	{
		String[][] revenueList = new String[lst.size()][5];
		float total = 0;
		this.displayAll(new Boolean[] {true, true, false, false, false, false,true, true, true, true, false});
		this.showAll(new String[] {"DOANH THU", ""});
		this.placeAll(new int[][] {{331, 48, 300, 30}, {330, 380, 300, 30}});
		for(Revenue r : lst)
		{
			if(r.getId().substring(0, 2).equalsIgnoreCase("HD"))
			{
				revenueList[lst.indexOf(r)][0] = "Hóa đơn";
				total = total + r.getValue();
			}
			revenueList[lst.indexOf(r)][1] = r.getId();
			revenueList[lst.indexOf(r)][2] = r.getStaffName();
			revenueList[lst.indexOf(r)][3] = r.getDayCreated();
			revenueList[lst.indexOf(r)][4] = String.valueOf(this.numberFormat.format(r.getValue()));
			
		}
		infoEight.setText(" Tổng doanh thu >>> ");
		infoEight.setVisible(true);
		infoEleven.setText(" " + this.numberFormat.format(total) + " ");
		infoEleven.setHorizontalAlignment(JTextField.RIGHT);
		infoEleven.setVisible(true);
		this.createNonIconTable(this.managementDisplay, revenueList, new String[]{"Loại", "Mã", "Nhân viên lập", "Ngày lập", "Tổng tiền"}, SwingConstants.CENTER, true, 13, 35, Color.gray, new Color(0xabb4f5), 5, 81, 958, 412, true, true);
	}
	
	public void refreshStatistics()
	{
		Card staffInfo = new Card(new Color(0xf5475b), new Color(0xff0000));
		Card customerInfo = new Card(new Color(0xff8b3d), new Color(0xffa500));
		Card itemInfo = new Card(new Color(0x28ad11), new Color(0x00ff00));
		Card serviceInfo = new Card(new Color(0x91b1ed), new Color(0x0000ff));
		Chart overallFinanceChart = new Chart("Tổng quan kinh doanh", Color.white);
		Chart individualFinanceChart = new Chart("Thu nhập theo nhân viên", Color.white);
		Chart overallChart = new Chart("Hóa đơn dịch vụ lập theo năm", Color.white);
		ChartPanel chartPanelOne;
		ChartPanel chartPanelTwo;
		ChartPanel chartPanelThree;
		Statistics st = StatisticsDao.getOverallFinance();
		ArrayList<Statistics> lst =  StatisticsDao.getServiceReceiptByYear();
		ArrayList<String> service = new ArrayList<String>();
		ArrayList<String> year = new ArrayList<String>();
		String[] services = new String[StatisticsDao.totalBilledService];
		String[] years = new String[StatisticsDao.totalYear];
		double[][] quantity = new double[StatisticsDao.totalBilledService][StatisticsDao.totalYear];
		if(quantity.length != 0)
		{
			quantity[0][0] = 0;
			int i = 0;
			int j = 0;
			StatisticsDao.sortAcsByServiceName(lst);
			for(Statistics s : lst)
			{
				if(!service.contains(s.getServiceName()))
				{
					service.add(s.getServiceName());
					services[service.size()-1] = s.getServiceName();
				}
				if(!year.contains(String.valueOf(s.getYear())))
				{
					year.add(String.valueOf(s.getYear()));
					years[year.size()-1] = String.valueOf(s.getYear());
				}
			}
			for(Statistics s : lst)
			{
				if(s.getServiceName().equals(services[i]))
				{
					if(String.valueOf(s.getYear()).equals(years[j])) quantity[i][j] = quantity[i][j] + s.getReceiptByYearQuantity();
					else
					{
						if(j != years.length-1) j++;
						quantity[i][j] = s.getReceiptByYearQuantity();
					}
				}
				else
				{
					for(int temp=0; temp<years.length; temp++)
					{
						if(String.valueOf(s.getYear()).equals(years[temp]))
						{
							quantity[i+1][temp] = s.getReceiptByYearQuantity();
							j = temp;
							break;
						}
						else quantity[i+1][temp] = 0;
					}
					i++;
				}
			}
			year.clear();
			StatisticsDao.sortAcsByYear(lst);
			for(Statistics s : lst)
			{
				if(!year.contains(String.valueOf(s.getYear()))) 
				{
					year.add(String.valueOf(s.getYear()));
					years[year.size()-1] = String.valueOf(s.getYear());
				}
			}
		}
		this.displayAll(new Boolean[] {false, false, false, false, false, false, false, false, false, false, false});
		staffInfo.setData(new CardValue(new ImageIcon("./icon_image/staffInfo.png"), "Nhân viên", String.valueOf(StaffDao.getAllStaff()), ""));
		staffInfo.setBounds(5, 10, 235, 100);
		serviceInfo.setData(new CardValue(new ImageIcon("./icon_image/productInfo.png"), "Sản phẩm", String.valueOf(ItemDao.getAllItem().size()), ""));
		serviceInfo.setBounds(245, 10, 235, 100);
		//THÊM
		itemInfo.setData(new CardValue(new ImageIcon("./icon_image/serviceInfo.png"), "Dịch vụ", String.valueOf(ServiceDao.getAllService().size()), ""));
		itemInfo.setBounds(485, 10, 235, 100);
		customerInfo.setData(new CardValue(new ImageIcon("./icon_image/customerInfo.png"), "Khách hàng", String.valueOf(CustomerPersonalDao.getTotalCount()), ""));
		customerInfo.setBounds(725, 10, 235, 100);
		overallFinanceChart.createPieChart(new String[] {"Phiếu nhập", "Phiếu xuất", "Hóa đơn"}, new int[] {st.getImportQuantity(), st.getExportQuantity(), st.getReceiptQuantity()}, 3);
		overallFinanceChart.getChart().getTitle().setPaint((new Color(0x429e86)));
		individualFinanceChart.createBarChart(StatisticsDao.getIndividualFinance(), "Nhân viên", "Tiền thu (triệu đồng)");
		individualFinanceChart.getChart().getTitle().setPaint((new Color(0x429e86)));
		overallChart.createAreaChart(services, years, quantity, "Năm", "Tổng hóa đơn lập");
		overallChart.getChart().getTitle().setPaint((new Color(0x429e86)));
		chartPanelOne = new ChartPanel(overallFinanceChart.getChart());
		chartPanelOne.setBounds(5, 120, 480, 200);
		chartPanelTwo = new ChartPanel(individualFinanceChart.getChart());
		chartPanelTwo.setBounds(485, 120, 480, 200);
		chartPanelThree = new ChartPanel(overallChart.getChart());
		chartPanelThree.setBounds(5, 330, 960, 200);
		this.comps.add(staffInfo);
		this.comps.add(itemInfo);
		this.comps.add(serviceInfo);
		this.comps.add(customerInfo);
		this.comps.add(chartPanelOne);
		this.comps.add(chartPanelTwo);
		this.comps.add(chartPanelThree);
		this.managementDisplay.add(staffInfo);
		this.managementDisplay.add(itemInfo);
		this.managementDisplay.add(serviceInfo);
		this.managementDisplay.add(customerInfo);
		this.managementDisplay.add(chartPanelOne);
		this.managementDisplay.add(chartPanelTwo);
		this.managementDisplay.add(chartPanelThree);
	}
	
	public void refreshCustomerHistory()
	{
		ArrayList<Receipt> lst = ReceiptDao.getAllReceiptByCustomerId(CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim()).getId());
		String[][] customerReceiptList = new String[lst.size()][7];
		for(Receipt r : lst)
		{
			customerReceiptList[lst.indexOf(r)][0] = r.getReceiptId();
			customerReceiptList[lst.indexOf(r)][1] = r.getCustomerId();
			customerReceiptList[lst.indexOf(r)][2] = String.valueOf(this.numberFormat.format(r.getTotalMoney() / (float) (1 - (float) r.getDiscountLevel()/100)));
			customerReceiptList[lst.indexOf(r)][3] = String.valueOf(r.getDiscountLevel()) + "%";
			customerReceiptList[lst.indexOf(r)][4] = String.valueOf(this.numberFormat.format(r.getTotalMoney()));
			customerReceiptList[lst.indexOf(r)][5] = r.getStaffId();
			customerReceiptList[lst.indexOf(r)][6] = r.getReceiptDay();
		}
		this.createNonIconTable(this.historyWindow, customerReceiptList, new String[] {"", "", "", "", "", "", ""}, SwingConstants.CENTER, false, 15, 50, new Color(0x69b5b8), Color.white, 2, 247, 1267, 400, false, false);
	}
	
	public boolean sendEmail(String newPassword)
	{
		String fromEmail = StaffDao.getManagerGmail(1).trim();
		String toEmail = AccountDao.getResetGmail(this.restoreUserName.getText().trim()).trim();
		String subject = "Hồi phục mật khẩu cho tài khoản của bạn - JOLENE SPA";
		Properties properties = new Properties();
	    properties.setProperty("mail.transport.protocol", "smtp");     
	    properties.setProperty("mail.host", "smtp.gmail.com");  
	    properties.put("mail.smtp.auth", "true"); 
	    properties.put("mail.smtp.starttls.enable", "true");

	    properties.put("mail.smtp.ssl.enable", "true"); 
	    properties.put("mail.smtp.port", "465");  
	    properties.put("mail.debug", "true");  
	    properties.put("mail.smtp.socketFactory.port", "465");  
	    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    properties.put("mail.smtp.socketFactory.fallback", "false"); 
		Session session = Session.getDefaultInstance(properties, new Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail, "twiceblackpink");
			}
		});
		try 
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText("Mật khẩu mới được hồi phục là: " + newPassword);
			Transport.send(message);
			return true;
		}
		catch(Exception ex)
		{
			JOptionPane.showOptionDialog(null, "Gửi email đã xảy ra lỗi, vui lòng kiểm tra lại các thông tin đã nhập !\nLỗi: " + ex.getMessage() + " !", "LỖI KẾT NỐI", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			return false;
		}
	}
	
	public int checkDayScheduled(String dayScheduled, String dayNow)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		Date date1;
		Date date2;
        try 
        {
        	date1 = sdf.parse(dayScheduled);
    		date2 = sdf.parse(dayNow);
		} 
        catch (ParseException e) 
        {
			return 2;
		}
        int result = date1.compareTo(date2);
        if (result == 0 || result < 0) return 0;
        else if (result > 0) return 1;
        else return 2;
	}
	
	public float checkDiscount(float totalValue)
	{
		ArrayList<Discount> lst = DiscountDao.getAllDiscount();
		DiscountDao.sortDescByMoneyLevel(lst);
		this.tempId = String.valueOf(0);
		if(lst.size() == 0) return totalValue;
		else
		{
			for(Discount d : lst)
			{
				if(totalValue >= d.getMoneyLevel())  
				{
					this.tempId = String.valueOf(d.getDiscountLevel());
					return totalValue - ((d.getDiscountLevel()/(float)100)*totalValue);
				}
			}
		}
		return totalValue;
	}
	
	public String checkIllegalQuantity()
	{
		ArrayList<Item> itemList = new ArrayList<Item>();
		ArrayList<String> itemIdList= new ArrayList<String>();
		for(Formula f : FormulaDao.formulaList)
		{
			if(!itemIdList.contains(f.getItemId()))
			{
				Item i = new Item();
				i.setItemId(f.getItemId());
				i.setQuantity(f.getQuantity());
				itemIdList.add(f.getItemId());
				itemList.add(i);
			}
			else
			{
				Item i = itemList.get(itemIdList.indexOf(f.getItemId()));
				i.setQuantity(i.getQuantity() + f.getQuantity());
			}
		}
		for(Item i : itemList)
		{
			Item temp = ItemDao.getOneItemById(i.getItemId());
			if(temp.getAmount() <= 0) return temp.getItemName();
			else
			{
				if(i.getQuantity() <=  temp.getQuantity())
				{
					i.setQuantity(temp.getQuantity() - i.getQuantity());
					if(i.getQuantity() / temp.getItemQuantity() == 1) i.setAmount((int) (i.getQuantity() / temp.getItemQuantity())); 
					else i.setAmount((int) Math.floor((i.getQuantity() / temp.getItemQuantity())));
					ItemDao.updateQuantyAmount(i);
				}
				else return temp.getItemName();
			}
		}
		return "";
	}
	
	@SuppressWarnings({"deprecation", "unchecked"})
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.login)
		{
			if(this.userName.getText().trim().isEmpty() || this.userName.getForeground().equals(new Color(0x55515c)))
			{
				JOptionPane.showOptionDialog(null, "Tên tài khoản không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.passwordHolder.getText().trim().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Mật khẩu không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			Account account = new Account();
			int check = AccountDao.checkAccount(this.userName.getText().trim(), this.passwordHolder.getText().trim(), account);
			if(check == 1)
			{
				this.acc = this.userName.getText().trim();
				this.pwd = this.passwordHolder.getText().trim();
				this.role = account.getRole();
				this.dispayLogin(false);
				if(this.role == 1 || this.role == 2)
				{
					if(this.role == 1)
					{
						this.staff.setEnabled(true);
						this.revenue.setEnabled(true);
						this.statistics.setEnabled(true);
					}
					else if(this.role == 2)
					{
						this.staff.setEnabled(false);
						this.revenue.setEnabled(false);
						this.statistics.setEnabled(false);
					}
					this.managementSection.setVisible(false);
					this.line1.setVisible(true);
					this.resetInfo();
					this.refreshPersonal();
					this.moveScreen(new JPanel(), this.managementWindow, "LEFT");
				}
				else if(this.role == 3) this.moveScreen(new JPanel(), this.mainWindow, "LEFT");
			}
			else if(check == 2) JOptionPane.showOptionDialog(null, "Tài khoản không đúng !", "LỖI ĐĂNG NHẬP", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			else if(check == 3) JOptionPane.showOptionDialog(null, "Mật khẩu không đúng !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			else if(check == 4) JOptionPane.showOptionDialog(null, "Tài khoản của bạn đã bị khóa !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
		}
		if(e.getSource() == this.signup)
		{
			if(!this.checkAllFilled(new JTextField[] {this.newUserName, this.surname, this.name, this.mail, this.phoneNumber, this.newPassword}))
			{
				JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi đăng ký !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkOnlyLetter(this.surname))
			{
				JOptionPane.showOptionDialog(null, "Trong họ không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkOnlyLetter(this.surname))
			{
				JOptionPane.showOptionDialog(null, "Trong tên không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkValidGmail(this.mail))
			{
				JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}	
			if(!checkOnlyNumber(this.phoneNumber))
			{
				JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.phoneNumber.getText().trim().length() != 10)
			{
				JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!CustomerPersonalDao.checkDuplicatePhoneNumberRegister(this.phoneNumber.getText().trim()))
			{
				JOptionPane.showOptionDialog(null, "Số điện thoại đã được đăng ký !\nVui lòng sử dụng số khác", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			String account = this.newUserName.getText().trim();
			String password = this.newPassword.getText().trim();
			String id = CustomerPersonalDao.checkQuantity().trim().isEmpty() ? "KH0" : "KH" + String.valueOf(Integer.parseInt(CustomerPersonalDao.checkQuantity().trim().substring(2, CustomerPersonalDao.checkQuantity().trim().length()))+1);
			String surname = this.surname.getText().trim();
			String name = this.name.getText().trim();
			String phoneNumber = this.phoneNumber.getText().trim();
			String gmail = this.mail.getText().trim();
			int check = 0;
			check = AccountDao.createAccount(new Account(account, password, 3, 1));
			if(check == 0) return;
			if(check == 2) 
			{
				JOptionPane.showOptionDialog(null, "Tên tài khoản bị trùng !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!CustomerPersonalDao.addCustomerPersonal(new CustomerPersonal(id, surname, name, gmail, phoneNumber, account)))
			{
				AccountDao.deleteAccount(account);
				return;
			}
			JOptionPane.showOptionDialog(null, "Tạo tài khoản thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);			
			this.moveScreen(this.signupWindow, this.mainWindow, "LEFT");
			this.acc = this.newUserName.getText().trim();
		}
		if(e.getSource() == this.signupGoBack)
		{
			this.resetTemplate();
			this.moveScreen(this.signupWindow, new JPanel(), "RIGHT");
			this.dispayLogin(true);
		}
		if(e.getSource() == this.resetMail)
		{
			String newPassword = String.valueOf(new Random().hashCode());
			boolean check = AccountDao.updateAccount(this.restoreUserName.getText().trim(), newPassword);
			if(check) if(this.sendEmail(newPassword.trim())) JOptionPane.showOptionDialog(null, "Hệ thống đã hồi phục mật khẩu thành công !\nBạn hãy vào gmail cá nhân và kiểm tra đã có mật khẩu mới chưa nhé", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
		}
		if(e.getSource() == this.resetMailGoBack)
		{
			this.resetTemplate();
			this.dispayLogin(true);
			this.moveScreen(this.resetMailWindow, new JPanel(), "RIGHT");
		}
		if(e.getSource() == this.updateManagement)
		{
			if(this.role == 1)
			{
				JTextField[] tempList = new JTextField[] {this.personalSurname, this.personalName, this.personalSex, this.personalId, this.personalBirthDate, this.personalHomeTown, this.personalGmail, this.personalPhoneNumber};
				int check;
				if(!this.checkAllFilled(tempList))
				{
					JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi đăng ký !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(this.personalSurname))
				{
					JOptionPane.showOptionDialog(null, "Trong họ không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(this.personalName))
				{
					JOptionPane.showOptionDialog(null, "Trong tên không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!(this.personalSex.getText().trim().equalsIgnoreCase("Nam") || this.personalSex.getText().trim().equalsIgnoreCase("Nữ")))
				{
					JOptionPane.showOptionDialog(null, "Phái chỉ có thể là 'nam' hoặc 'nữ' !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(this.personalId))
				{
					JOptionPane.showOptionDialog(null, "Trong Cccd/Cmnd chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.personalId.getText().trim().length() != 12 && this.personalId.getText().trim().length() != 9)
				{
					JOptionPane.showOptionDialog(null, "Cccd/Cmnd phải có 9 hoặc 12 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.splitDate(this.personalBirthDate.getText().trim()).size() != 3)
				{
					JOptionPane.showOptionDialog(null, "Ngày sinh không hợp lệ !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				String year = this.splitDate(this.personalBirthDate.getText().trim()).get(2);
				String month = this.splitDate(this.personalBirthDate.getText().trim()).get(1);
				String day = this.splitDate(this.personalBirthDate.getText().trim()).get(0);
				this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				check = this.checkDayScheduled(year + "-" + month + "-" + day, this.dateNow);
				if(check == 2)
				{
					JOptionPane.showOptionDialog(null, "Ngày sinh không hợp lệ !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else if(check == 1)
				{
					JOptionPane.showOptionDialog(null, "Ngày sinh phải trước ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkValidGmail(this.personalGmail))
				{
					JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(this.personalPhoneNumber))
				{
					JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.personalPhoneNumber.getText().trim().length() != 10)
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!AccountDao.checkDuplicatePhoneNumberUpdate(this.personalPhoneNumber.getText().trim(), this.acc.trim(), this.role))
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại đã được đăng ký !\nVui lòng sử dụng số khác", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Staff s = new Staff();
				s.setSurname(this.personalSurname.getText().trim());
				s.setName(this.personalName.getText().trim());
				s.setSex(this.personalSex.getText().trim());
				s.setId(this.personalId.getText().trim());
				s.setBirthDate(year + "-" + month + "-" + day);
				s.setHomeTown(this.personalHomeTown.getText().trim());
				s.setGmail(this.personalGmail.getText().trim());
				s.setPhoneNumber(this.personalPhoneNumber.getText().trim());
				s.setAccount(this.personalAccount.getText().trim());
				if(StaffDao.updateManagerPersonal(s)) JOptionPane.showOptionDialog(null, "Cập nhật thông tin cá nhân thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			else if(this.role == 2)
			{
				if(this.personalGmail.getText().trim().isEmpty())
				{
					JOptionPane.showOptionDialog(null, "Gmail không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.personalPhoneNumber.getText().trim().isEmpty())
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkValidGmail(this.personalGmail))
				{
					JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(this.personalPhoneNumber))
				{
					JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.personalPhoneNumber.getText().trim().length() != 10)
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!AccountDao.checkDuplicatePhoneNumberUpdate(this.personalPhoneNumber.getText().trim(), this.acc.trim(), this.role))
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại đã được đăng ký !\nVui lòng sử dụng số khác", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Staff s = new Staff();
				s.setGmail(this.personalGmail.getText().trim());
				s.setPhoneNumber(this.personalPhoneNumber.getText().trim());
				s.setAccount(this.personalAccount.getText().trim());
				if(StaffDao.updateStaffPersonal(s)) JOptionPane.showOptionDialog(null, "Cập nhật thông tin cá nhân thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
		}
		if(e.getSource() == this.changePasswordManagement)
		{
			if(this.personalPassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng nhập mật khẩu cũ để xác nhận !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.personalNewPassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng nhập mật khẩu mới !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.personalConfirmPassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng xác nhận lại mật khẩu mới !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.personalPassword.getText().equals(this.pwd))
			{
				JOptionPane.showOptionDialog(null, "Nhập mật khẩu cũ không chính xác !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.personalNewPassword.getText().equals(this.personalConfirmPassword.getText()))
			{
				JOptionPane.showOptionDialog(null, "Xác nhận lại mật khẩu mới không đúng !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(AccountDao.updateAccount(this.acc.trim(), this.personalNewPassword.getText()))
			{
				this.resetTemplate();
				this.moveScreen(this.managementWindow, new JPanel(), "RIGHT");
				this.dispayLogin(true);
				this.keepTrack.clear();
				JOptionPane.showOptionDialog(null, "Cập nhật mật khẩu thành công !\nMật khẩu cũ không còn hiệu lực nên bạn vui lòng đăng nhập lại với mật khẩu mới", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
		}
		if(e.getSource() == this.staff)
		{
			this.refreshInfo(1, "Tìm họ tên nhân viên");
			this.refreshAccount(AccountDao.getAllAccount(2), 2);
		}
		if(e.getSource() == this.customer)
		{
			this.refreshInfo(2, "Tìm họ tên khách hàng");
			this.refreshAccount(AccountDao.getAllAccount(3), 3);
		}
		if(e.getSource() == this.item)
		{
			this.refreshInfo(3, "Tìm tên sản phẩm");
			this.refreshItem(ItemDao.getAllItem());
			this.managementOne.setText("Xem phiếu");
		}
		if(e.getSource() == this.service)
		{
			this.refreshInfo(4, "Tìm tên dịch vụ");
			this.refreshService(ServiceDao.getAllService());
		}
		//THÊM
		if(e.getSource() == this.notify)
		{
			this.refreshInfo(100, "Tìm tên sản phẩm trong nhập hàng");
			this.refreshMachine(MachineDao.getAllMachine());
			this.managementOne.setText("Lập phiếu");
			//this.refreshMachine(ItemDao.getAllItem());
		}
		
		if(e.getSource() == this.schedule)
		{
			this.refreshInfo(5, "Tìm họ tên khách hàng");
			this.refreshSchedule(ScheduleDao.getScheduleByStatus(0), 0);
			this.managementPickerInfo.setText("Trạng thái lịch hẹn:");
			this.managementPickerTwo.removeAllItems();
			this.managementPickerTwo.setModel(new DefaultComboBoxModel<String>(new String[] {"Đang chờ xác nhận", "Đã xác nhận", "Đã hủy"}));
			this.managementPickerTwo.setSelectedIndex(0);
		}
		if(e.getSource() == this.billing)
		{
			this.refreshInfo(6, "Tìm họ tên nhân viên");
			this.refreshReceipt(this.role == 1 ? ReceiptDao.getAllReceipt() : ReceiptDao.getAllReceiptByStaffId(StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId()));
			this.managementOne.setText("Xem ưu đãi");
		}
		if(e.getSource() == this.revenue)
		{
			this.refreshInfo(7, "Tìm họ tên nhân viên");
			this.refreshRevenue(RevenueDao.getRevenue(Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.YEAR)));
			this.managementPickerInfo.setText("Xem theo tháng/năm:");
			this.managementPickerOne.removeAllItems();
			this.managementPickerTwo.removeAllItems();
			this.createMonthPicker(this.managementPickerOne);
			this.managementPickerOne.setSelectedItem(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1));
			this.createYearPicker(this.managementPickerTwo);
			this.managementPickerTwo.setSelectedItem(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		}
		if(e.getSource() == this.statistics)
		{
			this.refreshInfo(8, "");
			this.refreshStatistics();
		}
		if(e.getSource() == this.managementAdd)
		{
			if(this.menu == 1)
			{
				this.managementTwo.setText("Quay lại");
				this.refreshInfo(11, "");
				this.refreshAddStaff();
			}
			else if(this.menu == 3)
			{
				this.managementTwo.setText("Quay Lại");
				this.refreshInfo(31, "");
				this.refreshAddItem();
			}
			else if(this.menu == 4)
			{
				this.managementOne.setText("Thêm sản phẩm");
				this.managementTwo.setText("Quay lại");
				this.refreshInfo(41, "");
				this.refreshAddServiceFormula("Tên dịch vụ", "Giá dịch vụ", new Color[] {null, Color.gray, Color.gray, null, null, Color.gray}, true);
			}
			else if(this.menu == 6)
			{
				this.managementOne.setText("Thêm dịch vụ");
				this.managementTwo.setText("Quay lại");
				this.refreshInfo(62, "");
				this.refreshAddReceipt();
			}
			else if(this.menu == 63)
			{
				this.refreshInfo(631, "");
				this.refreshAddDiscount();
			}
			else if(this.menu == 100)
			{
				this.managementOne.setText("Nhập");
				this.managementPickerTwo.setSelectedIndex(-1);
				this.refreshInfo(332, "");
				this.refreshAddImportExportMachine(MachineDao.getAllMachine());
			}
			/*else if(this.menu == 10)
			{
				this.managementTwo.setText("Quay Lại");
				this.refreshInfo(31, "");
				this.refreshAddItem();
			}*/
		}
		
		
		if(e.getSource() == this.managementUpdate)
		{
			if(this.menu == 11)
			{
				JTextField[] tempList = new JTextField[8];
				this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				String yearScheduled;
				String monthScheduled;
				String dayScheduled;
				String dateScheduled1;
				String dateScheduled2;
				int check;
				int pos = 0;
				for(int i=0; i<14; i++)
				{
					if(i < 4 || i > 9)
					{
						tempList[pos] = ((JTextField) this.comps.get(i));
						pos++;
					}
				}
				if(!this.checkAllFilled(tempList) || ((JDatePickerImpl) this.comps.get(7)).getModel().getValue() == null || ((JDatePickerImpl) this.comps.get(9)).getModel().getValue() == null || ((JComboBox<String>) this.comps.get(4)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(((JTextField) this.comps.get(2))))
				{
					JOptionPane.showOptionDialog(null, "Trong họ không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(((JTextField) this.comps.get(3))))
				{
					JOptionPane.showOptionDialog(null, "Trong tên không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(10))))
				{
					JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(10)).getText().trim().length() != 10)
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(11))))
				{
					JOptionPane.showOptionDialog(null, "Trong Cccd/Cmnd chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(11)).getText().trim().length() != 12 && ((JTextField) this.comps.get(11)).getText().trim().length() != 9)
				{
					JOptionPane.showOptionDialog(null, "Cccd/Cmnd phải có 9 hoặc 12 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkValidGmail(((JTextField) this.comps.get(12))))
				{
					JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				yearScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getYear());
				monthScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getMonth()+1);
				dayScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getDay());
				dateScheduled1 = yearScheduled + "-" + monthScheduled + "-" + dayScheduled;
				check = this.checkDayScheduled(dateScheduled1, this.dateNow);
				if(check == 2)
				{
					JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi, vui lòng thử lại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else if(check == 1)
				{
					JOptionPane.showOptionDialog(null, "Ngày sinh phải trước ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				yearScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(9)).getModel().getYear());
				monthScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(9)).getModel().getMonth()+1);
				dayScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(9)).getModel().getDay());
				dateScheduled2 = yearScheduled + "-" + monthScheduled + "-" + dayScheduled;
				check = this.checkDayScheduled(dateScheduled2, this.dateNow);
				if(check == 2)
				{
					JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi, vui lòng thử lại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else if(check == 1)
				{
					JOptionPane.showOptionDialog(null, "Ngày vào làm phải trước ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!StaffDao.checkDuplicatePhoneNumberRegister(((JTextField) this.comps.get(10)).getText().trim()))
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại đã tồn tại !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Staff s = new Staff();
				String id = StaffDao.checkQuantity().isEmpty() ? "NV1" : "NV" + String.valueOf(Integer.parseInt(StaffDao.checkQuantity().trim().substring(2, StaffDao.checkQuantity().trim().length()))+1);
				s.setStaffId(id);
				s.setSurname(((JTextField) this.comps.get(2)).getText().trim());
				s.setName(((JTextField) this.comps.get(3)).getText().trim());
				s.setSex(((JComboBox<String>) this.comps.get(4)).getSelectedItem().toString().trim());
				s.setPhoneNumber(((JTextField) this.comps.get(10)).getText().trim());
				s.setId(((JTextField) this.comps.get(11)).getText().trim());
				s.setGmail(((JTextField) this.comps.get(12)).getText().trim());
				s.setBirthDate(dateScheduled1.trim());
				s.setHomeTown(((JTextField) this.comps.get(13)).getText().trim());
				s.setDayWork(dateScheduled2.trim());
				s.setAccount((((JTextField) this.comps.get(0)).getText().trim()));
				int checkOne = AccountDao.createAccount(new Account(((JTextField) this.comps.get(0)).getText().trim(), ((JTextField) this.comps.get(1)).getText().trim(), 2, 1));
				boolean checkTwo = StaffDao.addStaff(s);
				if(checkOne == 1 && checkTwo)
				{
					JOptionPane.showOptionDialog(null, "Thêm nhân viên thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);	
					this.refreshInfo(1, "Tìm họ tên nhân viên");
					this.refreshAccount(AccountDao.getAllAccount(2), 2);
				}
				else if(checkOne == 2) JOptionPane.showOptionDialog(null, "Tên tài khoản bị trùng !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			else if(this.menu == 12)
			{
				JTextField[] tempList = new JTextField[6];
				this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				String yearScheduled;
				String monthScheduled;
				String dayScheduled;
				String dateScheduled1;
				String dateScheduled2;
				int check;
				int pos = 0;
				for(int i=0; i<12; i++)
				{
					if(i < 2 || i > 7)
					{
						tempList[pos] = ((JTextField) this.comps.get(i));
						pos++;
					}
				}
				if(!this.checkAllFilled(tempList) || ((JDatePickerImpl) this.comps.get(5)).getModel().getValue() == null || ((JDatePickerImpl) this.comps.get(7)).getModel().getValue() == null || ((JComboBox<String>) this.comps.get(2)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi cập nhật !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(((JTextField) this.comps.get(0))))
				{
					JOptionPane.showOptionDialog(null, "Trong họ không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyLetter(((JTextField) this.comps.get(1))))
				{
					JOptionPane.showOptionDialog(null, "Trong tên không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(8))))
				{
					JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(8)).getText().trim().length() != 10)
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(9))))
				{
					JOptionPane.showOptionDialog(null, "Trong Cccd/Cmnd chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(9)).getText().trim().length() != 12 && ((JTextField) this.comps.get(9)).getText().trim().length() != 9)
				{
					JOptionPane.showOptionDialog(null, "Cccd/Cmnd phải có 9 hoặc 12 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkValidGmail(((JTextField) this.comps.get(10))))
				{
					JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				yearScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(5)).getModel().getYear());
				monthScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(5)).getModel().getMonth()+1);
				dayScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(5)).getModel().getDay());
				dateScheduled1 = yearScheduled + "-" + monthScheduled + "-" + dayScheduled;
				check = this.checkDayScheduled(dateScheduled1, this.dateNow);
				if(check == 2)
				{
					JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi, vui lòng thử lại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else if(check == 1)
				{
					JOptionPane.showOptionDialog(null, "Ngày sinh phải trước ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				yearScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getYear());
				monthScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getMonth()+1);
				dayScheduled = String.valueOf(((JDatePickerImpl) this.comps.get(7)).getModel().getDay());
				dateScheduled2 = yearScheduled + "-" + monthScheduled + "-" + dayScheduled;
				check = this.checkDayScheduled(dateScheduled2, this.dateNow);
				if(check == 2)
				{
					JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi, vui lòng thử lại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else if(check == 1)
				{
					JOptionPane.showOptionDialog(null, "Ngày vào làm phải trước ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!AccountDao.checkDuplicatePhoneNumberUpdate(((JTextField) this.comps.get(8)).getText().trim(), this.acc.trim(), this.role))
				{
					JOptionPane.showOptionDialog(null, "Số điện thoại đã tồn tại !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Staff s = new Staff();
				s.setAccount(this.data.getValueAt(this.data.getSelectedRow(), 0).toString().trim());
				s.setSurname(((JTextField) this.comps.get(0)).getText().trim());
				s.setName(((JTextField) this.comps.get(1)).getText().trim());
				s.setSex(((JComboBox<String>) this.comps.get(2)).getSelectedItem().toString().trim());
				s.setPhoneNumber(((JTextField) this.comps.get(8)).getText().trim());
				s.setId(((JTextField) this.comps.get(9)).getText().trim());
				s.setGmail(((JTextField) this.comps.get(10)).getText().trim());
				s.setBirthDate(dateScheduled1.trim());
				s.setHomeTown(((JTextField) this.comps.get(11)).getText().trim());
				s.setDayWork(dateScheduled2.trim());
				if(StaffDao.updateStaff(s)) JOptionPane.showOptionDialog(null, "Cập nhật thông tin nhân viên thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			else if(this.menu == 31 || this.menu == 32)
			{
				if(((JTextField) this.comps.get(2)).getText().trim().isEmpty() || !this.comps.get(2).getForeground().equals(Color.black))
				{
					JOptionPane.showOptionDialog(null, "Tên sản phẩm không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!(this.checkFloat(((JTextField) this.comps.get(3))) && this.comps.get(3).getForeground().equals(Color.black) || this.comps.get(3).getForeground().equals(Color.gray)))
				{
					JOptionPane.showOptionDialog(null, "Dung tích chỉ được chứa số hoặc dấu '.' !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Item i = new Item();
				i.setItemName(((JTextField) this.comps.get(2)).getText().trim());
				if(!((JTextField) this.comps.get(3)).getText().trim().isEmpty() && this.comps.get(3).getForeground().equals(Color.black)) i.setItemQuantity(Float.parseFloat(((JTextField) this.comps.get(3)).getText().trim()));
				if(!((JTextField) this.comps.get(4)).getText().trim().isEmpty() && this.comps.get(4).getForeground().equals(Color.black)) i.setUnit(((JTextField) this.comps.get(4)).getText().trim());
				i.setAmount(0);
				i.setImage(((JTextField) this.comp).getText().trim());
				if(this.menu == 31)
				{
					String id = ItemDao.checkQuantity().isEmpty() ? "SP0" : "SP" + String.valueOf(Integer.parseInt(ItemDao.checkQuantity().trim().substring(2, ItemDao.checkQuantity().trim().length()))+1);
					i.setItemId(id);
					if(ItemDao.addItem(i))
					{
						JOptionPane.showOptionDialog(null, "Thêm sản phẩm thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						this.refreshInfo(3, "Tìm tên sản phẩm");
						this.refreshItem(ItemDao.getAllItem());
					}
				}
				else if(this.menu == 32)
				{
					String id = this.data.getValueAt(this.data.getSelectedRow(), 0).toString();
					i.setItemId(id);
					if(ItemDao.updateItem(i)) JOptionPane.showOptionDialog(null, "Cập nhật sản phẩm thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				}
			}
			else if(this.menu == 332)
			{
				if(this.managementPickerTwo.getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Không thể lập mà chưa chọn loại phiếu !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.data.getRowCount() == 0 )
				{
					JOptionPane.showOptionDialog(null, "Phiếu lập phải chứa ít nhất một sản phẩm !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else
				{
					ImportExport ie;
					ArrayList<ImportExportDetail> lst = new ArrayList<ImportExportDetail>();
					this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					String id = "";
					float totalValue = 0;
					if(this.managementPickerTwo.getSelectedIndex() == 0) id = ImportExportDao.checkQuantity("PN").isEmpty() ? "PN0" : "PN" + String.valueOf(Integer.parseInt(ImportExportDao.checkQuantity("PN").trim().substring(2, ImportExportDao.checkQuantity("PN").trim().length()))+1);
					else id = ImportExportDao.checkQuantity("PX").isEmpty() ? "PX0" : "PX" + String.valueOf(Integer.parseInt(ImportExportDao.checkQuantity("PX").trim().substring(2, ImportExportDao.checkQuantity("PX").trim().length()))+1);
					for(int i=0; i<this.data.getRowCount(); i++)
					{
						String itemName = checkItemAmountIllegal(this.data.getValueAt(i, 0).toString().trim(), Integer.parseInt(this.data.getValueAt(i, 3).toString().trim()));
						if(!itemName.isEmpty() && this.managementPickerTwo.getSelectedIndex() == 1)
						{
							JOptionPane.showOptionDialog(null, "Sản phẩm '" + itemName + "' hiện đang không có đủ số lượng để xuất !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
							return;
						}
						String itemId =  this.data.getValueAt(i, 0).toString().trim();
						float quantity = (float)Integer.parseInt(this.data.getValueAt(i, 2).toString().trim().substring(0, this.data.getValueAt(i, 2).toString().trim().length()-2).replace(".", ""));
						int amount = Integer.parseInt(this.data.getValueAt(i, 3).toString().trim());
						lst.add(new ImportExportDetail(id, itemId, quantity, amount));
						totalValue = totalValue + (quantity*amount);
					}
					ie = new ImportExport(id, totalValue, this.dateNow, StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId().trim());
					if(ImportExportDao.addImportExport(ie))
					{
						if(ImportExportDetailDao.addImportExportDetail(lst, this.managementPickerTwo.getSelectedIndex()))
						{
							JOptionPane.showOptionDialog(null, "Lập phiếu thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
							this.managementPickerTwo.setSelectedIndex(-1);
							this.refreshInfo(33, "");
							//SỬA
							//this.refreshImportExport(new ArrayList<ImportExport>());
							this.refreshAddImportExport();
						}
					}
				}
			}
			else if(this.menu == 41 || menu == 42)
			{
				if(((JTextField) this.comps.get(1)).getText().trim().isEmpty() || ((JTextField) this.comps.get(1)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Tên dịch vụ không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(2)).getText().trim().isEmpty() || ((JTextField) this.comps.get(2)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Giá dịch vụ không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyNumber(((JTextField) this.comps.get(2))))
				{
					JOptionPane.showOptionDialog(null, "Giá dịch vụ chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				String name = ((JTextField) this.comps.get(1)).getText().trim();
				float price = (float) Integer.parseInt(((JTextField) this.comps.get(2)).getText().trim());
				if(this.menu == 41)
				{
					String id = ServiceDao.checkQuantity().isEmpty() ? "DV0" : "DV" + String.valueOf(Integer.parseInt(ServiceDao.checkQuantity().trim().substring(2, ServiceDao.checkQuantity().trim().length()))+1);
					String staffId  = StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId().trim();
					if(!ServiceDao.addService(new Service(id, name, price, staffId))) return;
					if(this.data.getRowCount() != 0)
					{
						ArrayList<Formula> lst = new ArrayList<Formula>();
						for(int i=0; i<this.data.getRowCount(); i++)
						{
							String itemId = this.data.getValueAt(i, 0).toString().trim();
							float quantity = Float.parseFloat(this.data.getValueAt(i, 2).toString().trim());
							lst.add(new Formula(id, itemId, quantity));
						}
						if(!FormulaDao.addFormula(lst)) return;
					}
					this.refreshInfo(4, "Tìm tên dịch vụ");
					this.refreshService(ServiceDao.getAllService());
					JOptionPane.showOptionDialog(null, "Thêm dịch vụ thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				}
				else if(this.menu == 42)
				{
					if(!ServiceDao.updateService(new Service(this.tempId.trim(), name, price, null))) return;
					ArrayList<Formula> lst = new ArrayList<Formula>();
					for(int i=0; i<this.data.getRowCount(); i++)
					{
						String itemId = this.data.getValueAt(i, 0).toString().trim();
						float quantity = Float.parseFloat(this.data.getValueAt(i, 2).toString().trim());
						lst.add(new Formula(this.tempId.trim(), itemId, quantity));
					}
					if(!FormulaDao.updateFormula(lst, this.tempId.trim())) return;
					JOptionPane.showOptionDialog(null, "Cập nhật dịch vụ thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				}
			}
			else if(this.menu == 62)
			{
				if(this.data.getRowCount() == 0 )
				{
					JOptionPane.showOptionDialog(null, "Hóa đơn phải chứa ít nhất một dịch vụ !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				ArrayList<ReceiptDetail> lst = new ArrayList<ReceiptDetail>();
				FormulaDao.formulaList.clear();
				String id = ReceiptDao.checkQuantity().isEmpty() ? "HD0" : "HD" + String.valueOf(Integer.parseInt(ReceiptDao.checkQuantity().trim().substring(2, ReceiptDao.checkQuantity().trim().length()))+1);
				float totalValue = 0;
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					float price = Float.parseFloat(this.data.getValueAt(i, 2).toString().trim().substring(0, this.data.getValueAt(i, 2).toString().trim().length()-2).replace(".", ""));
					int amount = Integer.parseInt(this.data.getValueAt(i, 3).toString().trim());
					FormulaDao.checkServiceExist(this.data.getValueAt(i, 0).toString().trim(), amount);
					totalValue = totalValue + (price*amount);
					lst.add(new ReceiptDetail(id, this.data.getValueAt(i, 0).toString().trim(), price, amount));
				}
				String check = this.checkIllegalQuantity();
				if(!check.isEmpty())
				{
					 JOptionPane.showOptionDialog(null, "Lượng của sản phẩm " + check + " không còn đủ để làm các dịch vụ đã chọn !\nHãy nhập thêm sản phẩm này để sử dụng", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					 return;
				}
				String staffId = StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId().trim();
				String customerId = ((JComboBox<String>) this.comps.get(1)).getSelectedIndex() == -1 ? null : "KH" + ((JComboBox<String>) this.comps.get(1)).getSelectedIndex();
				this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				float finalPrice = this.checkDiscount(totalValue);
				if(ReceiptDao.addReceipt(new Receipt(id, staffId, customerId, Integer.parseInt(this.tempId), this.dateNow, finalPrice))) 
				{
					if(!ReceiptDetailDao.addReceiptDetail(lst)) return;
					JOptionPane.showOptionDialog(null, "Thêm hóa đơn thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					this.refreshInfo(6, "Tìm họ tên nhân viên");
					this.refreshReceipt(this.role == 1 ? ReceiptDao.getAllReceipt() : ReceiptDao.getAllReceiptByStaffId(StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId()));
				}
			}
			else if(this.menu == 631)
			{
				JTextField[] tempList = new JTextField[] {((JTextField) this.comps.get(0)), ((JTextField) this.comps.get(1))};
				if(!this.checkAllFilled(tempList))
				{
					JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(0))))
				{
					JOptionPane.showOptionDialog(null, "Mức ưu đãi chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(1))))
				{
					JOptionPane.showOptionDialog(null, "Mức tiền chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(DiscountDao.checkDiscountLevelExist(Integer.parseInt(((JTextField) this.comps.get(0)).getText().trim())) == 1)
				{
					JOptionPane.showOptionDialog(null, "Mức ưu đãi này đã tồn tại !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Discount d = new Discount();
				d.setDiscountLevel(Integer.parseInt(((JTextField) this.comps.get(0)).getText().trim()));
				d.setMoneyLevel(Float.parseFloat(((JTextField) this.comps.get(1)).getText().trim()));
				d.setStaffId(StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId().trim());
				if(DiscountDao.addDiscount(d))
				{
					JOptionPane.showOptionDialog(null, "Thêm ưu đãi thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					this.refreshInfo(63, "Tìm mức ưu đãi");
					this.refreshDiscount(DiscountDao.getAllDiscount());
				}
			}
			else if(this.menu == 632)
			{
				if(((JTextField) this.comps.get(0)).getText().trim().isEmpty() || ((JTextField) this.comps.get(0)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Mức tiền không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkOnlyNumber(((JTextField) this.comps.get(0))))
				{
					JOptionPane.showOptionDialog(null, "Mức tiền chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				Discount d = new Discount();
				d.setDiscountLevel(Integer.parseInt(this.data.getValueAt(this.data.getSelectedRow(), 0).toString().replace("%", "")));
				d.setMoneyLevel(Float.parseFloat(((JTextField) this.comps.get(0)).getText().trim()));
				if(DiscountDao.updateDiscount(d)) JOptionPane.showOptionDialog(null, "Cập nhật ưu đãi thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			else if(this.menu == 101)
			{
				if(this.managementPickerTwo.getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Không thể lập mà chưa chọn loại phiếu !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(this.data.getRowCount() == 0 )
				{
					JOptionPane.showOptionDialog(null, "Phiếu lập phải chứa ít nhất một sản phẩm !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				else
				{
					ImportExport ie;
					ArrayList<ImportExportDetail> lst = new ArrayList<ImportExportDetail>();
					this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					String id = "";
					float totalValue = 0;
					if(this.managementPickerTwo.getSelectedIndex() == 0) id = ImportExportDao.checkQuantity("PN").isEmpty() ? "PN0" : "PN" + String.valueOf(Integer.parseInt(ImportExportDao.checkQuantity("PN").trim().substring(2, ImportExportDao.checkQuantity("PN").trim().length()))+1);
					else id = ImportExportDao.checkQuantity("PX").isEmpty() ? "PX0" : "PX" + String.valueOf(Integer.parseInt(ImportExportDao.checkQuantity("PX").trim().substring(2, ImportExportDao.checkQuantity("PX").trim().length()))+1);
					for(int i=0; i<this.data.getRowCount(); i++)
					{
						String itemName = checkItemAmountIllegal(this.data.getValueAt(i, 0).toString().trim(), Integer.parseInt(this.data.getValueAt(i, 3).toString().trim()));
						if(!itemName.isEmpty() && this.managementPickerTwo.getSelectedIndex() == 1)
						{
							JOptionPane.showOptionDialog(null, "Sản phẩm '" + itemName + "' hiện đang không có đủ số lượng để xuất !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
							return;
						}
						String itemId =  this.data.getValueAt(i, 0).toString().trim();
						float quantity = (float)Integer.parseInt(this.data.getValueAt(i, 2).toString().trim().substring(0, this.data.getValueAt(i, 2).toString().trim().length()-2).replace(".", ""));
						int amount = Integer.parseInt(this.data.getValueAt(i, 3).toString().trim());
						lst.add(new ImportExportDetail(id, itemId, quantity, amount));
						totalValue = totalValue + (quantity*amount);
					}
					ie = new ImportExport(id, totalValue, this.dateNow, StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId().trim());
					if(ImportExportDao.addImportExport(ie))
					{
						if(ImportExportDetailDao.addImportExportDetail(lst, this.managementPickerTwo.getSelectedIndex()))
						{
							JOptionPane.showOptionDialog(null, "Lập phiếu thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
							this.managementPickerTwo.setSelectedIndex(-1);
							this.managementOne.setText("Lập phiếu");
							this.refreshInfo(100, "");
							this.refreshMachine(MachineDao.getAllMachine());
							//SỬA
							//this.refreshImportExport(new ArrayList<ImportExport>());
							//this.refreshAddImportExportMachine(MachineDao.getAllMachine());
						}
					}
				}
			}
		}
		
		if(e.getSource() == this.managementOne)
		{
			if(this.menu == 1)
			{
				this.managementTwo.setText("Quay lại");
				this.refreshInfo(12, "");
				this.refreshUpdateStaff(this.data.getValueAt(this.data.getSelectedRow(), 0).toString().trim());
			}
			else if(this.menu == 3)
			{
				this.managementOne.setText("Lập phiếu");
				this.managementTwo.setText("Quay lại");
				this.managementPickerInfo.setText("Chọn loại phiếu:");
				this.managementPickerTwo.removeAllItems();
				this.managementPickerTwo.addItem("Phiếu nhập");
				this.managementPickerTwo.addItem("Phiếu xuất");
				this.managementPickerTwo.setSelectedIndex(-1);
				this.refreshInfo(33, "Tìm họ tên nhân viên");
				this.refreshImportExport(new ArrayList<ImportExport>());
			}
			else if(this.menu == 33)
			{
				this.managementOne.setText("Thêm sản phẩm");
				this.managementPickerTwo.setSelectedIndex(-1);
				this.refreshInfo(332, "");
				this.refreshAddImportExport();
			}
			else if(this.menu == 100)
			{
				this.managementOne.setText("Thêm sản phẩm");
				this.managementTwo.setText("Quay lại");
				this.managementPickerInfo.setText("Chọn loại phiếu:");
				this.managementPickerTwo.removeAllItems();
				this.managementPickerTwo.addItem("Phiếu nhập");
				this.managementPickerTwo.setSelectedIndex(0);
				this.refreshInfo(101, "");
				//this.refreshMachine(MachineDao.getAllMachine());
				this.refreshAddImportExportMachine(MachineDao.getAllMachine());
				//this.refreshImportExport(new ArrayList<ImportExport>());
			}
			else if(this.menu == 101)
			{
				if(((JComboBox<String>) this.comps.get(1)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Chưa chọn sản phẩm để thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(2)).getText().trim().isEmpty() || ((JTextField) this.comps.get(2)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Giá không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(3)).getText().trim().isEmpty() || ((JTextField) this.comps.get(3)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Số lượng không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(2))))
				{
					JOptionPane.showOptionDialog(null, "Giá chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(3))))
				{
					JOptionPane.showOptionDialog(null, "Số lượng chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					String itemId = this.data.getValueAt(i, 0).toString().trim();
					if(itemId.equals("SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex())))
					{
						JOptionPane.showOptionDialog(null, "Sản phẩm này đã được chọn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) this.data.getModel();
				String id = "SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex());
				String name = ((JComboBox<String>) this.comps.get(1)).getSelectedItem().toString().trim();
				String price = this.numberFormat.format(Float.parseFloat(((JTextField) this.comps.get(2)).getText().trim()));
				String amount = ((JTextField) this.comps.get(3)).getText().trim();
				model.addRow(new String[]{id, name, price, amount});
			}
			else if(this.menu == 332)
			{
				if(((JComboBox<String>) this.comps.get(1)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Chưa chọn sản phẩm để thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(2)).getText().trim().isEmpty() || ((JTextField) this.comps.get(2)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Giá không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(3)).getText().trim().isEmpty() || ((JTextField) this.comps.get(3)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Số lượng không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(2))))
				{
					JOptionPane.showOptionDialog(null, "Giá chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(3))))
				{
					JOptionPane.showOptionDialog(null, "Số lượng chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					String itemId = this.data.getValueAt(i, 0).toString().trim();
					if(itemId.equals("SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex())))
					{
						JOptionPane.showOptionDialog(null, "Sản phẩm này đã được chọn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) this.data.getModel();
				String id = "SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex());
				String name = ((JComboBox<String>) this.comps.get(1)).getSelectedItem().toString().trim();
				String price = this.numberFormat.format(Float.parseFloat(((JTextField) this.comps.get(2)).getText().trim()));
				String amount = ((JTextField) this.comps.get(3)).getText().trim();
				model.addRow(new String[]{id, name, price, amount});
			}
			else if(this.menu == 101)
			{
				if(((JComboBox<String>) this.comps.get(1)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Chưa chọn sản phẩm để thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(2)).getText().trim().isEmpty() || ((JTextField) this.comps.get(2)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Giá không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(3)).getText().trim().isEmpty() || ((JTextField) this.comps.get(3)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Số lượng không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(2))))
				{
					JOptionPane.showOptionDialog(null, "Giá chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!checkOnlyNumber(((JTextField) this.comps.get(3))))
				{
					JOptionPane.showOptionDialog(null, "Số lượng chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					String itemId = this.data.getValueAt(i, 0).toString().trim();
					if(itemId.equals("SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex())))
					{
						JOptionPane.showOptionDialog(null, "Sản phẩm này đã được chọn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) this.data.getModel();
				String id = "SP" + String.valueOf(((JComboBox<String>) this.comps.get(1)).getSelectedIndex());
				String name = ((JComboBox<String>) this.comps.get(1)).getSelectedItem().toString().trim();
				String price = this.numberFormat.format(Float.parseFloat(((JTextField) this.comps.get(2)).getText().trim()));
				String amount = ((JTextField) this.comps.get(3)).getText().trim();
				model.addRow(new String[]{id, name, price, amount});
			}
			else if(this.menu == 4)
			{
				this.menu = 43;
				this.refreshServiceRating(RatingDao.getServiceRating(this.tempId.trim()));
				this.managementTwo.setText("Quay lại");
			}
			else if(this.menu == 41 || this.menu == 42)
			{
				if(((JComboBox<String>) this.comps.get(4)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Chưa chọn sản phẩm để thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(((JTextField) this.comps.get(5)).getText().trim().isEmpty() || ((JTextField) this.comps.get(5)).getForeground() == Color.gray)
				{
					JOptionPane.showOptionDialog(null, "Lượng không được để trống !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				if(!this.checkFloat(((JTextField) this.comps.get(5))))
				{
					JOptionPane.showOptionDialog(null, "Lượng chỉ được chứa số hoặc dấu '.' !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					String itemId = this.data.getValueAt(i, 0).toString().trim();
					if(itemId.equals("SP" + String.valueOf(((JComboBox<String>) this.comps.get(4)).getSelectedIndex())))
					{
						JOptionPane.showOptionDialog(null, "Sản phẩm này đã được chọn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) this.data.getModel();
				String id = "SP" + String.valueOf(((JComboBox<String>) this.comps.get(4)).getSelectedIndex());
				String name = ((JComboBox<String>) this.comps.get(4)).getSelectedItem().toString().trim();
				String quantity = ((JTextField) this.comps.get(5)).getText().trim();
				String unitFormula = ItemDao.getOneItemById(id).getUnit() != null ? ItemDao.getOneItemById(id).getUnit() : "không có";
				model.addRow(new String[]{id, name, quantity, unitFormula});
			}
			else if(this.menu == 6)
			{
				this.refreshInfo(63, "Tìm mức ưu đãi");
				this.refreshDiscount(DiscountDao.getAllDiscount());
				this.managementTwo.setText("Quay lại");
			}
			else if(this.menu == 62)
			{
				if(((JComboBox<String>) this.comps.get(3)).getSelectedIndex() == -1)
				{
					JOptionPane.showOptionDialog(null, "Chưa chọn dịch vụ để thêm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					return;
				}
				for(int i=0; i<this.data.getRowCount(); i++)
				{
					String serviceId = this.data.getValueAt(i, 0).toString().trim();
					if(serviceId.equals(ServiceDao.getIdByName(((JComboBox<String>) this.comps.get(3)).getSelectedItem().toString().trim())))
					{
						JOptionPane.showOptionDialog(null, "Dịch vụ này đã được chọn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) this.data.getModel();
				String id = ServiceDao.getIdByName(((JComboBox<String>) this.comps.get(3)).getSelectedItem().toString().trim());
				String name = ((JComboBox<String>) this.comps.get(3)).getSelectedItem().toString().trim();
				String price = String.valueOf(this.numberFormat.format(ServiceDao.getOneService(id).getPrice()));
				String amount = String.valueOf(((JSpinner) this.comps.get(5)).getValue().toString().trim());
				model.addRow(new String[]{id, name, price, amount});
			}
			
		}
		if(e.getSource() == this.managementTwo)
		{
			if(this.menu == 11 || this.menu == 12)
			{
				this.refreshInfo(1, "Tìm họ tên nhân viên");
				this.refreshAccount(AccountDao.getAllAccount(2), 2);
			}
			else if(this.menu == 31 || this.menu == 32 || this.menu == 33)
			{
				this.managementOne.setText("Xem phiếu");
				this.refreshInfo(3, "Tìm tên sản phẩm");
				this.refreshItem(ItemDao.getAllItem());
			}
			
			else if(this.menu == 331 || this.menu == 332 || this.menu == 333)
			{
				this.managementOne.setText("Lập phiếu");
				this.managementPickerTwo.setSelectedIndex(-1);
				this.refreshInfo(33, "");
				this.refreshImportExport(new ArrayList<ImportExport>());
			}
			else if(this.menu == 41 || this.menu == 42 || this.menu == 43)
			{
				this.refreshInfo(4, "Tìm tên dịch vụ");
				this.refreshService(ServiceDao.getAllService());
			}
			else if(this.menu == 61 || this.menu == 62 || this.menu == 63)
			{
				this.managementOne.setText("Xem ưu đãi");
				this.refreshInfo(6, "Tìm họ tên nhân viên");
				this.refreshReceipt(this.role == 1 ? ReceiptDao.getAllReceipt() : ReceiptDao.getAllReceiptByStaffId(StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId()));
			}
			else if(this.menu == 631 || this.menu == 632)
			{
				this.refreshInfo(63, "Tìm mức ưu đãi");
				this.refreshDiscount(DiscountDao.getAllDiscount());
			}
			//QUAY LAI
			else if(this.menu == 101)
			{
				this.managementOne.setText("Lập phiếu");
				this.managementPickerTwo.setSelectedIndex(0);
				this.refreshInfo(100, "");
				this.refreshMachine(MachineDao.getAllMachine());
			}
		}
		if(e.getSource() == this.tempButton)
		{
			if(this.menu == 31 || this.menu == 32)
			{
				CustomFileChooser chooser = new CustomFileChooser();
				chooser.setDialogTitle("Chọn ảnh sản phẩm"); 
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(true);
				int userSelection = chooser.showSaveDialog(this);	
				if(userSelection == JFileChooser.APPROVE_OPTION) 
				{
				    try 
				    {
				    	Image file = ImageIO.read(chooser.getSelectedFile());
				    	Image icon = file.getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING);
				    	JLabel image = new JLabel();  
				    	image.setIcon(new ImageIcon(icon));
                        image.setBounds(0, 0, 150, 150);
                        this.tempButton.setVisible(false);
                        this.comps.get(1).add(image);
                        ((JTextField) this.comp).setText(chooser.getSelectedFile().toString());
                    }
                    catch(Exception ex) 
				    {
                    	JOptionPane.showOptionDialog(null, "Tải hình ảnh thất bại !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
        				return;
				    }
				}
				chooser.resetUI();
			}
		}
		if(e.getSource() == this.managementGoBack)
		{
			this.resetTemplate();
			this.displayAll(new Boolean[] {false, false, false, false, false, false, false, false, false, false, false});
			this.moveScreen(this.managementWindow, new JPanel(), "RIGHT");
			this.dispayLogin(true); 
		}
		if(e.getSource() == this.customerService)
		{
			CustomerPersonal cp = CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim());
			this.menu = 9;
			this.resetTemplate();
			this.customerId.setText(cp.getId().trim());
			this.customerName.setText(cp.getSurname() + " " + cp.getName());
			this.createIconTable(this.serviceWindow, null, new String[] {"Tên dịch vụ", "Giá dịch vụ", "Giờ làm", "Ngày làm", "Bỏ lịch hẹn"}, null, "./icon_image/delete.png", SwingConstants.CENTER, true, 13, 35, new Color(0x69b5b8), new Color(0xabb4f5), 602, 227, 650, 330, true, true);
			this.moveScreen(this.mainWindow, this.serviceWindow, "LEFT");
			this.serviceWindow.add(this.headerGoBack);
			this.keepTrack.add(this.mainWindow);
			this.keepTrack.add(this.serviceWindow);
		}
		if(e.getSource() == this.serviceAdd)
		{
			if(this.servicePricePicker.getSelectedIndex() == -1)
			{
				JOptionPane.showOptionDialog(null, "Chưa chọn dịch vụ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.timePicker.getSelectedIndex() == -1)
			{
				JOptionPane.showOptionDialog(null, "Chưa chọn giờ làm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.datePicker.getModel().getValue() == null)
			{
				JOptionPane.showOptionDialog(null, "Chưa chọn ngày làm !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			String dateScheduled = String.valueOf(this.datePicker.getModel().getYear()) + "-" + String.valueOf(this.datePicker.getModel().getMonth()+1) + "-" + String.valueOf(this.datePicker.getModel().getDay());
			this.dateNow =  String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			int check = this.checkDayScheduled(dateScheduled, this.dateNow);
			if(check == 2) JOptionPane.showOptionDialog(null, "Đã xảy ra lỗi, vui lòng thử lại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			else if(check == 0) JOptionPane.showOptionDialog(null, "Ngày được chọn phải sau ngày hiện tại !", "LỖI HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			else if(check == 1)
			{	
				String serviceName = this.servicePricePicker.getSelectedItem().toString().split("-")[0].trim().replace("Tên:", "").trim();
				String servicePrice = this.servicePricePicker.getSelectedItem().toString().split("-")[1].trim().replace("Giá:", "").trim();
				String time = this.timePicker.getSelectedItem().toString().trim();
				String date = new SimpleDateFormat("d/M/yyyy").format(this.datePicker.getModel().getValue());
				((DefaultTableModel) this.data.getModel()).addRow(new String[]{serviceName, servicePrice, time, date});
			}
		}
		if(e.getSource() == this.serviceConfirm)
		{
			if(this.data.getRowCount() == 0)
			{
				JOptionPane.showOptionDialog(null, "Danh sách phải chứa ít nhất một lịch hẹn !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			ArrayList<Schedule> lst = new ArrayList<Schedule>();
			for(int i=0; i<this.data.getRowCount(); i++)
			{
				String customerId = this.customerId.getText().trim();
				String serviceId = this.getServiceId(this.data.getValueAt(i, 0).toString().trim());
				String date = this.data.getValueAt(i, 3).toString().trim().split("/")[2] + "-" + this.data.getValueAt(i, 3).toString().trim().split("/")[1] + "-" + this.data.getValueAt(i, 3).toString().trim().split("/")[0];
				String time = this.data.getValueAt(i, 2).toString().trim();
				lst.add(new Schedule(null, customerId, serviceId, date, time, 0, null));
			}
			if(ScheduleDao.bookSchedule(lst))
			{
				this.servicePricePicker.setSelectedIndex(-1);
				this.timePicker.setSelectedIndex(-1);
				this.datePicker.getModel().setValue(null);
				((DefaultTableModel) this.data.getModel()).setRowCount(0);
				JOptionPane.showOptionDialog(null, "Đặt toàn bộ lịch hẹn thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);	
			}
		}
		if(e.getSource() == this.customerScheduled)
		{
			ArrayList<Schedule> lst = ScheduleDao.getCustomerSchedule(CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim()).getId());
			String[][] scheduledList = new String[lst.size()][5];
			this.menu = 10;
			for(Schedule s : lst)
			{
				scheduledList[lst.indexOf(s)][0] = s.getScheduleId();
				scheduledList[lst.indexOf(s)][1] = s.getServiceId();
				scheduledList[lst.indexOf(s)][2] = s.getDate();
				scheduledList[lst.indexOf(s)][3] = s.getTime();
				scheduledList[lst.indexOf(s)][4] = s.getStatus() == 0 ? "Đang chờ xác nhận" : "Đã được xác nhận";
			}
			this.resetTemplate();
			this.scheduledWindow.add(this.headerGoBack);
			this.createIconTable(this.scheduledWindow, scheduledList, new String[] {"", "", "", "", "", ""}, null, "./icon_image/remove.png", SwingConstants.CENTER, false, 15, 50, new Color(0x69b5b8), Color.white, 2, 247, 1267, 400, false, false);
			this.moveScreen(this.mainWindow, this.scheduledWindow, "LEFT");
			this.keepTrack.add(this.mainWindow);
			this.keepTrack.add(this.scheduledWindow);
		}
		if(e.getSource() == this.customerPersonalInfo)
		{
			CustomerPersonal cp = CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim());
			this.resetTemplate();
			this.updateSurname.setText(cp.getSurname().trim());
			this.updateName.setText(cp.getName().trim());
			this.updateMail.setText(cp.getGmail().trim());
			this.updatePhoneNumber.setText(cp.getPhoneNumber().trim());
			this.personalInfoWindow.add(this.headerGoBack);
			this.moveScreen(this.mainWindow, this.personalInfoWindow, "LEFT");
			this.keepTrack.add(this.mainWindow);
			this.keepTrack.add(this.personalInfoWindow);
		}
		if(e.getSource() == this.updatePersonalInfo)
		{
			if(!this.checkAllFilled(new JTextField[] {this.updateSurname, this.updateName, this.updateMail, this.updatePhoneNumber}))
			{
				JOptionPane.showOptionDialog(null, "Tất cả các trường thông tin phải được điền đầy đủ trước khi đăng ký !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkOnlyLetter(this.updateSurname))
			{
				JOptionPane.showOptionDialog(null, "Trong họ không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkOnlyLetter(this.updateName))
			{
				JOptionPane.showOptionDialog(null, "Trong tên không được chứa số hoặc ký tự !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkValidGmail(this.updateMail))
			{
				JOptionPane.showOptionDialog(null, "Địa chỉ gmail không hợp lệ !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.checkOnlyNumber(this.updatePhoneNumber))
			{
				JOptionPane.showOptionDialog(null, "Trong số điện thoại chỉ được chứa số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.updatePhoneNumber.getText().trim().length() != 0 && this.updatePhoneNumber.getText().trim().length() != 10)
			{
				JOptionPane.showOptionDialog(null, "Số điện thoại phải có 10 số !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!AccountDao.checkDuplicatePhoneNumberUpdate(this.updatePhoneNumber.getText().trim(), this.acc.trim(), this.role))
			{
				JOptionPane.showOptionDialog(null, "Số điện thoại đã được đăng ký !\nVui lòng sử dụng số khác", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			String surname = this.updateSurname.getText().trim();
			String name = this.updateName.getText().trim();
			String gmail = this.updateMail.getText().trim();
			String phoneNumber = this.updatePhoneNumber.getText().trim();
			String account = this.acc.trim();
			if(CustomerPersonalDao.updateCustomerPersonal(new CustomerPersonal(null, surname, name, gmail, phoneNumber, account))) JOptionPane.showOptionDialog(null, "Cập nhật thông tin cá nhân thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
		}
		if(e.getSource() == this.updateAccount)
		{
			if(this.updatePassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng nhập mật khẩu cũ để xác nhận !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.updateNewPassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng nhập mật khẩu mới !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(this.updateConfirmPassword.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Vui lòng xác nhận lại mật khẩu mới !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.updatePassword.getText().equals(this.pwd))
			{
				JOptionPane.showOptionDialog(null, "Nhập mật khẩu cũ không chính xác !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(!this.updateNewPassword.getText().equals(this.updateConfirmPassword.getText()))
			{
				JOptionPane.showOptionDialog(null, "Xác nhận lại mật khẩu mới không đúng !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			if(AccountDao.updateAccount(this.acc.trim(), this.updateNewPassword.getText()))
			{
				this.resetTemplate();
				this.moveScreen(this.personalInfoWindow, new JPanel(), "RIGHT");
				this.dispayLogin(true);
				this.keepTrack.clear();
				JOptionPane.showOptionDialog(null, "Cập nhật mật khẩu thành công !\nMật khẩu cũ không còn hiệu lực nên bạn vui lòng đăng nhập lại với mật khẩu mới", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
		}
		if(e.getSource() == this.customerHistory)
		{
			this.resetTemplate();
			this.historyWindow.add(this.headerGoBack);
			this.refreshCustomerHistory();
			this.moveScreen(this.mainWindow, this.historyWindow, "LEFT");
			this.keepTrack.add(this.mainWindow);
			this.keepTrack.add(this.historyWindow);
		}
		if(e.getSource() == this.customerRating)
		{
			this.resetTemplate();
			this.ratingWindow.add(this.headerGoBack);
			this.moveScreen(this.mainWindow, this.ratingWindow, "LEFT");
			this.keepTrack.add(this.mainWindow);
			this.keepTrack.add(this.ratingWindow);
		}
		if(e.getSource() == this.ratingConfirm)
		{
			if(this.servicePicker.getSelectedIndex() == -1)
			{
				JOptionPane.showOptionDialog(null, "Bạn chưa chọn dịch vụ cần đánh giá !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			int count = 0;
			for(StarRating sr : StarRating.stars)
			{
				if(sr.clicked == true) count++;
			}
			if(count == 0)
			{
				JOptionPane.showOptionDialog(null, "Bạn chưa đánh giá !", "LỖI NHẬP THÔNG TIN", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				return;
			}
			String customerId = CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim()).getId();
			String receiptId = RatingDao.checkServiceBilled(customerId, this.tempId);
			Rating r = RatingDao.getOneRating(this.tempId, receiptId);
			if(r == null)
			{
				if(RatingDao.addRating(new Rating(this.tempId, receiptId, count, this.comment.getText().trim()))) JOptionPane.showOptionDialog(null, "Thêm đánh giá của bạn thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			else
			{
				if(RatingDao.updateRating(new Rating(r.getServiceId(), r.getReceiptId(), count, this.comment.getText().trim()))) JOptionPane.showOptionDialog(null, "Cập nhật đánh giá của bạn thành công !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
			}
			
		}
		if(e.getSource() == this.customerGoback)
		{
			this.resetTemplate();
			this.moveScreen(this.mainWindow, new JPanel(), "RIGHT");
			this.dispayLogin(true);
			this.keepTrack.clear();
		}
		if(e.getSource() == this.headerGoBack)
		{
			this.resetTemplate();
			this.moveScreen(this.keepTrack.get(this.keepTrack.size()-2), this.keepTrack.get(this.keepTrack.size()-1), "RIGHT");
			this.keepTrack.clear();
		}
		if(e.getSource() == this.managementPickerOne)
		{
			if(this.menu == 7)
			{
				if(this.managementPickerOne.getSelectedIndex() == -1 || this.managementPickerTwo.getSelectedIndex() == -1) return;
				int month = Integer.parseInt(this.managementPickerOne.getSelectedItem().toString());
				int year = Integer.parseInt(this.managementPickerTwo.getSelectedItem().toString());
				this.resetInfo();
				this.refreshRevenue(RevenueDao.getRevenue(month, year));
			}
		}
		if(e.getSource() == this.managementPickerTwo)
		{
			if(this.menu == 33) 
			{
				if(this.role == 1 && this.managementPickerTwo.getSelectedIndex() == 0) this.refreshImportExport(ImportExportDao.getAllByType("PN"));
				else if(this.role == 1 && this.managementPickerTwo.getSelectedIndex() == 1) this.refreshImportExport(ImportExportDao.getAllByType("PX"));
				else if(this.role == 2 && this.managementPickerTwo.getSelectedIndex() == 0) this.refreshImportExport(ImportExportDao.getAllByTypeAndStaffId("PN", StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId()));
				else if(this.role == 2 && this.managementPickerTwo.getSelectedIndex() == 1) this.refreshImportExport(ImportExportDao.getAllByTypeAndStaffId("PX", StaffDao.getOneStaffByAccount(this.acc.trim()).getStaffId()));
			}
			else if(this.menu == 5)
			{
				this.resetInfo();
				this.refreshSchedule(ScheduleDao.getScheduleByStatus(managementPickerTwo.getSelectedIndex()), managementPickerTwo.getSelectedIndex());
			}
			else if(this.menu == 7)
			{
				if(this.managementPickerOne.getSelectedIndex() == -1 || this.managementPickerTwo.getSelectedIndex() == -1) return;
				int month = Integer.parseInt(this.managementPickerOne.getSelectedItem().toString());
				int year = Integer.parseInt(this.managementPickerTwo.getSelectedItem().toString());
				this.resetInfo();
				this.refreshRevenue(RevenueDao.getRevenue(month, year));
			}
		}
		if(e.getSource() == this.servicePicker)
		{
			if(this.servicePicker.getSelectedIndex() == -1) return;
			String customerId = CustomerPersonalDao.getOnePersonalByAccount(this.acc.trim()).getId();
			String serviceId = ServiceDao.getIdByName(this.servicePicker.getSelectedItem().toString().trim());
			String receiptId = RatingDao.checkServiceBilled(customerId, serviceId);
			if(!receiptId.isEmpty())
			{
				Rating r = RatingDao.getOneRating(serviceId, receiptId);
				this.ratingConfirm.setEnabled(true);
				if(r != null)
				{
					StarRating.setRated(r.getRating());
					this.comment.setText(r.getComment());
				}
				else
				{
					StarRating.setRated(0);
					this.comment.setText("");
				}
				this.tempId = serviceId;
			}
			else if(receiptId.isEmpty())
			{
				JOptionPane.showOptionDialog(null, "Bạn không thể đánh giá vì bạn chưa sử dụng dịch vụ này !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Xác nhận"}, 0);
				StarRating.setRated(0);
				this.comment.setText("");
				this.servicePicker.setSelectedIndex(-1);
				
			}
			else 
			{
				this.servicePicker.setSelectedIndex(-1);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 10)
		{
			if(e.getSource() == this.search)
			{
				if(this.menu == 1) this.refreshAccount(AccountDao.searchAccount(this.search.getText().trim(), 2), 2);
				else if(this.menu == 2) this.refreshAccount(AccountDao.searchAccount(this.search.getText().trim(), 3), 3);
				else if(this.menu == 3) this.refreshItem(ItemDao.searchItem(this.search.getText().trim()));
				else if(this.menu == 33)
				{
					if(this.managementPickerTwo.getSelectedIndex() == -1) JOptionPane.showOptionDialog(null, "Không thể tìm vì chưa chọn loại phiếu !", "HỆ THỐNG", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Xác nhận"}, 0);
					else if(this.managementPickerTwo.getSelectedIndex() == 0) this.refreshImportExport(ImportExportDao.searchImportExport("PN", this.search.getText().trim()));
					else if(this.managementPickerTwo.getSelectedIndex() == 1) this.refreshImportExport(ImportExportDao.searchImportExport("PX", this.search.getText().trim()));
				}
				else if(this.menu == 331) this.refreshImportExportDetail(ImportExportDetailDao.searchImportExportDetail(this.tempId, this.search.getText().trim()));
				else if(this.menu == 4) this.refreshService(ServiceDao.searchService(this.search.getText().trim()));
				else if(this.menu == 5) this.refreshSchedule(ScheduleDao.searchCustomerSchedule(managementPickerTwo.getSelectedIndex(), this.search.getText().trim()), managementPickerTwo.getSelectedIndex());
				else if(this.menu == 6) this.refreshReceipt(ReceiptDao.searchReceipt(this.search.getText().trim()));
				else if(this.menu == 61) this.refreshReceiptDetail(ReceiptDetailDao.searchReceiptDetail(this.tempId, this.search.getText().trim()));
				else if(this.menu == 63) this.refreshDiscount(DiscountDao.searchDiscount(this.checkOnlyNumber(this.search) ? Integer.parseInt(this.search.getText().trim()) : -1));
				else if(this.menu == 7)
				{
					int month = Integer.parseInt(this.managementPickerOne.getSelectedItem().toString());
					int year = Integer.parseInt(this.managementPickerTwo.getSelectedItem().toString());
					this.refreshRevenue(RevenueDao.findStaffRevenue(month, year, this.search.getText().trim()));
				}
				//THÊM
				//else if(this.menu == 10) this.refreshMachine(MachineDao.searchItem(this.search.getText().trim()));
			}
		}
		else
		{
			for(CustomMouseListener customMouseListener : CustomMouseListener.comps)
			{
				if(e.getSource() == customMouseListener.comp && customMouseListener.originalClickType == 1) // text or password
				{
					if(customMouseListener.pwd != null)
					{
						customMouseListener.comp.setVisible(false);
						customMouseListener.pwd.setVisible(true);
						customMouseListener.pwd.setText("");
						customMouseListener.pwd.setText(String.valueOf(e.getKeyChar()));
						customMouseListener.pwd.setForeground(Color.black);
						customMouseListener.pwd.requestFocus();
					}
					else
					{
						JTextField temp = (JTextField) customMouseListener.comp;
						if(customMouseListener.clickType != -1)
						{
							temp.setText("");
							temp.setForeground(Color.black);
							customMouseListener.clickType = -1;
						}
					}
					break;
				}
				else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 2) // sign up
				{
					JTextField[] inputs = new JTextField[] {customMouseListener.frame.newUserName, customMouseListener.frame.surname, customMouseListener.frame.name, customMouseListener.frame.mail, customMouseListener.frame.phoneNumber};
					String[] components = new String[] {"Tên tài khoản", "Họ", "Tên", "Gmail", "Số điện thoại"};
					customMouseListener.comp.setForeground(customMouseListener.pressedColor);
					customMouseListener.frame.dispayLogin(false);
					for(int i=0; i<inputs.length; i++)
					{
							inputs[i].setText(components[i]);
							inputs[i].setForeground(Color.gray);
					}
					customMouseListener.frame.resetTemplate();
					customMouseListener.frame.moveScreen(new JPanel(), customMouseListener.frame.signupWindow, "LEFT");
					break;
				}
				else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 3) // reset password
				{
					customMouseListener.comp.setForeground(customMouseListener.pressedColor);
					customMouseListener.frame.dispayLogin(false);
					customMouseListener.frame.restoreUserName.setText("Tên tài khoản");
					customMouseListener.frame.restoreUserName.setForeground(Color.gray);
					customMouseListener.frame.resetTemplate();
					customMouseListener.frame.moveScreen(new JPanel(), customMouseListener.frame.resetMailWindow, "LEFT");
					break;
				}
				else if(e.getSource() == customMouseListener.comp && customMouseListener.clickType == 4) // password
				{
					customMouseListener.comp.setVisible(false);
					customMouseListener.pwd.setVisible(true);
					customMouseListener.pwd.setText("");
					customMouseListener.pwd.setText(String.valueOf(e.getKeyChar()));
					customMouseListener.pwd.setForeground(Color.black);
					customMouseListener.pwd.requestFocus();
					break;
				}
				else if(e.getSource() == customMouseListener.comp && customMouseListener.originalClickType == 5) // management menu bar
				{
					if(customMouseListener.clickType == 5)
					{
						customMouseListener.frame.managementSection.setVisible(true);
						customMouseListener.frame.managementPersonal.setVisible(false);
						customMouseListener.frame.line1.setVisible(false);
						customMouseListener.clickType = -1;
					}
					else if(customMouseListener.clickType == -1)
					{
						customMouseListener.frame.managementSection.setVisible(false);
						customMouseListener.frame.managementPersonal.setVisible(true);
						customMouseListener.frame.line1.setVisible(true);
						customMouseListener.clickType = 5;
					}
					break;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}