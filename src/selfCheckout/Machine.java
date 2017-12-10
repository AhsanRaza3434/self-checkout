package selfCheckout;

import java.io.IOException;
import java.util.*;

public class Machine
{
	private ItemTable itemTable;
    private ArrayList<Item> scannedItems = new ArrayList<Item>();
    private ArrayList<Item> scannedItemsSorted = new ArrayList<Item>();
    Scanner scanner = new Scanner(System.in);
    private double total = 0;
    
    public Machine() throws IOException {
    	itemTable = new ItemTable();
    }
    
    public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public ArrayList<Item> getScannedItems()
	{
		return scannedItems;
	}
	public void sortList()
	{
		for(Item x: scannedItems)
    	{
    		
    		scannedItemsSorted.add(x);
    		
    	}
    	Collections.sort(scannedItemsSorted);
	}

	public ItemTable getItemTable()
    {
    	return itemTable;
    }
    
    public void scanItemGUI()
    {
    	
    	String barcodeEntered = Main.barcodeField.getText();
    	BarcodedItemType type = itemTable.findCodedItemType(barcodeEntered);
    	
    	if(type != null)
    	{
    		scanItem(type);
    		Main.barcodeMessageLabel.setText("Scanned");
    		updateScreen();
    	}
    	else
    	{
    		Main.barcodeMessageLabel.setText("Wrong code, try again.");

    	}
    	
    }
    
    public void printReceiptGUISorted()
    {
    	WindowPrint.setTextAreaText("");
    	WindowPrint.appendTextArea("Thank you for shopping~ \n");
    	WindowPrint.appendTextArea("--------------------------- \n");
    	for(Item x: scannedItemsSorted)
    	{
    		x.printToScreen();
    	}
    	WindowPrint.appendTextArea("--------------------------- \n");
    	WindowPrint.appendTextArea("Total: $" + total + " \n");
    }
    
    public void printReceiptGUI()
    {
    	WindowPrint.setTextAreaText("");
    	
    	WindowPrint.appendTextArea("Thank you for shopping~ \n");
    	WindowPrint.appendTextArea("--------------------------- \n");
    	for(Item x: scannedItems)
    	{
    		
    		x.printToScreen();
    	}
    	
    	WindowPrint.appendTextArea("--------------------------- \n");
    	WindowPrint.appendTextArea("Total: $" + total + " \n");
    }
    
    

    public void updateScreen()
    {
    	
    	WindowPrint.setTextAreaText("");
    	WindowPrint.appendTextArea("Total: $" + total + " \n");
    	WindowPrint.appendTextArea("--------------------------- \n");
    	
    	for(Item x: scannedItems)
    	{
    		x.printToScreen();
    		
    	}
//    	WindowPrint.appendTextArea("--------------------------- \n");
//    	WindowPrint.appendTextArea("Total: $" + total);
    }
    
    public void startMachine()
    {
    	boolean promptAgain = true;
    	
    	while(promptAgain)
    	{
    		String bInput;
    	//	Main.textArea.setText("Welcome to self checkout.");
        	System.out.println("Welcome to self checkout. Enter 's' to start scanning. ");
        	System.out.println("Enter 'q' to quit ");
        	bInput = scanner.nextLine();
        	
        	if (bInput.equals("s") || bInput.equals("S"))
        	{
        		
        		promptAgain = false;
        		startScan();
        		
        	}
        	else if(bInput.equals("q") || bInput.equals("Q"))
    		{
        		promptAgain = false;
        		System.out.println("Bye!");
    		}
 	
    	}
    		
    }
    
    
    public void startScan()
    {

    	boolean scanFinished = false;
    	resetMachine();
    	System.out.println("Enter 'f' to finish. 'b' to go back. ");
    	System.out.println("'L' if your item has no barcode.");
    	System.out.println("or Enter barcode: ");
    	    	
    	String bInput; 
    	while(!scanFinished)
    	{
    		
    		
    		bInput = scanner.nextLine();
    		BarcodedItemType type = itemTable.findCodedItemType(bInput);
	    	
    		//Prints receipt, exists loop.
    		if(bInput.equals("f") || bInput.equals("F") )
    		{
    			scanFinished = true;
	        	
    		}
    		
    		//Goes into lookup item screen
    		else if(bInput.equals("l") || bInput.equals("L"))
    		{
    			boolean lookupChosen = false;
    			
    			while(!lookupChosen)
    			{
    				
    				System.out.println("Enter 'weight' or 'count'.");
    				String userLookupChoice = scanner.nextLine();
    				switch(userLookupChoice)
    				{
    				case "b":       lookupChosen = true;
    								System.out.println("Enter barcode: ");
    							    break;
    					
    				case "B":	    lookupChosen = true;
    								System.out.println("Enter barcode: ");
								    break;
    					
    				case "weight":  System.out.println("CHOOSE ITEM BY ID");
									System.out.println("-----------------------");
    								itemTable.printWeightedList();
    								boolean weightFinished = false;
    								String wInput;
    			        			while(!weightFinished)
    			        			{
    			        				wInput = scanner.nextLine();
    			        				WeightedItemType lookupType = (WeightedItemType) itemTable.findItemTypeByID(wInput);
    			        				if(wInput.equals("b") || wInput.equals("B"))
    			            			{
    			            				weightFinished = true;
    		
    			            			}
    			        				else if(wInput.equals("f") || wInput.equals("F"))
    			        				{
    			        					weightFinished = true;
    			        					lookupChosen = true;		        					
    			        					scanFinished = true;
    			        				}
    			        				//if the type returned is not null then scan.
    			        				else if(lookupType != null)
    			        				{

    			        					lookupType.askUser();
    			        					double numInput = scanner.nextDouble();
    			        					scanItem(lookupType,numInput);
    			        					System.out.println("Choose next item (ID) : ");
    			        					wInput = scanner.nextLine();    			        					
    			        				}
    			        				else 
    			        				{
    			        					System.out.println("Enter valid ID.");
    			        				}
    			        			} 	
    								break;
    					
    				case "count":	System.out.println("CHOOSE ITEM BY ID");
									System.out.println("-----------------------");
									itemTable.printCountedList();
    								boolean countFinished = false;
    								String cInput;
    			        			while(!countFinished)
    			        			{
    			        				cInput = scanner.nextLine();
    			        				CountedItemType lookupType = (CountedItemType)itemTable.findItemTypeByID(cInput);
    			        				//if B is entered we go back
    			        				if(cInput.equals("b") || cInput.equals("B"))
    			            			{
    			            				countFinished = true;
    		
    			            			}
    			        				//if F is entered all loops exit.
    			        				else if(cInput.equals("f") || cInput.equals("F"))
    			        				{
    			        					countFinished = true;
    			        					lookupChosen = true;   			        					
    			        					scanFinished = true;
    			        				}
    			        				//if the type returned is not null then scan.
    			        				else if(lookupType != null)
    			        				{

    			        					lookupType.askUser();
    			        					int numInput = scanner.nextInt();
    			        					scanItem(lookupType,numInput);
    			        					System.out.println("Choose next item (ID) : ");
    			        					cInput = scanner.nextLine();    			        					
    			        				}
    			        				else 
    			        				{
    			        					System.out.println("Enter valid ID.");
    			        				}
    			        			} 	
									break;
					default: System.out.println("Enter correct ID:");
							 break;
    				}
    				
    			}
    			
    		}
    		//Scans barcode if correct.
    		else
    		{
    			
    			if(type != null)
	        	{
	        		scanItem(type);
	        		System.out.println("Scan next item: ");
	        	}
	        	else
	        	{
	        		System.out.println("Wrong barcode try again: ");
	        	}
    		}
        	
        	
    	}
    	printReceipt();
    	
    }
    
    public void printReceipt()
    {
    	System.out.println("----------------------------------");
    	for(Item x: scannedItems)
    	{
    		x.print();
    	}
    	System.out.println("----------------------------------");
    	
    	 	
    }    
    
    public void resetMachine()
    {
    	
    	scannedItems.clear();
    	total = 0;
    	updateScreen();
    }
    


    public void scanItem(CountedItemType type, int count)
    {
    	
    	CountedItem item = new CountedItem((CountedItemType)type,count);
    	scannedItems.add(item);
    	addToTotal(item);
 
    	
    }
    
    
    public void scanItem(WeightedItemType type,double kiloGrams)
    {
    	WeightedItem item = new WeightedItem((WeightedItemType)type,kiloGrams);
    	
    	scannedItems.add(item);
    	addToTotal(item);

    }
    
    
    
    public void scanItem(BarcodedItemType type)
    {
    	
    	BarcodedItem item = new BarcodedItem(type);
    	scannedItems.add(item);
    	addToTotal(item);

    }
    
    public void addToTotal(Item x)
    {
    	double price = x.getPrice();
    	total += price;
    }
    
	
}
