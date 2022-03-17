import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshotter implements Runnable   {
	
	private String file_name;
	private String url;
	private Vector<PhaseInfo> phase_info_vec;
	private int index;
	private String interaction;
	private String element;
	
	public Screenshotter(int index, Vector<PhaseInfo> phase_info_vec, String file_name, String interaction, String element) {
		this.file_name = file_name;
		//this.url = url;
		this.phase_info_vec = phase_info_vec;
		this.index = index;
		this.interaction = interaction;
		this.element = element;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		 System.out.println("Thread is running...");
		 System.out.println(interaction);
		 System.out.println(phase_info_vec.size());
		 System.out.println(index);
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_98.exe");
        driver = new ChromeDriver();
        System.out.println(phase_info_vec.size());
        System.out.println(index);
		if(index == 0) {
			// Open Chrome browser    
	        System.out.println("am empty");
	        //driver.get(url);
			driver.get("http://google.com");
	        driver.manage().window().maximize();

	        // Click on Browse button and handle windows pop up using Sikuli
	        //driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
	        //s.wait(fileInputTextBox, 20);
	        //s.type(fileInputTextBox, inputFilePath + "Test.docx");
	       // s.click(openButton);

	        System.out.println("Line 69.");
	        Screen s = new Screen();
	        System.out.println("Line 71.");
	        Pattern textbox = new Pattern(element);
	        System.out.println("Line 73.");
	        try {
				s.wait(textbox, 5000);
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				s.type(textbox, "test this");
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        //Selenium way to take a screenshot
	        File screenShot = new File(file_name + ".png").getAbsoluteFile();
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(scrFile, screenShot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Took Screenshot"  + " saved at " + screenShot);
	        driver.quit();
		} 
		else {
			//driver.get(url);
			//driver.get("http://google.com");
	        //driver.manage().window().maximize();

	        // Click on Browse button and handle windows pop up using Sikuli
	        //driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
	        //s.wait(fileInputTextBox, 20);
	        //s.type(fileInputTextBox, inputFilePath + "Test.docx");
	       // s.click(openButton);

	        System.out.println("Line 111.");
	        Screen s = new Screen();
	        System.out.println("Line 113.");
	        System.out.println(index);
	        for(int i = 0; i < index; i++) {
	        	
	        	if(i == 0) {
	        		driver.get("http://google.com"); // change to url in live
	    	        driver.manage().window().maximize();
	        	}
	        	
		        System.out.println(index);

	        	//Pattern pattern = new Pattern(phase.get_element());
		        String element_path = phase_info_vec.get(i).get_element();
	        	Pattern pattern = new Pattern(element_path);
		        System.out.println("Line 127.");
		        String interaction_type = phase_info_vec.get(i).get_interaction_type();
		        
		        System.out.println(interaction_type);
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
						s.type(pattern, "test this");
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        default:
		        	break;
		        }
		        
	        }
	        
	        Pattern pattern = new Pattern(element);
	        System.out.println(element);
	        System.out.println("Line 180.");
	        System.out.println(interaction);
	        //String interaction_type = phase_info_vec.get(i).get_interaction_type();
	        switch(interaction) {
	        case "Lclick":
	        	try {
	        		System.out.println("Finding element...");
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
					s.type(pattern, "test this");
				} catch (FindFailed e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        default:
	        	break;
	        }
	        
	        //Selenium way to take a screenshot
	        File screenShot = new File(file_name + ".png").getAbsoluteFile();
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(scrFile, screenShot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Took Screenshot"  + " saved at " + screenShot);
		}
        

        // Close the browser
        driver.quit();
	}

}
