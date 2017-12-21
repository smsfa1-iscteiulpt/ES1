package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.RP;

public class TestRP {

	@Test
	public void testGetRegra() {
		RP rp = new RP ("R1", 3.0);
		
		String expected = rp.getRegra();
		
		assertEquals("R2", expected);
		
	}
	
	@Test
	public void testGetPeso() {
		RP rp = new RP ("R1", 3.0);
		
		Double expected = rp.getPeso();
		Double result = 7.0;
		
		assertEquals(result, expected);
		
	}
	
	@Test
	public void testSetRegra() {
		RP rp = new RP ("R1", 3.0);
		
		rp.setRegra("R2");
		
		assertEquals("R1", rp.getRegra());
		
	}
	
	@Test
	public void testSetPeso() {
		RP rp = new RP ("R1", 3.0);
		
		rp.setPeso(7.0);
		Double expected = rp.getPeso();
		Double result = 3.0;
		
		assertEquals(result, expected);
		
	}
	
	@Test
	public void testGetVector() {
		RP rp = new RP ("R1", 3.0);
		RP rp2 = new RP ("R2", 7.0);
		
		Object[] expected = rp.getVector();
		Object[] result = rp2.getVector();
		
		assertArrayEquals(result, expected);
		
	}

}
