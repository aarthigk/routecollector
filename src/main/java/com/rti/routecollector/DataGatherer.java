package com.rti.routecollector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class DataGatherer {
    private static WebDriver driver;
	
	public static List<String> getInput() throws IOException {
	    ObalendaCaller objExcelFile = new ObalendaCaller();
	    String filePath = System.getProperty("user.dir")+"\\src\\";
	    ArrayList<String> route = new ArrayList<String>();
	    route= objExcelFile.readExcel(filePath,"Bustops on the Island.xlsx","Sheet1");
	    return route;
	    }
	
	public static void connectchat() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:/Users//Downloads/chromedriver.exe");
	    driver = new ChromeDriver();
		String baseUrl = "https://lara.ng/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		int i=1;
		String text =getInput().get(i);
		driver.findElement(By.id("chatinput")).sendKeys(text);
		driver.findElement(By.id("cbutton")).click();
		WebDriverWait wait = new WebDriverWait(driver,60) ;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[2]/div[2]/div[2]/div[5]/div")));
		String routepath = driver.findElement(By.xpath("html/body/div[2]/div[2]/div[2]/div[5]/div")).getText();
		System.out.println("output"+routepath);
		driver.close();
	}
	
		
	
	public static void main(String[] args) throws IOException {
	connectchat();
	
	}
	}


