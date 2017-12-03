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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import antiSpamFilter.Functions;


public class Gui extends AbstractTableModel {

	private JFrame frame;
	private int WindowX = 900;
	private int WindowY = 900;
	private String rulespath;
	private String hampath;
	private String spampath;
	private Object [][] allrules;
	private JTable tabela1;
	private JTable tabela2;
	private String [] colums = {"Regra", "Peso"};
	private JPanel manual;
	private JScrollPane scroll;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	
	
	
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
	 
	 
	 //Labels 
	 JLabel rules = new JLabel("rules.cf");
	 JLabel ham = new JLabel("ham.log");
	 JLabel spam = new JLabel("spam.log");
	 
	 
	 //Text areas para inserir o path para os ficheiros
	 JTextArea regras = new JTextArea();
	 regras.setFont(regras.getFont().deriveFont(16f));
	 JTextArea ham1 = new JTextArea();
	 ham1.setFont(ham1.getFont().deriveFont(16f));
	 JTextArea spam1 = new JTextArea();
	 spam1.setFont(spam1.getFont().deriveFont(16f));
	 
	 //botão que apaga os paths
	 JButton reset = new JButton("Apagar");
	 
	 reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regras.setText("");
				ham1.setText("");
				spam1.setText("");
				
			}
		});
	 
	 
	 //botão para confirmar os paths
	 JButton confirm = new JButton("Confirmar");
	 
	 confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regras.getText().equals("")||spam1.getText().equals("")||ham1.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Tem de preencher todas as opções");
				}else{
					rulespath=regras.getText();
					hampath=ham1.getText();
					spampath=spam1.getText();
					allrules = Functions.getRules(rulespath);
					((DefaultTableModel) tabela1.getModel()).getDataVector().removeAllElements();
					for(int i =0;i<allrules.length;i++){
						((DefaultTableModel) tabela1.getModel()).insertRow(tabela1.getRowCount(),(Object[])allrules[i]);
					}
					scroll.repaint();
					frame.repaint();
				}
			}
		});
	 
	 
	 
	 //adicionar os componentes ao painel
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
	 manual = new JPanel();
	 manual.setLayout(new FlowLayout());
	 
	 //text area para mostrar os resultados dos testes
	 JTextArea resulman = new JTextArea();
	 resulman.setSize(400, 200);
	 resulman.setEditable(false);
	 resulman.setFont(resulman.getFont().deriveFont(24f));
	 resulman.setText("FP: 0"+System.lineSeparator()+"FN: 0"+System.lineSeparator()+"                    ");
	 
	 allrules = Functions.getRules("rules.cf");
	 
	 //tabela com as regras e os pesos
	 model = new DefaultTableModel(allrules,colums){
		 public boolean isCellEditable(int row, int col) {
		        if (col== 1) { //columnIndex: the column you want to make it editable
		            return true;
		        } else {
		            return false;
		        }
		    }
		 
	 };
	 tabela1 = new JTable(model);
	 scroll = new JScrollPane(tabela1);
	 
	 
	//botão para testar a configuração
		 JButton runmanual = new JButton("Testar");
		 runmanual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						int fn=Functions.Fn(allrules, hampath);
						int fp=Functions.Fp(allrules, spampath);
						resulman.setText("FP:"+fp+System.lineSeparator()+"FN:"+fn+System.lineSeparator()+"                    ");
						
				}
			});
		 
		 
		 //Botão para guardar a configuração
		 JButton save = new JButton("Guardar");
		 save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Functions.save(tabela1,0);
				}
			});
	 
	 extra.add(new JLabel("Configuração Manual"));
	 extra.add(runmanual);
	 extra.add(save);
	 manual.add(extra);
	 manual.add(resulman);
	 manual.add(scroll);
	 
	 //criar o painel para introdução automática
	 
	 	//Painel extra para garantir a posição dos botões
		 JPanel extra1 = new JPanel();
		 extra1.setLayout(new GridLayout(3,0));
		 
		 //painel principal
		 JPanel auto = new JPanel();
		 auto.setLayout(new FlowLayout());
		
		 
		 //botão para gerar configurações automaticamente
		 JButton runauto = new JButton("Gerar Configuração");
		 runauto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		 
		 //botão para gerar a configuração gerada
		 JButton save1 = new JButton("Guardar");
		 save1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Functions.save(tabela2,1);
				}
			});
		 
		 
		 //text area para mostrar os resultados dos testes
		 JTextArea resulauto = new JTextArea();
		 resulauto.setSize(400, 200);
		 resulauto.setEditable(false);
		 resulauto.setFont(resulman.getFont().deriveFont(24f));
		 resulauto.setText("FP: 0"+System.lineSeparator()+"FN: 0"+System.lineSeparator()+"                    ");
		 
		 
		 //tabela que mostra as regras, e os pesos automaticamente gerados
		 Object [][] allrules1 = Functions.getRules("rules.cf");
		 
		 model1 = new DefaultTableModel(allrules,colums){
			 public boolean isCellEditable(int row, int col) {
			            return false;
			    }
		 };
		 
		 tabela2 = new JTable(model1);
		 
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


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

