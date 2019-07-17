import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class OutletHome extends JFrame {

	String license = "License : ";
	String oname = "Outlet Name : ";
	String owner = "Owner : ";
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField outletname;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutletHome frame = new OutletHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OutletHome(String usr) throws Exception {
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT ONAME,OWNER FROM OUTLET WHERE OID=?";
		
		PreparedStatement st = con.prepareStatement(query);
		
		st.setString(1,usr);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			license = license + usr;
			oname = oname + rs.getString(1);
			owner = owner + rs.getString(2);
		}
		
		st.close();
		con.close();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		
		textField = new JTextField();
		textField.setBounds(141, 129, 1022, 44);
		panel.add(textField);
		textField.setColumns(10);
		textField.setForeground(Color.YELLOW);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 35));
		textField.setOpaque(false);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setText(license);
		textField.setEditable(false);
		
		outletname = new JTextField();
		outletname.setBounds(141, 206, 564, 50);
		panel.add(outletname);
		outletname.setColumns(10);
		outletname.setForeground(Color.YELLOW);
		outletname.setFont(new Font("Tahoma", Font.PLAIN, 35));
		outletname.setOpaque(false);
		outletname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		outletname.setText(oname);
		outletname.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setBounds(747, 206, 569, 50);
		panel.add(textField_2);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		textField_2.setColumns(10);
		textField_2.setForeground(Color.YELLOW);
		textField_2.setOpaque(false);
		textField_2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField_2.setText(owner);
		textField_2.setEditable(false);
		
		textField_3 = new JTextField();
		textField_3.setSize(214, 50);
		textField_3.setBounds(770, 225, 569, 50);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setForeground(Color.YELLOW);
		textField_3.setOpaque(false);
		textField_3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField_3.setText(owner);
		textField_3.setEditable(false);
		
		btnNewButton.setBounds(0, 0, 98, 90);
		panel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled( false );
		btnNewButton.setBorder( null );
		btnNewButton.setBackground(null);
		btnNewButton.setForeground(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToInventory(usr);
			}
		});
		button.setBounds(130, 454, 295, 90);
		panel.add(button);
		button.setOpaque(false);
		button.setContentAreaFilled( false );
		button.setBorder( null );
		button.setBackground(null);
		button.setForeground(null);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToIndents(usr);
			}
		});
		button_1.setBounds(162, 624, 248, 78);
		panel.add(button_1);
		button_1.setOpaque(false);
		button_1.setContentAreaFilled( false );
		button_1.setBorder( null );
		button_1.setBackground(null);
		button_1.setForeground(null);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSales(usr);
			}
		});
		button_2.setBounds(749, 466, 391, 96);
		panel.add(button_2);
		button_2.setOpaque(false);
		button_2.setContentAreaFilled( false );
		button_2.setBorder( null );
		button_2.setBackground(null);
		button_2.setForeground(null);
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suppliers(usr);
			}
		});
		button_3.setBounds(710, 612, 478, 90);
		panel.add(button_3);
		button_3.setOpaque(false);
		button_3.setContentAreaFilled( false );
		button_3.setBorder( null );
		button_3.setBackground(null);
		button_3.setForeground(null);
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.YELLOW);
		textField_1.setBounds(419, 332, 166, 44);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setOpaque(false);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 45));
		textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		String url1 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username1 = "CHANDRASHEKARA";
		String password1 = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con1;
		
		con1 = DriverManager.getConnection(url1,username1,password1);
		
		String query1 = "SELECT AVG(RATING) FROM RATING WHERE OUTLETID = ?";
		PreparedStatement st1 = con1.prepareStatement(query1);
		st1.setString(1,usr);
		
		ResultSet rs1 = st1.executeQuery();
		
		
		if(rs1.next()) {
			
			BigDecimal rate = new BigDecimal(0);
			rate = rs1.getBigDecimal(1);
			try {
				textField_1.setText(rate.toString()); 
			}
			catch (Exception e) {
			}
			          
			textField_1.setEditable(false);
		}
		
		st1.close();
		rs1.close();
		con1.close();
		
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1366, 756);
		panel.add(label);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(0, 0, 1366,756);
		panel.add(label);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Outlet detailsGG.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
	}


	protected void suppliers(String usr) {
		this.setVisible(false);
		Suppliers frame;
		try {
			frame = new Suppliers(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void goToInventory(String usr) {
		this.setVisible(false);
		Inventory frame;
		try {
			frame = new Inventory(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	protected void viewSales(String usr) {
		this.setVisible(false);
		try {
		Sales frame = new Sales(usr);
		frame.setVisible(true);
		}catch(Exception e) {}
	}

	protected void goToIndents(String usr) {
		this.setVisible(false);
		Indents frame;
		try {
			frame = new Indents(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void goToHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
}
