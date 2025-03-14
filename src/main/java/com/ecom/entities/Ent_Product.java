package com.ecom.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Ent_Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long proId;
	
	@Column(name = "name")
	private String name;          //  name price description image addedBy category = name price description image
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "added_by")
	private int addedBy;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@JoinColumn(name ="category_id")
	@ManyToOne
	private Ent_Category category;

	public Ent_Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ent_Product(long proId, String name, double price, String description, String image, int addedBy,
			Date createdOn, Date updatedOn, Ent_Category category) {
		super();
		this.proId = proId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.addedBy = addedBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.category = category;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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
                "proId=" + proId +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description +
                ", addedBy=" + addedBy +
                ", image=" + image +
                ", category=" + category +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
	
	

}
