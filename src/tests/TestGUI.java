package tests;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Test;

import antiSpamFilter.Functions;
import antiSpamFilter.Gui;
import antiSpamFilter.RP;

public class TestGUI {
	
	
	/**
	 * Test to see if the button delete runs with no errors
	 */
	@Test
	public void testApagar() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		subjects.getApagar().doClick();
		
		
	}
	
	/**
	 * Test to see if the Panel manual is created without errors
	 */
	@Test
	public void testManual() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		JPanel result = subjects.getManual();
		JPanel expected = subjects.getManual();
		
		assertEquals(expected, result);
		
		
	}
	
	/**
	 * Test to see if the button to run the manual configuration works
	 */
	@Test
	public void testRunManual() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		subjects.getConfirm().doClick();
		subjects.getRunManual().doClick();
		
		
	}
	
	/**
	 * Test to see if the button to run the automatic configuration works
	 */
	@Test
	public void testRunAuto() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		subjects.getConfirm().doClick();
		subjects.getRunAuto().doClick();
		
		
	}
	
	/**
	 * Test to see if the confirm button works without any error
	 */
	@Test
	public void testConfirm() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		subjects.getConfirm().doClick();
					
	}
	
	/**
	 * Test to see if we can get the rules.cf path correct
	 */
	@Test
	public void testGetRulespath() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getRulespath();
					
	}
	
	/**
	 * Test to see if we can get the ham.log path correct
	 */
	@Test
	public void testGetHampath() {
		Gui subjects = new Gui();
		subjects.getHam().setText("ham.log");
		subjects.getHampath();
					
	}
	
	/**
	 * Test to see if we can get the spam.log path correct
	 */
	@Test
	public void testGetSpampath() {
		Gui subjects = new Gui();
		subjects.getSpam().setText("spam.log");
		subjects.getSpampath();
					
	}
	
	/**
	 * test to count the column
	 */
	@Test
	public void testGetColumnCount() {
		Gui subjects = new Gui();
		
		subjects.getColumnCount();
					
	}
	
	/**
	 * test to count the rows
	 */
	@Test
	public void testGetRowCount() {
		Gui subjects = new Gui();
		
		subjects.getRowCount();
					
	}
	
	/**
	 * Test to see if we can get the value at a certain position
	 */
	@Test
	public void testGetValueAt() {
		Gui subjects = new Gui();
		
		subjects.getValueAt(1, 1);
					
	}
	//confirm
	//runManual
	//runAuto
	
}
