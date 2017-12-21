package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		expected[1]="4";
		expected[2]="1";
		
		assertArrayEquals(expected, result);
		
	}
	
	@Test
	public void testReadConfig() {
		
		RP[] result = Functions.readConfig(0);
		String line="";
		RP[] rules = Functions.getRules("rules.cf");
		try {
			BufferedReader in = new BufferedReader(new FileReader("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rs"));
				line=in.readLine();
				    String fx[] = line.split(" ");
				    	for(int j = 0; j < fx.length;j++){
				    		rules[j].setPeso(Double.valueOf(fx[j]));
				    	}
				in.close();
		} catch (IOException e) {}

		assertArrayEquals(rules, result);
		
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
		assertEquals(695, result);
		
	}
	
	//n esta a passar em certos pontos no codigo
	@Test
	public void testCountFN_Failure() {
		
		int result = Functions.Fn(Functions.getRules("rules.cf"), "ham.log");
		assertNotEquals(0, result);
		
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
		assertNotEquals(1, result);
		
	}
	
	@Test
	public void testGetVector() {
		Object[][] result = Functions.getVector(Functions.getRules("jUnitTests/FilesExample/rulesExample.cf"));
		Object[][] expected = Functions.getVector(Functions.getRules("jUnitTests/FilesExample/rules2Example.cf"));
		assertArrayEquals(expected, result);
		
	}
	
}
