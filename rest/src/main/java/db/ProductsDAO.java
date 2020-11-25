package db;

import json.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO implements DAO<Product> {

	Connection connection = Connect.connect();

	@Override
	public List<Product> getAll() {
		List<Product> list = new ArrayList<>();

		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from \"products\"");
			while (rs.next()){
				list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> getByCompany(String company) {
		List<Product> list = new ArrayList<>();

		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from \"products\" where \"company\" = '"+company+"'");
			while (rs.next()){
				list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(Product entity) {
		try{
			PreparedStatement statement = connection.prepareStatement("insert into \"products\"(name,quantity,company) values (?,?,?)");
			statement.setString(1,entity.getName());
			statement.setInt(2, entity.getQuantity());
			statement.setString(3, entity.getCompany());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(false);
			return false;
		}
		System.out.println(true);
		return true;
	}

	@Override
	public boolean delete(String name) {
		int rows =0;
		try {
			Statement	statement = connection.createStatement();
			rows = statement.executeUpdate("DELETE FROM products WHERE \"products\".\"name\" = '"+ name+"'" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows != 0;
	}
}
