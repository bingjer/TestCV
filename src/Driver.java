import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver implements Runnable   {
	
	private String file_name;
	private Vector<PhaseInfo> phase_info_vec;
	private int index;
	private String interaction;
	private String element;
	private int counter = 0; 
	private DefaultListModel logs;
	private JList list;
	private String url;
	private String driver_loc;
	private String driver_type;
	private int test_run_counter;
	
	
	public Driver(int test_run_counter, Vector<PhaseInfo> phase_info_vec, DefaultListModel logs, JList list, String url, String driver_loc, String driver_type) {
		this.file_name = file_name;
		this.phase_info_vec = phase_info_vec;
		this.counter = 0;
		this.logs = logs;
		this.list = list;
		this.url = url;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.test_run_counter = test_run_counter;
		
	}
	

	@Override
	public void run() {
		int run_counter = 1;
		while(test_run_counter > 0) {
//			// Clear Images folder before each test
//			 try {
//					FileUtils.cleanDirectory(new File("Images"));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					NotifyFrame nf = new NotifyFrame("clearing directory error");
//				}	
			
		WebDriver driver;
		//System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_98.exe");
		if(driver_type.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", driver_loc);
	        driver = new ChromeDriver();
		} 
		else if (driver_type.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", driver_loc);
	        driver = new FirefoxDriver();
		}
		else {
			driver = null;
		}
		
//		System.setProperty("webdriver.chrome.driver", driver_loc);
//        driver = new ChromeDriver();
        //System.out.println(phase_info_vec.size());
        
			//driver.get(url);
			//driver.get("http://google.com");
	        //driver.manage().window().maximize();

	        // Click on Browse button and handle windows pop up using Sikuli
	        //driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
	        //s.wait(fileInputTextBox, 20);
	        //s.type(fileInputTextBox, inputFilePath + "Test.docx");
	       // s.click(openButton);

	        Screen s = new Screen();
	        
	        
	        
	        
	        logs.add(counter, "===============================================================");
	        list.setModel(logs);

	        counter++;
	        logs.add(counter, "Starting run number: " + run_counter);
			list.setModel(logs);
			counter++;
			run_counter++;
			
	        
	        for(int i = 0; i < phase_info_vec.size(); i++) {
//	        	if(phase_info_vec.get(i).get_wait_time() != 0) {
//	        		synchronized(this) {
//	    	        	try {
//		        			System.out.println("waiting");
//
//	    					this.wait(phase_info_vec.get(i).get_wait_time() * 1000);
//		        			System.out.println("has it been 5 sec?");
//
//	    				} catch (InterruptedException e) {
//	    					// TODO Auto-generated catch block
//	    					e.printStackTrace();
//	    				}
//	    	        }
//
//	        	}
	        	System.out.println(phase_info_vec.size());
	        	System.out.println(phase_info_vec.get(i).get_screenshot());
	        	if(i == 0) {
	        		//driver.get("http://google.com"); // change to url in live
	        		System.out.println("Here");
	        		System.out.println(url);

	        		driver.get(url);
	    	        driver.manage().window().maximize();
	    	        //TODO: change to url or phase
	    	        logs.add(counter, "Opening web page at: " + url);
	    			list.setModel(logs);
	    			counter++;
	        	}
	        	
	        	logs.add(counter, "===============================================================");
		        list.setModel(logs);

		        counter++;
	        	logs.add(counter, "Starting phase: " + phase_info_vec.get(i).get_phase_name());
				list.setModel(logs);
				counter++;
	        	

	        	//Pattern pattern = new Pattern(phase.get_element());
		        String element_path = phase_info_vec.get(i).get_element();
	        	Pattern pattern = new Pattern(element_path);
		        System.out.println("Line 127.");
		        String interaction_type = phase_info_vec.get(i).get_interaction_type();
		        
		        System.out.println(interaction_type);
		        if(phase_info_vec.get(i).get_wait_time() != 0) {
	        		synchronized(this) {
	    	        	try {
		        			System.out.println("waiting");

	    					this.wait(phase_info_vec.get(i).get_wait_time() * 1000);
		        			System.out.println("has it been 5 sec?");

	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    	        }

	        	}
		        switch(interaction_type) {
		        case "Lclick":
		        	try {
						s.wait(pattern, 5000);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
			        	s.click(pattern, 5000);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	break;
		        case "Rclick":
		        	try {
						s.wait(pattern, 5000);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
			        	s.rightClick(pattern, 5000);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	break;
		        case "Type":
		        	try {
						s.wait(pattern, 5000);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						s.type(pattern, phase_info_vec.get(i).get_message());
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        default:
		        	break;
		        }
		        
		        //Selenium way to take a screenshot
		       
		        String file_name = ("TestCV_Images\\testcvimg_" + (i + 1) + ".png");
		        File screenShot = new File(file_name).getAbsoluteFile();
		        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        try {
					FileUtils.copyFile(scrFile, screenShot);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("Took Screenshot"  + " saved at " + screenShot);
		        
		        //compare the two images
//		        ImageComparison test = new ImageComparison("C:\\Users\\adamn\\OneDrive\\Documents\\tst.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test2.png.png");
//				test.compareImages("C:\\Users\\adamn\\OneDrive\\Documents\\tst.png.png", "C:\\Users\\adamn\\\\OneDrive\\Documents\\test2.png.png");
		        
		        //DefaultListModel logs = new DefaultListModel();

				
				//JList list = new JList(logs);

				//JScrollPane scrollpane = new JScrollPane(list);
				
				
				
		        
				
				
		        
		        ImageComparison test = new ImageComparison(phase_info_vec.get(i).get_screenshot(), screenShot.getAbsolutePath());

				double[] data = test.compareImages(phase_info_vec.get(i).get_screenshot(), screenShot.getAbsolutePath());
				
				double correlation_pct = data[0] * 100;
				
				logs.add(counter, "The images were " + correlation_pct + "% similar.");
				list.setModel(logs);
				counter++;
				
		        
	        }
	        
	        
	        
	        driver.quit();
	        test_run_counter--;
	        
		}
		logs.add(counter, "========================END TEST================================");
		list.setModel(logs);
		}
	

}
