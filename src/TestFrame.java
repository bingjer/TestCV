// This file TestFrame.java constructs the Test Frame UI.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private int counter;
	private String driver_loc;
	private JFrame frmTestRunner = new JFrame(); 
	private JTextField txtField_url;
	private JTextField txtField_driver;
	private JButton btn_driver;
	private JList list;
	private ButtonGroup btnGroup;
	private JRadioButton rdbtn_chrome;
	private JRadioButton rdbtn_firefox;
	private DefaultListModel phase_list;
	private JSpinner spinner;

	
	// Test Frame for constructed if creating a new test is selected.
	public TestFrame(String workfolder) {
		// Frame and content pane construction.
		frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmTestRunner.setContentPane(contentPane);
		
		// Text field definition.
		txtField_url = new JTextField();
		txtField_url.setColumns(10);
		txtField_driver = new JTextField();
		txtField_driver.setEditable(false);
		txtField_driver.setColumns(10);
		
		// Button definitions.
		JButton add_phase_btn = new JButton("Add Phase");
		JButton delete_phase_btn = new JButton("Delete Phase");
		JButton save_test_btn = new JButton("Save Test");
		JButton run_btn = new JButton("Run");
		JButton view_phase_btn = new JButton("View Phase");
		JButton btn_driver = new JButton("Choose Driver");
		
		// Label definitions.
		JLabel lbl_runs = new JLabel("Runs:");
		JLabel lbl_url = new JLabel("URL:");
		JLabel lbl_driver = new JLabel("Driver:");
		
		// Radio button definition.
		btnGroup = new ButtonGroup();
		rdbtn_chrome = new JRadioButton("Chrome");
		rdbtn_firefox = new JRadioButton("FireFox");
		btnGroup.add(rdbtn_chrome);
		btnGroup.add(rdbtn_firefox);
		
		// List definition.
		phase_list = new DefaultListModel();
		list = new JList(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		// Definition of vector to hold phase information.
		Vector<PhaseInfo> phase_info_vec = new Vector<PhaseInfo>();
		
		// Counter definition to keep track of user selected item.
		counter = 0;
		
		// Spinner definition for number of runs.
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		
		// Button listeners.
		add_button_listener(add_phase_btn, phase_info_vec);
		delete_button_listener(delete_phase_btn, phase_info_vec);
		save_button_listener(save_test_btn, phase_info_vec);
		run_button_listener(run_btn, phase_info_vec, workfolder);
		view_phase_listener(view_phase_btn, phase_info_vec);
		driver_button_listener(btn_driver);
		close_window_listener(frmTestRunner);
	
		// Group layout implementation.
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_runs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(run_btn))
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_url)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtField_url, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addComponent(view_phase_btn)
						.addComponent(delete_phase_btn)
						.addComponent(add_phase_btn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtn_chrome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtn_firefox))
						.addComponent(btn_driver)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_driver)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtField_driver, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addComponent(save_test_btn))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_url)
								.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtn_chrome)
								.addComponent(rdbtn_firefox))
							.addGap(8)
							.addComponent(btn_driver)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_driver)
								.addComponent(txtField_driver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(add_phase_btn)
							.addGap(10)
							.addComponent(delete_phase_btn)
							.addGap(13)
							.addComponent(view_phase_btn)
							.addGap(1)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_runs)
						.addComponent(save_test_btn)
						.addComponent(run_btn)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
					contentPane.setLayout(gl_contentPane);
	}
	
	
	// Test frame for existing test
	public TestFrame(Vector<PhaseInfo> phase_info_vec, String workfolder) {
		frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmTestRunner.setContentPane(contentPane);
		
	
		// List definitions.
		phase_list = new DefaultListModel();
		for(PhaseInfo phase : phase_info_vec) {
			phase_list.addElement(phase.get_phase_name());
		}
		list = new JList(phase_list);
		list.setModel(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		// TextField definitions.
		txtField_url = new JTextField();
		txtField_url.setColumns(10);
		txtField_driver = new JTextField();
		txtField_driver.setEditable(false);
		txtField_driver.setColumns(10);
		
		// Radio button definitions
		btnGroup = new ButtonGroup();
		rdbtn_chrome = new JRadioButton("Chrome");
		rdbtn_firefox = new JRadioButton("FireFox");
		btnGroup.add(rdbtn_chrome);
		btnGroup.add(rdbtn_firefox);
		
		//Button definitions
		btn_driver = new JButton("Choose Driver");
		JButton add_phase_btn = new JButton("Add Phase");
		JButton delete_phase_btn = new JButton("Delete Phase");
		JButton save_test_btn = new JButton("Save Test");
		JButton run_btn = new JButton("Run");
		JButton view_phase_btn = new JButton("View Phase");

		// Label definitions
		JLabel lbl_url = new JLabel("URL:");
		JLabel lbl_runs = new JLabel("Runs:");
		JLabel lbl_driver = new JLabel("Driver:");
		
		// Spinner definition for choosing number of runs.
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		//	Button listeners.
		add_button_listener(add_phase_btn, phase_info_vec);
		delete_button_listener(delete_phase_btn, phase_info_vec);
		save_button_listener(save_test_btn, phase_info_vec);
		run_button_listener(run_btn, phase_info_vec, workfolder);
		view_phase_listener(view_phase_btn, phase_info_vec);
		driver_button_listener(btn_driver);
		close_window_listener(frmTestRunner);

		
		// GroupLayout implementation
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_runs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(run_btn))
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_url)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtField_url, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addComponent(view_phase_btn)
						.addComponent(delete_phase_btn)
						.addComponent(add_phase_btn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtn_chrome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtn_firefox))
						.addComponent(btn_driver)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_driver)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtField_driver, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addComponent(save_test_btn))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_url)
								.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtn_chrome)
								.addComponent(rdbtn_firefox))
							.addGap(8)
							.addComponent(btn_driver)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_driver)
								.addComponent(txtField_driver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(add_phase_btn)
							.addGap(10)
							.addComponent(delete_phase_btn)
							.addGap(13)
							.addComponent(view_phase_btn)
							.addGap(1)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_runs)
						.addComponent(save_test_btn)
						.addComponent(run_btn)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
					contentPane.setLayout(gl_contentPane);
		
	}
	
	// Validates the url and alerts the user if it doesn't start with http:// or https://.
	private boolean validate_url(String url) {
		if(url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
			return true;
		}
		else {
			String opt_buttons[] = {"Ok"};
	        JOptionPane.showOptionDialog(null, "The URL must begin with http:// or https://. Please try again.", "TestCV", JOptionPane.DEFAULT_OPTION, 
	        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
			return false;
		}
	}
	
	// Creates a listener for the "Choose Driver' button.
	private void driver_button_listener(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opens the system file chooser for the user to select the location of their driver.
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				driver_loc = j.getSelectedFile().getAbsolutePath();
				txtField_driver.setText(driver_loc);

			}
		});
	}
	
	// Creates a listener for the "View Phase" button.
	private void view_phase_listener(JButton btn, Vector<PhaseInfo> phase_info_vec) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				System.out.println(index);
				System.out.println(phase_info_vec.get(index).get_phase_name());
				PhaseInfo selected_phase = phase_info_vec.get(index);
				String phase_name = selected_phase.get_phase_name();
				String element = selected_phase.get_element();
				String screenshot = selected_phase.get_screenshot();
				String interaction = selected_phase.get_interaction_type();
				String message = selected_phase.get_message();
				String url = txtField_url.getText();
				int delay = selected_phase.get_wait_time();
				
				// Checks to validate user input on the test frame before opening a JDialog.
				if(validate_url(url)) {
					if(!rdbtn_chrome.isSelected() && !rdbtn_firefox.isSelected()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please select your driver type.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_url.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please fill out the URL.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_driver.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please select your driver.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else {
						String driver_loc = txtField_driver.getText();
						String driver_type = "";
						if(rdbtn_chrome.isSelected()) {
							driver_type = "chrome";
						}
						else  {
							driver_type = "firefox";
						}
						// Logic to make sure the phase is being added in the correct order of the list.
						counter = list.getSelectedIndex();
						PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction, message, delay, frmTestRunner, driver_loc, driver_type);
						phase_info_vec.set(index, selected_phase);
						phase_list.set(index, selected_phase.get_phase_name());
						list.setModel(phase_list);
					}
				}
			}
		});
	}
	
	// Creates a listener for the "Run Phase" button.
	private void run_button_listener(JButton btn, Vector<PhaseInfo> phase_info_vec, String workfolder) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driver_type = "";
				
				// Checks to validate user input on the test frame before opening a the ResultFrame.
				if(validate_url(txtField_url.getText())) {
					if(!rdbtn_chrome.isSelected() && !rdbtn_firefox.isSelected()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please select your driver type.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_url.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please fill out the URL.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_driver.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Please select your driver.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(phase_list.isEmpty()) {
						String opt_buttons[] = {"Ok"};
						JOptionPane.showOptionDialog(null, "Your list is empty. Please add a phase or phases.", "TestCV", 
			        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else {
						if(rdbtn_chrome.isSelected()) {
							driver_type = "chrome";
						}
						else {
							driver_type = "firefox";
						}
						ResultFrame frame = new ResultFrame((int) spinner.getValue(), txtField_url.getText(), txtField_driver.getText(), driver_type, phase_info_vec, workfolder);
						frmTestRunner.dispose();
						
						
					}
				}
				
				
			}
		});
	}
	
	// Creates a listener for the "Save" button.
	private void save_button_listener(JButton btn, Vector<PhaseInfo> phase_info_vec) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opens the system file chooser to allow the user to select where they can save their test.
				TestInfo test = new TestInfo();
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				// Writes the file to the location.
				try {
					test.write_json(file, phase_info_vec);
				} catch (IOException e1) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not write to file. Please try again.", "TestCV", JOptionPane.DEFAULT_OPTION, 
			        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e1.printStackTrace();
				}
			}
		});
	}
	
	// Creates a listener for the "Delete" button.
	private void delete_button_listener(JButton btn, Vector<PhaseInfo> phase_info_vec) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				
				// Makes sure an index in the list is selected before deleting.
				if (index != -1) {
					phase_list.remove(index);
					phase_info_vec.remove(index);
				}
			}
		});
	}
	
	// Creats a listener for the "Add Phase" button.
	private void add_button_listener(JButton btn, Vector<PhaseInfo> phase_info_vec) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhaseInfo phase_info = new PhaseInfo();
				String url = txtField_url.getText();
				String driver_loc = txtField_driver.getText();
				String driver_type = "";
				
				// Adjusts the counter depending on where in the list the user is adding.
				if(list.isSelectionEmpty()) {
					counter = phase_info_vec.size();
				}
				else {
					counter = list.getSelectedIndex() + 1;
				}
				
				if(validate_url(url)) {
					if(!rdbtn_chrome.isSelected() && !rdbtn_firefox.isSelected()) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Please select your driver type.", "TestCV", JOptionPane.DEFAULT_OPTION, 
				        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_url.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Please fill out the URL.", "TestCV", JOptionPane.DEFAULT_OPTION, 
				        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else if(txtField_driver.getText().isEmpty()) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Please select your driver.", "TestCV", JOptionPane.DEFAULT_OPTION, 
				        		JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					}
					else {
						if(rdbtn_chrome.isSelected()) {
							driver_type = "chrome";
						}
						else  {
							driver_type = "firefox";
						}
						PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner, driver_loc, driver_type);
						
						// If the user does not cancel the phase, the information is added to the test list.
						if(!phase_dialog.get_cancel_check()) {
							++counter;
							System.out.println(phase_info.get_phase_name());
							int index = list.getSelectedIndex();
							if (index != -1) {
								phase_info_vec.add(index + 1, phase_info);
								phase_list.add(index + 1, phase_info.get_phase_name());
								list.setModel(phase_list);
							} else {
								phase_info_vec.add(phase_info);
								phase_list.addElement(phase_info.get_phase_name());
								list.setModel(phase_list);
							}
						}
					}
				}
			}
		});
	}
	
	// Adds a listener to check if the red 'X' has been clicked to gracefully exit the program.
	private void close_window_listener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			  @Override
			   public void windowClosing(WindowEvent we) { 
				  String opt_buttons[] = {"Yes","No"};
			      int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
			       if(result == JOptionPane.YES_OPTION) {
			    	   System.exit(0);
			       }
			   }
		});
	}
}
