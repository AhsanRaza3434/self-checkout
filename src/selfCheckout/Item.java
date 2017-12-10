package selfCheckout;

public abstract class Item implements Comparable<Item>
{
	public abstract ItemType getType();
	
	public abstract double getPrice();
	
	public void print()
	{
		System.out.println(getType().getName() + " $" + getPrice());
	}
	
	public String  getName()
	{
		return getType().getName();
	}
	
	public abstract void printToScreen();
	
	@Override
	public int compareTo(Item o)
	{
		return getType().getName().compareTo(o.getType().getName());
	}
	

	
	
}
