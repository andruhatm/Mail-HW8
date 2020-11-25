package api;

import json.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/addProduct")
public class ProductAddingREST {

	ProductService service;

	public ProductAddingREST() {
		service = new ProductService();
	}

	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response addingProduct(@FormParam("name") String name,
																@FormParam("quantity") Integer quantity,
																@FormParam("company") String company
	){
		if(service.addProduct(name,quantity,company)){
			//return Response.ok(new Product(name, quantity,company)).header(HttpHeaders.CACHE_CONTROL,"no-cache").build();
			return Response.seeOther(URI.create("/getProducts")).build();
		}
		else {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

	}
}
