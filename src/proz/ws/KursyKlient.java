package proz.ws;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class KursyKlient {
	/*public static void main(String[] args) {
		KursyKlient kk = new KursyKlient();
		kk.pobierzKursy("A","GBP","20");
	}*/
	public static String pobierzKursy (String table, String code, String topCount ) {

		Client client = ClientBuilder.newClient();

		URI uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount + "/").build();

		WebTarget webTarget = client.target(uri);
		//webTarget = webTarget.path(table).path(code).path(topCount);
		String response = webTarget.request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString();
		String xmlAnswer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		
		System.out.println(xmlAnswer);
		return xmlAnswer;
	}
}
