package api;

import db.Connect;
import json.Product;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("/addProduct")
public class ProductAddingREST {

	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response addingProduct(@FormParam("name") String name,
																@FormParam("quantity") Integer quantity,
																@FormParam("company") String company){

		Product product = new Product(name,quantity,company);

		Connection connection = Connect.connect();
		try{
			PreparedStatement statement = connection.prepareStatement("insert into \"products\"(name,quantity,company) values (?,?,?)");
			statement.setString(1,product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setString(3, product.getCompany());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.ok(new Product(name, quantity,company))
						.header(HttpHeaders.CACHE_CONTROL,"no-cache").build();
	}

}
