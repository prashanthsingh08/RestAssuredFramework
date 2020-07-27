package com.retailapp.tests;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.retail.base.BaseTest;
import com.retail.base.Category;

import static io.restassured.RestAssured.given;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class AddCategory extends BaseTest {

	@Test(dataProvider="getData")
	public void addCat(Hashtable<String, String> data){
		
		System.out.println(data.get("CategoryName"));
		String catname = data.get("CategoryName");
		Category Cname = new Category();
		Cname.setCategoryname(catname);
		
		
		Response resp =given().contentType(ContentType.JSON).headers("sessionid","Sessionid").log().all().when().body(Cname).post();
		resp.prettyPrint();		
	}	
}
