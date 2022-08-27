/*

 */

package inventory;

import items.*;
import java.util.*;

public class Inventory
{
    private ArrayList<Item> items = new ArrayList<>();
    private Gold gold = new Gold();
    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        if (!containsItem(item) && item.getId() != Item.ItemID.GOLD)
        {
            this.items.add(item);
        }
    }
    public void removeItem(Item.ItemID id)
    {
        if (this.containsItem(id))
        {
            items.removeIf(i -> containsItem(id));
        }
    }

    public Armor getArmor()
    {
        for (Item i : items)
        {
            if (i.getId() == Item.ItemID.ARMOR)
            {
                return (Armor)i;
            }
        }
        return null;
    }
    public Sword getSword()
    {
        for (Item i : items)
        {
            if (i.getId() == Item.ItemID.SWORD)
            {
                return (Sword)i;
            }
        }
        return null;
    }

    public void addGold(int amount)
    {
        this.gold.addAmount(amount);
    }
    public int getGold()
    {
        return this.gold.getAmount();
    }
    public void removeGold(int amount)
    {
        this.gold.addAmount(-amount);
    }
    public void removeAllGold()
    {
        this.gold.setAmount(0);
    }

    public boolean containsItem(Item item)    {
        for (Item i : items)
        {
            if (i.getId() == item.getId())
            {
                return true;
            }
        }
        return false;
    }
    public boolean containsItem(Item.ItemID id)
    {
        for (Item i : items)
        {
            if (i.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.size(); i++)
        {
            switch (i)
            {
                case 0 -> result.append(items.get(i).getName());
                default ->
                {
                    result.append(", ");
                    result.append(items.get(i).getName());
                }
            }
        }
        result.append(".");
        return result.toString();
    }
}
