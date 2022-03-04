import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is running...");
		WebDriver driver;

        // Open Chrome browser    
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_98.exe");
        driver = new ChromeDriver();
        driver.get("http://google.com");
        driver.manage().window().maximize();

        // Click on Browse button and handle windows pop up using Sikuli
        //driver.findElement(By.xpath(".//*[@id='photoimg']")).click();
        //s.wait(fileInputTextBox, 20);
        //s.type(fileInputTextBox, inputFilePath + "Test.docx");
       // s.click(openButton);

        System.out.println("I am here.");
        Screen s = new Screen();
        System.out.println("I am here 111.");
        Pattern textbox = new Pattern("D:\\TestImages\\Capture.PNG");
        System.out.println("I am here 222222.");
        try {
			s.wait(textbox, 5000);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			s.type(textbox, "pooplava");
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        //Selenium way to take a screenshot
        File screenShot = new File("D:\\Downloads"  + ".png").getAbsoluteFile();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(scrFile, screenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Took Screenshot"  + " saved at " + screenShot);

        // Close the browser
        driver.quit();
	}

}
