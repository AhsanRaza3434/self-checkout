package selfCheckout;

public class ItemType
{
	protected String id;
	protected String name;
	
	public ItemType(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
	
	public void print()
	{
		System.out.println(id +" "+ name);
	}
	
	public void askUser()
	{
		
	}
	
	
}
