import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame  {

	private JPanel contentPane;
	private Vector<PhaseInfo> phase_info_vec;
	private String url;
	private String driver_loc;
	private String driver_type;
	private int run_counter;
	private String workfolder;

	
	public MainFrame(int run_counter, String url, String driver_loc, String driver_type, Vector<PhaseInfo> phase_info_vec, String workfolder) {
		this.phase_info_vec = phase_info_vec;	
		this.url = url;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.run_counter = run_counter;
		this.workfolder = workfolder;
		initialize();
		
	}
	
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setTitle("TestCV");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 669, 432);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Something");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Logs:");
		
		JToolBar toolBar = new JToolBar();
		
		DefaultListModel logs = new DefaultListModel();

		
		JList list = new JList(logs);

		JScrollPane scrollpane = new JScrollPane(list);
		
		JLabel label = new JLabel("TestCV");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 30));
		
		
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
		
		JButton btnNewButton = new JButton("Save Logs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
				String file = j.getSelectedFile().getAbsolutePath();
				System.out.println(file);
				System.out.println(logs.toString());
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
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeFrame wcframe = new WelcomeFrame();
				frame.dispose();
			}
		});
		toolBar.add(btnNewButton_1);
		contentPane.setLayout(gl_contentPane);
		frame.setVisible(true);
		System.out.println("no issue yet");
			Driver ss = new Driver(run_counter, phase_info_vec, logs, list, url, driver_loc, driver_type, workfolder);
			Thread t = new Thread(ss);
			t.start();
		
	}  
}
