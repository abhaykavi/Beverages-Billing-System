import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
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
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField Username;
	private JTextField UsenameEnter;
	private JTextField Message;
	private JTextField OTPEnter;
	private JTextField EnterNewPassword;
	private JTextField EnterPassword;
	private JTextField ReenterNewPassword;
	private JTextField Again;
	private JButton ResetPassword;
	private JTextArea Otp;
	private JButton Submit;
	private JButton SendOTP;
	private String typeusr = "";
	private String usrname = "";
	private String user = "";
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ForgotPassword(String usrtype) {
		
	    typeusr = usrtype;
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Username = new JTextField();
		Username.setBackground(Color.BLUE);
		Username.setFont(new Font("Tahoma", Font.BOLD, 30));
		Username.setText("Enter Username");
		Username.setForeground(Color.ORANGE);
		Username.setBounds(561, 331, 253, 43);
		panel.add(Username);
		Username.setColumns(10);
		Username.setBorder(null);
		
		UsenameEnter = new JTextField();
		UsenameEnter.setForeground(Color.ORANGE);
		UsenameEnter.setFont(new Font("Tahoma", Font.BOLD, 27));
		UsenameEnter.setBackground(Color.BLUE);
		UsenameEnter.setBounds(843, 331, 354, 43);
		panel.add(UsenameEnter);
		UsenameEnter.setColumns(10);
		UsenameEnter.setBorder(null);
		
		SendOTP =  new JButton("Send OTP");
		SendOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean next = validateUsr();
				
				if(next) {
				
				Username.setVisible(false);
				UsenameEnter.setVisible(false);
				SendOTP.setVisible(false);
				
				Message.setVisible(true);
				Otp.setVisible(true);
				OTPEnter.setVisible(true);
				Submit.setVisible(true);
				
				}
				
			}
		});
		SendOTP.setBackground(Color.BLUE);
		SendOTP.setFont(new Font("Tahoma", Font.BOLD, 30));
		SendOTP.setForeground(Color.ORANGE);
		SendOTP.setBounds(723, 385, 219, 43);
		panel.add(SendOTP);
		
		Otp = new JTextArea();
		Otp.setText("Enter OTP ");
		Otp.setFont(new Font("Tahoma", Font.BOLD, 30));
		Otp.setBackground(Color.BLUE);
		Otp.setForeground(Color.ORANGE);
		Otp.setBounds(637, 385, 177, 36);
		panel.add(Otp);
		Otp.setVisible(false);
		Otp.setBorder(null);
		
		Message = new JTextField();
		Message.setFont(new Font("Tahoma", Font.BOLD, 25));
		Message.setText("OTP has been sent to registered mobile");
		Message.setBackground(Color.BLUE);
		Message.setForeground(Color.ORANGE);
		Message.setBounds(637, 336, 512, 36);
		panel.add(Message);
		Message.setColumns(10);
		Message.setVisible(false);
		Message.setBorder(null);
		
		OTPEnter = new JTextField();
		OTPEnter.setFont(new Font("Tahoma", Font.BOLD, 27));
		OTPEnter.setForeground(Color.ORANGE);
		OTPEnter.setBackground(Color.BLUE);
		OTPEnter.setBounds(843, 385, 186, 36);
		panel.add(OTPEnter);
		OTPEnter.setColumns(10);
		OTPEnter.setVisible(false);
		OTPEnter.setBorder(null);
		
		Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Message.setVisible(false);
				Otp.setVisible(false);
				OTPEnter.setVisible(false);
				Submit.setVisible(false);
				
				EnterNewPassword.setVisible(true);
				EnterPassword.setVisible(true);
				ReenterNewPassword.setVisible(true);
				Again.setVisible(true);
				ResetPassword.setVisible(true);
			}
		});
		Submit.setFont(new Font("Tahoma", Font.BOLD, 30));
		Submit.setForeground(Color.ORANGE);
		Submit.setBackground(Color.BLUE);
		Submit.setBounds(733, 437, 193, 36);
		panel.add(Submit);
		Submit.setVisible(false);
		
		EnterNewPassword = new JTextField();
		EnterNewPassword.setText("Enter New Password");
		EnterNewPassword.setForeground(Color.ORANGE);
		EnterNewPassword.setBackground(Color.BLUE);
		EnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		EnterNewPassword.setBounds(561, 331, 325, 43);
		panel.add(EnterNewPassword);
		EnterNewPassword.setColumns(10);
		EnterNewPassword.setVisible(false);
		EnterNewPassword.setBorder(null);
		
		EnterPassword = new JTextField();
		EnterPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		EnterPassword.setForeground(Color.ORANGE);
		EnterPassword.setBackground(Color.BLUE);
		EnterPassword.setBounds(952, 331, 304, 43);
		panel.add(EnterPassword);
		EnterPassword.setColumns(10);
		EnterPassword.setVisible(false);
		EnterPassword.setBorder(null);
		
		ReenterNewPassword = new JTextField();
		ReenterNewPassword.setText("Re-Enter New Password");
		ReenterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		ReenterNewPassword.setForeground(Color.ORANGE);
		ReenterNewPassword.setBackground(Color.BLUE);
		ReenterNewPassword.setBounds(558, 388, 368, 36);
		panel.add(ReenterNewPassword);
		ReenterNewPassword.setColumns(10);
		ReenterNewPassword.setVisible(false);
		ReenterNewPassword.setBorder(null);
		
		Again = new JTextField();
		Again.setFont(new Font("Tahoma", Font.BOLD, 30));
		Again.setForeground(Color.ORANGE);
		Again.setBackground(Color.BLUE);
		Again.setText("");
		Again.setBounds(952, 385, 304, 36);
		panel.add(Again);
		Again.setColumns(10);
		Again.setVisible(false);
		Again.setBorder(null);
		
		ResetPassword = new JButton("Reset Password");
		ResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					try {
						
						String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
						String username = "CHANDRASHEKARA";
						String password = "CHANDRASHEKARA";
		
						Class.forName("oracle.jdbc.driver.OracleDriver");
						DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
						
						Connection con;
						
						con = DriverManager.getConnection(url,username,password);
						
						
						 String query = "UPDATE CUSCR  SET PWD = ?";
						
						if(typeusr == "Customer" ) {
							BigDecimal a = new BigDecimal(user);
							query = "UPDATE CUSCR  SET PWD = ? WHERE CUID = ?" ; 
						}
						if(typeusr == "Outlet" )
							query = "UPDATE OUCR  SET PWD = ? WHERE OUTLID = (SELECT OID FROM OUTLET WHERE ONAME = ?)" ;
						
						if(typeusr == "Supplier" ) {
							BigDecimal a = new BigDecimal(user);
							query = "UPDATE SUPCR  SET PWD = ? WHERE SUPID = ?" ;
						}			   
						PreparedStatement st = con.prepareStatement(query);
						
						try {
						st.setString(1, Again.getText());
						}catch(Exception e123) {System.out.println("Here 1");}
						
						
						
					int update = st.executeUpdate();
				
                        showSuccessMessage();	
                        
                        st.close();
                        con.close();
					}catch(Exception e2) {e2.printStackTrace();;}
					
				}
					
			
		});
		ResetPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		ResetPassword.setForeground(Color.ORANGE);
		ResetPassword.setBackground(Color.BLUE);
		ResetPassword.setBounds(749, 439, 279, 34);
		panel.add(ResetPassword);
		ResetPassword.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Forgot Password.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
	}

	protected boolean validateUsr() {
		
		user = UsenameEnter.getText();
		
		String query = "SELECT CID FROM CUSTOMER WHERE CID = ?";
		
		if(typeusr == "Customer") {
			query = "SELECT CID FROM CUSTOMER WHERE CID = ?";
		}
			          
		if(typeusr == "Outlet") {
			query = "SELECT OID FROM OUTLET WHERE OID = ?";
		}	
                      
		if(typeusr == "Supplier") {
			query = "SELECT SID FROM SUPPLIER WHERE SID = ?";
		}
		
		try {
			
			String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
			String username = "CHANDRASHEKARA";
			String password = "CHANDRASHEKARA";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			
			Connection con;
			
			con = DriverManager.getConnection(url,username,password);
			
			
            PreparedStatement st = con.prepareStatement(query);		
            
           
           
           if(typeusr == "Customer" || typeusr == "Supplier") {
        	   try {
        		   	st.setBigDecimal(1, new BigDecimal(UsenameEnter.getText()));
        	   }catch(Exception e) {
        		   showErrorMessage();
        		   return false;
        	   }
           }
           else
        	   st.setString(1,UsenameEnter.getText());
           
           try {
        	   	ResultSet rs =  st.executeQuery();
        	   	if(!rs.next()) {
              	  showErrorMessage();
              	  rs.close();
              	  return false;
                }
           }catch(Exception e3) 
           {
        	   showErrorMessage();
               return false;
           }
           
          
          
          st.close();
        
          
         con.close();
		    					
			
			
		}catch(Exception e1) {System.out.println(e1.getMessage());}	
		
		return true;
		
	}

	protected void showSuccessMessage() {
		JOptionPane.showMessageDialog(this, "Liqour DB Says....."+"\n"+"Password Changed Successfully");
	}

	protected void showErrorMessage() {
		JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"No Such User Exists","Error",JOptionPane.ERROR_MESSAGE);
	}
}
