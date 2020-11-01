import java.util.HashMap;
import java.util.*;
import java.util.Set;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;
    private ArrayList<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        items = new ArrayList<>();
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction,Room place ) 
    {
        exits.put(direction, place);
    }
    public void addItem(Item item) 
    {
        items.add(item);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description + ".\n" + getExitString() + ".\n" + getItemsString(); 
    }
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    /*public ArraItem itemsInRoom()
    {
        return items;
    }*/
  
    public String getExitString()
    {
        String returnString = "You can go: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    public String getItemsString()
    {
        String returnItems = "These item are in this room: ";
        Iterator<Item> it = items.iterator();
        while(it.hasNext())
        {
            returnItems += it.next().getItemDescription() + "   ";
        }
        return returnItems;
    }
    
    public String getEatDescription()
    {
        return "You just ate something, good job.\n" + getDescription();
    }
    /*public boolean getItemByName(String name)
    {
        for(Item item : items)
        {
            if (item.getItemName().equals(name))
            		{
            			return true;
            		}
            else {
            	return false;
            }

        }
        

    }*/
    


}