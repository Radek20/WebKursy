package proz.ws;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("rates")
public class KursyResource {
	/*
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public String getXML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		System.out.println("GET!!!!"+table+"!"+code+"!"+topCount);
		Kursy kursy = pobierzKursy(table,code,topCount);
		kursy.getDane();
		return "aaa";
	}

	@GET
	@Produces({ MediaType.TEXT_XML })
	public String getHTML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		System.out.println("GET xml!!!!"+table+"!"+code+"!"+topCount);
		Kursy kursy = pobierzKursy(table,code,topCount);
		kursy.getDane();
		return "bbb";
	}
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String getText(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		System.out.println("GET plain!!!!"+table+"!"+code+"!"+topCount);
		Kursy kursy = pobierzKursy(table,code,topCount);
		kursy.getDane();
		return "ccc";
	}
*/
	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces(MediaType.TEXT_HTML)
	//@Produces({ MediaType.TEXT_HTML })
	public String getHtml(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		//System.out.println("GET plain!!!!"+table+"!"+code+"!"+topCount);
		//Kursy kursy = pobierzKursy(table,code,topCount);
		//kursy.getDane();
		//String resp = KursyKlient.pobierzKursy(table, code, topCount);
		Kursy kursy=null;
		try {
			kursy = unMarshaling(table,code,topCount);
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
	
	private static Kursy unMarshaling(String table, String code, String topCount ) throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Kursy.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    URL url=null;
		try {
			url = new URL("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount + "/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    
	    if(url!=null) {
	    	Kursy emps = (Kursy) jaxbUnmarshaller.unmarshal( url );
	    	return emps;
	    }
	    
	    return null;
	}
}