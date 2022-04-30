// This file ResultFrame.java constructs the result window for the viewer to view live logs and save logs to a file.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ResultFrame extends JFrame  {

	private JPanel contentPane;
	private Vector<PhaseInfo> phase_info_vec;
	private String url;
	private String driver_loc;
	private String driver_type;
	private int run_counter;
	private String workfolder;
	JFrame frame;
	DefaultListModel logs;
	
	public ResultFrame(int run_counter, String url, String driver_loc, String driver_type, Vector<PhaseInfo> phase_info_vec, String workfolder) {
		// Initialize private members
		this.phase_info_vec = phase_info_vec;	
		this.url = url;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.run_counter = run_counter;
		this.workfolder = workfolder;
		logs = new DefaultListModel();
		frame = new JFrame();
		
		// Frame construction.
		frame.setTitle("TestCV");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 669, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		// Label definition.
		JLabel lblNewLabel = new JLabel("Logs:");
		JLabel label = new JLabel("TestCV");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		// Button definitions.
		JButton btn_save = new JButton("Save Logs");
		JButton btn_newTest = new JButton("New Test");

		// Toolbar construction.
		JToolBar toolBar = new JToolBar();
		toolBar.add(btn_save);
		toolBar.add(btn_newTest);

		// List construction.
		logs = new DefaultListModel();
		JList list = new JList(logs);
		JScrollPane scrollpane = new JScrollPane(list);
		
		// Button listeners
		btn_save_listener(btn_save);
		btn_new_listener(btn_newTest);
		close_window_listener(frame);
		
		// Start driver on frame creation.
		Driver ss = new Driver(run_counter, phase_info_vec, logs, list, url, driver_loc, driver_type, workfolder);
		Thread t = new Thread(ss);
		t.start();
		
		// GroupLayou construction.
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(111)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(97, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(247)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(267, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		contentPane.setLayout(gl_contentPane);
		frame.setVisible(true);
	}  
	
	// Creates a listener for the "New Test" button.
	private void btn_new_listener(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Closes the ResultFrame and opens a new WelcomeFrame.
				WelcomeFrame wcframe = new WelcomeFrame();
				frame.dispose();
			}
		});
	}
	
	// Creates a listener for the "Save Test" button.
	private void btn_save_listener(JButton btn) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opens up the system chooser window for the user to select where to save their test file.
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				System.out.println(file);
				System.out.println(logs.toString());
				
				// Writes to the file.
				try {
					BufferedWriter  writer = new BufferedWriter(new FileWriter(file, true));
					for(int i =0; i < logs.size(); i++) {
						writer.write(logs.toArray()[i].toString());
						writer.newLine();
					}
					writer.close();
				}
				catch (IOException e1) {
				      System.out.println("An error occurred.");
				      e1.printStackTrace();
				}
				
			}
		});
	}
	
	// Creates a listener for the "X" button.
	private void close_window_listener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		    	String opt_buttons[] = {"Yes","No"};
		        int result = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[1]);
		        if(result == JOptionPane.YES_OPTION) {
		        	System.exit(0);
		        }
		    }
		});
	}
}
