package selfCheckout;

public class BarcodedItemType extends ItemType
{
	public static final String PREFIX = "B";

	private String barcode;
	private double price;
	
	public BarcodedItemType(String id, String name, String barcode, double price)
	{
		super(id,name);
		this.barcode = barcode;
		this.price = price;		
	}

	public String getBarcode()
	{
		return barcode;
	}

	public double getPrice()
	{
		return price;
	}
	
	public void print()
	{
		System.out.println(id +" "+ name + " " + barcode + " " + price);
	}	
}
