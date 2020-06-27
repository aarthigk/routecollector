package com.rti.routecollector;
	
	import java.io.File;

	import java.io.FileInputStream;

	import java.io.IOException;
	import java.util.ArrayList;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;

	import org.apache.poi.ss.usermodel.Row;

	import org.apache.poi.ss.usermodel.Sheet;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ObalendaCaller {

	    public ArrayList<String> readExcel(String filePath,String fileName,String sheetName) throws IOException{
	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
 
	    Workbook busWorkbook = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx")){
	    busWorkbook = new XSSFWorkbook(inputStream);

	    }
	    else if(fileExtensionName.equals(".xls")){
         busWorkbook = new HSSFWorkbook(inputStream);
        }
	    
	    //Read sheet inside the workbook by its name
    	ArrayList<String> a = new ArrayList<String>();
	  
	    Sheet busSheet = busWorkbook.getSheet(sheetName);
	    int rowCount = busSheet.getLastRowNum()-busSheet.getFirstRowNum();
	    for (int i = 0; i < rowCount+1; i++) {
	       
	    Row row = busSheet.getRow(i);
	    for (int j = 0; j < row.getLastCellNum();j++){
       
	    a.add("From Obalende, Lagos "+"to "+row.getCell(j).getStringCellValue() +" bus stop");
	    }   
	   
	  //  System.out.println();
	    } 
	   // System.out.println(a.get(19));
	    return a;

	    }  
	}


