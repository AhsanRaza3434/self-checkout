package selfCheckout;

public class WeightedItemType extends ItemType
{
	public static final String PREFIX = "W";
	private double pricePerKg;
	
	public WeightedItemType(String id, String name, double pricePerKg)
	{
		super(id,name);
		this.pricePerKg = pricePerKg;
	}

	public double getPricePerKg()
	{
		return pricePerKg;
	}
	
	public void screenPrint()
	{
		System.out.println("ID: (" + id + ")" + " name: " + name + "    price per kg:  $" + getPricePerKg() );
	}
	public void print()
	{
		System.out.println(id +" "+ name + " " + pricePerKg);
	}
	
	public void askUser()
	{
		System.out.println("How many kg?: ");
	}

	
}
