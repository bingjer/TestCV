import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;



public class WelcomeFrame extends JInternalFrame {
	
	private JFrame jFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//System.out.println(Core.NATIVE_LIBRARY_NAME);
		//System.loadLibrary("opencv_java455");
	}
	
	
	
	
	public WelcomeFrame() {
		jFrame = new JFrame("Welcome!");
		jFrame.setAlwaysOnTop(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setBounds(100, 100, 450, 300);
		//contentPane.add(internalFrame, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Exisiting test");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser j = new JFileChooser();
					j.showOpenDialog(null);
					String file = j.getSelectedFile().getAbsolutePath();
					System.out.println(file);
					
					
					Vector<PhaseInfo> phase_vec = new Vector<PhaseInfo>();
					TestInfo test = new TestInfo();
					test.load_json(file, phase_vec);
					TestFrame tFrame = new TestFrame(phase_vec);
					jFrame.dispose();
					
				} catch (JsonSyntaxException err) {
					err.printStackTrace();
					NotifyFrame nf = new NotifyFrame("Could not read the JSON file. Please make sure the correct file is chosen.");
					
				} catch (FileNotFoundException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
					NotifyFrame nf = new NotifyFrame("Could not open file.");
				} 
			}
		});
		
		JButton btnNewButton_1 = new JButton("New Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.dispose();
				TestFrame tFrame = new TestFrame();
			}
		});
		
		JLabel lblNewLabel = new JLabel("TestCV");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		
		GroupLayout groupLayout = new GroupLayout(jFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(90))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(165, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(62))
		);
		jFrame.getContentPane().setLayout(groupLayout);

	}
}
