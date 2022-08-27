/*

 */

package entities;

import images.Sprite;
import items.Armor;
import items.Item;
import items.Sword;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Treasure
{
    private int state = 0; // 0 unopened, 1 opened
    private Sprite sprite;
    private int x;
    private int y;
    private ArrayList<Item> items = new ArrayList<>();


    public Treasure(int col, int row, int tileSize)
    {
        Random random = new Random();
        sprite = new Sprite("images/chest_32x32.png");
        this.setX(random.nextInt(col - 1) * tileSize);
        this.setY(random.nextInt(row - 2) * tileSize);

        int item = new Random().nextInt(10);
        if (item < 7)
        {
            items.add(new Sword());
        }
        else
        {
            items.add(new Armor());
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Image getSprite(float scale)
    {
        int size = sprite.getSize();

        switch (state)
        {
            case 0 -> {return sprite.getSprite(0).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            default -> {return sprite.getSprite(1).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
        }
    }

    public int getState()
    {
        return state;
    }
    public void setState(int state)
    {
        this.state = state;
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public String getName()
    {
        return items.get(0).getName();
    }

    public Item getTreasure()
    {
        if (!items.isEmpty())
        {
            return items.get(0);
        }
        return null;
    }
    public Item removeTreasure()
    {
        Item result = null;
        if (!items.isEmpty())
        {
            result = items.get(0);
            items.remove(0);
        }
        return result;
    }

    @Override
    public String toString()
    {
        return "Treasure{" +
                "state=" + state +
                ", sprite=" + sprite +
                ", x=" + x +
                ", y=" + y +
                ", items=" + items +
                '}';
    }
}
