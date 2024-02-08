package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	
//	Methods for page timeouts
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	
//	Method for generating timeStamps 
	public String generateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "example"+timeStamp+"@gmail.com";
		
	}
	
	
//	Method for reading data from excel file
	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null;
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\utils\\tutorialsNinjaTestData.xlsx");
		try {
			FileInputStream fisExcel = new FileInputStream(file);
			workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
				
		XSSFSheet sheet = workbook.getSheet(sheetName);
//		Getting the ROWS and COLS
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
//		Creating 2 dimensional Objects
		Object [][] data = new Object[rows][cols];
//		Looping Through the rows and column
		for(int i=0; i<cols; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for (int j=0; j<cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
				
			}
		}
		
		return data;
	}
}
