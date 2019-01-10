package proz.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Kursy_STARE {
	private String dane;
	private Double srednia;
	private String mime;
	
	public Double getSrednia() {
		return srednia;
	}

	public void setSrednia(Double srednia) {
		this.srednia = srednia;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public void setDane(String dane) {
		this.dane = dane;
	}

	public Kursy_STARE(String xmlAnswer){
		dane=xmlAnswer;
	}
	
	public void getDane() {
		System.out.println("WYPISALO SIE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(dane);
	}
	
	private double policzSrednia() {
		double suma=0;
		double ile=0;
		
		return
	}
}

