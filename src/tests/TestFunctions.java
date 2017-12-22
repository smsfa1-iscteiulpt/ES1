package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	/* String NSGAII_2 = "jUnitTests/TestFiles/NSGAII.rs"; */

	/**
	 * Test to see if we could get the rules as we expected
	 */
	@Test
	public void testGetRules() {

		RP[] result = Functions.getRules(rulesExample);
		RP[] expected = Functions.getRules(rules2Example);
		Assert.assertNotEquals(expected, result);

	}

	/**
	 * Test to see if the read automatic function works
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadAutomatic() throws IOException {
		String[] result = Functions.readAutomatic();

		String[] expected = new String[3];

		Double media = 0.0;
		int control = 0;
		ArrayList<Integer> fp = new ArrayList<Integer>();
		ArrayList<Integer> fn = new ArrayList<Integer>();

		String line = "";

		BufferedReader in = new BufferedReader(
				new FileReader("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rf"));
		while ((line = in.readLine()) != null) {
			String fx[] = line.split(" ");
			fp.add((int) Double.parseDouble(fx[0]));
			fn.add((int) Double.parseDouble(fx[1]));
		}
		in.close();

		Double min = (double) (fp.get(0) + fn.get(0));
		for (int i = 0; i < fp.size(); i++) {
			media = (double) (fp.get(i) + fn.get(i));
			if (media < min) {
				min = media;
				control = i;
			}

		}
		expected[0] = fp.get(control).toString();
		expected[1] = fn.get(control).toString();
		expected[2] = "" + control;

		assertArrayEquals(expected, result);

	}

	/**
	 * Test to see if the function readConfig works as expected
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@Test
	public void testReadConfig() throws NumberFormatException, IOException {

		
		RP[] rules = Functions.getRules("rules.cf");
		int i = 0;
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rs"));
				while ((line = in.readLine()) != null) {
					if(i == 0){
				    String fx[] = line.split(" ");
				    	for(int j = 0; j < fx.length;j++){
				    		rules[j].setPeso(Double.valueOf(fx[j]));
				    	}
					}
					i++;
					}
				in.close();
		} catch (IOException e) {}
		

		RP[] result = Functions.readConfig(0);

		
		assertEquals(result[0].getPeso(), rules[0].getPeso(),0);

	}

	/**
	 * Test to see if a file is saved
	 */
	@Test
	public void testSave() {
		Gui a = new Gui();
		Functions.save(a.getTabela1(), "jUnitTests/FilesExample/saveExample.log");
		File file = new File("jUnitTests/FilesExample/saveExample.log");
		assertTrue(file.exists());
	}

	/**
	 * Test to see if the FN function works like we expected
	 */
	@Test
	public void testCountFN() {
		int result = Functions.Fn(Functions.getRules("rules.cf"), "ham.log");
		assertEquals(695, result);

	}

	/**
	 * Test to see if the FN function works like we expected
	 */
	@Test
	public void testCountFN_Failure() {

		int result = Functions.Fn(Functions.getRules("rules.cf"), "ham.log");
		assertNotEquals(0, result);

	}

	/**
	 * Test to see if the FP function works like we expected
	 */
	@Test
	public void testCountFP() {

		int result = Functions.Fp(Functions.getRules("rules.cf"), "spam.log");
		assertEquals(0, result);

	}

	/**
	 * Test to see if the FP function works like we expected
	 */
	@Test
	public void testCountFP_Failure() {

		int result = Functions.Fp(Functions.getRules("rules.cf"), "spam.log");
		assertNotEquals(1, result);

	}

	/**
	 * Test to see if we could get the vector of the rules and it's weight that
	 * exist in the rules.cf
	 */
	@Test
	public void testGetVector() {
		Object[][] result = Functions.getVector(Functions.getRules("rules.cf"));
		Object[][] expected = Functions.getVector(Functions.getRules("rules1.cf"));
		assertNotEquals(expected, result);

	}

}
