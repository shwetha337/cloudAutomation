package cloudAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesBase {
	
	
	public void browser(){
		
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pal\\workspace1\\cloudAutomation\\drivers\\chromedriver.exe");
	 
	// Initialize browser
	WebDriver driver=new ChromeDriver();
	 
	// Open nifi
	driver.get("http://localhost:8080/nifi");
	 
	// Maximize browser
	 
	driver.manage().window().maximize();
	}


}
