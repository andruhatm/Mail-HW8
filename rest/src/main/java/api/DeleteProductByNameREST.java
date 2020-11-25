package api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/delete")
public class DeleteProductByNameREST {

	ProductService service;

	public DeleteProductByNameREST() {
		service = new ProductService();
	}

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

		if(service.deleteProduct(name)){
			return Response.seeOther(URI.create("/getProducts")).build();
		}
		else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
			//return Response.status(Response.Status.NOT_FOUND).entity("Product not found for name:"+ name ).build();
		}

	}
}
