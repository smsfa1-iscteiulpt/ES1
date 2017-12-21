package tests;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Test;

import antiSpamFilter.Functions;
import antiSpamFilter.Gui;
import antiSpamFilter.RP;

public class TestGUI {
	
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
	
	@Test
	public void testRunManual() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		subjects.getRunManual().doClick();
		
		
	}
	
	@Test
	public void testRunAuto() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		
		subjects.getRunAuto().doClick();
		
		
	}
	
	@Test
	public void testConfirm() {
		Gui subjects = new Gui();
		subjects.getRegras().setText("rules.cf");
		subjects.getSpam().setText("spam.log");
		subjects.getHam().setText("ham.log");
		subjects.getConfirm().doClick();
					
	}
	//confirm
	//runManual
	//runAuto
	
}
