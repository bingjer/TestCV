// This file Driver.java for starting and controlling the WebDriver.

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
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

public class Driver implements Runnable {

	private Vector<PhaseInfo> phase_info_vec;
	private int counter = 0;
	private DefaultListModel logs;
	private JList list;
	private String url;
	private String driver_loc;
	private String driver_type;
	private int test_run_counter;
	private String workfolder;

	public Driver(int test_run_counter, Vector<PhaseInfo> phase_info_vec, DefaultListModel logs, JList list, String url,
			String driver_loc, String driver_type, String workfolder) {
		this.phase_info_vec = phase_info_vec;
		this.counter = 0;
		this.logs = logs;
		this.list = list;
		this.url = url;
		this.driver_loc = driver_loc;
		this.driver_type = driver_type;
		this.test_run_counter = test_run_counter;
		this.workfolder = workfolder;

	}

	@Override
	public void run() {
		int run_counter = 1;
		while (test_run_counter > 0) {

			WebDriver driver = null;
			try {
				if (driver_type.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", driver_loc);
					driver = new ChromeDriver();
				} else {
					System.setProperty("webdriver.gecko.driver", driver_loc);
					driver = new FirefoxDriver();
				}
			}
			// Catches the driver exception if an incorrect driver was used for the browser.
			catch (Exception e) {
				String opt_buttons[] = { "Ok" };
				JOptionPane.showOptionDialog(null,
						"Could not start driver. Make sure you have the correct driver selected for your browser version.",
						"TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons,
						opt_buttons[0]);

			}
			
			// Creates the SikuliX screen object.
			Screen s = new Screen();

			logs.add(counter, "===============================================================");
			list.setModel(logs);

			counter++;
			logs.add(counter, "Starting run number: " + run_counter);
			list.setModel(logs);
			counter++;
			run_counter++;

			// Runs all the tests in the test list.
			for (int i = 0; i < phase_info_vec.size(); i++) {
				if (i == 0) {
					driver.get(url);
					driver.manage().window().maximize();
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

				String element_path = phase_info_vec.get(i).get_element();
				// Creates the pattern of the element to interact with. 
				Pattern pattern = new Pattern(element_path);
				String interaction_type = phase_info_vec.get(i).get_interaction_type();
				
				if (phase_info_vec.get(i).get_wait_time() != 0) {
					synchronized (this) {
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
				switch (interaction_type) {
				case "Lclick":
					try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						s.click(pattern);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "Rclick":
					try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						s.rightClick(pattern);
					} catch (FindFailed e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "Type":
					try {
						s.wait(pattern, 10);
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

				// Default 3 second wait before taking a screenshot.
				if (interaction_type != "Wait") {
					synchronized (this) {
						try {
							this.wait(3000);
						} catch (InterruptedException e) {
							logs.add(counter, "Could not take screenshot for test " + run_counter + " Phase "
									+ phase_info_vec.get(i).get_phase_name());
							list.setModel(logs);
							counter++;
							continue;
						}
					}
				}

				// Selenium way to take a screenshot
				String file_name = workfolder + ("\\TestCV_Images\\testcvimg_" + (i + 1) + ".png");
				File screenShot = new File(file_name).getAbsoluteFile();
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, screenShot);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Took Screenshot" + " saved at " + screenShot);

				ImageComparison test = new ImageComparison();
				
				

				double[] data = test.compareImages(phase_info_vec.get(i).get_screenshot(),
						screenShot.getAbsolutePath());
				
				if (data == null) {
					logs.add(counter, "The images were empty.");
					list.setModel(logs);
					counter++;
				}
				else {
					double correlation_pct = data[0] * 100;

					logs.add(counter, "The images were " + correlation_pct + "% similar.");
					list.setModel(logs);
					counter++;
				}

				

			}

			driver.quit();
			test_run_counter--;

		}
		logs.add(counter, "========================END TEST================================");
		list.setModel(logs);
	}

}
