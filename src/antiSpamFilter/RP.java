package antiSpamFilter;

/**
 * RP (Regra + Peso) is the class that initialize a rule and its weight.
 * It has the basic functions of an object, getRule(getRegra), getPeso(getWeight)
 * setRegra(setRule), setPeso(setWeight) and getVector
 *
 * @author Nuno Fialho EIC1 72910
 * @author Sandro Ferreira EIC1 72911
 * @author Duarte Pinto EIC1 73117
 */

public class RP {
	private String regra;
	private double peso;
	
	/**
	 * Creating a rule and it's weight
	 */
	public RP(String regra, double peso){
		this.regra=regra;
		this.peso=peso;
	}
	
	/**
	 * Function that get a rule
	 * @return the rule that we want
	 */
	public String getRegra(){
		return this.regra;
	}
	
	/**
	 * Function that get the weight of a rule
	 * @return the weight of a rule
	 */
	public double getPeso(){
		return this.peso;
	}
	
	/**
	 * Function that allows to set a rule
	 * @param r is the name of the rule
	 */
	public void setRegra(String r){
		this.regra=r;
	}
	
	/**
	 * Function that allows to set a weight of a rule
	 * @param p is a double and it's the weight that we want to put in a rule
	 */
	public void setPeso(double p){
		this.peso=p;
	}
	
	/**
	 * Function that unites the rule and it's weight into a vector
	 * @return the RP (rule + weight)
	 */
	public Object[] getVector(){
		Object[] x ={this.regra,this.peso} ;
		return x;
	}
	
}
