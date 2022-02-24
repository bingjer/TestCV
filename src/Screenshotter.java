import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshotter {
	
	
	public void take_screenshot() {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://google.com"); //have to use http for some reason
        driver.manage().window().maximize();
        
        File screenShot = new File("D:\\default"  + ".png").getAbsoluteFile();
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

}
