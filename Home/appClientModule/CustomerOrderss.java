import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;



import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class CustomerOrderss extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JScrollPane sp;
	public  ListSelectionModel mdl;
	public JToggleButton a[] = new JCheckBox[9];
	int clickedRow;
	
	/**
	 * Create the frame.
	 */
	
	public CustomerOrderss(String cid,String oid)  throws Exception {
		
		/*Note: If you want to use cid in processing query :
		 *      new BigDecimal(cid) as customer id is number(12)
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
		
		JButton home = new JButton("");
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
				goBack(cid);
			}
		});
		back.setBounds(1289, 0, 67, 66);
		panel.add(back);
		back.setOpaque(false);
		back.setContentAreaFilled( false );
		back.setBorder( null );
		back.setBackground(null);
		back.setForeground(null);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		String url = "jdbc:oracle:thin:@DESKTOP-R2AIKK5:1521:XE";
		String username = "CHANDRASHEKARA";
		String password = "CHANDRASHEKARA";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
		
		Connection con;
		
		con = DriverManager.getConnection(url,username,password);
		
		String query = "SELECT P.PNAME,I.DISCOUNT,P.PRICE,P.CAT,P.HLTYLVL FROM PRODUCTS P,INVENTORY I WHERE (P.PID = I.PRID) AND (I.OUTID = ?) AND(I.STOCK > 5) ORDER BY P.PRICE";
		
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1,oid);
		
		ResultSet rs = st.executeQuery();
		
		/*The properties of tables are as follows: */
		
		table = new JTable();
		table.setForeground(new Color(220, 20, 60));
		table.setFont(new Font("Tahoma", Font.BOLD, 30));
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
				"Products", "Discount", "Price", "Actual Price", "Category", "Healthy Level"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(427);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(2).setPreferredWidth(145);
		table.getColumnModel().getColumn(3).setPreferredWidth(179);
		table.getColumnModel().getColumn(4).setPreferredWidth(221);
		table.getColumnModel().getColumn(5).setPreferredWidth(122);
		
		 
		
	    //table.setBounds(247, 46, 905, 328);View of table can be obtained 
		//by setting bounds of the scrollpane sp
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
	
		  int i=0;
		  
		  while(rs.next()) {
			 
				int discount = rs.getInt(2);
				int price = rs.getInt(3);
				
				double actual = (price - ((price*discount)/100));
				
				model.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getInt(3),actual,rs.getString(4),rs.getString(5)});
				
			    table.setRowHeight( i++, 100 );
				  
		}
		 
		  
		  table.setPreferredScrollableViewportSize(new Dimension(0,0));
	      table.setFillsViewportHeight(true);
	      ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

	        sp = new JScrollPane();
	        sp.setBounds(100,204,1233,420);
	        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        sp.setOpaque(false);
	        table.setShowGrid(true);
	        sp.setViewportView(table);
	        sp.getViewport().setOpaque(false);
	        sp.setVisible(true);
	        panel.add(sp);
	        
	     	        
	        table.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
					Point point = e.getPoint();
				    clickedRow = table.rowAtPoint(point);
					table.addRowSelectionInterval(clickedRow,clickedRow);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					Point point = e.getPoint();
					int clickedRow = table.rowAtPoint(point);
					table.addRowSelectionInterval(clickedRow,clickedRow);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
				
	        });
		
		//Table Header:
		Font bigFont = new Font("Tahoma", Font.PLAIN, 18); 
		DefaultTableCellRenderer renderer;
		renderer = (DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		renderer.setFont(bigFont);
		renderer.setPreferredSize(new Dimension(0,50));
		renderer.setBackground(null);
		renderer.setOpaque(false);
		renderer.setForeground(Color.RED);
		
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(renderer);
		
	 JButton place_order = new JButton();
	 place_order.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent e) {
	 	
	 		int arr[];
	 		
	 		arr = table.getSelectedRows();
	 		
	 		String products[] = new String[arr.length];
	 		double price[] = new double[arr.length];
	 		int quantity[] = new int[arr.length];
	 		
	 		for(int i=0;i<arr.length;i++) {
	 			products[i] = (String) table.getValueAt(arr[i],0);
	 			price[i] = (double) table.getValueAt(arr[i], 3);
	 		}
	 		
	 		selectQuantity(products,price,cid,oid);

	 	}
	 });
	 place_order.setBounds(1008, 652, 295, 66);
	 panel.add(place_order);
	 
	panel.add(sp);
	place_order.setOpaque(false);
	place_order.setContentAreaFilled( false );
	place_order.setBorder( null );
	place_order.setBackground(null);
	place_order.setForeground(null);
	place_order.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	     
	JLabel label = new JLabel("");
	panel.add(label);
	label.setBackground(Color.WHITE);
	label.setForeground(Color.WHITE);
	label.setFont(new Font("Tahoma", Font.BOLD, 35));
	label.setBounds(10, 11, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	panel.add(label);
	BufferedImage img1 = null;
	try {
	    img1 = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\new order new.jpg"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Image dimg = img1.getScaledInstance(label.getWidth(),label.getHeight(),
	        Image.SCALE_SMOOTH);
	ImageIcon imageIcon = new ImageIcon(dimg);
	label.setIcon(imageIcon);
		
}
	

	protected void selectQuantity(String[] products, double[] price,String cid,String oid) {
		
		this.setVisible(false);
		SelectQuantity frame = new SelectQuantity(products,price,cid,oid);
		frame.setVisible(true);
		
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

