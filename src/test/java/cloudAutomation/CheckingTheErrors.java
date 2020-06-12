package cloudAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cloudAut.qa.Util.WebEventListener;



public class CheckingTheErrors  {
	
	static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String name;

	  @DataProvider
	public Object[][] getData(){
		Object data[][] = getTestData("Sheet1");
		return data;
	}
	 
	  @Test(dataProvider="getData")
		public void  getUrlAndName(String url,String name) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pal\\workspace1\\cloudAutomation\\drivers\\chromedriver.exe");
		 
		// Initialize browser
		 driver=new ChromeDriver();
		 
		// Maximize browser
		 
		driver.manage().window().maximize();
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		// Open nifi 
		driver.get(url);
		Reporter.log("Checking for the client "+ url + " " + "Client Name is "+ name);
		Reporter.log("Checking forTotal Queued data");
		checkigTheTotalQueueddata();
		Reporter.log("Checking for error messages");
		Thread.sleep(4000);
		checkingForErrorMessages();
		
		driver.quit();
		
		
	}
	
	
	public void checkigTheTotalQueueddata() throws IOException, InterruptedException{
 
		Thread.sleep(6000);
		WebElement totalQueuedData =driver.findElement(By.xpath("//span[@id='total-queued']"));
		Thread.sleep(4000);
		String value=totalQueuedData.getText();
		Reporter.log("The Total queued data found is " + value);
		String[] sval=value.split("/");
		 
		String secHalf = sval[1];
		String[] splitStr = secHalf .split("\\s+");
		
		String num = splitStr[1];
		String string=splitStr[2];
		double i = Double.parseDouble(num);
		
		
		if(i>=25 && string.equals("MB")){
			Reporter.log("More than 25MB : Error taking the screenshot");
			screenshot();
			}
		
		else {
			Reporter.log( "Checked for Total Queued data, Queued data is not more than 25MB");
		  }
		
		
	}
	
	
	public void checkingForErrorMessages() throws InterruptedException, IOException{
		

		       //Bulletien board
		        Actions action = new Actions(driver);
				WebElement zoomBtn=driver.findElement(By.xpath("//div[@id='naviagte-zoom-actual-size']//button"));
				zoomBtn.click();
				
				Thread.sleep(3000);
				
				//click on processGroup 
				
				/*WebElement processGroup =driver.findElement(By.xpath("//div[@id='canvas-container']//*[name()='g'][5]/*[name()='g'][1]/*[name()='rect'][3]"));
				Thread.sleep(2000);
				action.contextClick(processGroup).build().perform();
				
				WebElement startMenu =driver.findElement(By.xpath("//div[@id='start-menu-item']"));
				startMenu.click();*/
				
				//this above code is optional for production
				
				WebElement globalButton = driver.findElement(By.xpath("//button[@id='global-menu-button']"));
				globalButton.click();
				
				WebElement bulletinBoard=driver.findElement(By.xpath("//a[@id='bulletin-board-link']"));
				Thread.sleep(2000);
				bulletinBoard.click();
				
				
				driver.switchTo().frame("shell-iframe");
				Thread.sleep(3000);
				
				List<WebElement> myElements=driver.findElements(By.xpath("//div[@id='bulletin-board-container']//div[@class='bulletin']"));
				int xpathCount = myElements.size();
			
				if(xpathCount>=1){
					Reporter.log("Error exist in the Nifi Flow,Taking the screenshot");
					screenshot();
				}
				else {
					Reporter.log("No errors found!!! in Nifi Flow");
				}
				
				
		
	      }
	
	
	
	
    public static void screenshot() throws IOException{
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
		 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\" + System.currentTimeMillis() + ".png"));
		
		
		}

	
   
	

	
	 static Workbook book;
	 static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("C:\\Users\\Pal\\workspace1\\cloudAutomation\\TestData\\ListOfUrls.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			
			}
		}
		return data;
	}

}
