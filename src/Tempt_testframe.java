import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Tempt_testframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Tempt_testframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btn_add_phase = new JButton("Add Phase");
		
		JButton btn_view_phase = new JButton("View Phase");
		
		JLabel lbl_url = new JLabel("URL:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btn_delete_phase = new JButton("Delete Phase");
		
		JButton btn_save_test = new JButton("Save Test");
		
		JButton btn_run = new JButton("Run");
		
		DefaultListModel phase_list = new DefaultListModel();
		
		JList list = new JList(phase_list);
		JScrollPane scrollpane = new JScrollPane(list);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_url)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_add_phase)
						.addComponent(btn_delete_phase)
						.addComponent(btn_view_phase)
						.addComponent(btn_save_test)
						.addComponent(btn_run))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_url)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addComponent(btn_add_phase)
							.addGap(14)
							.addComponent(btn_delete_phase)
							.addGap(10)
							.addComponent(btn_view_phase)
							.addGap(17)
							.addComponent(btn_save_test)
							.addGap(4)
							.addComponent(btn_run))
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
