package com.cloudAut.qa.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.cloudAut.qa.Util.CommonMethods;

import cloudAutomation.Demo1;

public class NifiHomePage extends Demo1{
	
	//public static CommonMethods cm;
	
	public static void checkigTheTotalQueueddata() throws IOException, InterruptedException {
		//cm=new CommonMethods();
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
			CommonMethods.screenshot();
			}
		
		else {
			Reporter.log( "Checked for Total Queued data, Queued data is not more than 25MB");
		  }
	}
	
	public static void checkingForErrorMessages() throws InterruptedException, IOException{
		

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
				CommonMethods.screenshot();
			}
			else {
				Reporter.log("No errors found!!! in Nifi Flow");
			}
			
			
	
   }
		
	}


