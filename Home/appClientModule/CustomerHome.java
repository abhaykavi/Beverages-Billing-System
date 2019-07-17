import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CustomerHome extends JFrame {

	private JPanel contentPane,panel;
	
	String id =   "Customer id"+"   :";
	String name = "Customer name "+" :";
	String phno = "Customer phone"+":";
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */

	public CustomerHome(String usr) throws Exception {
		
		/*Note: If you want to use usr in processing query :
		 *      new BigDecimal(usr) as customer id is number(12)
		 */
		
		String url =      "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		
		String query = "SELECT CNAME,PHNO FROM CUSTOMER WHERE CID=?";
		
		PreparedStatement st = con.prepareStatement(query);
		
		st.setBigDecimal(1,new BigDecimal(usr));
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			id = id + usr;
			name = name + rs.getString(1);
			phno = phno + rs.getBigDecimal(2).toString();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		button.setBounds(0, 5, 89, 79);
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
				showOrders(usr);
			}
		});
		button_1.setBounds(489, 602, 244, 92);
		panel.add(button_1);
		button_1.setOpaque(false);
		button_1.setContentAreaFilled( false );
		button_1.setBorder( null );
		button_1.setBackground(null);
		button_1.setForeground(null);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 60));
		textField.setBounds(274, 120, 890, 50);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(id);
		textField.setOpaque(false);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 60));
		textField_1.setBounds(274, 201, 890, 50);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(name);
		textField_1.setOpaque(false);
		textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.RED);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 60));
		textField_2.setBounds(274, 298, 890, 50);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(phno);
		textField_2.setOpaque(false);
		textField_2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\customer home(1).jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
		
	}
	
	

	protected void goToHome() {
		
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}

	protected void showOrders(String usr) {
		
		this.setVisible(false);
		CustomerNewOrder frame;
		try {
			frame = new CustomerNewOrder(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
