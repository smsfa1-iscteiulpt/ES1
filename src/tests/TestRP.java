package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.RP;

public class TestRP {
	
	/**
	 * Test to see if we could get the rule as we expected
	 */
	@Test
	public void testGetRegra() {
		RP rp = new RP ("R1", 3.0);
		
		String expected = rp.getRegra();
		
		assertEquals("R1", expected);
		
	}
	
	/**
	 * Test to see if we could get the weight of a rule as we expected
	 */
	@Test
	public void testGetPeso() {
		RP rp = new RP ("R1", 3.0);
		
		Double expected = rp.getPeso();
		Double result = 3.0;
		
		assertEquals(result, expected);
		
	}
	
	/**
	 * Test to see if we could set a rule with no errors
	 */
	@Test
	public void testSetRegra() {
		RP rp = new RP ("R1", 3.0);
		
		rp.setRegra("R2");
		
		assertNotEquals("R1", rp.getRegra());
		
	}
	
	/**
	 * Test to see if we could set a weight with no errors
	 */
	@Test
	public void testSetPeso() {
		RP rp = new RP ("R1", 3.0);
		
		rp.setPeso(7.0);
		Double expected = rp.getPeso();
		Double result = 3.0;
		
		assertNotEquals(result, expected);
		
	}
	
	/**
	 * Test to see if we could get the vector of a rule
	 */
	@Test
	public void testGetVector() {
		RP rp = new RP ("R1", 3.0);
		RP rp2 = new RP ("R2", 7.0);
		
		Object[] expected = rp.getVector();
		Object[] result = rp2.getVector();
		
		assertNotEquals(result, expected);
		
	}

}
