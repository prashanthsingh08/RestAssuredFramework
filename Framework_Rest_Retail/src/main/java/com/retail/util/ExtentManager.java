package com.retail.util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.retail.base.BaseTest;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(String path){
		
		if(extent==null){
		
			Date date = new Date();
			String folderName = date.toString().replace(":","_").replace(" ","_");
			new File(path+folderName+"//log").mkdirs();
			BaseTest.reportFolder =path+folderName;
			String filename = path+folderName+"//report.html";
			createInstance(filename);
			
		}
		return extent;
	}

	public static ExtentReports createInstance(String filename){
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
		htmlReporter.setAppendExisting(true);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
}
