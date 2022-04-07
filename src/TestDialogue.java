//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Vector;
//
//import javax.swing.DefaultListModel;
//import javax.swing.GroupLayout;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.border.EmptyBorder;
//
//public class TestDialogue extends JDialog {
//
//	private final JPanel contentPanel = new JPanel();
//
//	/**
//	 * Launch the application.
//	 */
//	private JPanel contentPane;
//	private int counter;
//	private JDialog frmTestRunner;
//	private Vector<PhaseInfo> phase_info_vec;
//	private JTextField txtField_url;
//
//	
//	/**
//	 * Create the frame.
//	 */
//	public TestDialogue(JFrame dialog, Logger logs) {
//		frmTestRunner = new JDialog(dialog, ModalityType.APPLICATION_MODAL); 
//		frmTestRunner.setModal(true);
//		frmTestRunner.setTitle("Phase Frame");
//		phase_info_vec = new Vector<PhaseInfo>();
//		counter = 0;
//		setTitle("Add Test");
//		//JFrame frmTestRunner = new JFrame();
//		frmTestRunner.setAlwaysOnTop(true);
//		frmTestRunner.setTitle("Test Runner");
//		
//		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frmTestRunner.setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		frmTestRunner.setContentPane(contentPane);
//		
//		//Vector<String> phase_list = new Vector<String>();
//		DefaultListModel phase_list = new DefaultListModel();
//		
//		JList list = new JList(phase_list);
//		JScrollPane scrollpane = new JScrollPane(list);
//		
//		txtField_url = new JTextField();
//		txtField_url.setColumns(10);
//		
//		JButton add_phase_btn = new JButton("Add Phase");
//		add_phase_btn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				PhaseInfo phase_info = new PhaseInfo();
//				String url = "test"; //txtField_url.getText();
//				//PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
//				System.out.println(phase_info.get_phase_name());
////				boolean wasSuccessful = true;
////				PhaseFrame phaseFrame = new PhaseFrame(phase_info, wasSuccessful);
////				System.out.println(phase_info.get_phase_name());
////					System.out.println(phase_info.get_phase_name());
//				int index = list.getSelectedIndex();
//				
//				if (index != -1) {
//					counter = index;
//					phase_info_vec.add(index + 1, phase_info);
//					++counter;
//					//phase_list.addElement(phase_info.get_phase_name());
//					phase_list.add(index + 1, phase_info.get_phase_name());
//					list.setModel(phase_list);
//				} else {
//					counter = phase_info_vec.size();
//					++counter;
//					System.out.println("shouldn't be here");
//					phase_info_vec.add(phase_info);
//					phase_list.addElement(phase_info.get_phase_name());
//					list.setModel(phase_list);
//				}
//				
////		
//	
//			}
//		});
//
//		JButton delete_phase_btn = new JButton("Delete Phase");
//		delete_phase_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = list.getSelectedIndex();
//				System.out.println(index);
//				if (index != -1) {
//					phase_list.remove(index);
//					phase_info_vec.remove(index);
//				}
//			}
//		});
//		
//		
//		
//		JButton save_test_btn = new JButton("Save Test");
//		save_test_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for(PhaseInfo phase : phase_info_vec) {
//					System.out.println(phase.get_phase_name());
//				}
//				TestInfo test = new TestInfo();
//				JFileChooser j = new JFileChooser();
//				j.showSaveDialog(null);
//				String file = j.getSelectedFile().getAbsolutePath();
//				System.out.println(file);
//				test.write_json(file, phase_info_vec);
//			}
//		});
//		
//		JButton run_btn = new JButton("Run");
//		run_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				logs.set_logs("Success mcvest!");
//				frmTestRunner.dispose();
////				while(true) {
////					System.out.println("Sucesss twice.");
////				}
//				//logs.set_logs("made it testframe 132");
//				long start = System.currentTimeMillis();
//				long finish = System.currentTimeMillis();
//				long elapsed = finish - start;
//				System.out.println(finish);
//				while(elapsed < 5000) {
//					finish = System.currentTimeMillis();
//					elapsed = finish - start;
//				}
//				System.out.println("Test");
//			}
//		});
//		
//		JButton view_phase_btn = new JButton("View Phase");
//		view_phase_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = list.getSelectedIndex();
//				PhaseInfo selected_phase = phase_info_vec.get(index);
//				String phase_name = selected_phase.get_phase_name();
//				String element = selected_phase.get_element();
//				String screenshot = selected_phase.get_screenshot();
//				String interaction = selected_phase.get_interaction_type();
//				String message = selected_phase.get_message();
//				String url = txtField_url.getText();
//				//PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction,message, frmTestRunner);
//				phase_info_vec.set(index, selected_phase);
//				phase_list.set(index, selected_phase.get_phase_name());
//				list.setModel(phase_list);
//			}
//		});
//		
//		JLabel lbl_url = new JLabel("URL:");
//		
//		txtField_url = new JTextField();
//		txtField_url.setColumns(10);
//
//		
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
//					.addGap(48)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addComponent(lbl_url)
//							.addPreferredGap(ComponentPlacement.UNRELATED)
//							.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
//						.addComponent(add_phase_btn)
//						.addComponent(delete_phase_btn)
//						.addComponent(view_phase_btn)
//						.addComponent(save_test_btn)
//						.addComponent(run_btn))
//					.addGap(10))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
//					.addGap(19)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//								.addComponent(lbl_url)
//								.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//							.addGap(20)
//							.addComponent(add_phase_btn)
//							.addGap(14)
//							.addComponent(delete_phase_btn)
//							.addGap(10)
//							.addComponent(view_phase_btn)
//							.addGap(17)
//							.addComponent(save_test_btn)
//							.addGap(4)
//							.addComponent(run_btn))
//						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
//					.addContainerGap(135, Short.MAX_VALUE))
//		);
//		contentPane.setLayout(gl_contentPane);
//		frmTestRunner.setVisible(true);
//		
//	}
//	
//	public TestDialogue(Vector<PhaseInfo> phase_info_vec) {
//		setTitle("Add Test");
//		JFrame frmTestRunner = new JFrame();
//		frmTestRunner.setTitle("Test Runner");
//		frmTestRunner.setVisible(true);
//		frmTestRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frmTestRunner.setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		frmTestRunner.setContentPane(contentPane);
//		
//		//Vector<String> phase_list = new Vector<String>();
//		DefaultListModel phase_list = new DefaultListModel();
//		for(PhaseInfo phase : phase_info_vec) {
//			phase_list.addElement(phase.get_phase_name());
//		}
//		JList list = new JList(phase_list);
//		list.setModel(phase_list);
//		JScrollPane scrollpane = new JScrollPane(list);
//		
//		JLabel lbl_url = new JLabel("URL:");
//		
//		
//		txtField_url = new JTextField();
//		txtField_url.setColumns(10);
//		
//		JButton add_phase_btn = new JButton("Add Phase");
//		add_phase_btn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				PhaseInfo phase_info = new PhaseInfo();
//				String url = txtField_url.getText();
//				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, phase_info, url, frmTestRunner);
//				++counter;
//				System.out.println(phase_info.get_phase_name());
////				boolean wasSuccessful = true;
////				PhaseFrame phaseFrame = new PhaseFrame(phase_info, wasSuccessful);
////				System.out.println(phase_info.get_phase_name());
////					System.out.println(phase_info.get_phase_name());
//				int index = list.getSelectedIndex();
//				if (index != -1) {
//					phase_info_vec.add(index + 1, phase_info);
//					//phase_list.addElement(phase_info.get_phase_name());
//					phase_list.add(index + 1, phase_info.get_phase_name());
//					list.setModel(phase_list);
//				} else {
//					phase_info_vec.add(phase_info);
//					phase_list.addElement(phase_info.get_phase_name());
//					list.setModel(phase_list);
//				}
//				
////		
//	
//			}
//		});
//
//		JButton delete_phase_btn = new JButton("Delete Phase");
//		delete_phase_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = list.getSelectedIndex();
//				System.out.println(index);
//				if (index != -1) {
//					phase_list.remove(index);
//					phase_info_vec.remove(index);
//				}
//			}
//		});
//		
//		
//		
//		JButton save_test_btn = new JButton("Save Test");
//		save_test_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				for(PhaseInfo phase : phase_info_vec) {
//					System.out.println(phase.get_phase_name());
//				}
//				TestInfo test = new TestInfo();
//				JFileChooser j = new JFileChooser();
//				j.showSaveDialog(null);
//				String file = j.getSelectedFile().getAbsolutePath();
//				System.out.println(file);
//				test.write_json(file, phase_info_vec);
//			}
//		});
//		
//		JButton run_btn = new JButton("Run");
//		
//		JButton view_phase_btn = new JButton("View Phase");
//		view_phase_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = list.getSelectedIndex();
//				PhaseInfo selected_phase = phase_info_vec.get(index);
//				String phase_name = selected_phase.get_phase_name();
//				String element = selected_phase.get_element();
//				String screenshot = selected_phase.get_screenshot();
//				String interaction = selected_phase.get_interaction_type();
//				String message = selected_phase.get_message();
//				String url = "test";//txtField_url.getText();
//				PhaseInfoDialog phase_dialog = new PhaseInfoDialog(counter, phase_info_vec, selected_phase, url, phase_name, element, screenshot, interaction,message, 10, frmTestRunner);
//				phase_info_vec.set(index, selected_phase);
//				phase_list.set(index, selected_phase.get_phase_name());
//				list.setModel(phase_list);
//			}
//		});
//
//		
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
//					.addGap(48)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addComponent(lbl_url)
//							.addPreferredGap(ComponentPlacement.UNRELATED)
//							.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
//						.addComponent(add_phase_btn)
//						.addComponent(delete_phase_btn)
//						.addComponent(view_phase_btn)
//						.addComponent(save_test_btn)
//						.addComponent(run_btn))
//					.addGap(10))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
//					.addGap(19)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//								.addComponent(lbl_url)
//								.addComponent(txtField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//							.addGap(20)
//							.addComponent(add_phase_btn)
//							.addGap(14)
//							.addComponent(delete_phase_btn)
//							.addGap(10)
//							.addComponent(view_phase_btn)
//							.addGap(17)
//							.addComponent(save_test_btn)
//							.addGap(4)
//							.addComponent(run_btn))
//						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
//					.addContainerGap(135, Short.MAX_VALUE))
//		);
//		contentPane.setLayout(gl_contentPane);
//		
//	}
//}
//
