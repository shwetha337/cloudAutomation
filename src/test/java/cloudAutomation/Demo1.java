package cloudAutomation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cloudAut.qa.Util.ExcelDrivenData;
import com.cloudAut.qa.Util.WebEventListener;
import com.cloudAut.qa.pages.NifiHomePage;


public class Demo1{
	
	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String name;

	  @DataProvider
	public Object[][] getData(){
		Object data[][] = ExcelDrivenData.getTestData("Sheet1");
		return data;
	}
	 
	  @Test(priority=1,dataProvider="getData")
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
		NifiHomePage.checkigTheTotalQueueddata();
		Reporter.log("Checking for error messages");
		Thread.sleep(4000);
		NifiHomePage.checkingForErrorMessages();
		System.out.println("hii" +name);
		//return name;
		//driver.quit();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//public static WebDriver driver;
	//public static screenshot sh;
	
	/*public static void main(String[] args) throws Exception{
		//sh=new screenshot();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pal\\workspace1\\cloudAutomation\\drivers\\chromedriver.exe");
		 
		// Initialize browser
		WebDriver driver=new ChromeDriver();
		 
		// Open nifi
		driver.get("http://localhost:8080/nifi/");
		 
		// Maximize browser
		 
		driver.manage().window().maximize();
		
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
				
				WebElement globalButton = driver.findElement(By.xpath("//button[@id='global-menu-button']"));
				globalButton.click();
				
				WebElement bulletinBoard=driver.findElement(By.xpath("//a[@id='bulletin-board-link']"));
				Thread.sleep(2000);
				bulletinBoard.click();
				
				
				driver.switchTo().frame("shell-iframe");
				Thread.sleep(3000);
				
				
				
				List<WebElement> myElements=driver.findElements(By.xpath("//div[@id='bulletin-board-container']//div[@class='bulletin']"));
				int xpathCount = myElements.size();
				System.out.println(xpathCount);
				
				if(xpathCount>=1){
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				    FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
					 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
				}
				else {
					System.out.println("No error found!!!");
				}
				
				for(WebElement e : myElements) {
			    System.out.println(e.getText());
			    if(e!=null)
			    {
			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
				 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
				}
				
			    else{
			    	System.out.println("no error");
			    }
					
				
				
			    }
				if(Errortext!=null){
					Thread.sleep(2000);
					System.out.println("Taking the screenshot");
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
					 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
				
				  }
				
				
				else{
					System.out.println("No errors found");
				}
				
               //String title=driver.switchTo().frame("shell-iframe").getTitle();
				WebElement bulletinBoardContainer =driver.findElement(By.xpath("//iframe[@id='shell-iframe']"));
               
               driver.findElement(By.xpath("//body/div[@id='bulletin-board-refresh-container']/md-switch/div/div[1]")).click();
               System.out.println("title1122");
				
			   String title11=driver.findElement(By.xpath("//body/div[@id='bulletin-board']/div[@id='bulletin-board-container']/div[2]")).getText();
				
               System.out.println(title11);
               
               //WebElement bulletinBoardContainer =driver.findElement(By.xpath("//iframe[@id='shell-iframe']"));
				
				String title=driver.switchTo().frame("shell-iframe").getTitle();
				
			    //String Text=title.getText();
				
				System.out.println(title);

		
		
		WebElement button =driver.findElement(By.xpath("//body/div[@class='layout-align-space-between-center layout-row flex']/div[@class='layout-align-end-center layout-row']/button[1]"));
		String color=button.getCssValue("color");
		if(color.equals("red")){
			button.click();
			Thread.sleep(2000);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
			 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
		
		  }
		else {
			System.out.println("No error");
		}
		
		WebElement totalQueuedData =driver.findElement(By.xpath("//span[@id='total-queued']"));
		String value=totalQueuedData.getText();
		System.out.println(value);
		String[] sval=value.split("/");
		 
		String secHalf = sval[1];
		String[] splitStr = secHalf .split("\\s+");
		
		String zero = splitStr[1];
		String bytes=splitStr[2];
		double i = Double.parseDouble(zero);
		//System.out.println(zero + " " + bytes + "" + i);
			
		if(i>=25 && bytes.equals("MB")){
			System.out.println("More than 25MB : Error and taking the screen shot");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
			 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
			}
		
		else {
			
			System.out.println("No error");
		}
		
		
		
		
		
		Actions action = new Actions(driver);
		WebElement zoomBtn=driver.findElement(By.xpath("//div[@id='naviagte-zoom-actual-size']//button"));
		zoomBtn.click();
		System.out.println("Zoom btn clicked");
		Thread.sleep(3000);
		
		//click on processGroup 
		
		WebElement processGroup =driver.findElement(By.xpath("//div[@id='canvas-container']//*[name()='g' and @id='id-95dfc4fa-0171-1000-32dc-d2c0ec8a15ad']//*[name()='g' and contains(@class,'process-gr')]//*[name()='rect'][1]"));
		action.click(processGroup).build().perform();
		action.doubleClick(processGroup).build().perform();
		System.out.println("ProcessGroup clicked");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
		 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
		System.out.println("ProcessGroup clicked123");
		
		
		Thread.sleep(4000);
		//double clicking on processor
		WebElement genrateFlowProcessor =driver.findElement(By.xpath("//div[@id='canvas-container']//*[name()='g'][6]/*[name()='g'][2]/*[name()='text'][1]"));
		action.click(genrateFlowProcessor).build().perform();
		action.doubleClick(genrateFlowProcessor).build().perform();
		System.out.println("Genrate the processor clicked");
        Thread.sleep(4000);
		
		//To take the screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         
		 
		 FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
		 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\screenshots" + System.currentTimeMillis() + ".png"));
		 System.out.println("Took the screenshot of GenrateFlowFileProcessor");
		
		//click on applyButton
		WebElement ProcessorApplyBtn= driver.findElement(By.xpath("//div[@id='processor-configuration']//span[contains(text(),'Apply')]"));
		 ProcessorApplyBtn.click();
		 
		 System.out.println("Clicked on Apply button of GenrateFlowprocessor");*/
		 
		
		
	
	}

	
	
