import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame  {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	String selected;
	String check;
	/**
	 * Launch the application.
	 */
	
	
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		String type_users[] = {"Customer","Outlet","Supplier"};
		JComboBox comboBox = new JComboBox(type_users);
		comboBox.setBackground(Color.ORANGE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 35));
		comboBox.setBounds(391, 175, 189, 44);
		panel.add(comboBox);
		comboBox.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component getListCellRendererComponent(JList list, Object value,
		            int index, boolean isSelected, boolean cellHasFocus) {
		        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }});
		comboBox.setForeground(Color.BLACK);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 32));
		textField.setForeground(Color.WHITE);
		textField.setBounds(371, 396, 318, 38);
		panel.add(textField);
		textField.setColumns(60);
		textField.setOpaque(false);
		textField.setCaretColor(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Harlow Solid Italic", Font.BOLD, 34));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(371, 539, 318, 38);
		panel.add(passwordField);
		passwordField.setOpaque(false);
		passwordField.setCaretColor(Color.WHITE);
		
		JButton customer = new JButton("");
		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					createCustomer();
			}
		});
		customer.setBounds(814, 300, 449, 67);
		panel.add(customer);
		customer.setOpaque(false);
		customer.setContentAreaFilled( false );
		customer.setBorder( null );
		customer.setBackground(null);
		customer.setForeground(null);
		customer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton Outlet = new JButton("");
		Outlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					createOutlet();
			}
		});
		Outlet.setBounds(858, 462, 369, 67);
		panel.add(Outlet);
		Outlet.setOpaque(false);
		Outlet.setContentAreaFilled( false );
		Outlet.setBorder( null );
		Outlet.setBackground(null);
		Outlet.setForeground(null);
		Outlet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton Submit = new JButton("");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 selected = comboBox.getSelectedItem().toString();
				
				if(textField.getText().equals("") || passwordField.getText().equals("") ) {
					showMessage();
				}
				
				else {
				
				if(selected == "Customer"){
						goToCustomer();
				}
				if(selected == "Outlet"){
					goToOutlet();
			}
				if(selected == "Supplier"){
					goToSupplier();
			}
				
		}
			    
		}
	});
		Submit.setBounds(497, 600, 156, 60);
		panel.add(Submit);
		Submit.setOpaque(false);
		Submit.setContentAreaFilled( false );
		Submit.setBorder( null );
		Submit.setBackground(null);
		Submit.setForeground(null);
		Submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton Forgot_Password = new JButton("");
		Forgot_Password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgotPassword(selected);
			}
		});
		Forgot_Password.setBounds(120, 664, 273, 44);
		panel.add(Forgot_Password);
		Forgot_Password.setOpaque(false);
		Forgot_Password.setContentAreaFilled( false );
		Forgot_Password.setBorder( null );
		Forgot_Password.setBackground(null);
		Forgot_Password.setForeground(null);
		Forgot_Password.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Logins.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
	}

	protected void showMessage() {
		JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Enter the credentials properly","Error",JOptionPane.ERROR_MESSAGE);	
	}




	protected void goToSupplier() {
		
		String usr = textField.getText();
		this.setVisible(false);
		SupplierHome frame = new SupplierHome(usr);
		frame.setVisible(true);
	}

	protected void goToOutlet() {
		
		String usr = textField.getText();
		String pwd = passwordField.getText();
		boolean valid ;
		
		try {
			valid = validateOutlet(usr,pwd);
			if(valid) {
				this.setVisible(false);
				OutletHome frame;
				try {
					String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
					String username = "CHANDRASHEKARA";
					String password = "CHANDRASHEKARA";

					Class.forName("oracle.jdbc.driver.OracleDriver");
					DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
					
					Connection con;
					
					con = DriverManager.getConnection(url,username,password);
					
					String query = "SELECT OID FROM OUTLET WHERE ONAME = ?";
					
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1,usr);
					
					ResultSet rs = st.executeQuery();
					
					if(rs.next()) {
						
						frame = new OutletHome(rs.getString(1));
						frame.setVisible(true);
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Not a valid user","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private boolean validateOutlet(String usr, String pwd) throws Exception {
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT PWD FROM OUCR WHERE OUTLID = (SELECT OID FROM OUTLET WHERE ONAME = ?)";
		PreparedStatement st = con.prepareStatement(query);
		
		st.setString(1,usr);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			 check = rs.getString(1);
			
			if(check.equals(pwd)) {
				st.close();
				con.close();
				return true;
			}
			JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Incorrect Password","Error",JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"User not found","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		st.close();
		
	    con.close();
		
		
		
		return false;
		
	}




	protected void goToCustomer() {
		
		String usr = textField.getText();
		String pwd = passwordField.getText();
		boolean valid ;
		
			try {
				valid = validateCustomer(usr,pwd);
				if(valid) {
					this.setVisible(false);
					CustomerHome frame;
					try {
						frame = new CustomerHome(usr);
						frame.setVisible(true);
					} catch (Exception e) {
						
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
	}

	private boolean validateCustomer(String usr,String pwd) throws Exception{
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT PWD FROM CUSCR WHERE CUID =?";
		PreparedStatement st = con.prepareStatement(query);
		
		try {
		
		st.setBigDecimal(1,new BigDecimal(usr));
		
		ResultSet rs;
		
		        rs = st.executeQuery();
		        
		        if(rs.next()) {
					 check = rs.getString(1);
					
					if(check.equals(pwd)) {
						st.close();
						return true;
					}
					JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Incorrect Password","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"User not found","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				st.close();
				con.close();
				return false;
				
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Not a valid user","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		return true;
			
	}

	protected void forgotPassword(String selected) {
		this.setVisible(false);
		ForgotPassword frame = new ForgotPassword(selected);
		frame.setVisible(true);
	}

	protected void createOutlet() {
		this.setVisible(false);
		NewOutletReg frame = new NewOutletReg();
		frame.setVisible(true);
	}

	protected void createCustomer() {
		
		this.setVisible(false);
	    NewCustomerReg frame;
		
	    frame = new NewCustomerReg();
		frame.setVisible(true);
		
	}
}
