import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class WelcomeFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	private JInternalFrame internalFrame;
	
	public WelcomeFrame() {
		internalFrame = new JInternalFrame("Welcome!");
		internalFrame.setClosable(false);
		internalFrame.setVisible(true);
		//contentPane.add(internalFrame, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Exisiting test");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose_frame();
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file_path = j.getSelectedFile().getAbsolutePath();
				System.out.println(file_path);
				
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
				dispose_frame();
				TestFrame tFrame = new TestFrame();
			}
		});
		
		JLabel lblNewLabel = new JLabel("TestCV");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		
		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
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
		internalFrame.getContentPane().setLayout(groupLayout);

	}
	
	public JInternalFrame getInteralFrame() {
		return internalFrame;
	}
	
	public void dispose_frame() {
		internalFrame.dispose();
	}
}
