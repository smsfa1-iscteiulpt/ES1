package tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JTable;

import org.junit.Assert;
import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.Functions;
import antiSpamFilter.Gui;
import antiSpamFilter.RP;

public class TestFunctions {
	
	String rulesExample = "jUnitTests/FilesExample/rulesExample.cf";
	String rules2Example = "jUnitTests/FilesExample/rules2Example.cf";
	String spamExample = "jUnitTests/FilesExample/spamExample.log";
	String hamExample = "jUnitTests/FilesExample/hamExample.log";
	/*String NSGAII_2 = "jUnitTests/TestFiles/NSGAII.rs";*/
	
	@Test
	public void testGetRules() {
		
		RP[] result = Functions.getRules(rulesExample);
		RP[] expected = Functions.getRules(rules2Example);
		Assert.assertNotEquals(expected, result);
		
	}
	
	@Test
	public void testReadAutomatic() {
		String[] result = Functions.readAutomatic();
		
		String[] expected = new String [3];
		expected[0]="2";
		expected[1]="3";
		expected[2]="4";
		
		assertArrayEquals(expected, result);
		
	}
	
	@Test
	public void testReadConfig() {
		
		RP[] result = Functions.readConfig(1);
		RP[] expected = Functions.readConfig(0);
		/*String[] expected = new String [4];
		expected[0]="A";
		expected[1]="B";
		expected[2]="D";
		expected[3]="Z";*/
		
		assertArrayEquals(expected, result);
		
	}
	
//	DA ERRO
	@Test
	public void testSave() {
		Gui a = new Gui();
		Functions.save(a.getTabela1(), "jUnitTests/FilesExample/saveExample.log");
		File file = new File("jUnitTests/FilesExample/saveExample.log");
		assertTrue(file.exists());
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFN() {
		int result = Functions.Fn(Functions.getRules("rules.cf"), "ham.log");
		assertEquals(3, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFN_Failure() {
		
		int result = Functions.Fn(Functions.getRules("rules.cf"), "ham.log");
		assertEquals(1, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFP() {
		
		int result = Functions.Fp(Functions.getRules("rules.cf"), "spam.log");
		assertEquals(0, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFP_Failure() {
		
		int result = Functions.Fp(Functions.getRules("rules.cf"), "spam.log");
		assertEquals(1, result);
		
	}
	
	@Test
	public void testGetVector() {
		Object[][] result = Functions.getVector(Functions.getRules(rulesExample));
		Object[][] expected = Functions.getVector(Functions.getRules(rules2Example));
		assertArrayEquals(expected, result);
		
	}
	
}
