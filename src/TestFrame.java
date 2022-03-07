
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.List;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextField;

public class TestFrame extends JFrame {

	protected static final String JLabel = null;
	private JPanel contentPane;
	private int counter;
	private JFrame frame = new JFrame(); 
	private Vector<PhaseInfo> phase_info_vec;
	private JTextField txtField_url;

	
	/**
	 * Create the frame.
	 */
	public TestFrame() {
		phase_info_vec = new Vector<PhaseInfo>();
		counter = 0;
		setTitle("Add Test");
		JFrame frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmTestRunner.setContentPane(contentPane);
		
		//Vector<String> phase_list = new Vector<String>();
		DefaultListModel phase_list = new DefaultListModel();
		
		JList list = new JList(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		
		
		JButton add_phase_btn = new JButton("Add Phase");
		add_phase_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PhaseInfo phase_info = new PhaseInfo();
				String url = txtField_url.getText();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
				System.out.println(phase_info.get_phase_name());
//				boolean wasSuccessful = true;
//				PhaseFrame phaseFrame = new PhaseFrame(phase_info, wasSuccessful);
//				System.out.println(phase_info.get_phase_name());
//					System.out.println(phase_info.get_phase_name());
				int index = list.getSelectedIndex();
				counter = index;
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
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction,message, frmTestRunner);
				phase_info_vec.set(index, selected_phase);
				phase_list.set(index, selected_phase.get_phase_name());
				list.setModel(phase_list);
			}
		});
		
		JLabel lbl_url = new JLabel("URL:");
		
		txtField_url = new JTextField();
		txtField_url.setColumns(10);

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(view_phase_btn)
						.addComponent(run_btn)
						.addComponent(save_test_btn)
						.addComponent(delete_phase_btn)
						.addComponent(add_phase_btn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_url)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(add_phase_btn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(delete_phase_btn)
							.addGap(8)
							.addComponent(view_phase_btn)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_url)
								.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED.RELATED)
							.addComponent(view_phase_btn)
							.addGap(30)
							.addComponent(save_test_btn)
							.addGap(9)
							.addComponent(run_btn))
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	public TestFrame(Vector<PhaseInfo> phase_info_vec) {
		setTitle("Add Test");
		JFrame frmTestRunner = new JFrame();
		frmTestRunner.setTitle("Test Runner");
		frmTestRunner.setVisible(true);
		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTestRunner.setBounds(100, 100, 450, 300);
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
		
		
		
		
		
		
		JButton add_phase_btn = new JButton("Add Phase");
		add_phase_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PhaseInfo phase_info = new PhaseInfo();
				String url = txtField_url.getText();
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
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
				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction,message, frmTestRunner);
				phase_info_vec.set(index, selected_phase);
				phase_list.set(index, selected_phase.get_phase_name());
				list.setModel(phase_list);
			}
		});

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(view_phase_btn)
						.addComponent(delete_phase_btn)
						.addComponent(add_phase_btn)
						.addComponent(run_btn)
						.addComponent(save_test_btn))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(add_phase_btn)
							.addGap(9)
							.addComponent(delete_phase_btn)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(view_phase_btn)
							.addGap(30)
							.addComponent(save_test_btn)
							.addGap(9)
							.addComponent(run_btn))
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
