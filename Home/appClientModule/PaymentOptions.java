import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import java.sql.Statement;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PaymentOptions extends JFrame {

	private JPanel contentPane;
	private JTextField amount;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	
	public PaymentOptions(String[] products,double[] price,int[] quantity,String total,String cid,String oid) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JButton card = new JButton("");
		card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToTransaction(products,price,quantity,total,cid,oid);
			}
		});
		card.setBounds(319, 102, 217, 150);
		panel.add(card);
		card.setOpaque(false);
		card.setContentAreaFilled( false );
		card.setBorder( null );
		card.setBackground(null);
		card.setForeground(null);
		card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton cash = new JButton("");
		cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String orders = "";
				try {
					orders = updateOrders(products,cid,oid,quantity,total);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				gotoPaymentApproved( products, price, quantity,total,cid,oid,orders);
				
				
				
			}
		});
		cash.setBounds(828, 164, 311, 144);
		panel.add(cash);
		cash.setOpaque(false);
		cash.setContentAreaFilled( false );
		cash.setBorder( null );
		cash.setBackground(null);
		cash.setForeground(null);
		cash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		amount = new JTextField();
		amount.setFont(new Font("Tahoma", Font.BOLD, 40));
		amount.setForeground(Color.RED);
		amount.setBounds(1012, 56, 259, 50);
		panel.add(amount);
		amount.setColumns(10);
		amount.setOpaque(false);
		amount.setText(total);
		amount.setEditable(false);
		
		
		JLabel label = new JLabel("");
		panel.add(label);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(0, 0, 1366,756);
		panel.add(label);
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\payments page.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img1.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
		
		
	}

	protected String updateOrders(String[] products, String cid, String oid, int[] quantity, String total) throws Exception {
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		Statement st = con.createStatement();
		
		String query = "Select * from ORDCOUNT";
		
		ResultSet rs;
		
		rs = st.executeQuery(query);
		
		BigDecimal ordcount = new BigDecimal(10000000);
		
		if(rs.next()) {
			 ordcount = rs.getBigDecimal(1);
		}
		
		double ordercount1 = ordcount.doubleValue();
        ordercount1++;
        String order = "BV"+Double.toString(ordercount1);
	
		st.close();
		con.close();
		
		String url1 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username1 = "CHANDRASHEKARA";
		String password1 = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con1;
		
		con1 = DriverManager.getConnection(url1,username1,password1);
		
		String query1 = "UPDATE ORDCOUNT SET ORC = ? WHERE ORC > 0";
		
		PreparedStatement st1 = con1.prepareStatement(query1);
		st1.setBigDecimal(1, new BigDecimal(ordercount1));
		
		int updated = st1.executeUpdate();
		
		st1.close();
		con1.close();
		
		String url2 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username2 = "CHANDRASHEKARA";
		String password2 = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con2;	
		con2 = DriverManager.getConnection(url1,username1,password1);
		
		PreparedStatement st2 ;
		
		String query2 = "";
		BigDecimal cusid = new BigDecimal(cid); 
		
		for(int i=0;i<products.length;i++) {
			
			query2 = "INSERT INTO ORDET VALUES(?,?,?,?,?)";
			st2 = con2.prepareStatement(query2);
			
			st2.setBigDecimal(1,cusid);
			st2.setString(2,order);
			st2.setString(3,oid);
			st2.setInt(4,quantity[i]);
			st2.setString(5,products[i]);
			
			int updates = st2.executeUpdate();
			
			st2.close();
		
		}
		con2.close();
		
		String url3 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username3 = "CHANDRASHEKARA";
		String password3 = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con3;	
		con3 = DriverManager.getConnection(url3,username3,password3);
		
		String query3 = "BEGIN SELECT_SUPPLIER(?); END;";
		PreparedStatement st3 = con3.prepareStatement(query3);	
		st3.setString(1, oid);
		st3.executeQuery();
		
		st3.close();
		con3.close();
		
		String url4 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username4 = "CHANDRASHEKARA";
		String password4 = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con4;	
		con4 = DriverManager.getConnection(url4,username4,password4);
		
		String query4 = "SELECT * FROM SETSUPPLIER";
		PreparedStatement st4 = con4.prepareStatement(query4);	
		ResultSet rs4 = st4.executeQuery();
		
		BigDecimal supplier = new BigDecimal(10000);
		
		if(rs4.next()) {
			supplier = rs4.getBigDecimal(1);
		}
		
		st4.close();
		con4.close();
		rs4.close();
		
		String url5 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username5 = "CHANDRASHEKARA";
		String password5 = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con5;	
		con5 = DriverManager.getConnection(url5,username5,password5);
		
		String query5 = "INSERT INTO ORDERS VALUES(?,?,?,0,?)";
		PreparedStatement st5 = con5.prepareStatement(query5);	
		st5.setString(1,order);
		
		st5.setBigDecimal(2, new BigDecimal(total));
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		st5.setTimestamp(3, timestamp);
		
		st5.setBigDecimal(4, supplier);
		
		st5.executeUpdate();
		 
		st5.close();
		con5.close();
		
		String url6 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username6 = "CHANDRASHEKARA";
		String password6 = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con6;	
		con6 = DriverManager.getConnection(url6,username6,password6);
		
		PreparedStatement st6 ;
		
		for(int i=0;i<products.length;i++) {
			
			String query6 = "UPDATE INVENTORY SET STOCK = STOCK - ?  WHERE PRID = (SELECT PID FROM PRODUCTS WHERE PNAME = ?) AND OUTID = ? ";
			
			st6 = con6.prepareStatement(query6);
			
			st6.setBigDecimal(1, new BigDecimal(quantity[i]));
			st6.setString(2,products[i]);
			st6.setString(3,oid);
			
			st6.executeUpdate();
			
			st6.close();

		}
		
		con6.close();
	
		return order;
		
	}

	protected void goToTransaction(String[] products, double[] price, int[] quantity, String total, String cid, String oid) {
		
		this.setVisible(false);
		CardDetails frame = new CardDetails(products,price,quantity,total,cid,oid);
		frame.setVisible(true);
		
	}

	protected void gotoPaymentApproved(String[] products,double[] price,int[] quantity,String total,String cid,String oid, String orders) {
	
		this.setVisible(false);
		PaymentApproved frame = new PaymentApproved(products, price, quantity, total,cid,oid,orders);
		frame.setVisible(true);
		
	}

}
