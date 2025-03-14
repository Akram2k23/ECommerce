package com.ecom.controller.model;

import com.ecom.entities.Ent_Cart;
import com.ecom.entities.Ent_Product;

public class Model_AddToCartRequest {
	
	private long userId;
	
	private Ent_Cart cart;
	
	private Ent_Product product;
	
	private int quantity;

	public Model_AddToCartRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Ent_Cart getCart() {
		return cart;
	}

	public void setCart(Ent_Cart cart) {
		this.cart = cart;
	}

	public Ent_Product getProduct() {
		return product;
	}

	public void setProduct(Ent_Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Model_AddToCartRequest [userId=" + userId + ", cart=" + cart + ", product=" + product + ", quantity="
				+ quantity + "]";
	}

}
