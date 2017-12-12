package antiSpamFilter;

public class RP {
	private String regra;
	private int peso;
	
	public RP(String regra, int peso){
		this.regra=regra;
		this.peso=peso;
	}
	
	public String getRegra(){
		return this.regra;
	}
	
	public int getPeso(){
		return this.peso;
	}

	public void setRegra(String r){
		this.regra=r;
	}
	
	public void setPeso(int p){
		this.peso=p;
	}
	
	public Object[] getVector(){
		Object[] x ={this.regra,this.peso} ;
		return x;
	}
	
}
