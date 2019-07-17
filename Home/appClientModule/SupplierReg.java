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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SupplierReg extends JFrame {

	private JPanel contentPane;
	private JTextField Aadhar;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField Name;
	private JTextField textField_2;
	private JTextField Salary;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierReg frame = new SupplierReg();
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
	public SupplierReg(String usr) throws Exception {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
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
		btnNewButton.setBounds(0, 0, 68, 68);
		panel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled( false );
		btnNewButton.setBorder( null );
		btnNewButton.setBackground(null);
		btnNewButton.setForeground(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(usr);
			}
		});
		button_1.setBounds(1288, 0, 68, 77);
		panel.add(button_1);
		button_1.setOpaque(false);
		button_1.setContentAreaFilled( false );
		button_1.setBorder( null );
		button_1.setBackground(null);
		button_1.setForeground(null);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton Submit = new JButton("");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						registerSupplier(usr);
				}catch(Exception f) {
					f.printStackTrace();
				}
			}
		});
		Submit.setBounds(1021, 664, 156, 60);
		panel.add(Submit);
		Submit.setOpaque(false);
		Submit.setContentAreaFilled( false );
		Submit.setBorder( null );
		Submit.setBackground(null);
		Submit.setForeground(null);
		Submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		Aadhar = new JTextField();
		Aadhar.setOpaque(false);
		Aadhar.setForeground(Color.RED);
		Aadhar.setFont(new Font("Tahoma", Font.BOLD, 30));
		Aadhar.setBounds(526, 141, 533, 43);
		panel.add(Aadhar);
		Aadhar.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.RED);
		passwordField.setFont(new Font("Harlow Solid Italic", Font.BOLD, 30));
		passwordField.setBounds(526, 228, 533, 43);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setOpaque(false);
		passwordField_1.setForeground(Color.RED);
		passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		passwordField_1.setBounds(526, 318, 533, 43);
		panel.add(passwordField_1);
		
		Name = new JTextField();
		Name.setOpaque(false);
		Name.setForeground(Color.RED);
		Name.setFont(new Font("Tahoma", Font.BOLD, 30));
		Name.setBounds(526, 423, 533, 43);
		panel.add(Name);
		Name.setColumns(30);
		
		textField_2 = new JTextField();
		textField_2.setOpaque(false);
		textField_2.setForeground(Color.RED);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		textField_2.setBounds(526, 504, 533, 43);
		panel.add(textField_2);
		textField_2.setColumns(50);
		textField_2.setText(usr);
		textField_2.setEditable(false);
		textField_2.setEnabled(false);
		
		Salary = new JTextField();
		Salary.setOpaque(false);
		Salary.setForeground(Color.RED);
		Salary.setFont(new Font("Tahoma", Font.BOLD, 30));
		Salary.setBounds(526, 608, 533, 37);
		panel.add(Salary);
		Salary.setColumns(10);
		
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
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Supplier Re.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
		
	}
		
	
	protected void registerSupplier(String usr) throws Exception {
		
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
			validate = validate + ",Aadhar Id";
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
		if(Salary.getText().equals("")) {
			validate = validate + ",Owner";
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
			String query1 = "INSERT INTO SUPPLIER VALUES(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query1);
			
			st.setBigDecimal(1,new BigDecimal(Aadhar.getText()));
			st.setString(2,usr);
			st.setString(3,Name.getText());
			st.setInt(4,Integer.parseInt(Salary.getText()));
			
			inserted = st.executeUpdate();
			
			if(inserted == 1) {
				this.setVisible(false);
				RegistrationSucessful frame = new RegistrationSucessful(true,usr);
				frame.setVisible(true);
			}
			
			st.close();
			
			String query2 = "INSERT INTO SUPCR VALUES(?,?)";
			st = con.prepareStatement(query2);
			
			st.setBigDecimal(1,new BigDecimal(Aadhar.getText()));
			st.setString(2,passwordField.getText());
			
			inserted = st.executeUpdate();
			st.close();
			
			String query3 = "INSERT INTO SUPTURN VALUES(?,?,0)";
			st = con.prepareStatement(query3);
			
			st.setBigDecimal(1,new BigDecimal(Aadhar.getText()));
			st.setString(2,usr);
			
			inserted = st.executeUpdate();
			
			con.close();
		
	}

}
	protected void goBack(String usr) {
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

	protected void goToHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
}
