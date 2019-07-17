import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaymentApproved extends JFrame {

	private JPanel contentPane;
	Timer timer;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @param orders 
	 */
	
	public PaymentApproved(String[] products, double[] price,int[] quantity, String total,String cid,String oid, String orders) {
		
		
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
		
		JLabel label = new JLabel("");
		panel.add(label);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 35));
		label.setBounds(0, 0, 1366,756);
		panel.add(label);
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File("C:\\Users\\Dell\\Downloads\\payment_data.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img1.getScaledInstance(label.getWidth(),label.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		label.setIcon(imageIcon);
		
		timer = new Timer(3000,new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent ae) {
				
                goToOrderDetails(products, price,quantity, total,cid,oid,orders);
                
            }
		});
		
		timer.start();
		
	}

	protected void goToOrderDetails(String[] products, double[] price,int[] quantity,String total,String cid,String oid, String orders) {
		
		this.setVisible(false);
		this.dispose();
		
		OrderDetails frame;
		try {
			 frame = new OrderDetails(products, price,quantity, total,cid,oid,orders);
			 frame.setVisible(true);
			 timer.stop();
		}catch(Exception e) {};
		
		
		
	}

}
