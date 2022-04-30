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
	private JSpinner spinner;
	private JRadioButton rdbtn_lClick;

	private JRadioButton rdbtn_rClick;

	private JRadioButton rdbtn_wait;

	private JRadioButton rdbtn_type;
	private JDialog dialog;

	// Used for viewing phase
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url,
			String phase_name, String element, String screenshot, String interaction, String message, int delay,
			JFrame frame, String driver_loc, String driver_type) {
		
		// Variable to track if the "Take Screenshot" button has been pressed
		take_screenshot_selected = false;
	
		// Variable to track whether a phase was canceled to account for adding the phase to the list or not.
		cancel_check = false;
		
		// JDialog construction.
		dialog = new JDialog(frame);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		// JLabel Definitions.
		JLabel lblPhaseName = new JLabel("Phase Name:");
		JLabel lbl_delay = new JLabel("Delay:");
		JLabel lbl_interaction = new JLabel("Interaction:");
		JLabel lbl_or = new JLabel("Or");
		JLabel lblWebpageUrl = new JLabel("WebPage URL:");


		
		// TextField Definitions
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		txtField_addElement = new JTextField();
		txtField_addElement.setEditable(false);
		txtField_addElement.setColumns(10);
		textField_takeScreenshot = new JTextField();
		textField_takeScreenshot.setEditable(false);
		textField_takeScreenshot.setColumns(10);
		txtField_type = new JTextField();
		txtField_type.setColumns(10);
		txtField_phaseName.setText(phase_name);
		txtField_addElement.setText(element);
		textField_takeScreenshot.setText(screenshot);
		txtField_type.setText(message);
		
		// Radio button and grouping to ensure only 1 is clicked at a time.
		ButtonGroup btnGroup = new ButtonGroup();
		rdbtn_lClick = new JRadioButton("Left Click");
		rdbtn_rClick = new JRadioButton("Right Click");
		rdbtn_wait = new JRadioButton("Wait");
		rdbtn_type = new JRadioButton("Type:");
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		btnGroup.add(rdbtn_wait);
		// Sets the radio button to be selected if viewing a phase.
		switch (interaction) {
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
		
		// Button definitions.
		JButton btn_upload = new JButton("Upload");
		JButton btn_done = new JButton("Ok");
		JButton btn_addElement = new JButton("Add Element");
		JButton btn_takeScreenShot = new JButton("Take Screenshot");
		
		// Spinner construction
		spinner = new JSpinner(new SpinnerNumberModel(delay, 0, 100, 1));


		// Button listeners.
		upload_button_listener(btn_upload);
		close_window_listener(dialog);
		btn_done_listener(btn_done, index, phase_info, phase_info_vec,  driver_loc,  driver_type,  url, "ok");
		btn_element_listener(btn_addElement, phase_info);
		btn_screenshot_listener(btn_takeScreenShot);

		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(47)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblPhaseName)
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btn_addElement).addComponent(lbl_interaction))
												.addGap(20))
										.addGroup(groupLayout.createSequentialGroup().addComponent(lbl_delay)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addGap(22)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(txtField_addElement,
																		GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
																.addComponent(txtField_phaseName,
																		GroupLayout.DEFAULT_SIZE, 202,
																		Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup().addGap(24)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(rdbtn_lClick).addComponent(rdbtn_wait)
																.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE,
																		89, GroupLayout.PREFERRED_SIZE)
																.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE,
																		89, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE,
																		178, GroupLayout.PREFERRED_SIZE)
																.addComponent(btn_done))
														.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(148)))
						.addGap(38))
						.addGroup(groupLayout.createSequentialGroup().addGap(103)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup().addComponent(btn_upload)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lbl_or).addGap(53).addComponent(btn_takeScreenShot))
										.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, 301,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(69)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPhaseName).addComponent(
						txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btn_addElement).addComponent(
						txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(22)
				.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btn_upload)
						.addComponent(btn_takeScreenShot).addComponent(lbl_or))
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_delay))
						.addComponent(rdbtn_rClick))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtn_wait)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtn_type)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btn_done).addContainerGap(30, Short.MAX_VALUE)));
		contentPanel.setLayout(groupLayout);
		dialog.setVisible(true);
	}

	// This is the constructor for the dialog window if adding a new test. 
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url, JFrame frame,
			String driver_loc, String driver_type) {
		
		// Variable to track if the "Take Screenshot" button has been pressed
		take_screenshot_selected = false;
		
		// Variable to track whether a phase was canceled to account for adding the phase to the list or not.
		cancel_check = false;
		
		// JDialog set up.
		dialog = new JDialog(frame);		
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);

		// Label definitions.
		JLabel lblPhaseName = new JLabel("Phase Name:");
		JLabel lbl_delay = new JLabel("Delay:");
		JLabel lbl_interaction = new JLabel("Interaction:");
		JLabel lbl_or = new JLabel("Or");
		JLabel lblWebpageUrl = new JLabel("WebPage URL:");


		
		// Text field deifnitions.
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		txtField_addElement = new JTextField();
		txtField_addElement.setEditable(false);
		txtField_addElement.setColumns(10);
		textField_takeScreenshot = new JTextField();
		textField_takeScreenshot.setEditable(false);
		textField_takeScreenshot.setColumns(10);
		txtField_type = new JTextField();
		txtField_type.setColumns(10);
		
		// Radio button definitions. This also constructs a grouping so only one box can be selected at a time.
		ButtonGroup btnGroup = new ButtonGroup();
		rdbtn_lClick = new JRadioButton("Left Click");
		rdbtn_rClick = new JRadioButton("Right Click");
		rdbtn_wait = new JRadioButton("Wait");
		rdbtn_type = new JRadioButton("Type:");
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		btnGroup.add(rdbtn_wait);

		
		// Button definitions.
		JButton btn_upload = new JButton("Upload");
		JButton btn_done = new JButton("Done");
		JButton btn_addElement = new JButton("Add Element");
		JButton btn_takeScreenShot = new JButton("Take Screenshot");
		
		// Spinner definition
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		
		// Button listeners.
		upload_button_listener(btn_upload);
		close_window_listener(dialog);
		btn_done_listener(btn_done, index, phase_info, phase_info_vec,  driver_loc,  driver_type,  url, "done");
		btn_element_listener(btn_addElement, phase_info);
		btn_screenshot_listener(btn_takeScreenShot);
		
		
		// GroupLayou constructions
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(47)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblPhaseName)
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btn_addElement).addComponent(lbl_interaction))
												.addGap(20))
										.addGroup(groupLayout.createSequentialGroup().addComponent(lbl_delay)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addGap(22)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(txtField_addElement,
																		GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
																.addComponent(txtField_phaseName,
																		GroupLayout.DEFAULT_SIZE, 202,
																		Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup().addGap(24)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(rdbtn_lClick).addComponent(rdbtn_wait)
																.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE,
																		89, GroupLayout.PREFERRED_SIZE)
																.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE,
																		89, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE,
																		178, GroupLayout.PREFERRED_SIZE)
																.addComponent(btn_done))
														.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(148)))
						.addGap(38))
						.addGroup(groupLayout.createSequentialGroup().addGap(103)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup().addComponent(btn_upload)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lbl_or).addGap(53).addComponent(btn_takeScreenShot))
										.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, 301,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(69)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPhaseName).addComponent(
						txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btn_addElement).addComponent(
						txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(22)
				.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btn_upload)
						.addComponent(btn_takeScreenShot).addComponent(lbl_or))
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_delay))
						.addComponent(rdbtn_rClick))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtn_wait)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtn_type)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(btn_done).addContainerGap(30, Short.MAX_VALUE)));
		contentPanel.setLayout(groupLayout);
		dialog.setVisible(true);

	}

	public String format_path(String path) {
		if (path.endsWith(".png")) {
			return path;
		} else {
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

	private void btn_element_listener(JButton btn, PhaseInfo phase_info) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file_path = j.getSelectedFile().getAbsolutePath();
				txtField_addElement.setText(file_path);
				phase_info.set_element_path(file_path);
				System.out.println(file_path);
			}
		});
	}

	private void btn_screenshot_listener(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				file_name = j.getSelectedFile().getAbsolutePath();
				textField_takeScreenshot.setText(file_name);
				take_screenshot_selected = true;
			}
		});
	}


	private void btn_done_listener(JButton btn, int index, PhaseInfo phase_info, Vector<PhaseInfo> phase_info_vec, String driver_loc, String driver_type, String url, String done_or_ok) {
		btn.addActionListener(new ActionListener() {
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
				
				if (done_or_ok.equals("done")) {
					for (PhaseInfo phase : phase_info_vec) {
						if (phase.get_phase_name().equals(text)) {
							has_duplicate = true;
						}
					}
				}
				
				else {
					for(int i = 0; i < phase_info_vec.size(); i++) {
						if(i == index) {
							System.out.println("this is2: " + index);
							continue;
						}
						if(phase_info_vec.get(i).get_phase_name().equals(text)) {
							has_duplicate = true;
						}
					}
				}
				// Checks if a duplicate phase name is being added.
				

				if (txtField_phaseName.getText().isEmpty()) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null, "Please enter a unique phase name.", "TestCV",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				} else if (has_duplicate) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null,
							"The desired phase name already exists. Please choose another name.", "TestCV",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				} else if (!rdbtn_lClick.isSelected() && !rdbtn_rClick.isSelected() && !rdbtn_wait.isSelected()
						&& !rdbtn_type.isSelected()) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null, "Please select an interaction type.", "TestCV",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				} else if (textField_takeScreenshot.getText().isEmpty()) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null, "Please upload or take a screenshot.", "TestCV",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				} else if (rdbtn_type.isSelected() && txtField_type.getText().isEmpty()) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null, "Please enter a message to be typed.", "TestCV",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				} else if (txtField_addElement.getText().isEmpty() && !rdbtn_wait.isSelected()) {
					String opt_buttons[] = { "Ok" };
					JOptionPane.showOptionDialog(null, "Please upload an element unlessing choosing the wait option.",
							"TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons,
							opt_buttons[0]);
				} else {
					if (rdbtn_lClick.isSelected()) {
						interaction_type = "Lclick";
						phase_info.set_interaction_type("Lclick");
					} else if (rdbtn_rClick.isSelected()) {
						phase_info.set_interaction_type("Rclick");
						interaction_type = "Rclick";
					} else if (rdbtn_wait.isSelected()) {
						phase_info.set_interaction_type("Wait");
						interaction_type = "Wait";
					} else {
						phase_info.set_interaction_type("Type");
						String message = txtField_type.getText();
						phase_info.set_message(message);
						interaction_type = "Type";
					}

					if (take_screenshot_selected == true) {
						System.out.println("screenshot for index:" + index);
						Screenshotter ss = new Screenshotter(index, phase_info_vec, file_name, interaction_type,
								element, driver_loc, driver_type, url, phase_info);
						Thread t = new Thread(ss);
						t.start();
					}

					dialog.dispose();
				}

			}
		});
	}
	
	private void upload_button_listener(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				file_name = j.getSelectedFile().getAbsolutePath();
				textField_takeScreenshot.setText(file_name);

			}
		});
	}
	
	private void close_window_listener(JDialog dialog) {
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String opt_buttons[] = { "Yes", "No" };
				int result = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit? This phase will not be saved.", "TestCV",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
				if (result == JOptionPane.YES_OPTION) {
					dialog.dispose();
					set_cancel_check(true);
				}
			}
		});
	}
	
	
}
