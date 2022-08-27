/*

 */

package entities;

import images.Sprite;
import inventory.Inventory;

import java.awt.*;
import java.util.Random;

public class Goblin extends Entity
{
    private Sprite sprite;

    public Goblin(int col, int row, int tileSize)
    {
        Random random = new Random();
        this.sprite = new Sprite("images/frog_soldier_32x32.png");
        this.setDirection(Direction.SOUTH);
        this.setX(random.nextInt(col - 1) * tileSize);
        this.setY(random.nextInt(row - 2) * tileSize);

        this.setName("Goblin");
        this.setHealth(random.nextInt(4) + 2);
        this.setArmor(random.nextInt(1));
        this.setStrength(random.nextInt(2) + 1);
        this.setDexterity(random.nextInt(2));
        this.setTurn(false); // human always goes first

        this.setInventory(new Inventory());
        this.getInventory().addGold(random.nextInt(20) + 1);
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
            case EAST -> {return sprite.getSprite(6).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
            default -> {return sprite.getSprite(9).getScaledInstance((int)(size * scale),
                    (int)(size * scale), 0);}
        }
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
