import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationSucessful extends JFrame {

	private JPanel contentPane;
	JButton btnNewButton_1 = new JButton();
	
	/**
	 * Create the frame.
	 */
	
	public RegistrationSucessful(boolean outlet,String usr) {
		if(outlet) {
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
			
			JButton btnNewButton = new JButton("");
			btnNewButton.setBounds(0, 0, 89, 75);
			panel.add(btnNewButton);
			panel.add(btnNewButton);
			btnNewButton.setOpaque(false);
			btnNewButton.setContentAreaFilled( false );
			btnNewButton.setBorder( null );
			btnNewButton.setBackground(null);
			btnNewButton.setForeground(null);
			btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			btnNewButton_1 = new JButton("<-");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 50));
			btnNewButton_1.setForeground(Color.WHITE);
			btnNewButton_1.setBounds(1185, 11, 145, 51);
			panel.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoSup(usr);
				}
			});
			btnNewButton_1.setOpaque(false);
			btnNewButton_1.setContentAreaFilled( false );
			btnNewButton_1.setBorder( null );
			btnNewButton_1.setBackground(null);
			btnNewButton_1.setForeground(Color.WHITE);
			btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblNewLabel.setBounds(0, 0, 1366,756);
			panel.add(lblNewLabel);
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Registration Successful.jpg"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			lblNewLabel.setIcon(imageIcon);
		}
	}
		
	protected void gotoSup(String usr) {
		
		this.setVisible(false);
		Suppliers frame;
		try {
			frame = new Suppliers(usr);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

 	public RegistrationSucessful() throws Exception {
	
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoHome();
			}
		});
		btnNewButton.setBounds(0, 0, 89, 75);
		panel.add(btnNewButton);
		panel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled( false );
		btnNewButton.setBorder( null );
		btnNewButton.setBackground(null);
		btnNewButton.setForeground(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(0, 0, 1366,756);
		panel.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\Registration Successful.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
		
	}

	protected void gotoHome() {
		this.setVisible(false);
		Home frame = new Home();
		frame.setVisible(true);
	}
}
