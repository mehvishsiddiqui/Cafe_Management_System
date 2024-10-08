package com.inn.cafe.wrapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductWrapper {
	
	Integer id;
	
	String name;
	
	String description;
	
	Integer price;
  
	String Status;
	
	Integer categoryId;
	
	String categoryName;
	
	

	public ProductWrapper(Integer id, String name, String description, Integer price, String status, Integer categoryId,
			String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		Status = status;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	
	

	public ProductWrapper() {
		super();
	}


	


	public ProductWrapper(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	


	public ProductWrapper(Integer id, String name, String description, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
