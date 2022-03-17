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

public class Driver implements Runnable   {
	
	private String file_name;
	private String url;
	private Vector<PhaseInfo> phase_info_vec;
	private int index;
	private String interaction;
	private String element;
	
	public Driver(Vector<PhaseInfo> phase_info_vec) {
		this.file_name = file_name;
		this.phase_info_vec = phase_info_vec;
	}
	

	@Override
	public void run() {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_98.exe");
        driver = new ChromeDriver();
        System.out.println(phase_info_vec.size());
        
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
	        
	        for(int i = 0; i < phase_info_vec.size(); i++) {
	        	
	        	System.out.println(phase_info_vec.size());
	        	System.out.println(phase_info_vec.get(i).get_screenshot());
	        	if(i == 0) {
	        		driver.get("http://google.com"); // change to url in live
	    	        driver.manage().window().maximize();
	        	}
	        	

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
		        
		        //Selenium way to take a screenshot
		        String file_name = ("C:\\Users\\adamn\\OneDrive\\Documents\\testcvimg_" + (i + 1) + ".png");
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
		        
		        ImageComparison test = new ImageComparison(phase_info_vec.get(i).get_screenshot(), screenShot.getAbsolutePath());
				test.compareImages(phase_info_vec.get(i).get_screenshot(), screenShot.getAbsolutePath());
//				
		        
	        }
	        
	        driver.quit();
	        
	       
		}

}
