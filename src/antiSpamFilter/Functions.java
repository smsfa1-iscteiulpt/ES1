package antiSpamFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
}
