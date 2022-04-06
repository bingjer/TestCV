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
		frm_notify.setBounds(100, 100, 289, 154);
		
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
		
		JLabel lbl_details = new JLabel(details);
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_ok)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lbl_details)
							.addComponent(lbl_warning)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(lbl_warning)
					.addGap(18)
					.addComponent(lbl_details)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(btn_ok)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
