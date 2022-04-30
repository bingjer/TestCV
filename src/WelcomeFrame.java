// This file WelcoemFrame.java contains the logic to construct and implement the Welcome Frame for TestCV.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.google.gson.JsonSyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;



public class WelcomeFrame extends JInternalFrame {
	
	public WelcomeFrame() {
		// Frame configurations.
		JFrame jFrame = new JFrame("Welcome!");
		jFrame.setAlwaysOnTop(true);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setBounds(100, 100, 450, 300);
		
		// Button creation.
		JButton btn_existing_test = new JButton("Exisiting Test");
		JButton btn_new_test = new JButton("New Test");
		JButton btn_workspace = new JButton("Select Workspace");
		
		// TextField creation.
		JTextField txtField_workspace = new JTextField();
		txtField_workspace.setEditable(false);
		txtField_workspace.setColumns(10);
		
		// Label creation.
		JLabel lbl_logo = new JLabel("TestCV");
		lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_logo.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		// Button listeners.		
		close_window_listener(jFrame);
		existing_button_listener(btn_existing_test, txtField_workspace, jFrame);
		new_button_listener(btn_new_test, txtField_workspace, jFrame);
		workspace_button_listener(btn_workspace, txtField_workspace);
		
		
		// Group layout construction.
		GroupLayout groupLayout = new GroupLayout(jFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(lbl_logo, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(165, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtField_workspace, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_existing_test)
							.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
							.addComponent(btn_new_test)))
					.addGap(90))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(152)
					.addComponent(btn_workspace)
					.addContainerGap(165, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lbl_logo)
					.addGap(44)
					.addComponent(txtField_workspace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_workspace)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_existing_test)
						.addComponent(btn_new_test))
					.addGap(62))
		);
		jFrame.getContentPane().setLayout(groupLayout);

	}
	
	// Validates whether a user has selected a working directory or not.
	private boolean is_workfolder_valid(JTextField txtField) {
		return txtField.getText().isEmpty() ? false : true;
	}
	
	// Adds a listener to check if the red 'X' has been clicked to gracefully exit the program.
	private void close_window_listener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String opt_buttons[] = {"Yes","No"};
		        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
		        if(result == JOptionPane.YES_OPTION) {
		        	System.exit(0);
		        }
		    }
		});
	}
	
	// Adds a listener to check if the existing test button has been clicked.
	private void existing_button_listener(JButton btn, JTextField txt, JFrame frame) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(is_workfolder_valid(txt)) {
					try {
						JFileChooser j = new JFileChooser();
						j.showOpenDialog(null);
						String file = j.getSelectedFile().getAbsolutePath();
						Vector<PhaseInfo> phase_vec = new Vector<PhaseInfo>();
						TestInfo test = new TestInfo();
						test.load_json(file, phase_vec);
						TestFrame tFrame = new TestFrame(phase_vec, txt.getText());
						frame.dispose();
					} 
					catch (JsonSyntaxException err) {
						err.printStackTrace();
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "The text file is not properly formatted for TestCV. Please try creating a new test.", "TestCV", 
				        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					} 
					catch (FileNotFoundException err) {
						err.printStackTrace();
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "The file could not be found. Please try again.", "TestCV", JOptionPane.DEFAULT_OPTION, 
				        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					} 
				} 
				
				else {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please first select a workfolder before continuing.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
			}
		});
	}
	
	// Adds a listener to check if the new button has been clicked.
	private void new_button_listener(JButton btn, JTextField txt, JFrame frame) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(is_workfolder_valid(txt)) {
					frame.dispose();
					TestFrame tFrame = new TestFrame(txt.getText());
				}
				else {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please first select a workfolder before continuing.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				
			}
		});
	}
	
	// Creates a listener for the "Select Workspace" button.
	private void workspace_button_listener(JButton btn, JTextField txt) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Populates system window for user to choose the workspace folder of their choosing.
					JFileChooser j = new JFileChooser();
					j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = j.showOpenDialog(null);
					
					if(option == JFileChooser.APPROVE_OPTION) {
						String folder = j.getSelectedFile().getAbsolutePath();
						txt.setText(folder);
					} 
				}  
				catch (Exception err){
					err.printStackTrace();
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "The directory is not valid. Please try again.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
			}
		});
	}	
}
