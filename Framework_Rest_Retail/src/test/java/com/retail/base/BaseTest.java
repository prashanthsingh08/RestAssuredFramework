package com.retail.base;

import static io.restassured.RestAssured.given;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.retail.util.DataUtil;
import com.retail.util.ExtentManager;
import com.retail.util.Xls_Reader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BaseTest {
	
	
	public static String Sessionid;
	public Xls_Reader xls;
	public SoftAssert softAssert = new SoftAssert();
	public Properties testprop;
	
// extent variables
	public ExtentReports rep;
	public static String reportFolder;
	public ExtentTest test;
	public int iteration;
	
// to filter our request and send it to log file in our report folder, we are using this
	
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	
	
	@BeforeTest
	public void init(){
		
		testprop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir")+"//src//test//resources//project.properties");
			
			try {
				testprop.load(fs);         // Loading the properties file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xls = new Xls_Reader(testprop.getProperty("xlspath"));
		RestAssured.baseURI = testprop.getProperty("baseurl");
		String testname = this.getClass().getSimpleName().toLowerCase();
		RestAssured.basePath = testprop.getProperty(testname);
	}
    
//Create a method which will dynamically select different tests for passing data, ie LoginTest,AddProduct
	 
	@DataProvider
	public Object[][] getData(){
		
		
		
		return DataUtil.getData1(xls, this.getClass().getSimpleName()); //-->testname
	}
	
	public void reportFailure(String errMsg, boolean stop){
		
		 softAssert.fail(errMsg);
		 
		 if(stop)
			 softAssert.assertAll();
	}
	
	@BeforeMethod
	public void initextent(){
		
		iteration++;
		System.out.println("before report path detect");
		rep = ExtentManager.getInstance(testprop.getProperty("reportpath"));
		System.out.println("report path detect has been read");
		test = rep.createTest("loginTestResult");
		
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
			
	}
	
	@AfterMethod
	public void afterextent(){
		
		rep.flush();
	}
	
	public void addReqLinkToReport(String Message, String fileName, String Content) throws IOException{
		System.out.println(reportFolder);
		System.out.println(reportFolder+"//log//"+fileName+".html");
		
		try {	
			System.out.println("File creation started");
		new File(reportFolder+"//log//"+fileName+".html").createNewFile();
			System.out.println("File has been created successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileWriter fw;
		
		try{
			System.out.println("Before filewriter");
		fw = new FileWriter(reportFolder+"//log//"+fileName+".html");
			System.out.println("After filewriter");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Content);
		System.out.println("Content has been written");
		bw.close();
		fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
