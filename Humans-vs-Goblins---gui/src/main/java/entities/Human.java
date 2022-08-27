/*

 */

package entities;

import images.Sprite;
import inventory.Inventory;

import java.awt.*;
import java.util.Random;

public class Human extends Entity
{
    private Sprite sprite;

    public Human(int col, int row, int tileSize)
    {
        Random random = new Random();
        this.sprite = new Sprite("images/the_knight_32x32.png");
        this.setDirection(Direction.SOUTH);
        this.setX(random.nextInt(col - 1) * tileSize);
        this.setY(random.nextInt(row - 2) * tileSize);
        this.setInventory(new Inventory());
        this.setTurn(true); // human always goes first

        // default stats for a human.
        this.setHealth(10);
        this.setArmor(0);
        this.setStrength(2);
        this.setDexterity(2);
    }

    public Image getSprite(float scale)
    {
        int size = sprite.getSize();

        switch (this.getDirection())
        {
            case NORTH -> {return sprite.getSprite(2).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            case SOUTH -> {return sprite.getSprite(0).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            case EAST -> {return sprite.getSprite(4).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            default -> {return sprite.getSprite(7).getScaledInstance((int)(size * scale),
                        (int)(size * scale), 0);
            }
        }
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
