package selfCheckout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Main extends JFrame
{
	JLabel kgItemInfoLabel = new JLabel("");
	JButton receiptButton = new JButton("Print Receipt");
	private static JCheckBox alphabeticalCheckBox = new JCheckBox("Alphabetical Order");
	public static WindowPrint printFrame;
	public static JLabel barcodeMessageLabel = new JLabel("");
	public static JTextField barcodeField = new JTextField();
	public static Machine machine;
	private static final long serialVersionUID = 1L;
	private static WeightedItemType selectedWeightedItemType;
	private static CountedItemType selectedCountedItemType;
	private static JLabel countItemInfoLabel = new JLabel("");
	public JPanel contentPane;
	private static JTextField kgTextField;
	private static JButton bananaButton;
	private static JTextField countTextField;
	

	
	
	public static void main(String[] args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Main frame = new Main();
					printFrame = new WindowPrint();
					printFrame.setVisible(true);
					frame.setVisible(true);
					machine = new Machine();
					
					printFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//code for launching console app.
		
		machine.startMachine();	
		
		
		
	}
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Main() throws IOException
	{
		
		setTitle("Checkout");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 718, 543);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel barcodeText = new JLabel("Enter barcode:");
		barcodeText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		barcodeText.setBounds(46, 19, 167, 24);
		panel.add(barcodeText);
		
		
		barcodeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		barcodeField.setBounds(223, 17, 236, 32);
		barcodeField.setColumns(10);
		panel.add(barcodeField);
		
		JButton scanButton = new JButton("Scan");
		scanButton.setBackground(Color.RED);
		scanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.scanItemGUI();
			}
		});
		scanButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scanButton.setBounds(503, 13, 160, 40);
		panel.add(scanButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 77, 698, 361);
		panel.add(tabbedPane);
		
		JPanel weightTab = new JPanel();
		tabbedPane.addTab("Weighted Items", null, weightTab, null);
		
		JButton appleButton = new JButton("Apples");
		appleButton.setBackground(Color.GREEN);
		appleButton.setBounds(184, 41, 115, 79);
		appleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(0).getName() + " $"+
				machine.getItemTable().getWeightedItemType(0). getPricePerKg() + "/KG");
				kgTextField.setEditable(true);
				selectedWeightedItemType = machine.getItemTable().getWeightedItemType(0);
				kgTextField.setText("");
				
				
			}
		});
		weightTab.setLayout(null);
		
				
				weightTab.add(appleButton);
				
				JButton onionButton = new JButton("Onions");
				onionButton.setBackground(Color.GREEN);
				
				onionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(1).getName() + " $"+
						machine.getItemTable().getWeightedItemType(1). getPricePerKg() + "/KG");
						kgTextField.setEditable(true);
						selectedWeightedItemType = machine.getItemTable().getWeightedItemType(1);
						kgTextField.setText("");
						
					}
				});
				onionButton.setBounds(184, 156, 115, 79);
				weightTab.add(onionButton);
				
				kgTextField = new JTextField();
				kgTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				kgTextField.setEditable(false);
				kgTextField.setBounds(262, 292, 115, 33);
				weightTab.add(kgTextField);
				kgTextField.setColumns(10);
				
				JLabel kgLabel = new JLabel("Enter kg:");
				kgLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				kgLabel.setBounds(154, 292, 98, 30);
				weightTab.add(kgLabel);
				
				JButton kgBasketButton = new JButton("Add to basket");
				kgBasketButton.setBackground(Color.RED);
				kgBasketButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if(kgTextField.isEditable() && !kgTextField.getText().isEmpty() && isNumeric(kgTextField.getText()))
						{
							Double kgEntered = Double.parseDouble(kgTextField.getText());

							machine.scanItem(selectedWeightedItemType, kgEntered);
							kgTextField.setEditable(false);
							kgTextField.setText("");
							kgItemInfoLabel.setText("");
							machine. updateScreen();
							


						}
					
						
						
					}
				});
				
				kgBasketButton.setBounds(406, 292, 139, 35);
				weightTab.add(kgBasketButton);
				
				JButton orangeButton = new JButton("Orange");
				orangeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(2).getName() + " $"+
								machine.getItemTable().getWeightedItemType(2). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(2);
								kgTextField.setText("");
					}
				});
				orangeButton.setBackground(Color.GREEN);
				orangeButton.setBounds(535, 156, 115, 79);
				weightTab.add(orangeButton);
				
				JButton cucumberButton = new JButton("Cucumber");
				cucumberButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(3).getName() + " $"+
								machine.getItemTable().getWeightedItemType(3). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(3);
								kgTextField.setText("");
					}
				});
				cucumberButton.setBackground(Color.GREEN);
				cucumberButton.setBounds(358, 41, 115, 79);
				weightTab.add(cucumberButton);
				
				JButton peanutButton = new JButton("Peanut");
				peanutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(7).getName() + " $"+
								machine.getItemTable().getWeightedItemType(7). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(7);
								kgTextField.setText("");
					}
				});
				peanutButton.setBackground(Color.GREEN);
				peanutButton.setBounds(535, 41, 115, 79);
				weightTab.add(peanutButton);
				
				JButton pineButton = new JButton("Pineapple");
				pineButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(5).getName() + " $"+
								machine.getItemTable().getWeightedItemType(5). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(5);
								kgTextField.setText("");
					}
				});
				pineButton.setBackground(Color.GREEN);
				pineButton.setBounds(34, 41, 115, 79);
				weightTab.add(pineButton);
				
				bananaButton = new JButton("Banana");
				bananaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(6).getName() + " $"+
								machine.getItemTable().getWeightedItemType(6). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(6);
								kgTextField.setText("");
					}
				});
				bananaButton.setBackground(Color.GREEN);
				bananaButton.setBounds(358, 158, 115, 75);
				weightTab.add(bananaButton);
				
				JButton leekButton = new JButton("Leek");
				leekButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						kgItemInfoLabel.setText(machine.getItemTable().getWeightedItemType(4).getName() + " $"+
								machine.getItemTable().getWeightedItemType(4). getPricePerKg() + "/KG");
								kgTextField.setEditable(true);
								selectedWeightedItemType = machine.getItemTable().getWeightedItemType(4);
								kgTextField.setText("");
					}
				});
				leekButton.setBackground(Color.GREEN);
				leekButton.setBounds(34, 156, 115, 79);
				weightTab.add(leekButton);
				kgItemInfoLabel.setBounds(271, 11, 139, 14);
				weightTab.add(kgItemInfoLabel);
				
				
				kgItemInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				kgItemInfoLabel.setForeground(Color.RED);
		
		JPanel countTab = new JPanel();
		tabbedPane.addTab("Counted Items", null, countTab, null);
		countTab.setLayout(null);
		
		JButton breadButton = new JButton("Bread Roll");
		breadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countItemInfoLabel.setText(machine.getItemTable().getCountedItemType(0).getName() + " $"+
						machine.getItemTable().getCountedItemType(0).getPricePerUnit() + "/Unit");
						countTextField.setEditable(true);
						selectedCountedItemType = machine.getItemTable().getCountedItemType(0);
						countTextField.setText("");
			}
		});
		breadButton.setBounds(33, 41, 115, 79);
		breadButton.setBackground(Color.GREEN);
		countTab.add(breadButton);
		
		JButton donutButton = new JButton("Donut");
		donutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				countItemInfoLabel.setText(machine.getItemTable().getCountedItemType(2).getName() + " $"+
						machine.getItemTable().getCountedItemType(2).getPricePerUnit() + "/Unit");
						countTextField.setEditable(true);
						selectedCountedItemType = machine.getItemTable().getCountedItemType(2);
						countTextField.setText("");
			}
		});
		donutButton.setBounds(362, 41, 115, 79);
		donutButton.setBackground(Color.GREEN);
		countTab.add(donutButton);
		
		JButton crosButton = new JButton("Croissant");
		crosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countItemInfoLabel.setText(machine.getItemTable().getCountedItemType(1).getName() + " $"+
						machine.getItemTable().getCountedItemType(1).getPricePerUnit() + "/Unit");
						countTextField.setEditable(true);
						selectedCountedItemType = machine.getItemTable().getCountedItemType(1);
						countTextField.setText("");
			}
		});
		crosButton.setBounds(196, 41, 115, 79);
		crosButton.setBackground(Color.GREEN);
		countTab.add(crosButton);
		
		JButton cakeButton = new JButton("Cake");
		cakeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countItemInfoLabel.setText(machine.getItemTable().getCountedItemType(3).getName() + " $"+
						machine.getItemTable().getCountedItemType(3).getPricePerUnit() + "/Unit");
						countTextField.setEditable(true);
						selectedCountedItemType = machine.getItemTable().getCountedItemType(0);
						countTextField.setText("");
			}
		});
		cakeButton.setBounds(527, 41, 115, 79);
		cakeButton.setBackground(Color.GREEN);
		countTab.add(cakeButton);
		
		
		countItemInfoLabel.setForeground(Color.RED);
		countItemInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		countItemInfoLabel.setBounds(302, 11, 139, 14);
		countTab.add(countItemInfoLabel);
		
		countTextField = new JTextField();
		countTextField.setEditable(false);
		countTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		countTextField.setColumns(10);
		countTextField.setBounds(287, 289, 115, 33);
		countTab.add(countTextField);
		
		JButton countBasketButton = new JButton("Add to basket");
		countBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(countTextField.isEditable() && !countTextField.getText().isEmpty() && isNumeric(countTextField.getText()) && isNumericInt(countTextField.getText()))
				{
					int countEntered = Integer.parseInt(countTextField.getText());
					machine.scanItem(selectedCountedItemType, countEntered);
					countTextField.setEditable(false);
					countTextField.setText("");
					countItemInfoLabel.setText("");
					machine.updateScreen();
					


				}
			}
		});
		countBasketButton.setBackground(Color.RED);
		countBasketButton.setBounds(431, 287, 139, 35);
		countTab.add(countBasketButton);
		
		JLabel countLabel = new JLabel("Enter count:");
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		countLabel.setBounds(154, 289, 98, 30);
		countTab.add(countLabel);
		
		JLabel lblOrSelectA = new JLabel("or select a lookup item.");
		lblOrSelectA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrSelectA.setBounds(10, 55, 167, 24);
		panel.add(lblOrSelectA);
		barcodeMessageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		

		barcodeMessageLabel.setForeground(Color.RED);
		barcodeMessageLabel.setBounds(258, 60, 167, 19);
		panel.add(barcodeMessageLabel);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setBackground(SystemColor.textHighlight);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alphabeticalCheckBox.setEnabled(false);
				receiptButton.setEnabled(false);
				tabbedPane.setVisible(true);
				machine.resetMachine();
				barcodeMessageLabel.setText("");
				barcodeField.setText("");
				kgItemInfoLabel.setText("");
				
				appleButton.setEnabled(true);
				cucumberButton.setEnabled(true);
				peanutButton.setEnabled(true);
				pineButton.setEnabled(true);
				leekButton.setEnabled(true);
				orangeButton.setEnabled(true);
				bananaButton.setEnabled(true);
				onionButton.setEnabled(true);
				breadButton.setEnabled(true);
				crosButton.setEnabled(true);
				donutButton.setEnabled(true);
				cakeButton.setEnabled(true);
				scanButton.setEnabled(true);
				barcodeField.setEnabled(true);
				kgBasketButton.setEnabled(true);
				countBasketButton.setEnabled(true);
				
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		resetButton.setBounds(10, 484, 140, 48);
		panel.add(resetButton);
		
		JButton Undo = new JButton("Undo");
		Undo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				double priceToUndo = 0;
				if(machine.getScannedItems().size() > 0)
				{
					priceToUndo = machine.getScannedItems().get(machine.getScannedItems().size() - 1).getPrice();
					machine.getScannedItems().remove(machine.getScannedItems().size() - 1);
				}
				
				
				
				
				machine.setTotal(machine.getTotal() - priceToUndo);
				
				machine.updateScreen();
				
			}
		});
		Undo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Undo.setBackground(SystemColor.textHighlight);
		Undo.setBounds(177, 484, 140, 48);
		panel.add(Undo);
		
		
		receiptButton.setEnabled(false);
		receiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!alphabeticalCheckBox.isSelected())
				{
					machine.printReceiptGUI();
				}
				else
				{
					machine.printReceiptGUISorted();
				}
				
				barcodeMessageLabel.setText("");
				barcodeField.setText("");
				kgItemInfoLabel.setText("");
				
				
			}
		});
		receiptButton.setBackground(SystemColor.textHighlight);
		receiptButton.setBounds(526, 485, 140, 48);
		panel.add(receiptButton);
		alphabeticalCheckBox.setEnabled(false);
		
		
		alphabeticalCheckBox.setBounds(526, 455, 137, 23);
		panel.add(alphabeticalCheckBox);
		
		JButton finishedButton = new JButton("Checkout");
		finishedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appleButton.setEnabled(false);
				cucumberButton.setEnabled(false);
				peanutButton.setEnabled(false);
				pineButton.setEnabled(false);
				leekButton.setEnabled(false);
				orangeButton.setEnabled(false);
				bananaButton.setEnabled(false);
				onionButton.setEnabled(false);
				breadButton.setEnabled(false);
				crosButton.setEnabled(false);
				donutButton.setEnabled(false);
				cakeButton.setEnabled(false);
				scanButton.setEnabled(false);
				barcodeField.setEnabled(false);
				kgBasketButton.setEnabled(false);
				countBasketButton.setEnabled(false);
				
				
				machine.sortList();
				alphabeticalCheckBox.setEnabled(true);
				receiptButton.setEnabled(true);
				
				
			}
		});
		finishedButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		finishedButton.setBackground(Color.YELLOW);
		finishedButton.setBounds(356, 484, 140, 48);
		panel.add(finishedButton);

	}
	
	public static boolean isNumericInt(String str)  
	{  
	  try  
	  {  
	    double d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
