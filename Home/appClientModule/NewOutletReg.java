import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewOutletReg extends JFrame {

	private JPanel contentPane;
	private JTextField OutletId;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField Name;
	private JTextField owner;
	private JButton btnNewButton_1;
	private JTextField textField_5;
	private JTextArea address;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public NewOutletReg() {
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
		
		OutletId = new JTextField();
		OutletId.setForeground(Color.RED);
		OutletId.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		OutletId.setBounds(467, 130, 709, 40);
		panel.add(OutletId);
		OutletId.setColumns(10);
		OutletId.setOpaque(false);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		passwordField.setForeground(Color.RED);
		passwordField.setBounds(467, 219, 709, 40);
		panel.add(passwordField);
		passwordField.setColumns(10);
		passwordField.setOpaque(false);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(Color.RED);
		passwordField_1.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		passwordField_1.setBounds(467, 320, 709, 40);
		panel.add(passwordField_1);
		passwordField_1.setColumns(10);
		passwordField_1.setOpaque(false);
		
		Name = new JTextField();
		Name.setForeground(Color.RED);
		Name.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		Name.setBounds(467, 409, 709, 40);
		panel.add(Name);
		Name.setColumns(10);
		Name.setOpaque(false);
		
		owner = new JTextField();
		owner.setForeground(Color.RED);
		owner.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		owner.setBounds(467, 503, 709, 40);
		panel.add(owner);
		owner.setColumns(10);
		owner.setOpaque(false);
		
		address = new JTextArea();
		address.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		address.setForeground(Color.RED);
		address.setBounds(467, 596, 420, 112);
		panel.add(address);
		address.setOpaque(false);
	
		JButton Submit = new JButton("");
		Submit.setBounds(1019, 655, 157, 62);
		panel.add(Submit);
		Submit.setOpaque(false);
		Submit.setContentAreaFilled( false );
		Submit.setBorder( null );
		Submit.setBackground(null);
		Submit.setForeground(null);
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registerOutlet();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		btnNewButton_1.setBounds(0, 0, 68, 62);
		panel.add(btnNewButton_1);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled( false );
		btnNewButton_1.setBorder( null );
		btnNewButton_1.setBackground(null);
		btnNewButton_1.setForeground(null);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0,0,1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Outlet reg.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
		
	}

	protected void registerOutlet() throws Exception {
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
				
				if(OutletId.getText().equals("")) {
					validate = validate + ",Outlet Id";
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
				if(owner.getText().equals("")) {
					validate = validate + ",Owner";
					wrong = true;
				}
				if(address.getText().equals("")) {
					validate = validate + ",Address";
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
			String query1 = "INSERT INTO OUTLET VALUES(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query1);
			
			st.setString(1,OutletId.getText());
			st.setString(2,Name.getText());
			st.setString(3,owner.getText());
			st.setString(4,address.getText());
			
			inserted = st.executeUpdate();
			
			if(inserted == 1) {
				this.setVisible(false);
				RegistrationSucessful frame = new RegistrationSucessful();
				frame.setVisible(true);
			}
			
			st.close();
			
			String query2 = "INSERT INTO OUCR VALUES(?,?)";
			st = con.prepareStatement(query2);
			
			st.setString(1,OutletId.getText());
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
