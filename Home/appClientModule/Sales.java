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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class Sales extends JFrame {

	private JPanel contentPane;
	JTable table;
	JScrollPane sp;
	private JTextField txtTotalPrice;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	public Sales(String usr) throws Exception{
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
	
	JButton button = new JButton("");
	button.setBounds(0, 0, 89, 72);
	panel.add(button);
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			goToHome();
		}
	});
	button.setBounds(0, 0, 88, 77);
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
			goBack();
		}
	});
	button_1.setBounds(1288, 11, 68, 66);
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
			"ORDER ID", "DATE", "STATUS", "SID", "PRICE"
		}
	));
	table.getColumnModel().getColumn(0).setPreferredWidth(294);
	table.getColumnModel().getColumn(1).setPreferredWidth(413);
	table.getColumnModel().getColumn(3).setPreferredWidth(197);
	table.getColumnModel().getColumn(4).setPreferredWidth(130);
	
	
	DefaultTableModel model = (DefaultTableModel)table.getModel();
	
	String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
	String username = "CHANDRASHEKARA";
	String password = "CHANDRASHEKARA";

	Class.forName("oracle.jdbc.driver.OracleDriver");
	DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
	
	Connection con;
	
	con = DriverManager.getConnection(url,username,password);
	
	String query = "SELECT ORDERS.ORDID,DT,STATUS,SID,PRICE FROM ORDET,ORDERS WHERE OID = ? AND ORDET.ORDID = ORDERS.ORDID  GROUP BY ORDERS.ORDID,DT,STATUS,SID,PRICE";
	
	PreparedStatement st = con.prepareStatement(query);
	st.setString(1, usr);
	ResultSet rs = st.executeQuery();
	
	int i = 0;
	double price = 0;
	BigDecimal num ;
	
	while(rs.next()) {
		 
		model.addRow(new Object[]{rs.getString(1),rs.getDate(2),rs.getBigDecimal(3),
				rs.getBigDecimal(4),rs.getBigDecimal(5)});
		
	    table.setRowHeight( i++, 100 );
	    
	    price = price + rs.getBigDecimal(5).doubleValue();
		  
}
	
	
	
	
	table.setPreferredScrollableViewportSize(new Dimension(0,0));
    table.setFillsViewportHeight(true);
    ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

    
    
    
    sp = new JScrollPane();
    sp.setBounds(104,207,1135,376);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    sp.setOpaque(false);
    table.setShowGrid(true);
    sp.setViewportView(table);
    sp.getViewport().setOpaque(false);
    sp.setVisible(true);
    panel.add(sp);
	
	txtTotalPrice = new JTextField();
	txtTotalPrice.setText("Total Price  :");
	txtTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 26));
	txtTotalPrice.setBackground(Color.ORANGE);
	txtTotalPrice.setForeground(Color.RED);
	txtTotalPrice.setBounds(846, 635, 172, 44);
	panel.add(txtTotalPrice);
	txtTotalPrice.setColumns(10);
	
	textField = new JTextField(String.valueOf(price));
	textField.setBackground(Color.ORANGE);
	textField.setFont(new Font("Tahoma", Font.BOLD, 26));
	textField.setForeground(Color.RED);
	textField.setBounds(1073, 635, 143, 44);
	panel.add(textField);
	textField.setColumns(10);
	textField.setText(String.valueOf(price));
	
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
	    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Sales.jpg"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Image dimg = img.getScaledInstance(label.getWidth(),label.getHeight(),
	        Image.SCALE_SMOOTH);
	ImageIcon imageIcon = new ImageIcon(dimg);
	label.setIcon(imageIcon);
}

	
	                                                       
		protected void goBack() {
		this.setVisible(false);
		OutletHome frame;
		try {
			frame = new OutletHome(null);
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
