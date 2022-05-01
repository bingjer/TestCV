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
	private DefaultListModel<String> logs;
	private JList<String> list;
	private String url;
	private String driver_loc;
	private String driver_type;
	private int test_run_counter;
	private String workfolder;

	public Driver(int test_run_counter, Vector<PhaseInfo> phase_info_vec, DefaultListModel<String> logs,
			JList<String> list, String url, String driver_loc, String driver_type, String workfolder) {
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

	// Overrides Java.Runnable to run the thread. Implements the logic of running
	// the WebDriver and creating logs.
	@Override
	public void run() {
		// Keeps track of the runs counter for logging purposes.
		int run_counter = 1;

		Vector<String> failure_map = new Vector<String>();
		Vector<String> warning_map = new Vector<String>();
		;

		// Loop to repeat the test for the number of user selected runs.
		while (test_run_counter > 0) {

			// Initializes the web driver.
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
			// Breaks out of loop and ends the test if the driver cannot be created.
			catch (Exception e) {
				String opt_buttons[] = { "Ok" };
				JOptionPane.showOptionDialog(null,
						"Could not start driver. Make sure you have the correct driver selected for your browser version.",
						"TestCV", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons,
						opt_buttons[0]);
				break;

			}

			// Creates the SikuliX screen object.
			Screen s = new Screen();

			logs.add(counter, "===============================================================");
			list.setModel(logs);
			counter++;

			logs.add(counter, "Starting run number: " + run_counter);
			list.setModel(logs);
			counter++;

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

				// Get element and interaction type from current object.
				String element_path = phase_info_vec.get(i).get_element();
				String interaction_type = phase_info_vec.get(i).get_interaction_type();

				// Creates the pattern of the element to interact with.
				Pattern pattern = new Pattern(element_path);

				// This is the logic for waiting if both the "Wait" interaction has been chosen
				// and if a delay was added by the user.
				if (phase_info_vec.get(i).get_wait_time() != 0) {
					synchronized (this) {
						try {
							this.wait(phase_info_vec.get(i).get_wait_time() * 1000);
						} catch (InterruptedException e) {
							logs.add(counter, "Could not wait. Phase failed.");
							list.setModel(logs);
							counter++;
							e.printStackTrace();
						}
					}

				}
				switch (interaction_type) {
				case "Lclick":
					try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
					}
					try {
						s.click(pattern);
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
					}
					break;
				case "Rclick":
					try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
					}
					try {
						s.rightClick(pattern);
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
					}
					break;
				case "Type":
					try {
						s.wait(pattern, 10);
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
					}
					try {
						s.type(pattern, phase_info_vec.get(i).get_message());
					} catch (FindFailed e) {
						e.printStackTrace();
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
						logs.add(counter, "Could not find element. Phase failed.");
						list.setModel(logs);
						counter++;
						break;
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
							if (!failure_map.contains(
									"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
								failure_map.add(
										"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
							}
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
					e.printStackTrace();
					if (!failure_map
							.contains("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
						failure_map.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
					}
					logs.add(counter, "Failed to take screenshot. Phase failed.");
					list.setModel(logs);
					counter++;
					break;
				}

				ImageComparison test = new ImageComparison();

				double[] data = test.compareImages(phase_info_vec.get(i).get_screenshot(),
						screenShot.getAbsolutePath());

				if (data == null) {
					if (!failure_map
							.contains("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
						failure_map.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
					}
					logs.add(counter, "The images were empty.");
					list.setModel(logs);
					counter++;
					break;
				} else {
					double correlation_pct = data[0] * 100;
					logs.add(counter, "The images were " + correlation_pct + "% similar.");
					list.setModel(logs);
					counter++;

					// The 90% threshold catches images that are too dissimilar to pass.
					if (correlation_pct < 90.0) {
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
					}
					// If below 99.5% the images are not identical but may still be ok. Adds to
					// warning map.
					else if (correlation_pct < 99.5) {
						if (!failure_map.contains(
								"Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name())) {
							failure_map
									.add("Test: " + run_counter + " Phase: " + phase_info_vec.get(i).get_phase_name());
						}
					}
				}

			}

			driver.quit();
			run_counter++;
			test_run_counter--;

		}

		logs.add(counter, "========================END TEST================================");
		list.setModel(logs);
		counter++;

		// Print out set of warnings accrued for each run.
		if (!warning_map.isEmpty()) {
			logs.add(counter, "========================Test Warnings================================");
			list.setModel(logs);
			counter++;
			for (String warning : warning_map) {
				logs.add(counter, warning);
				list.setModel(logs);
				counter++;
			}
		}

		// Print out set of failures accrued for each run.
		if (!failure_map.isEmpty()) {
			logs.add(counter, "========================Test Failures================================");
			list.setModel(logs);
			counter++;
			for (String failure : failure_map) {
				logs.add(counter, failure);
				list.setModel(logs);
				counter++;
			}
		}
	}
}
