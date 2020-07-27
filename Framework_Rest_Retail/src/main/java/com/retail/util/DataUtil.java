package com.retail.util;

import java.util.Hashtable;

public class DataUtil {

	
	//create function which has return type as 2D Array, becausr xls sheet is 2d
	
	public static Object[][] getData1(Xls_Reader xls, String testName){ //testName is AddProduct
		
		String sheetname = "data";        // Now start traversing in sheet data
		int testStartRowNum =1;           //LoginTest
		
//traverse from Logintest untill AddProduct
		while(!xls.getCellData(sheetname, 0, testStartRowNum).equals(testName)){   
			testStartRowNum++;
		}
		System.out.println("Test starts from row number: " +testStartRowNum);
		
		int colStartRowNum = testStartRowNum+1;
		int dataStartRowNum = testStartRowNum+2;
		
// Now find how many rows LoginTest consists off(total = 5rows), Addproduct(9)
		
		int rows =0;  // assume rows = 0
// Logic: after every test we have left a blank space, so traverse untill you find blank space		
		while(!xls.getCellData(sheetname, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		
		System.out.println("Total Data rows are: "+rows);
		// Now find how many columns LoginTest consists off(colums=5), AddProduct=6
		int cols=0;
// Logic: at the end of the column we have blank space after LoginTest column, so traverse untill there
		while(!xls.getCellData(sheetname, cols, colStartRowNum).equals("")){
			cols++;
		}
		
		System.out.println("Total Data cols are: "+cols);
		
//Now this method should return 2D array, so build that Array that gives us keys n values		
// key=Username, Value= admin		
		
		Object[][] data = new Object[rows][1];
		
		int dataRow = 0;
		Hashtable<String,String> table = null;
// Write logic to traverse and extract actual key n values for Logintest/AddProduct etc use loops
		
		for(int rNum=dataStartRowNum; rNum<dataStartRowNum+rows; rNum++){ // row traversing
			
			table = new Hashtable<String, String>(); // to put data collected in form of k and v
			
			for(int cNum=0; cNum<cols; cNum++){ // cols traversing
				
				// now fetch values in form of key n value pair
				
				String Key=xls.getCellData(sheetname,cNum , colStartRowNum);
				String Value=xls.getCellData(sheetname, cNum, rNum);
				table.put(Key, Value); // we have fteched and put the data now
				
				
			}
			
			data[dataRow][0]=table;
			dataRow++;
		}
		

		
		return data;
	}
	
}
