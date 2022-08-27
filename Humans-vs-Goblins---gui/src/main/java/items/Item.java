/*

 */

package items;

public class Item
{
    public enum ItemID
    {
        GOLD,
        SWORD,
        ARMOR
    }

    private String name;
    private ItemID itemId;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public ItemID getId()
    {
        return this.itemId;
    }
    protected void setItemId(ItemID itemId)
    {
        this.itemId = itemId;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name='" + name + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
