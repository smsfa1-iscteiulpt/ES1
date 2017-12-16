package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;



public class TestAntiSpamFilterAutomaticConfiguration {
	
	String rulesExample = "jUnitTests/FilesExample/rulesExample.cf";
	String rules2Example = "jUnitTests/FilesExample/rules2Example.cf";
	String spamExample = "jUnitTests/FilesExample/spamExample.log";
	String hamExample = "jUnitTests/FilesExample/hamExample.log";
	
	@Test
	public void testAntiSpamFilterAutomaticConfiguration() {
		AntiSpamFilterAutomaticConfiguration subjectA = new AntiSpamFilterAutomaticConfiguration();
		AntiSpamFilterAutomaticConfiguration subjectB = new AntiSpamFilterAutomaticConfiguration();		
		
	}

}
