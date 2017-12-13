package antiSpamFilter;

public class RP {
	private String regra;
	private double peso;
	
	public RP(String regra, double peso){
		this.regra=regra;
		this.peso=peso;
	}
	
	public String getRegra(){
		return this.regra;
	}
	
	public double getPeso(){
		return this.peso;
	}

	public void setRegra(String r){
		this.regra=r;
	}
	
	public void setPeso(double p){
		this.peso=p;
	}
	
	public Object[] getVector(){
		Object[] x ={this.regra,this.peso} ;
		return x;
	}
	
}
