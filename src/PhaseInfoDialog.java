import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import java.awt.Dialog.ModalityType;
import javax.swing.JSpinner;

public class PhaseInfoDialog extends JDialog {

	private final JPanel contentPanel;
	private JTextField txtField_phaseName;
	private JTextField txtField_addElement;
	private JTextField textField_takeScreenshot;
	private JTextField txtField_type;
	private String file_name;
	private boolean take_screenshot_selected;
	private boolean cancel_check;


	
	
	// Used for viewing phase
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url, String phase_name, String element, String screenshot, String interaction, String message, int delay, JFrame frame, String driver_loc, String driver_type) {
		take_screenshot_selected = false;
		this.cancel_check = false;
		JDialog dialog = new JDialog(frame);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		
		JButton btn_upload = new JButton("Upload");
		
		JLabel lbl_or = new JLabel("Or");
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(delay, 0, 100, 1));
		
		JLabel lbl_delay = new JLabel("Delay:");
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_lClick = new JRadioButton("Left Click");
		
		JRadioButton rdbtn_rClick = new JRadioButton("Right Click");
		
		JRadioButton rdbtn_wait = new JRadioButton("Wait");
		
		JRadioButton rdbtn_type = new JRadioButton("Type:");
		
		dialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String opt_buttons[] = {"Yes", "No"};
		        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit? This phase will not be saved.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
		        if(result == JOptionPane.YES_OPTION) {
		        	dialog.dispose();
		        	set_cancel_check(true);
		        }
		    }
		});
		
		
		
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		btnGroup.add(rdbtn_wait);
		
		switch(interaction) {
		case "Lclick":
			rdbtn_lClick.setSelected(true);
			break;
		case "Rclick":
			rdbtn_rClick.setSelected(true);
			break;
		case "Wait":
			rdbtn_wait.setSelected(true);
			break;
		case "Type":
			rdbtn_type.setSelected(true);
			break;
		default:
			break;
		}
		
		
		JButton btn_done = new JButton("Ok");
		btn_done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtField_phaseName.getText();
				String element = txtField_addElement.getText();
				String expected = format_path(textField_takeScreenshot.getText());
				int wait_time = (int) spinner.getValue();
				phase_info.set_wait_time(wait_time);
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
				System.out.println(phase_info.get_screenshot());
				
				String interaction_type = "";
				
				boolean has_duplicate = false;
				
				System.out.println("this is1: " + index);
				// Checks if a duplicate phase name is being added.
				for(int i = 0; i < phase_info_vec.size(); i++) {
					if(i == index) {
						System.out.println("this is2: " + index);
						continue;
					}
					if(phase_info_vec.get(i).get_phase_name().equals(text)) {
						has_duplicate = true;
					}
				}
				
				
				
				if(txtField_phaseName.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please enter a unique phase name.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(has_duplicate) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "The desired phase name already exists. Please choose another name.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(!rdbtn_lClick.isSelected() && !rdbtn_rClick.isSelected() && !rdbtn_wait.isSelected() && !rdbtn_type.isSelected()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please select an interaction type.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(textField_takeScreenshot.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please upload or take a screenshot.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(rdbtn_type.isSelected() && txtField_type.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please enter a message to be typed.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(txtField_addElement.getText().isEmpty() && !rdbtn_wait.isSelected()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please upload an element unlessing choosing the wait option.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else {
					if (rdbtn_lClick.isSelected()) {
						phase_info.set_interaction_type("Lclick");
						interaction_type = "Lclick";
					}
					else if (rdbtn_rClick.isSelected()) {
						phase_info.set_interaction_type("Rclick");
						interaction_type = "Rclick";
					}
					else if (rdbtn_wait.isSelected()) {
						phase_info.set_interaction_type("Wait");
						interaction_type = "Wait";
					}
					else  {
						phase_info.set_interaction_type("Type");
						interaction_type = "Type";
						String message = txtField_type.getText();
						phase_info.set_message(message);
					}
					
					if(take_screenshot_selected == true) {
						Screenshotter ss = new Screenshotter(index ,phase_info_vec, file_name, interaction_type, element, driver_loc, driver_type, url, phase_info);
						Thread t = new Thread(ss);
						t.start();
					}
					
					dialog.dispose();
				}
				
				
			}
		});
		
		JLabel lblWebpageUrl = new JLabel("WebPage URL:");
		
		
		JButton btn_addElement = new JButton("Add Element");
		btn_addElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file_path = j.getSelectedFile().getAbsolutePath();
				txtField_addElement.setText(file_path);
				phase_info.set_element_path(file_path);
				System.out.println(file_path);
			}
		});
		
		txtField_addElement = new JTextField();
		txtField_addElement.setEditable(false);
		txtField_addElement.setColumns(10);
		
		
		
		JButton btn_takeScreenShot = new JButton("Take Screenshot");
		btn_takeScreenShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				String element = phase_info.get_element();
//				Screenshotter ss = new Screenshotter(index, phase_info_vec, file, url, element);
//				Thread t = new Thread(ss);
//				t.start();
				file_name = j.getSelectedFile().getAbsolutePath();
				textField_takeScreenshot.setText(file_name);
				take_screenshot_selected = true;
			}
		});
		
		textField_takeScreenshot = new JTextField();
		textField_takeScreenshot.setEditable(false);
		textField_takeScreenshot.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		
		JLabel lbl_interaction = new JLabel("Interaction:");
		
		
		
		txtField_type = new JTextField();
		txtField_type.setColumns(10);
		
		txtField_phaseName.setText(phase_name);
		txtField_addElement.setText(element);
		textField_takeScreenshot.setText(screenshot);
		txtField_type.setText(message);
		
		
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhaseName)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btn_addElement)
												.addComponent(lbl_interaction))
											.addGap(20))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(lbl_delay)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addGap(22)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtField_addElement, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
												.addComponent(txtField_phaseName, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(24)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btn_done)
												.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(rdbtn_lClick)
												.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
												.addComponent(rdbtn_wait)
												.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(148)))
							.addGap(38))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(103)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btn_upload)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbl_or)
									.addGap(53)
									.addComponent(btn_takeScreenShot))
								.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhaseName)
						.addComponent(txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_addElement)
						.addComponent(txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_upload)
						.addComponent(btn_takeScreenShot)
						.addComponent(lbl_or))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_delay))
						.addComponent(rdbtn_rClick))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtn_wait)
					.addPreferredGap(ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
					.addComponent(rdbtn_type)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_done))
		);
		contentPanel.setLayout(groupLayout);
		dialog.setVisible(true);
		
//		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url, JFrame frame, String driver_loc, String driver_type) {
		take_screenshot_selected = false;
		cancel_check = false;
		JDialog dialog = new JDialog(frame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		JButton btn_upload = new JButton("Upload");
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				file_name = j.getSelectedFile().getAbsolutePath();
				textField_takeScreenshot.setText(file_name);
				
			}
		});
		
		JLabel lbl_or = new JLabel("Or");
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		JLabel lbl_delay = new JLabel("Delay:");
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_lClick = new JRadioButton("Left Click");
		
		JRadioButton rdbtn_rClick = new JRadioButton("Right Click");
		
		JRadioButton rdbtn_wait = new JRadioButton("Wait");
		
		JRadioButton rdbtn_type = new JRadioButton("Type:");
		
		
		
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		btnGroup.add(rdbtn_wait);
		
		dialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String opt_buttons[] = {"Yes", "No"};
		        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit? This phase will not be saved.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
		        if(result == JOptionPane.YES_OPTION) {
		        	dialog.dispose();
		        	set_cancel_check(true);
		        }
		    }
		});
		
		JButton btn_done = new JButton("Done");
		btn_done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("poop this:" + index);
				String text = txtField_phaseName.getText();
				String element = txtField_addElement.getText();
				String expected = textField_takeScreenshot.getText() + ".png";
				int wait_time = (int) spinner.getValue();
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
				phase_info.set_wait_time(wait_time);
				
				String interaction_type = "";
				
				boolean has_duplicate = false;
				
				
				// Checks if a duplicate phase name is being added.
				for(PhaseInfo phase : phase_info_vec) {
					if(phase.get_phase_name().equals(text)) {
						has_duplicate = true;
					}
				}
				
				
				if(txtField_phaseName.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please enter a unique phase name.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(has_duplicate) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "The desired phase name already exists. Please choose another name.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(!rdbtn_lClick.isSelected() && !rdbtn_rClick.isSelected() && !rdbtn_wait.isSelected() && !rdbtn_type.isSelected()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please select an interaction type.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(textField_takeScreenshot.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please upload or take a screenshot.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(rdbtn_type.isSelected() && txtField_type.getText().isEmpty()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please enter a message to be typed.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else if(txtField_addElement.getText().isEmpty() && !rdbtn_wait.isSelected()) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Please upload an element unlessing choosing the wait option.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				}
				else {
					if (rdbtn_lClick.isSelected()) {
						interaction_type = "Lclick";
						phase_info.set_interaction_type("Lclick");
					}
					else if (rdbtn_rClick.isSelected()) {
						phase_info.set_interaction_type("Rclick");
						interaction_type = "Rclick";
					}
					else if (rdbtn_wait.isSelected()) {
						phase_info.set_interaction_type("Wait");
						interaction_type = "Wait";
					}
					else {
						phase_info.set_interaction_type("Type");
						String message = txtField_type.getText();
						phase_info.set_message(message);
						interaction_type = "Type";
					}
					

					if(take_screenshot_selected == true) {
						System.out.println("screenshot for index:" + index);
						Screenshotter ss = new Screenshotter(index ,phase_info_vec, file_name, interaction_type, element, driver_loc, driver_type, url, phase_info);
						Thread t = new Thread(ss);
						t.start();
					}
					
					
					dialog.dispose();
				}
				
				
			}
		});
		
		JButton btn_addElement = new JButton("Add Element");
		btn_addElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file_path = j.getSelectedFile().getAbsolutePath();
				txtField_addElement.setText(file_path);
				phase_info.set_element_path(file_path);
				System.out.println(file_path);
			}
		});
		
		txtField_addElement = new JTextField();
		txtField_addElement.setEditable(false);
		txtField_addElement.setColumns(10);
		
		JButton btn_takeScreenShot = new JButton("Take Screenshot");
		btn_takeScreenShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				file_name = j.getSelectedFile().getAbsolutePath();
				textField_takeScreenshot.setText(file_name);
				take_screenshot_selected = true;
			}
		});
		
		textField_takeScreenshot = new JTextField();
		textField_takeScreenshot.setEditable(false);
		textField_takeScreenshot.setColumns(10);
		
		JLabel lbl_interaction = new JLabel("Interaction:");
		
		
		
		txtField_type = new JTextField();
		txtField_type.setColumns(10);
		
		
		
		
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhaseName)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btn_addElement)
												.addComponent(lbl_interaction))
											.addGap(20))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lbl_delay)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addGap(22)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtField_addElement, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
												.addComponent(txtField_phaseName, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(24)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtn_lClick)
												.addComponent(rdbtn_wait)
												.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
												.addComponent(btn_done))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(148)))
							.addGap(38))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(103)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btn_upload)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbl_or)
									.addGap(53)
									.addComponent(btn_takeScreenShot))
								.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhaseName)
						.addComponent(txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_addElement)
						.addComponent(txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_upload)
						.addComponent(btn_takeScreenShot)
						.addComponent(lbl_or))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_delay))
						.addComponent(rdbtn_rClick))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtn_wait)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtn_type)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_done)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		contentPanel.setLayout(groupLayout);
		dialog.setVisible(true);
		

	}
	
	
	public String format_path(String path) {
		if(path.endsWith(".png")) {
			return path;
		} 
		else {
			String new_path = path + ".png";
			return new_path;
		}
	}
	
	private void set_cancel_check(boolean checker) {
		this.cancel_check = checker;
	}
	
	public boolean get_cancel_check() {
		return this.cancel_check;
	}
}
