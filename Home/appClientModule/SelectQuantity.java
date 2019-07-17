import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
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
import javax.swing.table.TableColumn;
import javax.swing.JList;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class SelectQuantity extends JFrame {

	private JPanel contentPane;
	public JTable table;
	public JScrollPane sp;
	private JTextField TotalPrice;
	private JTextField setTotal;
	int quantity[];

	public SelectQuantity(String[] products,double[] price,String cid,String oid) {
		
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
				
		table = new JTable();
		table.setForeground(new Color(220, 20, 60));
		table.setFont(new Font("Tahoma", Font.BOLD, 30));
		table.setBackground(null);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setOpaque(false);
	    table.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Products", "Price", "SetQuantity"
	    	}
	    ));
	    table.getColumnModel().getColumn(0).setPreferredWidth(556);
	    table.getColumnModel().getColumn(1).setPreferredWidth(162);
	    table.getColumnModel().getColumn(2).setPreferredWidth(156);
	    
	    DefaultTableModel model = (DefaultTableModel)table.getModel();
	    
	    
	    for(int i=0;i<products.length;i++)
	    {
	    	model.addRow(new Object[] {products[i],price[i],1});
	    	
	    	table.setRowHeight( i, 100 );
	    }
	    
	
	    setQuantity(table, table.getColumnModel().getColumn(2));	     
	    
	    table.setPreferredScrollableViewportSize(new Dimension(0,0));
	    table.setFillsViewportHeight(true);
	    ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
	    table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
	    
	    sp = new JScrollPane();
        sp.setBounds(235,208,893,293);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setOpaque(false);
        table.setShowGrid(true);
        sp.setViewportView(table);
        sp.getViewport().setOpaque(false);
        sp.setVisible(true);
        panel.add(sp);
        
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
				goBack(cid,oid);
			}
		});
		back.setBounds(1277, 0, 63, 77);
		panel.add(back);
		back.setOpaque(false);
		back.setContentAreaFilled( false );
		back.setBorder( null );
		back.setBackground(null);
		back.setForeground(null);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		TotalPrice = new JTextField();
		TotalPrice.setText("Total Price :");
		TotalPrice.setFont(new Font("Tahoma", Font.BOLD, 60));
		TotalPrice.setForeground(Color.RED);
		TotalPrice.setBounds(527, 551, 384, 63);
		panel.add(TotalPrice);
		TotalPrice.setColumns(10);
		TotalPrice.setOpaque(false);
		TotalPrice.setBorder(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int quant[] = new int[products.length];
				quant = getQuantity();
				goToPayment(products,price,quant,cid,oid);
				
			}

			int quantity[] = new int[products.length];
			private int[] getQuantity() {
				for(int i=0;i<products.length;i++) {
					quantity[i] = (int)table.getValueAt(i, 2); 
				}
				return quantity;
			}
		});
		btnNewButton.setBounds(1008, 642, 299, 65);
		panel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled( false );
		btnNewButton.setBorder( null );
		btnNewButton.setBackground(null);
		btnNewButton.setForeground(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		setTotal = new JTextField();
		setTotal.setFont(new Font("Tahoma", Font.BOLD, 60));
		setTotal.setForeground(Color.RED);
		setTotal.setBounds(921, 551, 273, 63);
		panel.add(setTotal);
		setTotal.setColumns(10);
		setTotal.setOpaque(false);
		setTotal.setBorder(null);
		setTotal.setText(getTotal());
		
		JLabel label = new JLabel("");
		panel.add(label);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(0, 0, 1366,756);
		panel.add(label);
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\quantity.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img1.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
		
	}
	
	
	


	protected void goToPayment(String[] products,double[] price,int[] quant,String cid,String oid) {
		this.setVisible(false);
		PaymentOptions frame = new PaymentOptions(products,price,quant,setTotal.getText(),cid,oid);
        frame.setVisible(true);		
	}


	private String getTotal() {
		
		double total = 0;
		String totalValue = "";
		
		for(int i=0;i<table.getRowCount();i++) {
			
			total = total + (((double)table.getValueAt(i,1) * (int)table.getValueAt(i,2)));
		}
		
		totalValue = Double.toString(total);
		
		return totalValue;
	}


	public void setQuantity(JTable table,
            TableColumn setQuant) {
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		
		comboBox.setFont(new Font("Tahoma",Font.BOLD,30));
		comboBox.setBackground(Color.ORANGE);
		comboBox.setForeground(Color.RED);
		comboBox.setSize(156, 100);
		comboBox.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component getListCellRendererComponent(JList list, Object value,
		            int index, boolean isSelected, boolean cellHasFocus) {
		        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }});
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
			 
				setTotal.setText(getTotal());
                                				
			}
			
		});
		setQuant.setCellEditor(new DefaultCellEditor(comboBox));
		
	}

	protected void goBack(String cid,String oid) {
		this.setVisible(false);
		CustomerOrderss frame;
		try {
			frame = new CustomerOrderss(cid,oid);
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
