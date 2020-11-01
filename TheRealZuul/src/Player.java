import java.util.*;

public class Player
{
    private Room currentRoom;
    private ArrayList<Item> items;

    public Player()
    {
        items = new ArrayList<>();
    }

    public void addToInventory(Item item)
    {
        items.add(item);
    }
    public String printInventory()
    {
        String returnStr = "This items are in your inventroy: ";
        for(Item item : items)
        {
            returnStr += item.getItemDescription();
        }
        return returnStr;
    }
    
}