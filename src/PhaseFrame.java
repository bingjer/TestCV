import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;

public class PhaseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField url_textbox;
	private JTextField element_path_textbox;
	private JTextField msg_textbox;
	private JTextField screenshot_path_textbox;
	private JTextField phaseName_txtField;
	private JFrame frmEnterPhase;
	private boolean was_successful;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PhaseFrame(PhaseInfo phase_info, boolean successfullyAdded) {
		frmEnterPhase = new JFrame();
		frmEnterPhase.setTitle("Phase");
		frmEnterPhase.setVisible(true);
		frmEnterPhase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEnterPhase.setBounds(100, 100, 531, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmEnterPhase.setContentPane(contentPane);
		
		JButton screenshot_btn = new JButton("Take Screenshot");
		screenshot_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JFileChooser j = new JFileChooser();
//				j.showSaveDialog(null);
//				String ss_path = j.getSelectedFile().getAbsolutePath();
//				screenshot_path_textbox.setText(ss_path);
//				phase_info.set_expected_path(ss_path);
//				Screenshotter ss = new Screenshotter();
//				ss.take_screenshot();
//				
//				File file = new File(ss_path);
//				try {
//					file.createNewFile();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				System.out.println(ss_path);
				screenshot_path_textbox.setText("test");
				
			}
		});
		
		url_textbox = new JTextField();
		url_textbox.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Webpage URL:");
		
		element_path_textbox = new JTextField();
		element_path_textbox.setEditable(false);
		element_path_textbox.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Interaction:");
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton lclick_rdbtn = new JRadioButton("Left Click");
		
		JRadioButton type_rdbtn = new JRadioButton("Type");
		
		JRadioButton rclick_rdbtn = new JRadioButton("Right Click");
		
		btnGroup.add(lclick_rdbtn);
		btnGroup.add(type_rdbtn);
		btnGroup.add(rclick_rdbtn);
		
		msg_textbox = new JTextField();
		msg_textbox.setText("Enter message here...");
		msg_textbox.setEditable(false);
		msg_textbox.setColumns(10);
		
		JButton element_btn = new JButton("Add Element");
		element_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				String file_path = j.getSelectedFile().getAbsolutePath();
				element_path_textbox.setText(file_path);
				phase_info.set_element_path(file_path);
				System.out.println(file_path);
			}
		});
		
		screenshot_path_textbox = new JTextField();
		screenshot_path_textbox.setEditable(false);
		screenshot_path_textbox.setColumns(10);
		
		JButton add_phase_btn = new JButton("Add Phase");
		add_phase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = url_textbox.getText();
				String phase_name = phaseName_txtField.getText();
				phase_info.set_phase_name(phase_name);
				//System.out.println(phase_info.get_phase_name());
				
				if (lclick_rdbtn.isSelected()) {
					phase_info.set_interaction_type("Lclick");
				}
				else if (type_rdbtn.isSelected()) {
					phase_info.set_interaction_type("Rclick");
				}
				else if (rclick_rdbtn.isSelected()) {
					phase_info.set_interaction_type("Type");
				}
				else {
					//TODO: Add notification window
				}
				set_bool(true);
				frmEnterPhase.dispose();
			}
		});
		
		JProgressBar progressBar = new JProgressBar();
		
		JLabel phaseName_lbl = new JLabel("Phase Name:");
		
		phaseName_txtField = new JTextField();
		phaseName_txtField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(211)
					.addComponent(add_phase_btn)
					.addContainerGap(235, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(phaseName_lbl)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addContainerGap())
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(element_btn)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(screenshot_btn)
										.addComponent(lblNewLabel_2))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lclick_rdbtn)
											.addGap(299))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
												.addComponent(msg_textbox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
												.addComponent(screenshot_path_textbox, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
												.addComponent(element_path_textbox, Alignment.LEADING, 242, 242, Short.MAX_VALUE)
												.addComponent(url_textbox, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
												.addComponent(phaseName_txtField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
											.addGap(126))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(rclick_rdbtn)
											.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(type_rdbtn)
											.addContainerGap())))))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phaseName_lbl)
						.addComponent(phaseName_txtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(url_textbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(element_btn)
						.addComponent(element_path_textbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(screenshot_btn)
						.addComponent(screenshot_path_textbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lclick_rdbtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rclick_rdbtn)
					.addGap(3)
					.addComponent(type_rdbtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(msg_textbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(add_phase_btn)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void set_bool(boolean isTrue) {
		was_successful = isTrue;
	}
	
	public boolean get_bool() {
		return was_successful;
	}
	
	public boolean is_frame_visible() {
		return frmEnterPhase.isVisible();
	}
}
