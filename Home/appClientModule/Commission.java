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

public class Commission extends JFrame {

	private JPanel contentPane;
	JScrollPane sp;
	JTable table;
	private JTextField TotalCommission;
	private JTextField value;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commission frame = new Commission();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usr 
	 */
	public Commission(String usr) throws Exception {
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
		
		JButton button = new JButton("");
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
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToSupplierHome(usr);
			}
		});
		button_2.setBounds(1281, 0, 59, 85);
		panel.add(button_2);
		button_2.setOpaque(false);
		button_2.setContentAreaFilled( false );
		button_2.setBorder( null );
		button_2.setBackground(null);
		button_2.setForeground(null);
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
				"ORDER ID", "COMMISSION"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(402);
		table.getColumnModel().getColumn(1).setPreferredWidth(143);
		
		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT ORDID,PRICE FROM ORDERS WHERE SID = ? AND STATUS = 1";
		
		PreparedStatement st = con.prepareStatement(query);
		st.setBigDecimal(1, new BigDecimal(usr));
		ResultSet rs = st.executeQuery();
		
		int i=0;
		double total = 0;
		double commission = 0;
		
		BigDecimal var = new BigDecimal(0);
		
		while(rs.next()) {
			 
			var = rs.getBigDecimal(2);
			commission = (var.doubleValue()) / 10;
			
			total = total + commission;
			
			model.addRow(new Object[]{rs.getString(1),commission});
			
		    table.setRowHeight( i++, 100 );
			  
	}
		
		st.close();
		rs.close();
		con.close();
		
		  table.setPreferredScrollableViewportSize(new Dimension(0,0));
	      table.setFillsViewportHeight(true);
	      ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

	      sp = new JScrollPane();
	      sp.setBounds(384,207,541,376);
	      sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      sp.setOpaque(false);
	      table.setShowGrid(true);
	      sp.setViewportView(table);
	      sp.getViewport().setOpaque(false);
	      sp.setVisible(true);
	      panel.add(sp);
		
		TotalCommission = new JTextField();
		TotalCommission.setFont(new Font("Tahoma", Font.BOLD, 30));
		TotalCommission.setText("TOTAL COMMISSION :");
		TotalCommission.setForeground(Color.RED);
		TotalCommission.setBounds(402, 615, 342, 43);
		panel.add(TotalCommission);
		TotalCommission.setColumns(10);
		TotalCommission.setOpaque(false);
		TotalCommission.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		value = new JTextField();
		value.setFont(new Font("Tahoma", Font.PLAIN, 30));
		value.setForeground(Color.RED);
		value.setBounds(754, 615, 171, 43);
		panel.add(value);
		value.setColumns(10);
		value.setOpaque(false);
		value.setText(Double.toString(commission));
		value.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Commission.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
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
