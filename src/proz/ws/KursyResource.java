package proz.ws;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("rates/{table}/{code}/{topCount}")
public class KursyResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Kursy getXML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		
		Kursy kursy = pobierzKursy(table,code,topCount);
		kursy.getDane();
		return null;
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public Kursy getHTML() {
		return null;
	}

	
	private Kursy pobierzKursy (String table, String code, String topCount ) {
		
		// utworzenie obiektu klasy Client
		Client client = ClientBuilder.newClient();
		// konstruowanie adresu URI
		URI uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount + "/").build();
		// albo URI uri = URI.create(http://localhost:8080/atj);
		// reprezentacja zasobu
		WebTarget webTarget = client.target(uri);
		webTarget = webTarget.path(table).path(code).path(topCount);
		// wywo³anie ¿¹dania i odebranie odpowiedzi
		//String response = webTarget.request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString();
		//String plainAnswer = webTarget.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String xmlAnswer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		//String htmlAnswer = webTarget.request().accept(MediaType.TEXT_HTML).get(String.class);
		Kursy kursy= new Kursy(xmlAnswer);
		
		return kursy;
	
	}
	
}