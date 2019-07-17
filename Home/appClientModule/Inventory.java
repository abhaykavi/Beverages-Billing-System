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

public class Inventory extends JFrame {

	private JPanel contentPane;
	JTable table;
	JScrollPane sp;
	JButton home,back;

	/**
	 * Launch the application.
	 * @param usr 
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
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
	public Inventory(String usr) throws Exception{
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
		
		
		back = new JButton("");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(usr);
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
				"PRODUCT ID", "PRODUCT NAME", "QUANTITY"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(134);
		table.getColumnModel().getColumn(1).setPreferredWidth(491);
		table.getColumnModel().getColumn(2).setPreferredWidth(186);
		
		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT I.PRID,P.PNAME,I.STOCK FROM PRODUCTS P,INVENTORY I WHERE P.PID = I.PRID AND I.OUTID = ? ";
		
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, usr);
		ResultSet rs = st.executeQuery();
		
		int i=0;
		
		while(rs.next()) {
			 
			model.addRow(new Object[]{rs.getBigDecimal(1),rs.getString(2),rs.getBigDecimal(3)});
			
		    table.setRowHeight( i++, 100 );
			  
	}
		
		st.close();
		rs.close();
		con.close();
		
		  table.setPreferredScrollableViewportSize(new Dimension(0,0));
	      table.setFillsViewportHeight(true);
	      ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

	      sp = new JScrollPane();
	      sp.setBounds(256,202,780,420);
	      sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      sp.setOpaque(false);
	      table.setShowGrid(true);
	      sp.setViewportView(table);
	      sp.getViewport().setOpaque(false);
	      sp.setVisible(true);
	      panel.add(sp);
	      
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
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Inventory.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
	}
	
	protected void goToHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
	
	protected void goBack(String usr) {
		this.setVisible(false);
		OutletHome frame;
		try {
			frame = new OutletHome(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
