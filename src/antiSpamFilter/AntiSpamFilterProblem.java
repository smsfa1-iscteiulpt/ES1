package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rulesPath, hamPath, spamPath;
	
	
	  public AntiSpamFilterProblem(String rulesPath, String hamPath, String spamPath) {
	    this(335);
	    this.rulesPath = rulesPath;
	    this.hamPath = hamPath;
	    this.spamPath = spamPath;
	  }

	  public AntiSpamFilterProblem(Integer numberOfVariables) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(2);
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  public void evaluate(DoubleSolution solution){
		RP[] rules = Functions.getRules("rules.cf");
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }

	    for(int i = 0;i < rules.length;i++){
	    	rules[i].setPeso(x[i]);
	    }
	    
	    fx[0] = 0.0;
	    
		  fx[0] = Functions.Fp(rules, hamPath);
	    
	    
	    fx[1] = 0.0;
	   
	    	fx[1] = Functions.Fn(rules, spamPath);
	    

	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
	  }
	}
