package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.Functions;

public class tests {

	@Test
	public void testCountFN() {
		Functions subjects = new Functions();
		
		int result = subjects.Fn(subjects.getRules("rules.cf"), "ham.log");
		assertEquals(695, result);
		
	}
	
	@Test
	public void testCountFN_Failure() {
		Functions subjects = new Functions();
		
		int result = subjects.Fn(subjects.getRules("rules.cf"), "ham.log");
		assertEquals(0, result);
		
	}
	
	@Test
	public void testCountFP() {
		Functions subjects = new Functions();
		
		int result = subjects.Fp(subjects.getRules("rules.cf"), "spam.log");
		assertEquals(0, result);
		
	}
	
	@Test
	public void testCountFP_Failure() {
		Functions subjects = new Functions();
		
		int result = subjects.Fp(subjects.getRules("rules.cf"), "spam.log");
		assertEquals(695, result);
		
	}
	
	
}
