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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
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

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			PhaseInfoDialog dialog = new PhaseInfoDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * 
	 */
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url, String phase_name, String element, String screenshot, String interaction, String message, JFrame frame) {
		
		JDialog dialog = new JDialog(frame);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();
//		dialog.getContentPane().setLayout(new BorderLayout());
//		dialog.setContentPane(contentPanel);
//		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		
		JButton btn_upload = new JButton("Upload");
		
		JLabel lbl_or = new JLabel("Or");
		
		JSpinner spinner = new JSpinner();
		
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
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtField_phaseName.getText();
				String element = txtField_addElement.getText();
				String expected = textField_takeScreenshot.getText() + ".png";
				int wait_time = (int) spinner.getValue();
				phase_info.set_wait_time(wait_time);
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
				System.out.println(phase_info.get_screenshot());
				
				if (rdbtn_lClick.isSelected()) {
					phase_info.set_interaction_type("Lclick");
				}
				else if (rdbtn_rClick.isSelected()) {
					phase_info.set_interaction_type("Rclick");
				}
				else if (rdbtn_type.isSelected()) {
					phase_info.set_interaction_type("Type");
					String message = txtField_type.getText();
					phase_info.set_message(message);
				}
				else {
					//TODO: Add notification window
				}
				dialog.dispose();
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
												.addComponent(btnNewButton)
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
					.addComponent(btnNewButton))
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
	public PhaseInfoDialog(int index, Vector<PhaseInfo> phase_info_vec, PhaseInfo phase_info, String url, JFrame frame) {
		
		JDialog dialog = new JDialog(frame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(100, 100, 562, 464);
		contentPanel = new JPanel();
//		dialog.getContentPane().setLayout(new BorderLayout());
//		dialog.setContentPane(contentPanel);
//		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		JButton btn_upload = new JButton("Upload");
		
		JLabel lbl_or = new JLabel("Or");
		
		JSpinner spinner = new JSpinner();
		
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
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtField_phaseName.getText();
				String element = txtField_addElement.getText();
				String expected = textField_takeScreenshot.getText() + ".png";
				int wait_time = (int) spinner.getValue();
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
				phase_info.set_wait_time(wait_time);
				//System.out.println(phase_info.get_phase_name());
				
				if (rdbtn_lClick.isSelected()) {
					phase_info.set_interaction_type("Lclick");
				}
				else if (rdbtn_rClick.isSelected()) {
					phase_info.set_interaction_type("Rclick");
				}
				else if (rdbtn_type.isSelected()) {
					phase_info.set_interaction_type("Type");
					String message = txtField_type.getText();
					phase_info.set_message(message);
				}
				else {
					//TODO: Add notification window
				}
				
				String interaction_type = "";
				if (rdbtn_lClick.isSelected()) {
					interaction_type = "Lclick";
				}
				else if (rdbtn_rClick.isSelected()) {
					interaction_type = "Rclick";
				}
				else if (rdbtn_type.isSelected()) {
					interaction_type = "Type";
				} 
				else {
					//TODO: Add notification window
				}

				
				Screenshotter ss = new Screenshotter(index ,phase_info_vec, file_name, interaction_type, element);
				
				Thread t = new Thread(ss);
				t.start();
				//phase_info_vec.add(phase_info);
				dialog.dispose();
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
												.addComponent(btnNewButton))
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
					.addComponent(btnNewButton)
					.addContainerGap(30, Short.MAX_VALUE))
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
	
	public String get_text() {
		return txtField_phaseName.getText();
	}
}
