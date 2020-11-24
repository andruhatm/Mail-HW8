package api;

import db.Connect;
import json.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Path("/getProductsByCompany")
public class GetProductsByCompanyREST {

	@GET
	@Path("/{company}")
	public Response getByCompany(@PathParam("company")String company){

		List<Product> list = new ArrayList<>();

		Connection connection = Connect.connect();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from \"products\" where \"company\" = "+company);
			while (rs.next()){
				list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.ok(list).build();

	}
}
