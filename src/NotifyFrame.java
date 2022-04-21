import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class NotifyFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public NotifyFrame(String details) {
		JFrame frm_notify = new JFrame();
		frm_notify.setAlwaysOnTop(true);
		frm_notify.setVisible(true);
		frm_notify.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm_notify.setBounds(100, 100, 371, 205);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frm_notify.setContentPane(contentPane);
		
		JButton btn_ok = new JButton("Ok");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frm_notify.dispose();
			}
		});
		
		JLabel lbl_warning = new JLabel("Warning!");
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText(details);
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(138)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbl_warning)
								.addComponent(btn_ok)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(53)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lbl_warning)
					.addGap(18)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_ok)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
