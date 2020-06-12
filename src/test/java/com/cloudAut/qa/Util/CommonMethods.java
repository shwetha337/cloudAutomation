package com.cloudAut.qa.Util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonMethods {
	
	static WebDriver driver;
public static void screenshot() throws IOException{
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Pal"
		 		+ "\\workspace1\\cloudAutomation\\src\\test\\java\\" + System.currentTimeMillis() + ".png"));
		
		
		}

}
