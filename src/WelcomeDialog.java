import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalityType;

public class WelcomeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainFrame frame = new MainFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
private JDialog jFrame;
	
	public WelcomeDialog(JFrame frame, Logger logs) {
		jFrame = new JDialog(frame);
		jFrame.setModalityType(ModalityType.APPLICATION_MODAL);
		jFrame.setModal(true);
		jFrame.setTitle("WelcomeFrame");
		//jFrame.setVisible(true);
		jFrame.setBounds(100, 100, 450, 300);
		//contentPane.add(internalFrame, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Exisiting test");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				System.out.println(file);
				Vector<PhaseInfo> phase_vec = new Vector<PhaseInfo>();
				TestInfo test = new TestInfo();
				try {
					test.load_json(file, phase_vec);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TestFrame tFrame = new TestFrame(phase_vec);
				dispose_frame();
				//For getting a file and/or directory
//				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//				//or
//				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//				chooser.getCurrentDirectory()
//				//or
//				chooser.getSelectedFile();
			}
		});
		
		JButton btnNewButton_1 = new JButton("New Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//TestFrame tFrame = new TestFrame();
				jFrame.dispose();
				TestDialogue tFrame = new TestDialogue(frame, logs);
				
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
		jFrame.setVisible(true);

	}
	
	
	
	public void dispose_frame() {
		jFrame.dispose();
	}
}


