package proz.ws;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "Rate")
@XmlAccessorType (XmlAccessType.FIELD)
public class Kurs {
    private String No;
    private String EffectiveDate;
    private double Mid;
    
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}
	public double getMid() {
		return Mid;
	}
	public void setMid(double mid) {
		Mid = mid;
	}
    
}
