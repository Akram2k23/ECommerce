package com.ecom.controller.model;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.entities.Ent_Category;

public class Model_CreateProductRequest {
	
	private String name;
	
	private double price;
	
	private String description;
	
	private MultipartFile image;
	
	private int addedBy;
	
	private Ent_Category category;

	public Model_CreateProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model_CreateProductRequest(String name, double price, String description, MultipartFile image, int addedBy,
			Ent_Category category) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.addedBy = addedBy;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	public Ent_Category getCategory() {
		return category;
	}

	public void setCategory(Ent_Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" +
                "name=" + name +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", addedBy='" + addedBy + '\'' +
                ", category='" + category + '\'' +
                '}';
	}
	
	

}
