package com.ecom.controller.model;

public class Model_CreateCategoryRequest {
	
	private String catName;
	
	private String catDescription;
	
	private int addedBy;

	public Model_CreateCategoryRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model_CreateCategoryRequest(String catName, String catDescription, int addedBy) {
		super();
		this.catName = catName;
		this.catDescription = catDescription;
		this.addedBy = addedBy;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDescription() {
		return catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}
	
	@Override
    public String toString() {
        return "Category{" +
                "catName=" + catName +
                ", catDescription='" + catDescription + '\'' +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }


}
