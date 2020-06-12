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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class abcd1 {
	
	WebDriver driver;
	
	@DataProvider
	public Object[][] getData() throws InvalidFormatException{
		Object data[][] = getTestData("Sheet1");
		return data;
	}
	
	static Workbook book;
	 static Sheet sheet;
	 
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream("C:\\Users\\Pal\\workspace1\\cloudAutomation\\TestData\\Book7.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
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
  
  @Test(dataProvider="getData")
	public void  updateRunState(String url,String name){
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pal\\workspace1\\cloudAutomation\\drivers\\chromedriver.exe");
		 
		// Initialize browser
		 driver=new ChromeDriver();
		 
		// Maximize browser
		 
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Reporter.log("Starting for the client"+ name);
		// Open nifi 
		driver.get(url);	
		System.out.println(name);
	
	}

	 /* @DataProvider
	public Object[][] getUrlData(){
		Object data[][] = getTestData("Sheet1");
		return data;
	}
	 
	  @Test(priority=4, dataProvider="getUrlData")
	  public void getTheUrl(String Url) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pal\\workspace1\\cloudAutomation\\drivers\\chromedriver.exe");
		 
		// Initialize browser
		 driver=new ChromeDriver();
		 
		// Maximize browser
		 
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Reporter.log("Starting for the client"+ name);
		// Open nifi 
		driver.get(Url);
		Reporter.log("Starting for Total Queued Data messages");
		//checkigTheTotalQueueddata();
		Reporter.log("Starting for error messages");
		Thread.sleep(4000);
		//checkingForErrorMessages();
		driver.quit();
	}*/
	
	
	/*public void checkigTheTotalQueueddata() throws IOException, InterruptedException{

		Thread.sleep(4000);
		WebElement totalQueuedData =driver.findElement(By.xpath("//span[@id='total-queued']"));
		Thread.sleep(4000);
		String value=totalQueuedData.getText();
		Reporter.log("The Total queued data found is" + value);
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
				
				WebElement processGroup =driver.findElement(By.xpath("//div[@id='canvas-container']//*[name()='g'][5]/*[name()='g'][1]/*[name()='rect'][3]"));
				Thread.sleep(2000);
				action.contextClick(processGroup).build().perform();
				
				WebElement startMenu =driver.findElement(By.xpath("//div[@id='start-menu-item']"));
				startMenu.click();
				
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
		
	      }*/
	
	
	

	
 /*public void screenshot() throws IOException{
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
		 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
		
		
		}
	

	
	 static Workbook book;
	 static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream("C:\\Users\\Pal\\workspace1\\cloudAutomation\\TestData\\url1.xlsx");
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
	}*/
 
	/*public static void main(String[] args){
		String totalQueuedData ="0 / 0 bytes";
		String[] sval=totalQueuedData.split("/");
		//String part122 = sval[0]; 
		String part222 = sval[1];
		String[] splitStr = part222.split("\\s+");
		//String pa1 = splitStr[0]; 
		String pa2 = splitStr[1];
		String pa3=splitStr[2];
		
		System.out.println(pa2);
		System.out.println(pa3);
		
		
		
		
		
		//String[] sval=totalQueuedData.split("/");
		//String part1 = sval[0]; 
		String part2 = sval[1];
		//System.out.println(part1 + " " +part2);
		String[] para = part2.split("\\s+");
		String part122 = sval[0]; 
		String part222 = sval[1];
		String part223 = sval[2];
		
		System.out.println(part122 + " " + part222 + "" +part223);
		
		String string = "004-034556";
		String[] parts = string.split("-");
		String part11 = parts[0]; // 004
		String part21 = parts[1];
		System.out.println(part11 + " " +part21);
		
	}*/
}
