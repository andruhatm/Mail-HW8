package api;

import db.ProductsDAO;
import json.Product;

import java.util.List;

public class ProductService {

	private final ProductsDAO dao;

	public ProductService() {
		dao = new ProductsDAO();
	}

	public List<Product> getAll(){
		return dao.getAll();
	}

	public List<Product> getByCompany(String company){
		return dao.getByCompany(company);
	}

	public boolean addProduct(String name,Integer quantity,String company){
		return dao.add(new Product(name,quantity,company));
	}

	public boolean deleteProduct(String name){
		return dao.delete(name);
	}
}
