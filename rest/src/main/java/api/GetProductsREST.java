package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/getProducts")
public class GetProductsREST {

	ProductService service;

	public GetProductsREST() {
		service = new ProductService();
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(){
		return Response.ok(service.getAll())
						.header(HttpHeaders.CACHE_CONTROL, "no-cache")
						.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{company}")
	public Response getByCompany(@PathParam("company")String company){
		return Response.ok(service.getByCompany(company)).build();
	}
}
