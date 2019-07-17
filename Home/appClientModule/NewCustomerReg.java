import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class NewCustomerReg extends JFrame {

	private JPanel contentPane;
	private JTextField Aadhar;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField Name;
	private JTextField Phno;
	private JButton button;
	
	/**
	 * Create the frame.
	 */
	public NewCustomerReg() {
		
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
		
		JButton Submit = new JButton("Submit");
		Submit.setBackground(Color.WHITE);
		Submit.setForeground(Color.RED);
		Submit.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 50));
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registerCustomer();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Submit.setBounds(1019, 659, 242, 59);
		panel.add(Submit);
		Submit.setOpaque(true);
		Submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		Aadhar = new JTextField();
		Aadhar.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		Aadhar.setForeground(Color.RED);
		Aadhar.setBounds(413, 158, 270, 35);
		panel.add(Aadhar);
		Aadhar.setColumns(12);
		Aadhar.setOpaque(false);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.RED);
		passwordField.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		passwordField.setBounds(413, 265, 270, 35);
		panel.add(passwordField);
		passwordField.setOpaque(false);
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(Color.RED);
		passwordField_1.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		passwordField_1.setBounds(413, 354, 270, 35);
		panel.add(passwordField_1);
		passwordField_1.setOpaque(false);
		
		Name = new JTextField();
		Name.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		Name.setForeground(Color.RED);
		Name.setBounds(413, 459, 270, 35);
		panel.add(Name);
		Name.setColumns(10);
		Name.setOpaque(false);
		
		Phno = new JTextField();
		Phno.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		Phno.setForeground(Color.RED);
		Phno.setBounds(415, 553, 268, 35);
		panel.add(Phno);
		Phno.setColumns(10);
		Phno.setOpaque(false);
		
		button = new JButton("");
		button.setBounds(0, 0, 73, 59);
		panel.add(button);
		button.setContentAreaFilled( false );
		button.setBorder( null );
		button.setBackground(null);
		button.setForeground(null);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Cust re(1).jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
	}

	protected void registerCustomer() throws Exception {
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
			
		con = DriverManager.getConnection(url,username,password);
	
		String validate = "";
		boolean wrong = false;
		
		int inserted = 0;
				
				if(Aadhar.getText().equals("")) {
					validate = validate + ",Aadhar id";
					wrong = true;
				}
				if(passwordField.getText().equals("")) {
					validate = validate + ",Password";
					wrong = true;
				}
				if(passwordField_1.getText().equals("")) {
					validate = validate + ",Re-Enter Password";
					wrong = true;
				}
				if(Name.getText().equals("")) {
					validate = validate + ",Name";
					wrong = true;
				}
				if(Phno.getText().equals("")) {
					validate = validate + ",Phone number";
					wrong = true;
				}
		
		if(wrong) {
			validate = validate.substring(1)+" can't be empty";
			JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+validate,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		else if(!(passwordField.getText().equals(passwordField_1.getText()))) {
			passwordField.setText("");
			passwordField_1.setText("");
			JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Password mismatch"+"\n"+"Re-Enter Password","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		else {
			String query1 = "INSERT INTO CUSTOMER VALUES(?,?,?)";
			PreparedStatement st = con.prepareStatement(query1);
			
			st.setBigDecimal(1,new BigDecimal(Aadhar.getText()));
			st.setString(2,Name.getText());
			st.setBigDecimal(3,new BigDecimal(Phno.getText()));
			
			inserted = st.executeUpdate();
			if(inserted == 1) {
				this.setVisible(false);
				RegistrationSucessful frame = new RegistrationSucessful();
				frame.setVisible(true);
			}
			
			st.close();
			
			String query2 = "INSERT INTO CUSCR VALUES(?,?)";
			st = con.prepareStatement(query2);
			
			st.setBigDecimal(1,new BigDecimal(Aadhar.getText()));
			st.setString(2,passwordField.getText());
			
			inserted = st.executeUpdate();
			st.close();
			
			con.close();
		}
	
	}

	protected void goToHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
}
