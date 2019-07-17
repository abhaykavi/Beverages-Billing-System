import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class OrderDetails extends JFrame {

	private JPanel contentPane;
	JTable table;
    JTextArea jta;
    String display = "";
    boolean complete;
    String Cusname = "";
    String outname = "";
    private JTextField textField;
    JButton home;
    JButton back;
    JButton btnSubmit;
	/**
	 * Create the frame.
	 * @param orders 
	 */
	public OrderDetails(String[] products, double[] price,int[] quantity, String total,String cid,String oid, String orders) throws Exception{
		
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
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT CNAME FROM CUSTOMER WHERE CID =?";
		PreparedStatement st = con.prepareStatement(query); 
		st.setBigDecimal(1, new BigDecimal(cid));
		

		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Cusname = rs.getString(1);
			display = display + "BILL" + "\n\n";
			display = display + "Customer Name : "+Cusname+"\n";
			display = display + "Customer Id   : "+cid+"\n"+"\n";
		}
		
		rs.close();
		st.close();
		con.close();
	                     		
		double ttl = 0;
		
		for(int i=0;i<products.length;i++) {
			display = display + "Order Id : "+orders+"\n";
			display = display + "Product  : "+products[i]+"\n";
			display = display + "Price    : "+price[i]+"\n";
			display = display + "Quantity : "+quantity[i]+"\n";
			display = display + "Total    : "+price[i]*quantity[i]+"\n"+"\n";
			ttl = ttl + price[i]*quantity[i];
		}
		
		display = display + "Total Price : " + ttl;
		
	    jta = new JTextArea(display);
	    jta.setBackground(Color.ORANGE);
	    jta.setForeground(Color.RED);
	    jta.setFont(new Font("Tahoma",Font.BOLD,30));
	    
		JScrollPane sp = new JScrollPane();
		sp.setBounds(259, 203, 775, 330);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setOpaque(false);
        sp.setViewportView(jta);
        sp.getViewport().setOpaque(false);
        sp.setVisible(true);
		panel.add(sp);
		
		JButton btnNewButton = new JButton("Generate Bill");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 complete = jta.print();
					
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		if(complete) {
			JOptionPane.showMessageDialog(this,"Saved Bill","",JOptionPane.INFORMATION_MESSAGE);
		}
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(522, 558, 280, 44);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 30));
		textField.setForeground(Color.RED);
		textField.setBounds(542, 613, 111, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update_rating(cid,oid);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSubmit.setForeground(Color.RED);
		btnSubmit.setBounds(691, 613, 186, 29);
		panel.add(btnSubmit);
		
		home = new JButton("");
		home.setBounds(0, 0, 89, 72);
		panel.add(home);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		home.setBounds(0, 0, 88, 77);
		panel.add(home);
		home.setOpaque(false);
		home.setContentAreaFilled( false );
		home.setBorder( null );
		home.setBackground(null);
		home.setForeground(null);
		home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		home.setEnabled(false);
		
		back = new JButton("");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(cid);
			}
		});
		back.setBounds(1288, 11, 68, 66);
		panel.add(back);
		back.setOpaque(false);
		back.setContentAreaFilled( false );
		back.setBorder( null );
		back.setBackground(null);
		back.setForeground(null);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setEnabled(false);
		
		JLabel label = new JLabel("");
		panel.add(label);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(0, 0, 1366,756);
		panel.add(label);
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\order success.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img1.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
		
	}
	
	protected void update_rating(String cid, String oid) throws Exception {
		
		home.setEnabled(true);
		back.setEnabled(true);
		btnSubmit.setEnabled(false);
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
        Connection con;	
		con = DriverManager.getConnection(url,username,password);
		
		String query = "INSERT INTO RATING VALUES(?,?,?)";
		PreparedStatement st = con.prepareStatement(query);
		
		st.setString(1, oid);
		st.setBigDecimal(2, new BigDecimal(cid));
		st.setBigDecimal(3, new BigDecimal(textField.getText()));
	try
	{
		st.executeUpdate();
	}
	catch (Exception e)
	{
		
		btnSubmit.setEnabled(true);
		JOptionPane.showMessageDialog(this,"Liqour DB Says....."+"\n"+"Value out of bound","Error",JOptionPane.ERROR_MESSAGE);
	}
	}

	protected void goBack(String usr) {
		this.setVisible(false);
		CustomerHome frame;
		try {
			frame = new CustomerHome(usr);
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
