package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTable;

/**
 * Functions is the class that contains the functions that allow the application to get the rules,
 * that are used to see if a message is spam or ham or if its is false positive or false negative, to have their weights altered, to save 
 * that new alteration, and to count how many false positives and false negatives are in the spam.log and ham.log.
 *
 * @author Nuno Fialho EIC1 72910
 * @author Sandro Ferreira EIC1 72911
 * @author Duarte Pinto EIC1 73117
 */

public class Functions {

	/**
	 * Function that obtains the rules written in a file to an array.
	 * 
	 * @param path is the directory of the file
	 * @return rules that is an array with the name of the rules and it's weight
	 */
	public static Object[][] getRules(String path) {
		Object[][] rules = new Object[335][2];
		int i = 0;
		Scanner scanner;
		try {
			scanner = new Scanner(new File(path));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] line2 = line.split(" ");
				rules[i][0] = line2[0];
				if (line2.length > 1) {
					rules[i][1] = line2[1];
				} else {
					rules[i][1] = 0;
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return rules;

	}
	
	/**
	 * Function that saves the values of the rules in a file.
	 * 
	 * @param tabela1 is the table where the rules are
	 * @param m is what defines if we are saving the rules manually or automatically.
	 */
	public static void save(JTable tabela1, int m) {
		File f = null;
		int i = 1;
		String path="";
		//ensure the creation of files with sequential names
		if (m == 0) {
			path = "saved/regras_manual%02d.txt";
		} else {
			path = "saved/regras_automatica%02d.txt";
		}
		boolean n = true;
		while (n) {
			f = new File(String.format(path, i));
			if (!f.exists()) {
				break;
			}
			i++;
		}
		//write the rules and it's weight in a file
		try {
			PrintWriter print = new PrintWriter(String.format(path, i));
			for (int j = 0; j < tabela1.getRowCount(); j++) {
				if (tabela1.getValueAt(j, 1).equals(0)) {
					print.println((String) tabela1.getValueAt(j, 0));
				} else {
					print.println((String) tabela1.getValueAt(j, 0) + " " + (String) tabela1.getValueAt(j, 1));
				
				}
			}
			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function that counts the number of false negatives that exist in the ham.log file
	 * 
	 * @param rules are the rules to test the messages
	 * @param ham is the message that is being tested to see if it is false negative
	 * @return the number of false negatives messages are in the ham.log file
	 */
	public static int Fn(Object[][] rules, String spam){
		Scanner scanner;
		int score = 0;
		int fn = 0;
		try {
			scanner = new Scanner(new File(spam));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] line2 = line.split("	");
				for(int i=1;i<line2.length;i++){
					for(int j=0;j<rules.length;j++){
						if(rules[j][0].equals(line2[i])){
							score+=Integer.valueOf((String) rules[j][1]);
						}
					}
				}
				if(score<=5){
					fn+=1;
				}
				score=0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return fn;
	}
	
	/**
	 * Function that counts the number of false positives exist in the spam.log file
	 * 
	 * @param rules are the rules to test the messages
	 * @param spam is the message that is being tested to see if it is false positive
	 * @return the number of false positives messages are in the spam.log file
	 */
	public static int Fp(Object[][] rules, String ham){
		Scanner scanner;
		int score = 0;
		int fp = 0;
		try {
			scanner = new Scanner(new File(ham));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] line2 = line.split("	");
				for(int i=1;i<line2.length;i++){
					for(int j=0;j<rules.length;j++){
						if(rules[j][0].equals(line2[i])){
							score+=Integer.valueOf((String) rules[j][1]);
						}
					}
				}
				if(score>5){
					fp+=1;
				}
				score=0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return fp;
	}
}
