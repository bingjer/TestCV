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
import org.openqa.selenium.firefox.FirefoxDriver;

public class Screenshotter implements Runnable   {
	
	private String file_name;
	private String url;
	private Vector<PhaseInfo> phase_info_vec;
	private int index;
	private String interaction;
	private String element;
	private String driver_loc;
	private String driver_type;
	private PhaseInfo phase_info;
	
	public Screenshotter(int index, Vector<PhaseInfo> phase_info_vec, String file_name, String interaction, String element, String driver_loc, String driver_type, String url, PhaseInfo phase_info) {
		this.file_name = file_name;
		this.url = url;
		this.phase_info_vec = phase_info_vec;
		this.index = index;
		this.interaction = interaction;
		this.element = element;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.phase_info = phase_info;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		

		
		WebDriver driver;
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
        System.out.println(phase_info_vec.size());
        System.out.println(index);
		if(index == 0) {
			// Open Chrome browser    
	        System.out.println("am empty");
	        //driver.get(url);
			driver.get(url);
	        driver.manage().window().maximize();

	      

	        System.out.println("Line 69.");
	        Screen s = new Screen();
	        System.out.println("Line 71.");
	        Pattern pattern = new Pattern(element);
	        System.out.println("Line 73.");

	        String interaction_type = phase_info_vec.get(0).get_interaction_type();
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
					s.type(pattern, phase_info_vec.get(0).get_message());
				} catch (FindFailed e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        default:
	        	break;
	        }
	        
	        
	        //Selenium way to take a screenshot
	        String formatted_file = format_path(file_name);
	        File screenShot = new File(formatted_file).getAbsoluteFile();
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(scrFile, screenShot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Took Screenshot"  + " saved at " + screenShot);
		} 
		else {

	        System.out.println("Line 111.");
	        Screen s = new Screen();
	        System.out.println("Line 113.");
	        System.out.println(index);
	        for(int i = 0; i < index; i++) {
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
	        	if(i == 0) {
	        		driver.get(url); // change to url in live
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
						s.type(pattern, phase_info_vec.get(i).get_message());
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        default:
		        	break;
		        }
		        
	        }
	        
	        if(phase_info.get_wait_time() != 0) {
        		synchronized(this) {
    	        	try {
	        			System.out.println("waiting");

    					this.wait(phase_info.get_wait_time() * 1000);
	        			System.out.println("has it been 5 sec?");

    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	        }

        	}
	        
	        Pattern pattern = new Pattern(element);
	        System.out.println(element);
	        System.out.println("Line 180.");
	        System.out.println(interaction);
	        switch(interaction) {
	        case "Lclick 232":
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
					s.type(pattern, phase_info.get_message());
				} catch (FindFailed e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        default:
	        	break;
	        }
	        
	        //Selenium way to take a screenshot
	        String formatted_file = format_path(file_name);
	        File screenShot = new File(formatted_file).getAbsoluteFile();
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
	
	public String format_path(String path) {
		if(path.endsWith(".png")) {
			return path;
		} 
		else {
			String new_path = path + ".png";
			return new_path;
		}
	}

}
