package com.retailapp.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.retail.base.BaseTest;
import com.retail.base.CategoryList;
import com.retail.base.Product;
import com.retail.base.ProductDetails;

import static io.restassured.RestAssured.given;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class AddProduct extends BaseTest {
	
	//Category car is prepared, now add products to that category
	
	@Test
	public void addCarProducts(){
		
		//doLogin("admin","whizdom"); // login is done, sessionid also collected
		
		ProductDetails prod = new ProductDetails();
		
		Product marrazzo = new Product();
		marrazzo.setName("Marazzo");
		marrazzo.setQuantity("15");
		marrazzo.setPrice("5000");
		System.out.println("Marazzo created");
		
		
		Product creta = new Product();
		creta.setName("Creta");
		creta.setQuantity("15");
		creta.setPrice("6000");
		System.out.println("creta created");
		
		Product seltos = new Product();
		seltos.setName("Seltos");
		seltos.setQuantity("15");
		seltos.setPrice("7000");
		System.out.println("seltos created");
		
		//CategoryList catlist = prod.addCategory("Cars");
		CategoryList catlist = new CategoryList();
		catlist.addProduct(marrazzo);
		catlist.addProduct(creta);
		catlist.addProduct(seltos);
		
		
		System.out.println("Adding products to catlist");
		//----------------------------
		

		Product activa = new Product();
		marrazzo.setName("activa");
		marrazzo.setQuantity("15");
		marrazzo.setPrice("5000");
		System.out.println("activa created");
		
		Product cbr = new Product();
		creta.setName("cbr");
		creta.setQuantity("15");
		creta.setPrice("6000");
		System.out.println("cbr created");
		
		 catlist =prod.addCategory("bikes");
		 catlist.addProduct(activa);
		 catlist.addProduct(cbr);
		 System.out.println("adding bike products to bikes category");
		 
		 Map<String, List<Product>> cat = catlist.addProduct(activa);
		 
		 
		 prod.setProductDetails(cat);
		 System.out.println("Finally everthing is added to cat");
			
		Response resp = given().contentType(ContentType.JSON).headers("sessionid","Sessionid").log().all().when().body(prod).post("product/add");
		resp.prettyPrint();
		
	}
	
	
	
	
	
	
	
}
