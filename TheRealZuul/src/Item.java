//import java.util.ArrayList;
//import java.util.Iterator;

public class Item
     
{   
    private String itemDescription;
    private int weigth;
    private String name;
    public Item(String itemDescription, int weigth, String name)
    {
        this.itemDescription = itemDescription;
        this.weigth = weigth;
        this.name = name;
    }
    public String getItemDescription()
    {
        if (itemDescription.equals("nothing"))
        {
            return "This room contains no items :(";
        }
        else
        {
            return itemDescription;
        }
      
    }
    public String getItemName()
    {
        return name;
    }

    /*public int getItemWeigth()
    {
            return weight;
    }*/
    

    //advanced stuff
    /*public void addItemToInventory(Item stuff)
    {
        items.add(stuff);        
        System.out.println("This item was added to your inventory: " + stuff);
    }
    public void showItems()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext())
        {
            System.out.println("These item are in this room: " + it.next() + " ");
        }
    }*/
}