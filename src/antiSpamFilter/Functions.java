package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTable;

public class Functions {

	// Função para obter as regras escritas num ficheiro para um array
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

	// Função para guardar os valores das regras em um ficheiro.
	public static void save(JTable tabela1, int m) {
		File f = null;
		int i = 1;
		String path="";
		//garantir a criação de ficheiros com nomes sequenciais
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
		//escrever as regras e pesos fornecidos em um ficheiro
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
	
	
	public static int Fn(Object[][] rules, String ham){
		Scanner scanner;
		int score = 0;
		int fn = 0;
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
	
	public static int Fp(Object[][] rules, String spam){
		Scanner scanner;
		int score = 0;
		int fp = 0;
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
				if(score<5){
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
