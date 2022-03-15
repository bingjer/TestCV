import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;

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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class MainFrame extends JFrame  {

	private JPanel contentPane;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame frame = new MainFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	Vector<PhaseInfo> phase_info_vec;

	/**
	 * Create the frame.
	 */
	public MainFrame(Vector<PhaseInfo> phase_info_vec) {
		this.phase_info_vec = phase_info_vec;
		
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
		
		progressBar = new JProgressBar();
		
		DefaultListModel logs = new DefaultListModel();

		
		JList list = new JList(logs);

		JScrollPane scrollpane = new JScrollPane(list);
		
		int counter = 0; 
		
		for (PhaseInfo phase : phase_info_vec) {
			logs.add(counter, phase.get_phase_name());
			list.setModel(logs);
			counter++;
		}
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(214)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(283, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(111)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		
		JButton btnNewButton = new JButton("Save Logs");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New Test");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Load Test");
		toolBar.add(btnNewButton_2);
		contentPane.setLayout(gl_contentPane);
		frame.setVisible(true);
		
		//Uncomment to add welcome frame back
		//Logger test = new Logger();
		//WelcomeFrame wcFrame = new WelcomeFrame();
		//WelcomeDialog wcFrame = new WelcomeDialog(this, test);
		//System.out.println(test.get_logs());
		
		//test.set_logs("test");
		//System.out.println(test.get_logs());
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		
		ImageComparison test = new ImageComparison("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test3.png.png");
		test.compareImages("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test3.png.png");
		
//		ImageComparison test = new ImageComparison("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test3.png.png");
//		test.compareImages("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png");
	}
	
	public void run() {
		int vec_size = phase_info_vec.size();
		for(int i = 0; i < vec_size; i++) {
			progressBar.setValue(i + 25);
		}
	}
}
