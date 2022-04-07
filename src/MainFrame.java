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
	String url;
	String driver_loc;
	String driver_type;
	int run_counter;

	/**
	 * Create the frame.
	 */
	public MainFrame(int run_counter, String url, String driver_loc, String driver_type, Vector<PhaseInfo> phase_info_vec) {
		this.phase_info_vec = phase_info_vec;	
		this.url = url;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.run_counter = run_counter;
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
		
//		int counter = 0; 
//		
//		for (PhaseInfo phase : phase_info_vec) {
//			logs.add(counter, phase.get_phase_name());
//			list.setModel(logs);
//			counter++;
//		}
		
		
		
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
		
		//Uncomment to add welcome frame back
		//Logger test = new Logger();
		//WelcomeFrame wcFrame = new WelcomeFrame();
		//WelcomeDialog wcFrame = new WelcomeDialog(this, test);
		//System.out.println(test.get_logs());
		
		//test.set_logs("test");
		//System.out.println(test.get_logs());
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		
//		ImageComparison test = new ImageComparison("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test3.png.png");
//		test.compareImages("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\OneDrive\\Documents\\test3.png.png");
//		
//		ImageComparison test = new ImageComparison("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test3.png.png");
//		test.compareImages("C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png", "C:\\Users\\adamn\\OneDrive\\Documents\\test2.png.png");
		System.out.println("no issue yet");
			Driver ss = new Driver(run_counter, phase_info_vec, logs, list, url, driver_loc, driver_type);
			Thread t = new Thread(ss);
			t.start();
			//run_counter--;
		
	}
	
	
	
	
	
//	public void screenshot() {
//		WebDriver driver;
//		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_98.exe");
//        driver = new ChromeDriver();
//        System.out.println(phase_info_vec.size());
//		
//			//driver.get(url);
//			//driver.get("http://google.com");
//	        //driver.manage().window().maximize();
//
//	        // Click on Browse button and handle windows pop up using Sikuli
//	        //driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
//	        //s.wait(fileInputTextBox, 20);
//	        //s.type(fileInputTextBox, inputFilePath + "Test.docx");
//	       // s.click(openButton);
//
//	        System.out.println("Line 111.");
//	        Screen s = new Screen();
//	        System.out.println("Line 113.");
//	        
//	        for(int i = 0; i < phase_info_vec.size(); i++) {
//	        	System.out.println(phase_info_vec.size());
//	        	if(i == 0) {
//	        		driver.get(url); // change to url in live
//	    	        driver.manage().window().maximize();
//	        	}
//	        	
//		        System.out.println("IN SECOND LOOOP");
//
//	        	//Pattern pattern = new Pattern(phase.get_element());
//		        String element_path = phase_info_vec.get(i).get_element();
//	        	Pattern pattern = new Pattern(element_path);
//		        System.out.println("Line 127.");
//		        String interaction_type = phase_info_vec.get(i).get_interaction_type();
//		        
//		        System.out.println(interaction_type);
//		        switch(interaction_type) {
//		        case "Lclick":
//		        	try {
//						s.wait(pattern, 5000);
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			        try {
//			        	s.click(pattern, 5000);
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		        	break;
//		        case "Rclick":
//		        	try {
//						s.wait(pattern, 5000);
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			        try {
//			        	s.rightClick(pattern, 5000);
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		        	break;
//		        case "Type":
//		        	try {
//						s.wait(pattern, 5000);
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			        try {
//						s.type(pattern, "test this");
//					} catch (FindFailed e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		        default:
//		        	break;
//		        }
//		        
//		        //Selenium way to take a screenshot
//		        String file_name = ("C:\\Users\\adamn\\OneDrive\\Documents\\testcvimg_" + (i + 1) + ".png");
//		        File screenShot = new File(file_name).getAbsoluteFile();
//		        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		        try {
//					FileUtils.copyFile(scrFile, screenShot);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		        System.out.println("Took Screenshot"  + " saved at " + screenShot);
//		        
//		        //compare the two images
//		        ImageComparison test = new ImageComparison(phase_info_vec.get(i).get_screenshot(), file_name);
//				
//				
//		        
//	        }
//	        
//	        driver.quit();
//	        
//	       
//		}
        
        
}
