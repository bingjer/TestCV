// This file Screenshotter.java handles the driver logic to take a screenshot in the phase dialog window.

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
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
		// Create WebDriver and determine if chrome or firefox.
		WebDriver driver = null;
		try {
			if(driver_type.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", driver_loc);
		        driver = new ChromeDriver();
			} 
			else {
				System.setProperty("webdriver.gecko.driver", driver_loc);
		        driver = new FirefoxDriver();
			}
		} 
		catch (Exception e) {
			String opt_buttons[] = {"Ok"};
	        JOptionPane.showOptionDialog(null, "Could not start driver. Make sure you have the correct driver selected for your browser version.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
	        
		}
		
		
		
		
		// Handles edge case where the phase list is empty.
		if(index == 0) {
			// Starts the browser and opens it to the URL.
			driver.get(url);
			//Maximizes the browser window
	        driver.manage().window().maximize();
	        // Creates screen object which mirrors the browser window.
	        Screen s = new Screen();
	        // Creates a pattern object to interact with based off the provided screenshot.
	        Pattern pattern = new Pattern(element);
	        
	        String interaction_type = phase_info_vec.get(0).get_interaction_type();
	        switch(interaction_type) {
	        case "Lclick":
	        	try {
					s.wait(pattern, 10);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
		        	s.click(pattern);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
	        	break;
	        case "Rclick":
	        	try {
					s.wait(pattern, 10);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
		        	s.rightClick(pattern);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
	        	break;
	        case "Wait":
	        	synchronized(this) {
	    	        try {
	    				this.wait(phase_info_vec.get(0).get_wait_time() * 1000);

	    			} 
	    	        catch (InterruptedException e) {
	    				String opt_buttons[] = {"Ok"};
	    			       JOptionPane.showOptionDialog(null, "Could not wait. Please start test over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);	    					
	    			       e.printStackTrace();
	    			}
	    	    }
	        	break;
	        case "Type":
	        	try {
					s.wait(pattern, 10);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
					s.type(pattern, phase_info_vec.get(0).get_message());
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
		        break;
	        default:
	        	break;
	        }
	        
	        // Default wait 3 seconds before taking a screenshot.
	        if(interaction_type != "Wait") {
	        	synchronized(this) {
	    	        try {
	    				
	    	        	this.wait(3000);
	    			} 
	    	        catch (InterruptedException e) {
	    				String opt_buttons[] = {"Ok"};
	    			       JOptionPane.showOptionDialog(null, "Could not wait. Please start test over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);	    					
	    			       e.printStackTrace();
	    			}
	        	}
	        }
	        // Selenium way to take a screenshot.
	        String formatted_file = format_path(file_name);
	        File screenShot = new File(formatted_file).getAbsoluteFile();
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(scrFile, screenShot);
			} catch (IOException e) {
				String opt_buttons[] = {"Ok"};
		        JOptionPane.showOptionDialog(null, "Could not save screenshot. Please start this phase over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				e.printStackTrace();
			}
		} 
		
		// For all other cases
		else {
	        Screen s = new Screen();
	        
	        for(int i = 0; i < index; i++) {
	        	if(phase_info_vec.get(i).get_wait_time() != 0) {
	        		synchronized(this) {
	    	        	try {
	    					this.wait(phase_info_vec.get(i).get_wait_time() * 1000);

	    				} catch (InterruptedException e) {
	    					String opt_buttons[] = {"Ok"};
	    			        JOptionPane.showOptionDialog(null, "Could not wait. Please start test over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);	    					
	    			        e.printStackTrace();
	    				}
	    	        }

	        	}
	        	if(i == 0) {
	        		driver.get(url); 
	    	        driver.manage().window().maximize();
	        	}
	        	
		        String element_path = phase_info_vec.get(i).get_element();
	        	Pattern pattern = new Pattern(element_path);
	        	
		        String interaction_type = phase_info_vec.get(i).get_interaction_type();
		        
		        switch(interaction_type) {
		        case "Lclick":
		        	try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
						break;
					}
			        try {
			        	s.click(pattern);
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
					}
		        	break;
		        case "Rclick":
		        	try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
						break;
					}
			        try {
			        	s.rightClick(pattern);
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
					}
		        	break;
		        case "Type":
		        	try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
						break;
					}
			        try {
						s.type(pattern, phase_info_vec.get(i).get_message());
					} catch (FindFailed e) {
						String opt_buttons[] = {"Ok"};
				        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
						e.printStackTrace();
						driver.quit();
					}
			        break;
		        default:
		        	break;
		        }
		        
	        }
	        
	        // Handles the phase of the current index.
	        if(phase_info.get_wait_time() != 0) {
        		synchronized(this) {
    	        	try {
    					this.wait(phase_info.get_wait_time() * 1000);
    				} catch (InterruptedException e) {
    					String opt_buttons[] = {"Ok"};
    			        JOptionPane.showOptionDialog(null, "Could not wait. Please start test over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);	    					
    			        e.printStackTrace();
    				}
    	        }

        	}
	        
	        Pattern pattern = new Pattern(element);
	        switch(interaction) {
	        case "Lclick":
	        	try {
					s.wait(pattern, 10);
					
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
		        	
		        	//s.click(pattern, 10);
		        	s.click(pattern);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
	        	break;
	        case "Rclick":
	        	try {
					s.wait(pattern, 10);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
		        	s.rightClick(pattern);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
	        	break;
	        case "Type":
	        	try {
					s.wait(pattern, 10);
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
					break;
				}
		        try {
					s.type(pattern, phase_info.get_message());
				} catch (FindFailed e) {
					String opt_buttons[] = {"Ok"};
			        JOptionPane.showOptionDialog(null, "Could not find element. Please ensure the element screenshot is valid.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
					e.printStackTrace();
					driver.quit();
				}
		        break;
	        default:
	        	break;
	        }
	        
	     // Default wait 3 seconds before taking a screenshot.
	        if(interaction != "Wait") {
	        	synchronized(this) {
	    	        try {
	    				
	    	        	this.wait(3000);
	    			} 
	    	        catch (InterruptedException e) {
	    				String opt_buttons[] = {"Ok"};
	    			       JOptionPane.showOptionDialog(null, "Could not wait. Please start test over.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);	    					
	    			       e.printStackTrace();
	    			}
	        	}
	        }
	        
	        // Selenium way to take a screenshot
	        String formatted_file = format_path(file_name);
	        File screenShot = new File(formatted_file).getAbsoluteFile();
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
				FileUtils.copyFile(scrFile, screenShot);
			} catch (IOException e) {
				String opt_buttons[] = {"Ok"};
		        JOptionPane.showOptionDialog(null, "Could take screenshot. Please ensure elements are valid and try again.", "TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
				e.printStackTrace();
				driver.quit();
			}
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
