package selfCheckout;

public class CountedItemType extends ItemType
{
	public static final String PREFIX = "C";
	private double pricePerUnit;
	
	public CountedItemType(String id, String name, double pricePerUnit)
	{
		super(id,name);
		this.pricePerUnit = pricePerUnit;
	}

	public double getPricePerUnit()
	{
		return pricePerUnit;
	}
	
	public void screenPrint()
	{
		System.out.println("ID: (" + id + ")" + " name: " + name + "    price per unit:  $" + getPricePerUnit() );
	}
	
	public void print()
	{
		System.out.println(id +" "+ name + " " + pricePerUnit);
	}
	
	public void askUser()
	{
		System.out.println("How many?: ");
	}
}

