package com.ecom.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.controller.model.Model_AddToCartRequest;
import com.ecom.controller.model.Model_CreateCategoryRequest;
import com.ecom.controller.model.Model_CreateProductRequest;
import com.ecom.controller.model.Model_LoginRequest;
import com.ecom.controller.model.Model_SaveUserRequest;
import com.ecom.entities.Ent_Category;
import com.ecom.entities.Ent_Product;
import com.ecom.entities.Ent_User;

public interface MyService_Inf {
	
	void saveAdmin(Model_SaveUserRequest e1) throws ParseException;
	
	 void saveUser(Model_SaveUserRequest e1) throws ParseException;
	 
	 Optional<Ent_User> getUserById(long id);
	 
	 void updateUser();
	 
	 void deleteUser(long id);
	 
	 Ent_User loginAdmin(Model_LoginRequest e1);
	 
	 Ent_User loginUser(Model_LoginRequest e1);
	 
	 String createCategory(Model_CreateCategoryRequest e1) throws ParseException;
	 
	 String createProduct(Model_CreateProductRequest e1) throws ParseException;
	 
	 Map<String, Object> getAllCategory();
	 
	 Map<String, List<Map<String, Object>>> getAllProducts();
	 
	 Map<String, Object> getProductById(long id);
	 
	 String addToCart(Model_AddToCartRequest e1) throws ParseException;

}
