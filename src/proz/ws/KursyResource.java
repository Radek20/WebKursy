package proz.ws;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("rates")
public class KursyResource {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String getJSON(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {

		return "aaa";
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public String getXML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		String average = process(table, code, topCount);
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<Average><Value>" + average + "</Value></Average>";
	}
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public String getText(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {

		return "ccc";
	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.TEXT_HTML)
	//@Produces({ MediaType.TEXT_HTML })
	public String getHtml(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		String resp = KursyKlient.pobierzKursy(table, code, topCount);
		Kursy kursy=null;
		try {
			kursy = unMarshaling(resp);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double costam=kursy.countAverage();
		return Double.toString(costam);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello";
	}
	
	private static Kursy unMarshaling(String xmlAnswer) throws JAXBException
	{
		Kursy kursy = JAXB.unmarshal(new StringReader(xmlAnswer), Kursy.class);
	    
	    return kursy;
	}
	
	private static String process(String table, String code, String topCount) {
		String resp = KursyKlient.pobierzKursy(table, code, topCount);
		Kursy kursy=null;
		try {
			kursy = unMarshaling(resp);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double average=kursy.countAverage();
		return Double.toString(average);
	}
}