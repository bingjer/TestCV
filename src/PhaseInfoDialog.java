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
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

public class PhaseInfoDialog extends JDialog {

	private final JPanel contentPanel;
	private JTextField txtField_phaseName;
	private JTextField txtField_webpageURL;
	private JTextField txtField_addElement;
	private JTextField textField_takeScreenshot;
	private JTextField txtField_type;

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
	public PhaseInfoDialog(PhaseInfo phase_info, String phase_name, String url, String element, String screenshot, String interaction, String message, JFrame frame) {
		
		JDialog dialog = new JDialog(frame);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(100, 100, 531, 415);
		contentPanel = new JPanel();
//		dialog.getContentPane().setLayout(new BorderLayout());
//		dialog.setContentPane(contentPanel);
//		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_lClick = new JRadioButton("Left Click");
		
		JRadioButton rdbtn_rClick = new JRadioButton("Right Click");
		
		JRadioButton rdbtn_type = new JRadioButton("Type:");
		
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtField_phaseName.getText();
				String url = txtField_webpageURL.getText();
				String element = txtField_addElement.getText();
				String expected = textField_takeScreenshot.getText();
				phase_info.set_url(url);
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
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
				dialog.dispose();
			}
		});
		
		JLabel lblWebpageUrl = new JLabel("WebPage URL:");
		
		txtField_webpageURL = new JTextField();
		txtField_webpageURL.setColumns(10);
		
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
				textField_takeScreenshot.setText("test");
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
		txtField_webpageURL.setText(url);
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
								.addComponent(lblWebpageUrl)
								.addComponent(btn_addElement)
								.addComponent(btn_takeScreenShot)
								.addComponent(lbl_interaction))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtField_phaseName, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(txtField_webpageURL, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(txtField_addElement, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(textField_takeScreenshot, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtn_lClick)
										.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(208)
							.addComponent(btnNewButton)))
					.addGap(143))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhaseName))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWebpageUrl)
						.addComponent(txtField_webpageURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_addElement)
						.addComponent(txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_takeScreenShot)
						.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtn_rClick)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtn_type)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
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
	public PhaseInfoDialog(PhaseInfo phase_info, JFrame frame) {
		
		JDialog dialog = new JDialog(frame);
		dialog.setModal(true);
		dialog.setTitle("Phase Frame");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setBounds(100, 100, 531, 415);
		contentPanel = new JPanel();
//		dialog.getContentPane().setLayout(new BorderLayout());
//		dialog.setContentPane(contentPanel);
//		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPanel);
		
		JLabel lblPhaseName = new JLabel("Phase Name:");
		
		txtField_phaseName = new JTextField();
		txtField_phaseName.setColumns(10);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_lClick = new JRadioButton("Left Click");
		
		JRadioButton rdbtn_rClick = new JRadioButton("Right Click");
		
		JRadioButton rdbtn_type = new JRadioButton("Type:");
		
		btnGroup.add(rdbtn_lClick);
		btnGroup.add(rdbtn_rClick);
		btnGroup.add(rdbtn_type);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtField_phaseName.getText();
				String url = txtField_webpageURL.getText();
				String element = txtField_addElement.getText();
				String expected = textField_takeScreenshot.getText();
				phase_info.set_url(url);
				phase_info.set_phase_name(text);
				phase_info.set_element_path(element);
				phase_info.set_expected_path(expected);
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
				dialog.dispose();
			}
		});
		
		JLabel lblWebpageUrl = new JLabel("WebPage URL:");
		
		txtField_webpageURL = new JTextField();
		txtField_webpageURL.setColumns(10);
		
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
				textField_takeScreenshot.setText("test");
			}
		});
		
		textField_takeScreenshot = new JTextField();
		textField_takeScreenshot.setEditable(false);
		textField_takeScreenshot.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		
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
								.addComponent(lblWebpageUrl)
								.addComponent(btn_addElement)
								.addComponent(btn_takeScreenShot)
								.addComponent(lbl_interaction))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtField_phaseName, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(txtField_webpageURL, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(txtField_addElement, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(textField_takeScreenshot, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtn_rClick, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtn_lClick)
										.addComponent(rdbtn_type, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(208)
							.addComponent(btnNewButton)))
					.addGap(143))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtField_phaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhaseName))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWebpageUrl)
						.addComponent(txtField_webpageURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_addElement)
						.addComponent(txtField_addElement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_takeScreenShot)
						.addComponent(textField_takeScreenshot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_interaction)
						.addComponent(rdbtn_lClick))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtn_rClick)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtn_type)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtField_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
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
