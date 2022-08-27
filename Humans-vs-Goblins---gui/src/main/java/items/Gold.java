/*

 */

package items;

public class Gold extends Item
{
    private int amount;

    public Gold()
    {
        this.setName("Gold");
        this.setItemId(ItemID.GOLD);
    }

    public int getAmount()
    {
        return amount;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public void addAmount(int amount)
    {
        this.amount += amount;
    }

    @Override
    public String toString()
    {
        return "Gold{" +
                "amount=" + amount +
                '}';
    }
}
