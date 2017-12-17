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
		
		JPanel result = subjects.getManual();
		JPanel expected = subjects.getManual();
		
		assertEquals(expected, result);
		
		
	}
	
	@Test
	public void testRunManual() {
		Gui subjects = new Gui();
		
		subjects.getRunManual().getActionListeners()[0].actionPerformed(null);
		
		
	}
	
	@Test
	public void testRunAuto() {
		Gui subjects = new Gui();
		
		subjects.getRunAuto().getActionListeners()[0].actionPerformed(null);
		
		
	}
	
	@Test
	public void testConfirm() {
		Gui subjects = new Gui();
		
		subjects.getConfirm().getActionListeners()[0].actionPerformed(null);
				
		
		
		
	}
	//confirm
	//runManual
	//runAuto
	
}
