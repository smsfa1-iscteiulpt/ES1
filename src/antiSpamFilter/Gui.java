package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

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

/**
 * GUI is the class that creates the application interface
 *
 * @author Nuno Fialho EIC1 72910
 * @author Sandro Ferreira EIC1 72911
 * @author Duarte Pinto EIC1 73117
 */

public class Gui extends AbstractTableModel {

	private JFrame frame;
	private int WindowX = 900;
	private int WindowY = 900;
	private String rulespath;
	private String hampath;
	private String spampath;
	private RP[] allrules;
	private JTable tabela1;
	private JTable tabela2;
	private String[] colums = { "Regra", "Peso" };
	private JPanel manual;
	private JScrollPane scroll;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private JButton runmanual;
	private JButton runauto;
	private JButton confirm;
	private JTextArea regras;
	private JTextArea ham1;
	private JTextArea spam1;

	/**
	 * Creating the window.
	 */
	public Gui() {
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

		// Creates the Configuration Panel
		JPanel config = new JPanel();
		config.setLayout(new GridLayout(4, 2, 10, 10));
		config.setBorder(new EmptyBorder(2, 0, 2, 100));

		// Labels
		 JLabel rules = new JLabel("rules.cf");
		 JLabel ham = new JLabel("ham.log");
		 JLabel spam = new JLabel("spam.log");

		// Text areas to insert the path to open the files
		 regras = new JTextArea();
		regras.setFont(regras.getFont().deriveFont(16f));
		 ham1 = new JTextArea();
		ham1.setFont(ham1.getFont().deriveFont(16f));
		 spam1 = new JTextArea();
		spam1.setFont(spam1.getFont().deriveFont(16f));

		// button that deletes the paths
		JButton reset = new JButton("Apagar");

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regras.setText("");
				ham1.setText("");
				spam1.setText("");

			}
		});

		// button to confirm the paths
		confirm = new JButton("Confirmar");

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (regras.getText().equals("") || spam1.getText().equals("") || ham1.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Tem de preencher todas as opções");
				} else {
					rulespath = regras.getText();
					hampath = ham1.getText();
					spampath = spam1.getText();
					allrules = Functions.getRules(rulespath);
					((DefaultTableModel) tabela1.getModel()).getDataVector().removeAllElements();
					for (int i = 0; i < allrules.length; i++) {
						((DefaultTableModel) tabela1.getModel()).insertRow(tabela1.getRowCount(),
								(allrules[i].getVector()));
					}
					scroll.repaint();
					frame.repaint();
				}
			}
		});

		// add the components to the panel
		config.add(rules);
		config.add(regras);
		config.add(spam);
		config.add(spam1);
		config.add(ham);
		config.add(ham1);
		config.add(reset);
		config.add(confirm);

		frame.add(config, BorderLayout.NORTH);

		// create the panel that joins the creation methods of the configuration

		JPanel join = new JPanel();
		join.setLayout(new GridLayout(2, 0));

		// creates the panel of the manual introduction

		// Extra panel to ensure the button position
		JPanel extra = new JPanel();
		extra.setLayout(new GridLayout(3, 0));

		// main panel
		manual = new JPanel();
		manual.setLayout(new FlowLayout());

		// text area that shows the test results
		JTextArea resulman = new JTextArea();
		resulman.setSize(400, 200);
		resulman.setEditable(false);
		resulman.setFont(resulman.getFont().deriveFont(24f));
		resulman.setText("FP: 0" + System.lineSeparator() + "FN: 0" + System.lineSeparator() + "                    ");

		allrules = Functions.getRules("rules.cf");

		// table with the rules and it's weight
		model = new DefaultTableModel(Functions.getVector(allrules), colums) {
			public boolean isCellEditable(int row, int col) {
				if (col == 1) { // columnIndex: the column you want to make it
								// editable
					return true;
				} else {
					return false;
				}
			}

		};
		tabela1 = new JTable(model);
		scroll = new JScrollPane(tabela1);

		// button to test the configuration
		runmanual = new JButton("Testar");
		runmanual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (regras.getText().equals("") || spam1.getText().equals("") || ham1.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Tem de preencher todas as opções");
				} else {
					Vector tabela = ((DefaultTableModel) tabela1.getModel()).getDataVector();
					for (int i = 0; i < tabela.size(); i++) {
						Vector linha = (Vector) tabela.elementAt(i);
						allrules[i].setRegra(linha.elementAt(0).toString());
						allrules[i].setPeso(Double.parseDouble(linha.elementAt(1).toString()));
						

					}
					int fn = Functions.Fp(allrules, hampath);
					int fp = Functions.Fn(allrules, spampath);
					resulman.setText("FP:" + fp + System.lineSeparator() + "FN:" + fn + System.lineSeparator()
							+ "                    ");
				}
			}
		});

		// Button to save the manual configuration
		JButton save = new JButton("Guardar");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						Functions.save(tabela1,rulespath);
			}
		});

		extra.add(new JLabel("Configuração Manual"));
		extra.add(runmanual);
		extra.add(save);
		manual.add(extra);
		manual.add(resulman);
		manual.add(scroll);

		// create the panel for automatic introduction

		// Extra panel to ensure the buttons position
		JPanel extra1 = new JPanel();
		extra1.setLayout(new GridLayout(3, 0));

		// main panel
		JPanel auto = new JPanel();
		auto.setLayout(new FlowLayout());

		JTextArea resulauto = new JTextArea();

		// button to generate automatic configuration
		runauto = new JButton("Gerar Configuração");
		runauto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (regras.getText().equals("") || spam1.getText().equals("") || ham1.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Tem de preencher todas as opções");
				} else {
					try {
						AntiSpamFilterAutomaticConfiguration.automatic(rulespath, hampath, spampath);
						String[] fx = Functions.readAutomatic();
						resulauto.setText("FP:" + fx[0] + System.lineSeparator() + "FN:" + fx[1]
								+ System.lineSeparator() + "                    ");
						RP[] regras = Functions.readConfig(Integer.valueOf(fx[2]));
						((DefaultTableModel) tabela2.getModel()).getDataVector().removeAllElements();
						for (int i = 0; i < regras.length; i++) {
							((DefaultTableModel) tabela2.getModel()).insertRow(tabela2.getRowCount(),
									(regras[i].getVector()));
						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// button to save the generated configuration
		JButton save1 = new JButton("Guardar");
		save1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions.save(tabela2,"AntiSpamConfigurationForBalancedProfessionalAndLeisureMailbox\\rules.cf");
				
			}
		});

		// text area that shows the test results

		resulauto.setSize(400, 200);
		resulauto.setEditable(false);
		resulauto.setFont(resulman.getFont().deriveFont(24f));
		resulauto.setText("FP: 0" + System.lineSeparator() + "FN: 0" + System.lineSeparator() + "                    ");

		// table that shows the rules and it's weight automatically generated
		RP[] allrules1 = Functions.getRules("rules.cf");

		model1 = new DefaultTableModel(Functions.getVector(allrules), colums) {
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

	/**
	 * Function that get the path of the rules file
	 * 
	 * @return the path of the rules
	 */
	public String getRulespath() {
		return rulespath;
	}

	/**
	 * Function that get the path of the ham file
	 * 
	 * @return the path of the rules
	 */
	public String getHampath() {
		return hampath;
	}

	/**
	 * Function that get the path of the spam file
	 * 
	 * @return the path of the spam
	 */
	public String getSpampath() {
		return spampath;
	}

	/**
	 * 
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public JTextArea getRegras() {
		return regras;
	}
	public JTextArea getHam() {
		return ham1;
	}
	public JTextArea getSpam() {
		return spam1;
	}
	
	public JPanel getManual() {
		return manual;
	}

	public JButton getRunManual() {
		return runmanual;
	}

	public JButton getRunAuto() {
		return runauto;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JTable getTabela1() {
		return tabela1;
	}
}
