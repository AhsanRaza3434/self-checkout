package selfCheckout;

public class BarcodedItem extends Item
{
	private BarcodedItemType type;

	
	public BarcodedItem(BarcodedItemType type)
	{
		// super();
		this.type = type;
	}
	


	@Override
	public ItemType getType()
	{
		return type;
	}

	@Override
	public double getPrice()
	{
		return type.getPrice();
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarcodedItem other = (BarcodedItem) obj;
		if (type == null)
		{
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public void printToScreen()
	{
		if(getPrice() != 0)
		WindowPrint.getTextArea().append(getType().getName() + " $" + WindowPrint.round(getPrice(),2) + "\n");
	}




	

}
