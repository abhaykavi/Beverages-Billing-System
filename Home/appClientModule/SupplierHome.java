import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class SupplierHome extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtOutletName;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierHome frame = new SupplierHome();
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
	public SupplierHome(String usr) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		button.setBounds(10, 11, 91, 81);
		panel.add(button);
		button.setOpaque(false);
		button.setContentAreaFilled( false );
		button.setBorder( null );
		button.setBackground(null);
		button.setForeground(null);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button1 = new JButton("");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCommissions(usr);
			}
		});
		button1.setBounds(25,583,392,81);
		panel.add(button1);
		button1.setOpaque(false);
		button1.setContentAreaFilled( false );
		button1.setBorder( null );
		button1.setBackground(null);
		button1.setForeground(null);
		button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seeOrders(usr);
			}
		});
		button_1.setBounds(957, 534, 373, 173);
		panel.add(button_1);
		button_1.setOpaque(false);
		button_1.setContentAreaFilled( false );
		button_1.setBorder( null );
		button_1.setBackground(null);
		button_1.setForeground(null);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		txtName = new JTextField();
		txtName.setText("Name :");
		txtName.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtName.setBackground(Color.ORANGE);
		txtName.setForeground(Color.RED);
		txtName.setBounds(257, 179, 120, 42);
		panel.add(txtName);
		txtName.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 26));
		textField.setForeground(Color.RED);
		textField.setBackground(Color.ORANGE);
		textField.setBounds(426, 179, 346, 42);
		panel.add(textField);
		textField.setColumns(10);
		
		 textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		textField_2.setBackground(Color.ORANGE);
		textField_2.setForeground(Color.RED);
		textField_2.setBounds(426, 342, 346, 42);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		try{
			String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
			String username = "CHANDRASHEKARA";
			String password = "CHANDRASHEKARA";
	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			
			Connection con;
			
			con = DriverManager.getConnection(url,username,password);
			
			String query = "SELECT SNAME,OTID FROM SUPPLIER WHERE SID=?";
			
			PreparedStatement st = con.prepareStatement(query);
			
			st.setBigDecimal(1,new BigDecimal(usr));
			
			ResultSet rs = st.executeQuery();
			

			
			
			
			String oid = "s";
			if(rs.next()) {
			 oid = rs.getString(2);
			 String set = rs.getString(1);
			textField.setText(set);
			rs.close();
			}
			
			st.close();
			
			String query1 = "SELECT ONAME FROM OUTLET WHERE OID = ?";
			PreparedStatement st1 = con.prepareStatement(query1);
			
		    st1.setString(1, oid);
		    
		   ResultSet rs1 = st1.executeQuery();
		    
		   
		    if(rs1.next()) {
		    	textField_2.setText(rs1.getString(1));
		    }
			
			st1.close();
			rs1.close();
			con.close();
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
		JTextArea txtrId = new JTextArea();
		txtrId.setText("    ID :");
		txtrId.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtrId.setForeground(Color.RED);
		txtrId.setBackground(Color.ORANGE);
		txtrId.setBounds(257, 258, 120, 42);
		panel.add(txtrId);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		textField_1.setBackground(Color.ORANGE);
		textField_1.setBounds(426, 259, 346, 41);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(usr);
		
		txtOutletName = new JTextField();
		txtOutletName.setText("Outlet Name :");
		txtOutletName.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtOutletName.setBackground(Color.ORANGE);
		txtOutletName.setForeground(Color.RED);
		txtOutletName.setBounds(188, 342, 189, 42);
		panel.add(txtOutletName);
		txtOutletName.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Supplier home.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
	}

	protected void seeOrders(String usr) {
		this.setVisible(false);
		SeeOrders frame;
		try {
			frame = new SeeOrders(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	protected void checkCommissions(String usr) {
		this.setVisible(false);
		Commission frame;
		try {
			frame = new Commission(usr);
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


