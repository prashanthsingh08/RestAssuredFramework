package com.retail.base;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ProductDetails {
	
	Map<String, List<Product>> productDetails;
    CategoryList prodlist;
    
    public CategoryList addCategory(String catName){  //catname has Cars
    	
    	if(prodlist==null)
    		prodlist = new CategoryList();
    	    prodlist.createNewList(catName);
    	    return prodlist;
    }

	public Map<String, List<Product>> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Map<String, List<Product>> productDetails) {
		this.productDetails = productDetails;
	}

	public CategoryList getCatlist() {
		return prodlist;
	}

	public void setCatlist(CategoryList catlist) {
		this.prodlist = catlist;
	}
    
    
}
