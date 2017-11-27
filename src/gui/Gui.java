package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import antiSpamFilter.Functions;


public class Gui {

	private JFrame frame;
	private int WindowX = 1000;
	private int WindowY = 1000;
	private String rulespath;
	private String hampath;
	private String spampath;
	private Object [][] allrules;
	private JTable tabela1;
	private JTable tabela2;
	private String [] colums = {"Regra", "Peso"};
	
	
	/**
	* Creating the window.
	*/
	public Gui(){
		frame = new JFrame(" Anti-spam filtering ");
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		addFrameContent();
		
		frame.setVisible(true);

		frame.pack();

		frame.setSize(new Dimension(WindowX, WindowY));
		
		frame.setResizable(false);
	}

	
	/**
	* Adding the content to the window.
	*/
	private void addFrameContent() {
		
	//Criar o Painel de Configuração
	 JPanel config = new JPanel();
	 config.setLayout(new GridLayout(4,2, 10, 10));
	 config.setBorder(new EmptyBorder(2, 0, 2, 100));
	 
	 JLabel rules = new JLabel("rules.cf");
	 JLabel ham = new JLabel("ham.log");
	 JLabel spam = new JLabel("spam.log");
	 
	 JTextArea regras = new JTextArea();
	 regras.setFont(regras.getFont().deriveFont(16f));
	 JTextArea ham1 = new JTextArea();
	 ham1.setFont(ham1.getFont().deriveFont(16f));
	 JTextArea spam1 = new JTextArea();
	 spam1.setFont(spam1.getFont().deriveFont(16f));
	 
	 JButton reset = new JButton("Apagar");
	 
	 reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regras.setText("");
				ham1.setText("");
				spam1.setText("");
				
			}
		});
	 
	 JButton confirm = new JButton("Confirmar");
	 
	 confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regras.getText().equals("")||spam1.getText().equals("")||ham1.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Tem de preencher todas as opções");
				}else{
					System.out.println(allrules[0][1]);
					rulespath=regras.getText();
					hampath=ham1.getText();
					spampath=spam1.getText();
					allrules = Functions.getRules(rulespath);
					System.out.println(allrules[0][1]);
				}
			}
		});
	 
	 
	 config.add(rules);
	 config.add(regras);
	 config.add(spam);
	 config.add(spam1);
	 config.add(ham);
	 config.add(ham1);
	 config.add(reset);
	 config.add(confirm);
	 
	 frame.add(config,BorderLayout.NORTH);
	 
	 //criar o painel que junta os métodos de criação de configuração
	 
	 JPanel join = new JPanel();
	 join.setLayout(new GridLayout(2,0));
	 
	 //criar o painel de introdução manual
	 
	 
	 //Painel extra para garantir a posição dos botões
	 JPanel extra = new JPanel();
	 extra.setLayout(new GridLayout(3,0));
	 
	 //painel principal
	 JPanel manual = new JPanel();
	 manual.setLayout(new FlowLayout());
	
	 
	 
	 JButton runmanual = new JButton("Testar");
	 runmanual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	 
	 JButton save = new JButton("Guardar");
	 save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions.save(tabela1,0);
			}
		});
	 
	 JTextArea resulman = new JTextArea();
	 resulman.setSize(400, 200);
	 resulman.setEditable(false);
	 resulman.setFont(resulman.getFont().deriveFont(24f));
	 resulman.setText("FP:"+System.lineSeparator()+"FN:"+System.lineSeparator()+"                    ");
	 
	 allrules = Functions.getRules("rules.cf");
	 tabela1 = new JTable(allrules,colums);
	 
	 extra.add(new JLabel("Configuração Manual"));
	 extra.add(runmanual);
	 extra.add(save);
	 manual.add(extra);
	 manual.add(resulman);
	 manual.add(new JScrollPane(tabela1));
	 
	 //criar o painel para introdução automática
	 
	 	//Painel extra para garantir a posição dos botões
		 JPanel extra1 = new JPanel();
		 extra1.setLayout(new GridLayout(3,0));
		 
		 //painel principal
		 JPanel auto = new JPanel();
		 auto.setLayout(new FlowLayout());
		
		 
		 
		 JButton runauto = new JButton("Gerar Configuração");
		 runauto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		 
		 JButton save1 = new JButton("Guardar");
		 save1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Functions.save(tabela2,1);
				}
			});
		 
		 JTextArea resulauto = new JTextArea();
		 resulauto.setSize(400, 200);
		 resulauto.setEditable(false);
		 resulauto.setFont(resulman.getFont().deriveFont(24f));
		 resulauto.setText("FP:"+System.lineSeparator()+"FN:"+System.lineSeparator()+"                    ");
		 
		 Object [][] allrules1 = Functions.getRules("rules.cf");
		 tabela2 = new JTable(allrules1,colums);
		 
		 extra1.add(new JLabel("Configuração Automática"));
		 extra1.add(runauto);
		 extra1.add(save1);
		 auto.add(extra1);
		 auto.add(resulauto);
		 auto.add(new JScrollPane(tabela2));
	 
	 
	 
	 
	 join.add(manual);
	 join.add(auto);
	 frame.add(join, BorderLayout.CENTER);
	}


	public String getRulespath() {
		return rulespath;
	}


	public String getHampath() {
		return hampath;
	}


	public String getSpampath() {
		return spampath;
	}
	
	
	
}

