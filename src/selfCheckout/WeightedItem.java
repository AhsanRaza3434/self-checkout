package selfCheckout;

public class WeightedItem extends Item
{
	private WeightedItemType type;
	private double weight;

	public WeightedItem(WeightedItemType type, double weight)
	{
		this.type = type;
		this.weight = weight;
		// super();
	}
	
	@Override
	public ItemType getType()
	{
		return type;
	}

	@Override
	public double getPrice()
	{
		return type.getPricePerKg() * weight;
	}
	@Override
	public void print()
	{
		System.out.println(weight + "kg" + " " +getType().getName() + " $" + Math.round(getPrice()));
	}
	
	@Override
	public void printToScreen()
	{
		if(getPrice() != 0)
		WindowPrint.getTextArea().append(getType().getName() + " $" + WindowPrint.round(getPrice(),2)+ " | " + weight + "kg"+  "\n");
	}

	
	
	
	

}
