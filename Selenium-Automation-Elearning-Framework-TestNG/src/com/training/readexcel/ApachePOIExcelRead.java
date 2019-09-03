package com.training.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Naveen
 * @see this class will take the records from excel sheet, and return it as list
 *      of list of object, and can be generic, can given any records until it
 *      exists. Test it with main method provided, and the path is hard coded,
 *      participatns are asked to refractor this path in the property file and
 *      access.
 */
public class ApachePOIExcelRead {
	public  String [][] getExcelContent(String fileName) {
		int rowCount =0; 
		String [][] list1 = null; 
		
		try {
			System.out.println("File Name Got " + fileName);
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int rowTotal = sheet.getLastRowNum();

			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
			    rowTotal++;
			}
			
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			 list1 = new String[rowTotal][2];
			 
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				

				int cellCount = 0; 
				int noOfColumns = row.getLastCellNum(); 
				String[] tempList1 = new String[noOfColumns];
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_NUMERIC:
						
						if(((Double) cell.getNumericCellValue()).toString()!=null){
							tempList1[cellCount] = ((Double) cell.getNumericCellValue()).toString(); 
						} 
						break;
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue()!=null){
							tempList1[cellCount] =cell.getStringCellValue();
						}
						break;
					}
					cellCount ++; 
				}
				if(tempList1 != null){
					list1[rowCount++] = tempList1;
				}
			}
		
			
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list1;
	}
	
	public  String[][] getExcelContent(String fileName, String sheetName) {
		int rowCount =0; 
		String [][] list1 = null; 
		
		try {
			System.out.println("File Name Got " + fileName);
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int rowTotal = sheet.getLastRowNum();

			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
			    rowTotal++;
			}
			
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			 list1 = new String[rowTotal][2];
			 
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					
					int cellCount = 0; 
					int noOfColumns = row.getLastCellNum(); 
					String[] tempList1 = new String[noOfColumns];
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
	
						case Cell.CELL_TYPE_NUMERIC:
							
							if(((Double) cell.getNumericCellValue()).toString()!=null){
								tempList1[cellCount] = ((Double) cell.getNumericCellValue()).toString(); 
							} 
							break;
						case Cell.CELL_TYPE_STRING:
							if(cell.getStringCellValue()!=null){
								tempList1[cellCount] =cell.getStringCellValue();
							}
							break;
						}
						cellCount ++; 
					}
					if(tempList1 != null){
						list1[rowCount++] = tempList1;
					}
				}
			
				
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list1;
	}
	
	public String[][] getColumnData(String[][] fullData, String[] fields, String testcase){
		int numCols=fields.length;
		int numRows=0;
		for(int i=0; i<fullData.length; i++) {
			if(fullData[i][0].equals(testcase))
				numRows++;
		}
		String [][] columnData=new String[numRows][numCols];
		Map<String,Integer> map=new HashMap<String, Integer>();  
		for (String field : fields) {
			for(int i=0; i<fullData[0].length; i++) {
				if(fullData[0][i].equals(field)) {
					map.put(field, i);
				}
			}
		}
		
		int rowNum=0;
		boolean flag=false;
		for(int i=0; i<fullData.length; i++) {
			int colNum=0;
			for(int j=0; j<fullData[i].length; j++) {
				if(map.values().contains(j) && fullData[i][0].equals(testcase)) {
					columnData[rowNum][colNum]=fullData[i][j];
					colNum++;
					flag=true;
				}
			}
			if (flag==true) {
				rowNum++;
				flag=false;
			}
		}
		return columnData;
	}
	
	public static void main(String[] args) {
		String fileName = "C:\\Users\\VINODHFRANCIS\\git\\SeleniumProjctes\\final-framework-testng\\resources\\UniformStoreTestSuite.xlsx";
		
		for(String [] temp : new ApachePOIExcelRead().getExcelContent(fileName)){
			for(String  tt : temp){
				System.out.println(tt);
			}
		}
		
		String[][] fullData = {{"TESTCASEID", "USER", "PASS", "DESC", "NEWPASS"},
							   {"UN001", "u1", "p1", "login", "np1"},
							   {"UN001", "u2", "p2", "login", "np2"},
							   {"UN001", "u3", "p3", "login", "np3"},
							   {"UN002", "u1", "p1", "login", "np1"},
							   {"UN002", "u2", "p2", "login", "np2"}};
		
		String [] fields = {"USER", "PASS", "DESC"};
		String testcase="UN001";
		
		ApachePOIExcelRead ap = new ApachePOIExcelRead();
		Object[][] columnData=ap.getColumnData(fullData, fields, testcase);
		for(int i=0; i<columnData.length; i++) {
			System.out.println(columnData[i][0] + " " + columnData[i][1] + " " + columnData[i][2]);
		}
		
	}
}