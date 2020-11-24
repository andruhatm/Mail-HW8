package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("company")
	private String company;
	@JsonProperty("quantity")
	private int quantity;

	public Product() {
	}

	@JsonCreator
	public Product(@JsonProperty(value = "name",required = true) String name,
								 @JsonProperty(value = "quantity",required = true) Integer quantity,
								 @JsonProperty(value = "company",required = true) String company
	) {
		this.name = name;
		this.quantity = quantity;
		this.company = company;
	}

	@JsonCreator
	public Product(@JsonProperty(value = "id",required = true) int id,
								 @JsonProperty(value = "name",required = true) String name,
								 @JsonProperty(value = "company",required = true) String company,
								 @JsonProperty(value = "quantity",required = true) int quantity
	) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}
}
