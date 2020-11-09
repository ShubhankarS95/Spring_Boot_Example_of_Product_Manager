package entities;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable {

	@SerializedName("id")
	private Integer id;
	
	@SerializedName("name")
	private String	name;
	
	@SerializedName("price")
	private double price;
	
	@SerializedName("description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
