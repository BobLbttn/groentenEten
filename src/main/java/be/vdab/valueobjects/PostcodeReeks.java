package be.vdab.valueobjects;


import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {

	@Postcode
	private Integer vanpostcode; 
	@Postcode
	private Integer totpostcode;

	public Integer getVanpostcode() {
		return vanpostcode;
	}


	public void setVanpostcode( Integer vanpostcode) {
		this.vanpostcode = vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setTotpostcode(Integer totpostcode) {
		this.totpostcode = totpostcode;
	}

	public boolean bevat(Integer postcode) {
		return postcode >= vanpostcode && postcode <= totpostcode;
	}

}
