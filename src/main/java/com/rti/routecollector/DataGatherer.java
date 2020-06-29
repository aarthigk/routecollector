package com.rti.routecollector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class DataGatherer {
    private static WebDriver driver;

	public static List<String> getInput() throws IOException {
	    ObalendaReader objExcelFile = new ObalendaReader();
	    String filePath = System.getProperty("user.dir")+"\\src\\";
	     String fileName="Bustops on the Island.xlsx";
	    ArrayList<String> route = new ArrayList<String>();
	    route= objExcelFile.readExcel(filePath,"Bustops on the Island.xlsx","Sheet1");
	    return route;
	    }
	
	public static void connectchat() throws IOException {
		 try {
	     FileInputStream file = new FileInputStream(new File("C:\\Users\\AarthiGk\\Documents\\JavaTraining\\routecollector\\src\\Book2.xlsx")); 
	     XSSFWorkbook workbook = new XSSFWorkbook(file);
	     XSSFSheet sheet = workbook.getSheetAt(0);// sheet.getLastRowNum()
		for (int i=1 ; i<=18;i ++) 
			{
					
		System.setProperty("webdriver.chrome.driver", "C:/Users/AarthiGk/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		String baseUrl = "https://lara.ng/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		
		//	int i=1;
		String text =getInput().get(i);
		driver.findElement(By.id("chatinput")).sendKeys(text);
		driver.findElement(By.id("cbutton")).click();
	    WebDriverWait wait = new WebDriverWait(driver,60) ;//*[@id="chatarea"]/div[5]/div
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[2]/div[5]/div")));
		String routepath = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[5]/div")).getText();
		System.out.println("input"+ text);
		System.out.println("output"+i+routepath);
	try {	
	   String myresult =routepath.toString();
		
		Cell resultCell = sheet.getRow(i).getCell(2);
		System.out.println(resultCell.getStringCellValue());
	   System.out.println(resultCell.getCellType());
	   if(resultCell.getRichStringCellValue() != null) {
		   
           resultCell.setCellValue(myresult);
   } else {
           System.out.println("Search is not successful.");
           resultCell.setCellValue("FAIL");
   }
      // ((WebDriver) workbook).close();
	}
	catch (Exception e) {
		 System.out.println(e.getMessage()); 
	}
		driver.close(); 
		
			}
		
        file.close();
		
		 FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\AarthiGk\\Documents\\JavaTraining\\routecollector\\src\\Book1.xlsx"));
		  workbook.write(outFile);
		  outFile.close();
		 } 
		  catch(Exception e) {  
	            System.out.println(e.getMessage());  
		  }
	}
		
	
		
		
	
		
	
	public static void main(String[] args) throws IOException {
	connectchat();
	
	}


	
	}


