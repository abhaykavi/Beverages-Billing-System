import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SeeOrders extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTable table;
	JScrollPane sp;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeOrders frame = new SeeOrders();
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
	public SeeOrders(String usr) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1366,756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 1366, 756);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToSupplierHome(usr);
			}
		});
		
		JButton DELIVERED = new JButton("");
		DELIVERED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update_orders(usr);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		DELIVERED.setBounds(1201, 630, 113, 77);
		panel.add(DELIVERED);
		DELIVERED.setOpaque(false);
		DELIVERED.setContentAreaFilled( false );
		DELIVERED.setBorder( null );
		DELIVERED.setBackground(null);
		DELIVERED.setForeground(null);
		DELIVERED.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		button_1.setBounds(1274, 0, 66, 82);
		panel.add(button_1);
		button_1.setBounds(1281, 0, 59, 85);
		panel.add(button_1);
		button_1.setOpaque(false);
		button_1.setContentAreaFilled( false );
		button_1.setBorder( null );
		button_1.setBackground(null);
		button_1.setForeground(null);
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		table = new JTable();
		table.setForeground(new Color(255, 0, 0));
		table.setFont(new Font("Tahoma", Font.BOLD, 31));
		table.setBackground(null);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setOpaque(false);
		table.setSelectionForeground(Color.BLUE);
		table.setSelectionBackground(Color.BLUE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ORDER ID", "CUSTOMER ID"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(368);
		table.getColumnModel().getColumn(1).setPreferredWidth(269);
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT ORDERS.ORDID,CID FROM ORDET,ORDERS WHERE SID = ? AND ORDET.ORDID = ORDERS.ORDID AND STATUS = 0 GROUP BY ORDERS.ORDID,CID";
		
		PreparedStatement st = con.prepareStatement(query);
		st.setBigDecimal(1, new BigDecimal(usr));
		ResultSet rs = st.executeQuery();
		
		int i=0;
		
		
		
		
		while(rs.next()) {
			 
			model.addRow(new Object[]{rs.getString(1),rs.getBigDecimal(2)});
			
		    table.setRowHeight( i++, 100 );
			  
	}
		
		st.close();
		rs.close();
		con.close();
		
		  table.setPreferredScrollableViewportSize(new Dimension(0,0));
	      table.setFillsViewportHeight(true);
	      ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

	      sp = new JScrollPane();
	      sp.setBounds(215,207,643,376);
	      sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      sp.setOpaque(false);
	      table.setShowGrid(true);
	      sp.setViewportView(table);
	      sp.getViewport().setOpaque(false);
	      sp.setVisible(true);
	      panel.add(sp);
		
		JButton button = new JButton("");
		button.setBounds(0, 0, 89, 71);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToHome();
			}
		});
		button.setBounds(0, 0, 75, 71);
		panel.add(button);
		button.setOpaque(false);
		button.setContentAreaFilled( false );
		button.setBorder( null );
		button.setBackground(null);
		button.setForeground(null);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 35));
		textField.setBackground(Color.YELLOW);
		textField.setForeground(Color.RED);
		textField.setBounds(405, 662, 494, 45);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\orders to be fulfilled(1).jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
	}

	protected void update_orders(String usr) throws Exception {
		
		
		
		
		String url1 = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username1 = "CHANDRASHEKARA";
		String password1 = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con1;
		
		con1 = DriverManager.getConnection(url1,username1,password1);
	     
		String query1 = "UPDATE ORDERS SET STATUS = 1 WHERE ORDID = ?";
		PreparedStatement st1 = con1.prepareStatement(query1);
		
		st1.setString(1,textField.getText());
		
		st1.executeUpdate();
		
		st1.close();
		con1.close();
		
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT ORDERS.ORDID,CID FROM ORDET,ORDERS WHERE SID = ? AND ORDET.ORDID = ORDERS.ORDID AND STATUS = 0 GROUP BY ORDERS.ORDID,CID";
		
		PreparedStatement st = con.prepareStatement(query);
		st.setBigDecimal(1, new BigDecimal(usr));
		ResultSet rs = st.executeQuery();
		
		int i=0;
		
		while(rs.next()) {
			 
			dm.addRow(new Object[]{rs.getString(1),rs.getBigDecimal(2)});
			
		    table.setRowHeight( i++, 100 );
			  
	}
		
		st.close();
		rs.close();
		con.close();
	}

	protected void goToSupplierHome(String usr) {
		this.setVisible(false);
		SupplierHome frame = new SupplierHome(usr);
		frame.setVisible(true);
	}

	protected void goToHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
}
