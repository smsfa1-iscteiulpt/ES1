package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterProblem;

public class TestAntiSpamFilterProblem {
	
	String rulesExample = "jUnitTests/FilesExample/rulesExample.cf";
	String rules2Example = "jUnitTests/FilesExample/rules2Example.cf";
	String spamExample = "jUnitTests/FilesExample/spamExample.log";
	String hamExample = "jUnitTests/FilesExample/hamExample.log";
	
	@Test
	public void testAntiSpamFilterProblem() {
		AntiSpamFilterProblem result = new AntiSpamFilterProblem(rulesExample, hamExample, spamExample);
		AntiSpamFilterProblem expected = new AntiSpamFilterProblem(rules2Example, hamExample, spamExample);
		
		assertEquals(expected, result);
	}
	
	/*public void testEvaluate() {
		AntiSpamFilterProblem test1 = new AntiSpamFilterProblem(rulesExample, hamExample, spamExample);
		AntiSpamFilterProblem test2 = new AntiSpamFilterProblem(rules2Example, hamExample, spamExample);
		
		test1.evaluate();
		
		assertEquals(expected, result);
	}*/
	
}
