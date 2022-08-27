/*

 */

package entities;

import inventory.Inventory;
import java.util.Random;

public abstract class Entity
{
    public enum Direction
    {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    private int x;
    private int y;

    private String name;
    private int health;
    private int armor;
    private int strength;
    private int dexterity;

    private boolean turn;

    private Direction direction;

    private Inventory inventory;

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

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getArmor()
    {
        return armor;
    }
    public void setArmor(int armor)
    {
        this.armor = armor;
    }

    public int getStrength()
    {
        return strength;
    }
    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }
    public void setDexterity(int dexterity)
    {
        this.dexterity = dexterity;
    }

    public boolean isTurn()
    {
        return turn;
    }
    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }

    public Inventory getInventory()
    {
        return inventory;
    }
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public void randomLocation(int x, int y)
    {
        Random random = new Random();
        this.x = random.nextInt(x);
        this.y = random.nextInt(y);
    }

    public Direction getDirection()
    {
        return direction;
    }
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public String attack(Entity entity)
    {
        if (entity.dexterity / 10.0 >= Math.random())
        {
            return entity.name + " dodges the attack.";
        }

        int hit = this.strength;
        if (0.1 >= Math.random())
        {
            hit *= 2;
        }
        int damage = entity.armor - hit;

        if (damage == 0)
        {
            entity.armor = 0;
            return this.name + " broke " + entity.name + "'s armor.";
        }
        else if (damage < 0 )
        {
            entity.armor = 0;
            entity.health += damage;
            return this.name + " did " + Math.abs(damage) + " damage to " + entity.name;
        }
        else
        {
            entity.armor -= damage;
            System.out.println(entity.armor);
            System.out.println(damage);
            return entity.name + "'s armor took " + Math.abs(damage) + " damage.";
        }
    }
    public void move(Direction direction, int value)
    {
        switch (direction)
        {
            case NORTH -> {this.setY(this.getY() - value); this.direction = Direction.NORTH;}
            case SOUTH -> {this.setY(this.getY() + value); this.direction = Direction.SOUTH;}
            case EAST -> {this.setX(this.getX() + value); this.direction = Direction.EAST;}
            case WEST -> {this.setX(this.getX() - value); this.direction = Direction.WEST;}
        }
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Name: ");
        result.append(this.name);
        result.append(" Location: ");
        result.append(this.x);
        result.append(",");
        result.append(this.y);
        result.append(" Health: ");
        result.append(this.health);
        result.append(" Armor: ");
        result.append(this.armor);
        result.append(" Strength: ");
        result.append(this.strength);
        result.append(" Dexterity: ");
        result.append(this.dexterity);
        result.append(" Direction: ");
        result.append(this.direction);
        result.append(" Turn: ");
        result.append(this.turn);

        return result.toString();
    }
}
