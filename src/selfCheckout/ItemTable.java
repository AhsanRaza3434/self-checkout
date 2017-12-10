package selfCheckout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemTable
{
	private ArrayList<BarcodedItemType> barcodedList;
	private ArrayList<WeightedItemType> weightedList;
	private ArrayList<CountedItemType> countedList;
	
	public ItemTable() throws IOException
	{
		barcodedList = new ArrayList<BarcodedItemType>();
		weightedList = new ArrayList<WeightedItemType>();
		countedList = new ArrayList<CountedItemType>();
		
		//loadItems("itemTypes.txt");
		
		//Populate barcoded items here.
		
		barcodedList.add(new BarcodedItemType("B001", "Milk", "001", 2.00));
		barcodedList.add(new BarcodedItemType("B002", "Cookies", "002", 3.00));
		barcodedList.add(new BarcodedItemType("B003", "Ice Cream", "003", 5.00));
		barcodedList.add(new BarcodedItemType("B004", "Yogurt", "004", 4.00));
		barcodedList.add(new BarcodedItemType("B005", "Coca Cola", "005", 1.50));
		barcodedList.add(new BarcodedItemType("B006", "Cereal", "006", 4.80));
		barcodedList.add(new BarcodedItemType("B007", "Chips", "007", 2.00));
		barcodedList.add(new BarcodedItemType("B008", "Juice", "008", 3.20));
		barcodedList.add(new BarcodedItemType("B009", "Toothpaste", "009", 5.30));
		barcodedList.add(new BarcodedItemType("B010", "Biscuits", "010", 1.80));
		
		//Populate weighted items here.
		
		weightedList.add(new WeightedItemType("W001", "Apple", 2.50));
		weightedList.add(new WeightedItemType("W002", "Onion", 1.00));
		weightedList.add(new WeightedItemType("W003", "Orange", 3.00));
		weightedList.add(new WeightedItemType("W004", "Cucumber", 1.50));
		weightedList.add(new WeightedItemType("W005", "Leek", 1.00));
		weightedList.add(new WeightedItemType("W006", "Pineapple", 5.00));
		weightedList.add(new WeightedItemType("W007", "Banana", 3.00));
		weightedList.add(new WeightedItemType("W008", "Peanut", 12.00));
		
		//Populate counted items here.
		
		countedList.add(new CountedItemType("C001", "Bread Roll", 0.50));
		countedList.add(new CountedItemType("C003", "Croissant", 1.50));
		countedList.add(new CountedItemType("C002", "Donut", 1.00));
		countedList.add(new CountedItemType("C004", "Cake", 8.00));

		
				
	}
	
	private void loadItems(String fileName) throws IOException {
		// ArrayList<Item> items = new ArrayList<>();
		
		BufferedReader itemsDB = new BufferedReader(new FileReader("itemTypes.txt"));
		
		String line; 
		while((line = itemsDB.readLine()) != null) {
			line = line.trim();
			String[] fields = line.split("\n");
			
			if(fields[0].equals(BarcodedItemType.PREFIX)) {
				barcodedList.add(new BarcodedItemType(fields[1], fields[2], fields[3], Double.parseDouble(fields[4])));
			} 
			else if (fields[0].equals(CountedItemType.PREFIX)) {
				countedList.add(new CountedItemType(fields[1], fields[2], Double.parseDouble(fields[3])));
			}
			else if (fields[0].equals(WeightedItemType.PREFIX)) {
				weightedList.add(new WeightedItemType(fields[1], fields[2], Double.parseDouble(fields[3])));
			}
			
		
			
		}
		itemsDB.close();	
				
		
	}
	
	public void printCountedList()
	{
		for(CountedItemType x: countedList)
		{
			x.screenPrint();
		}
	}
	
	public void printWeightedList()
	{
		for(WeightedItemType x: weightedList)
		{
			x.screenPrint();
		}
	}
	
	
	
	public ArrayList<BarcodedItemType> getBarcodedList()
	{
		return barcodedList;
	}


	public ArrayList<WeightedItemType> getWeightedList()
	{
		return weightedList;
	}


	public ArrayList<CountedItemType> getCountedList()
	{
		return countedList;
	}

	//finds barcodedItemType by barcode
	public BarcodedItemType findCodedItemType (String barcode)
    {
        for (BarcodedItemType type : barcodedList)
        {
            if (type.getBarcode ().equals (barcode))
            {
            	return type;
            }
                
        }
 
        return null;
    }
	

	//find ItemType by id. (excluding barcodedItemType)
	public ItemType findItemTypeByID(String id)
	{
		for(ItemType type: weightedList)
		{
			if(type.getId().equals(id))
			{
				return (WeightedItemType)type;
			}
		}
		
		for(ItemType type: countedList)
		{
			if(type.getId().equals(id))
			{
				return (CountedItemType)type;
						
											
			}
		}
		return null;
	}
    
	
	public int getCountedItemTypeSize()
	{
		return countedList.size();
	}
	
	//returns ItemType by index
	public CountedItemType getCountedItemType(int i)
	{
		return countedList.get(i);
	}
	
	public int getWeightedItemTypeSize()
	{
		return weightedList.size();
	}
	
	//returns ItemType by index
	public WeightedItemType getWeightedItemType(int i)
	{
		return weightedList.get(i);
	}
	
	
}
