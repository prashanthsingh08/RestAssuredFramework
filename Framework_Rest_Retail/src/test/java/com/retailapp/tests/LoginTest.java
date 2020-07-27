package com.retailapp.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import com.aventstack.extentreports.Status;
import com.retail.base.BaseTest;
import com.retail.base.Session;

public class LoginTest extends BaseTest{

//Earlier we use to hardcode the values, now we will pass them from xls sheet	
// 	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String, String> data){
		
		
		System.out.println(data.get("Username"));  // from excel sheet
		System.out.println(data.get("Password"));
		
		String u = data.get("Username");
		String p = data.get("Password");
        Session s = new Session();		
		s.setUsername(u);                          // set values n pass it to request
		s.setPassword(p);
		
		Response resp = given().filter(new RequestLoggingFilter(requestCapture)).contentType(ContentType.JSON).log().all().when().body(s).post();
		
		Sessionid =resp.header("sessionid"); // we have collected sessionid from header
		System.out.println("Session id is "+ Sessionid);
		
		try {
			addReqLinkToReport("Login request1", "LoginRequest-"+iteration, requestWriter.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		test.log(Status.INFO, resp.prettyPrint());
		
	}

}
