package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/add")
public final class ProductAddingFormREST {

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
						"<form action=\"addProduct\" method=\"POST\">\n" +
						"  Product Name: <input name=\"name\"/>\n" +
						"  <br><br>\n" +
						"  Quantity: <input name=\"quantity\" />\n" +
						"  <br><br>\n" +
						"		Company: <input name=\"company\" />\n" +
						"  <br><br>\n" +
						"  <input type=\"submit\" value=\"Submit\" />\n" +
						"</form>\n" +
						"</body>\n" +
						"</html>";
	}
}
