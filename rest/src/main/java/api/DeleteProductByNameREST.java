package api;

import db.Connect;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/delete")
public class DeleteProductByNameREST {

	@POST
	@Path("/{name}")
	public Response deleteByName(@PathParam("name")String name){
		Connection connection = Connect.connect();

		int rows =0;
		try {
			Statement	statement = connection.createStatement();
			rows = statement.executeUpdate("DELETE FROM products WHERE name = "+ name );
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
