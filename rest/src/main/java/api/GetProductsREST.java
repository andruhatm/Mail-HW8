package api;

import db.Connect;
import json.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Path("/getProducts")
public class GetProductsREST {

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(){

		List<Product> list = new ArrayList<>();

		Connection connection = Connect.connect();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from \"products\"");
			while (rs.next()){
				list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.ok(list).build();

	}
}
