/*

 */

package items;

import images.Sprite;
import java.awt.*;

public class Armor extends Item
{
    public enum State
    {
        NONE,
        UNEQUIPPED,
        EQUIPPED
    }

    private Sprite sprite;
    private int armor;
    private State state;

    public Armor()
    {
        this.setName("slightly worn armor");
        this.setItemId(ItemID.ARMOR);
        this.sprite = new Sprite("images/armor.png");
        this.armor = 2;
        this.state = State.UNEQUIPPED;
    }

    public int getArmor()
    {
        return armor;
    }

    public State getState()
    {
        return this.state;
    }
    public void setState(State state)
    {
        this.state = state;
    }

    public Image getSprite(float scale, State state)
    {
        int size = sprite.getSize();

        switch (state)
        {
            case EQUIPPED -> {return sprite.getSprite(0).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            case UNEQUIPPED -> {return sprite.getSprite(1).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            default -> {return sprite.getSprite(2).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
        }
    }

    @Override
    public String toString()
    {
        return "Armor{" +
                "armor=" + armor +
                ", state=" + state +
                '}';
    }
}


