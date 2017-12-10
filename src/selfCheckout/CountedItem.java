package selfCheckout;

public class CountedItem extends Item
{
	private CountedItemType type;
	private int count;
	
	public CountedItem(CountedItemType type, int count)
	{
		this.type = type;
		this.count = count;
	}
	
	

	public int getCount()
	{
		return count;
	}



	public void setCount(int count)
	{
		this.count = count;
	}

	@Override
	public void printToScreen()
	{
		if(getPrice() != 0)
		WindowPrint.getTextArea().append(count + "x"  + " " +getType().getName() + " $" + getPrice() + "\n");
	}

	@Override
	public ItemType getType()
	{
		return type;
	}

	@Override
	public double getPrice()
	{
		return type.getPricePerUnit() * count;
	}
	
	public void print()
	{
		
		System.out.println(count + "x"+  " " + getType().getName() + " $" + WindowPrint.round(getPrice(),2));
	}




}
