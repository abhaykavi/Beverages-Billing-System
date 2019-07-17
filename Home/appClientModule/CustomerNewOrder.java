import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
import java.sql.Statement;
import java.util.EventObject;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JComponent;



public class CustomerNewOrder extends JFrame  {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	public CustomerNewOrder(String usr) throws Exception{
		
		/*Note: If you want to use usr in processing query :
		 *      new BigDecimal(usr) as customer id is number(12)
		 */
		
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
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		Connection con; 
		con = DriverManager.getConnection(url,username,password);
		
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = st.executeQuery("SELECT OID,ONAME FROM OUTLET");
		
		int rows = 0;
		
		if( rs.last()) {
			rows = rs.getRow();
		}
		
		rs.beforeFirst();
		
		String outlets[] = new String[rows+1];
		String outletid[] = new String[rows+1];
		
		outlets[0] = "Select Outlet";
		outletid[0] = "";
		
		int i = 1;
		
		while(rs.next()) {
			
			outletid[i] = rs.getString(1);
			outlets[i++] = rs.getString(2);
		}
		
		st.close();
		con.close();
		rs.close();
		
		
		JComboBox comboBox = new JComboBox(outlets);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 37));
		comboBox.setForeground(new Color(255, 255, 0));
		comboBox.setBounds(422, 246, 402, 52);
		comboBox.setBackground(Color.RED);
		comboBox.setName("Select Outlet");
		comboBox.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component getListCellRendererComponent(JList list, Object value,
		            int index, boolean isSelected, boolean cellHasFocus) {
		        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }});
		panel.add(comboBox);
		
		JButton home = new JButton("");
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
		
		JButton back = new JButton("");
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
		
		JButton Submit = new JButton("");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = comboBox.getSelectedIndex();
				String selected_oid = outletid[selected];
				customerorders(usr,selected_oid);
			}
		});
		Submit.setBounds(1007, 645, 303, 77);
		panel.add(Submit);
		Submit.setOpaque(false);
		Submit.setContentAreaFilled(false );
		Submit.setBorder( null );
		Submit.setBackground(null);
		Submit.setForeground(null);
		Submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\select outlet.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
	}
	
	protected void customerorders(String cid,String oid) {
		this.setVisible(false);
		try {
		CustomerOrderss frame = new CustomerOrderss(cid,oid);
		frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
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

