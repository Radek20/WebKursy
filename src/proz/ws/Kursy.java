package proz.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Kursy {
	private String dane;
	
	public Kursy(String xmlAnswer){
		dane=xmlAnswer;
	}
	
	public void getDane() {
		System.out.println(dane);
	}

}

