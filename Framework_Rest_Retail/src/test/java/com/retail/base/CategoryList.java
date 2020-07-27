package com.retail.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class CategoryList {
	
	
	Map<String,List<Product>> carsMap;  // Also has a list which we will be passing
    
	List<Product> actualCarList;       // we have to create the same list again 2nd property
    
    String carsCategoryName; //This will go inside the map as 1st Property
    
    public CategoryList(){
    	
    	if(carsMap==null)
    		carsMap = new HashMap<String, List<Product>>();
    }
    
    	public void createNewList(String carsListName){ // we are only creating Empty List
    	this.carsCategoryName=carsListName;
    	actualCarList = new ArrayList<Product>();
    	carsMap.put(carsCategoryName, actualCarList);
    	}
    
    	public Map<String, List<Product>> addProduct(Product p){ //here Product is added to the List
    	
    	actualCarList.add(p); // adds specified element to the end of the list
    	carsMap.put(carsCategoryName, actualCarList);//Associates the specified value with the specified key in this map
    	return carsMap;
    }
    
   
    
    
} 
