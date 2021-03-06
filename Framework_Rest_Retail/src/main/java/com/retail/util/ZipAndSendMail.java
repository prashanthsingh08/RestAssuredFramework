package com.retail.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ZipAndSendMail {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	static String[] toEmails = { "ironpatriot005@gmail.com" };
//	static String fromUser = "its.thakur";// just the id alone without
//	// @gmail.com
	static String fromUser = "prashanthsingh08@gmail.com";// just the id alone without
	// @gmail.com
//static String password = "Jungle@123";
	static String password = "butter@0049";

	public static void main(String[] args) throws Exception {

		// report folder - extent reports
		Properties prop = new Properties();
		// Step 1 - Load properties file
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir")+"//src//test//resources//project.properties");
		prop.load(fs);
		String reportFolder = prop.getProperty("reportpath");
		// find latest report in report folder

		File dir = new File(reportFolder);
		File[] files = dir.listFiles();
		File lastModified = Arrays.stream(files).filter(File::isDirectory)
				.max(Comparator.comparing(File::lastModified)).orElse(null);
		System.out.println(lastModified.getName());

		// zip
		Zip.zipDir(reportFolder + "\\" + lastModified.getName(), reportFolder
				+ "\\" + lastModified.getName() + ".zip");

		// mail
		Mail javaEmail = new Mail();

		javaEmail.setMailServerProperties();

		javaEmail.createEmailMessage("Automation Test Reports", // subject
				"Please find the reports in attachment.", // body
				reportFolder + "\\" + lastModified.getName() + ".zip", // attachment
																		// path
				"Reports.zip", // name of attachment
				toEmails// receivers
				);
		javaEmail.sendEmail(fromUser, password);

	}

}