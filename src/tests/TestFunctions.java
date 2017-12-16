package tests;

import static org.junit.Assert.*;

import javax.swing.JTable;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.Functions;
import antiSpamFilter.RP;

public class TestFunctions {
	
	String rulesExample = "jUnitTests/FilesExample/rulesExample.cf";
	String rules2Example = "jUnitTests/FilesExample/rules2Example.cf";
	String spamExample = "jUnitTests/FilesExample/spamExample.log";
	String hamExample = "jUnitTests/FilesExample/hamExample.log";
	/*String NSGAII_2 = "jUnitTests/TestFiles/NSGAII.rs";*/
	
	@Test
	public void testGetRules() {
		Functions subjects = new Functions();
		
		RP[] result = subjects.getRules(rulesExample);
		RP[] expected = subjects.getRules(rules2Example);
		assertArrayEquals(expected, result);
		
	}
	
	@Test
	public void testReadAutomatic() {
		Functions subjects = new Functions();
		
		String[] result = subjects.readAutomatic();
		
		String[] expected = new String [4];
		expected[0]="A";
		expected[1]="B";
		expected[2]="D";
		expected[3]="Z";
		
		assertArrayEquals(expected, result);
		
	}
	
	@Test
	public void testReadConfig() {
		Functions subjects = new Functions();
		
		RP[] result = subjects.readConfig(1);
		RP[] expected = subjects.readConfig(0);
		/*String[] expected = new String [4];
		expected[0]="A";
		expected[1]="B";
		expected[2]="D";
		expected[3]="Z";*/
		
		assertArrayEquals(expected, result);
		
	}
	
	@Test
	public void testSaveManually() {
		
	}
	
	@Test
	public void testSaveAutomatically() {
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFN() {
		Functions subjects = new Functions();
		
		int result = subjects.Fn(subjects.getRules(rulesExample), hamExample);
		assertEquals(3, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFN_Failure() {
		Functions subjects = new Functions();
		
		int result = subjects.Fn(subjects.getRules(rulesExample), hamExample);
		assertEquals(0, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFP() {
		Functions subjects = new Functions();
		
		int result = subjects.Fp(subjects.getRules(rulesExample), spamExample);
		assertEquals(0, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFP_Failure() {
		Functions subjects = new Functions();
		
		int result = subjects.Fp(subjects.getRules(rulesExample), spamExample);
		assertEquals(1, result);
		
	}
	
	@Test
	public void testGetVector() {
		Functions subjects = new Functions();
		
		Object[][] result = subjects.getVector(subjects.getRules(rulesExample));
		Object[][] expected = subjects.getVector(subjects.getRules(rules2Example));
		assertEquals(expected, result);
		
	}
	
}
