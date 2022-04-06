
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import java.awt.BorderLayout;
import java.awt.List;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class TestFrame extends JFrame {

	//protected static final String JLabel = null;
	private JPanel contentPane;
	private int counter;
	private String driver_loc;
	private JFrame frame = new JFrame(); 
	private Vector<PhaseInfo> phase_info_vec;
	//private JTextField txtField_url;
	private JTextField txtField_url;
	private JTextField txtField_url_1;
	private JTextField txtField_driver;

	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
		phase_info_vec = new Vector<PhaseInfo>();
		counter = 0;
		JFrame frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmTestRunner.setContentPane(contentPane);
		
		//Vector<String> phase_list = new Vector<String>();
		DefaultListModel phase_list = new DefaultListModel();
		
		JList list = new JList(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		txtField_url = new JTextField();
		txtField_url.setColumns(10);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_chrome = new JRadioButton("Chrome");
		
		JRadioButton rdbtn_firefox = new JRadioButton("FireFox");
		
		btnGroup.add(rdbtn_chrome);
		btnGroup.add(rdbtn_firefox);
		
		JLabel lbl_runs = new JLabel("Runs:");
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		JButton add_phase_btn = new JButton("Add Phase");
		add_phase_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PhaseInfo phase_info = new PhaseInfo();
				String url = "test"; //txtField_url.getText();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
				System.out.println(phase_info.get_phase_name());
//				boolean wasSuccessful = true;
//				PhaseFrame phaseFrame = new PhaseFrame(phase_info, wasSuccessful);
//				System.out.println(phase_info.get_phase_name());
//					System.out.println(phase_info.get_phase_name());
				int index = list.getSelectedIndex();
				
				if (index != -1) {
					counter = index;
					phase_info_vec.add(index + 1, phase_info);
					++counter;
					//phase_list.addElement(phase_info.get_phase_name());
					phase_list.add(index + 1, phase_info.get_phase_name());
					list.setModel(phase_list);
				} else {
					counter = phase_info_vec.size();
					++counter;
					System.out.println("shouldn't be here");
					phase_info_vec.add(phase_info);
					phase_list.addElement(phase_info.get_phase_name());
					list.setModel(phase_list);
				}
				
//		
	
			}
		});

		JButton delete_phase_btn = new JButton("Delete Phase");
		delete_phase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				System.out.println(index);
				if (index != -1) {
					phase_list.remove(index);
					phase_info_vec.remove(index);
				}
			}
		});
		
		
		
		JButton save_test_btn = new JButton("Save Test");
		save_test_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(PhaseInfo phase : phase_info_vec) {
					System.out.println(phase.get_phase_name());
				}
				TestInfo test = new TestInfo();
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				System.out.println(file);
				test.write_json(file, phase_info_vec);
			}
		});
		
		JButton run_btn = new JButton("Run");
		run_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driver_type = "";
				if(rdbtn_chrome.isSelected()) {
					driver_type = "chrome";
				}
				else if(rdbtn_firefox.isSelected()) {
					driver_type = "firefox";
				}
				else {
					//TODO: create notificaiton window that it needs to be selected
				}
				MainFrame frame = new MainFrame((int) spinner.getValue(), txtField_url.getText(), txtField_driver.getText(), driver_type, phase_info_vec);
				frmTestRunner.dispose();
				//logs.set_logs("made it testframe 132");
			}
		});
		
		JButton view_phase_btn = new JButton("View Phase");
		view_phase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				PhaseInfo selected_phase = phase_info_vec.get(index);
				String phase_name = selected_phase.get_phase_name();
				String element = selected_phase.get_element();
				String screenshot = selected_phase.get_screenshot();
				String interaction = selected_phase.get_interaction_type();
				String message = selected_phase.get_message();
				String url = txtField_url.getText();
				int delay = selected_phase.get_wait_time();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction, message, delay, frmTestRunner);
				phase_info_vec.set(index, selected_phase);
				phase_list.set(index, selected_phase.get_phase_name());
				list.setModel(phase_list);
			}
		});
		
		JLabel lbl_url = new JLabel("URL:");
		
		txtField_url_1 = new JTextField();
		txtField_url_1.setColumns(10);
		
		
		
		JButton btn_driver = new JButton("Choose Driver");
		btn_driver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				driver_loc = j.getSelectedFile().getAbsolutePath();
				System.out.println(driver_loc);
				txtField_driver.setText(driver_loc);

			}
		});
		
		JLabel lbl_driver = new JLabel("Driver:");
		
		txtField_driver = new JTextField();
		txtField_driver.setEditable(false);
		txtField_driver.setColumns(10);
		
		

		
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
							.addComponent(txtField_url_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
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
								.addComponent(txtField_url_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
	
	public TestFrame(Vector<PhaseInfo> phase_info_vec) {
		setTitle("Add Test");
		JFrame frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmTestRunner.setContentPane(contentPane);
		
		//Vector<String> phase_list = new Vector<String>();
		DefaultListModel phase_list = new DefaultListModel();
		for(PhaseInfo phase : phase_info_vec) {
			phase_list.addElement(phase.get_phase_name());
		}
		JList list = new JList(phase_list);
		list.setModel(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		JLabel lbl_url = new JLabel("URL:");
		
		
		txtField_url = new JTextField();
		txtField_url.setColumns(10);
		
		JLabel lbl_runs = new JLabel("Runs:");
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JRadioButton rdbtn_chrome = new JRadioButton("Chrome");
		
		JRadioButton rdbtn_firefox = new JRadioButton("FireFox");
		
		JLabel lbl_driver = new JLabel("Driver:");
		
		txtField_driver = new JTextField();
		txtField_driver.setEditable(false);
		txtField_driver.setColumns(10);
		
		btnGroup.add(rdbtn_chrome);
		btnGroup.add(rdbtn_firefox);
		
		JButton add_phase_btn = new JButton("Add Phase");
		add_phase_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PhaseInfo phase_info = new PhaseInfo();
				String url = txtField_url.getText();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
				++counter;
				System.out.println(phase_info.get_phase_name());
//				boolean wasSuccessful = true;
//				PhaseFrame phaseFrame = new PhaseFrame(phase_info, wasSuccessful);
//				System.out.println(phase_info.get_phase_name());
//					System.out.println(phase_info.get_phase_name());
				int index = list.getSelectedIndex();
				if (index != -1) {
					phase_info_vec.add(index + 1, phase_info);
					//phase_list.addElement(phase_info.get_phase_name());
					phase_list.add(index + 1, phase_info.get_phase_name());
					list.setModel(phase_list);
				} else {
					phase_info_vec.add(phase_info);
					phase_list.addElement(phase_info.get_phase_name());
					list.setModel(phase_list);
				}
				
//		
	
			}
		});

		JButton delete_phase_btn = new JButton("Delete Phase");
		delete_phase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				System.out.println(index);
				if (index != -1) {
					phase_list.remove(index);
					phase_info_vec.remove(index);
				}
			}
		});
		
		
		
		JButton save_test_btn = new JButton("Save Test");
		save_test_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(PhaseInfo phase : phase_info_vec) {
					System.out.println(phase.get_phase_name());
				}
				TestInfo test = new TestInfo();
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				System.out.println(file);
				test.write_json(file, phase_info_vec);
			}
		});
		
		JButton run_btn = new JButton("Run");
		run_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String driver_type = "";
				if(rdbtn_chrome.isSelected()) {
					driver_type = "chrome";
				}
				else if(rdbtn_firefox.isSelected()) {
					driver_type = "firefox";
				}
				else {
					//TODO: create notificaiton window that it needs to be selected
				}
				int test = (int) spinner.getValue();
				
				MainFrame frame = new MainFrame(test, txtField_url.getText(), txtField_driver.getText(), driver_type, phase_info_vec);
				frmTestRunner.dispose();
				//logs.set_logs("made it testframe 132");
			}
		});
		
		JButton view_phase_btn = new JButton("View Phase");
		view_phase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				PhaseInfo selected_phase = phase_info_vec.get(index);
				String phase_name = selected_phase.get_phase_name();
				String element = selected_phase.get_element();
				String screenshot = selected_phase.get_screenshot();
				String interaction = selected_phase.get_interaction_type();
				String message = selected_phase.get_message();
				String url = "test";//txtField_url.getText();
				int delay = selected_phase.get_wait_time();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction, message, delay, frmTestRunner);
				phase_info_vec.set(index, selected_phase);
				phase_list.set(index, selected_phase.get_phase_name());
				list.setModel(phase_list);
			}
		});

		
		
		JButton btn_driver = new JButton("Choose Driver");
		btn_driver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showOpenDialog(null);
				driver_loc = j.getSelectedFile().getAbsolutePath();
				System.out.println(driver_loc);
				txtField_driver.setText(driver_loc);

			}
		});
		
		
		
		
		
		
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
}
