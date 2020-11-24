package api;

import db.Connect;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/delete")
public class DeleteProductByNameREST {

	@Path("/")
	@GET
	@Produces("text/html; qs=0.1")
	public String getForm() {
		return "<!DOCTYPE html>\n" +
						"<html>\n" +
						"<head>\n" +
						"  <meta charset=\"UTF-8\">\n" +
						"  <title>Adding Form</title>\n" +
						"</head>\n" +
						"<body>\n" +
						"<form action=\"delete/deleteProduct\" method=\"POST\">\n" +
						"  Delete product : <input name=\"name\"/>\n" +
						"  <br><br>\n" +
						"  <input type=\"submit\" value=\"Submit\" />\n" +
						"</form>\n" +
						"</body>\n" +
						"</html>";
	}

	@POST
	@Path("/deleteProduct")
	public Response deleteByName(@FormParam("name") String name){
		Connection connection = Connect.connect();

		int rows =0;
		try {
			Statement	statement = connection.createStatement();
			rows = statement.executeUpdate("DELETE FROM products WHERE \"products\".\"name\" = '"+ name+"'" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rows!=0){
			return Response.ok().build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).entity("Product not found for name:"+ name ).build();
		}

	}
}
