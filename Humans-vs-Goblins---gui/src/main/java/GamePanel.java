/*

 */

import entities.*;
import images.Background;
import inventory.Inventory;
import items.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable
{
    private final int tileSize = 32;
    private final float scale = 1.5f;

    private final int scaledTileSize = (int)(tileSize * scale);
    private final int col = 20;
    private final int row = 15;
    private final int screenWidth = scaledTileSize * col;   // 960
    private final int screenHeight = scaledTileSize * row;  // 720

    private int fps = 60;
    private int currentFPS = 0;
    private int bodyCount = 0;

    private MainPanel ui;

    private Thread thread;
    private Human human;
    private boolean humanUpdated;
    private Goblin goblin;
    private Treasure treasure;
    private Background background;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.human = new Human(col, row, scaledTileSize);
        this.goblin = new Goblin(col, row, scaledTileSize);
        this.treasure = new Treasure(col, row, scaledTileSize);
        this.background = new Background("images/grass.png");
        this.humanUpdated = false;
        this.setFocusable(true);

        Rectangle swordIcon = new Rectangle();
        swordIcon.height = 32;
        swordIcon.width = 32;
        swordIcon.setLocation(screenWidth - tileSize * 2, screenHeight - tileSize);

        Rectangle armorIcon = new Rectangle();
        armorIcon.width = 32;
        armorIcon.height = 32;
        armorIcon.setLocation(screenWidth - tileSize, screenHeight - tileSize);

        this.addMouseListener(new MouseListener()
        {
            /**
             * Invoked when the mouse button has been clicked (pressed
             * and released) on a component.
             *
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Inventory inventory = human.getInventory();

                if (human.isTurn())
                {
                    if (swordIcon.contains(e.getX(), e.getY()) && inventory.containsItem(Item.ItemID.SWORD) &&
                            inventory.getSword().getState() == Sword.State.UNEQUIPPED)
                    {
                        inventory.getSword().setState(Sword.State.EQUIPPED);
                        human.setStrength(human.getStrength() + inventory.getSword().getDamage());
                        ui.appendConsole(human.getName() + " equips the " + inventory.getSword().getName(), Color.BLUE);
                        ui.updateStatus();

                    }
                    if (armorIcon.contains(e.getX(), e.getY()) && inventory.containsItem(Item.ItemID.ARMOR) &&
                            inventory.getArmor().getState() == Armor.State.UNEQUIPPED)
                    {
                        inventory.getArmor().setState(Armor.State.EQUIPPED);
                        human.setArmor(human.getArmor() + inventory.getArmor().getArmor());
                        ui.appendConsole(human.getName() + " equips the " + inventory.getArmor().getName(), Color.BLUE);
                        ui.updateStatus();
                    }
                }

            }

            /**
             * Invoked when a mouse button has been pressed on a component.
             *
             * @param e the event to be processed
             */
            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            /**
             * Invoked when a mouse button has been released on a component.
             *
             * @param e the event to be processed
             */
            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            /**
             * Invoked when the mouse enters a component.
             *
             * @param e the event to be processed
             */
            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            /**
             * Invoked when the mouse exits a component.
             *
             * @param e the event to be processed
             */
            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        });
    }

    public Human getHuman()
    {
        return human;
    }

    public void setUi(MainPanel ui)
    {
        this.ui = ui;
    }

    /**
     *
     */
    public void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    public void updateHuman(String str)
    {
        int startX = human.getX();
        int startY = human.getY();

        switch (str)
        {
            case "North" ->
            {
                human.move(Entity.Direction.NORTH, scaledTileSize);
                humanUpdated = true;
            }
            case "South" ->
            {
                human.move(Entity.Direction.SOUTH, scaledTileSize);
                humanUpdated = true;
            }
            case "East" ->
            {
                human.move(Entity.Direction.EAST, scaledTileSize);
                humanUpdated = true;
            }
            case "West" ->
            {
                human.move(Entity.Direction.WEST, scaledTileSize);
                humanUpdated = true;
            }
        }

        if (human.getX() < 0 || human.getY() < 0 || human.getX() == screenWidth
                || human.getY() == screenHeight || human.getHealth() == 0)
        {
            human.setX(startX);
            human.setY(startY);
            humanUpdated = false;
            ui.appendConsole("You are unable to move any further to the " + str.toLowerCase() + ".", Color.RED);

        }
        if (humanUpdated)
        {
            ui.appendConsole("You moved to the " + str.toLowerCase() + ".", null);
        }
    }

    private void updateGoblin()
    {
        int distanceX = human.getX() - goblin.getX();
        int distanceY = human.getY() - goblin.getY();

        if (Math.abs(distanceX) < Math.abs(distanceY) && Math.abs(distanceX) >= scaledTileSize || distanceY == 0)
        {
            if (distanceX > 0)
            {
                goblin.move(Entity.Direction.EAST, scaledTileSize);
            }
            else
            {
                goblin.move(Entity.Direction.WEST, scaledTileSize);
            }
        }
        else if (Math.abs(distanceX) >= Math.abs(distanceY) && Math.abs(distanceY) >= scaledTileSize || distanceX == 0)
        {
            if (distanceY > 0)
            {
                goblin.move(Entity.Direction.SOUTH, scaledTileSize);
            }
            else
            {
                goblin.move(Entity.Direction.NORTH, scaledTileSize);
            }
        }
    }

    private void checkTreasure()
    {
        if (human.getX() == treasure.getX() && human.getY() == treasure.getY())
        {
            Inventory inventory = human.getInventory();

            if (!treasure.isEmpty())
            {
                treasure.setState(1);
                ui.appendConsole("You break open the treasure chest and obtain " +
                        treasure.getName() + ".", Color.BLUE);
                if (inventory.containsItem(treasure.getTreasure()))
                {
                    ui.appendConsole("You're too weak to carry more than one " +
                            treasure.getTreasure().getName() + ".", Color.RED);
                }
                inventory.addItem(treasure.removeTreasure());

            }
        }
    }

    private void checkCombat()
    {
        if (human.getX() == goblin.getX() && human.getY() == goblin.getY())
        {
            Inventory inventory = human.getInventory();

            while (human.getHealth() > 0 && goblin.getHealth() > 0)
            {
                ui.appendConsole(human.attack(goblin), Color.BLUE);
                if (goblin.getHealth() <= 0)
                {
                    ui.appendConsole("The goblin has been killed.", Color.BLUE);
                    ui.appendConsole("The goblin dropped " + goblin.getInventory().getGold() + " gold.",
                            null);
                    human.getInventory().addGold(goblin.getInventory().getGold());

                    goblin = new Goblin(col, row, scaledTileSize);
                    treasure = new Treasure(col, row, scaledTileSize);
                    bodyCount++;
                    break;
                }
                ui.appendConsole(goblin.attack(human), Color.RED);
                if (human.getHealth() <= 0)
                {
                    human.setHealth(0);
                    ui.appendConsole("GAME OVER!", Color.RED);
                    ui.appendConsole("you killed " + bodyCount + " goblins.", null);
                    thread = null;
                }
            }
            if (inventory.containsItem(Item.ItemID.SWORD) &&
                    inventory.getSword().getState() == Sword.State.EQUIPPED)
            {
                human.setStrength(human.getStrength() - inventory.getSword().getDamage());
                inventory.removeItem(Item.ItemID.SWORD);
            }
            if (inventory.containsItem(Item.ItemID.ARMOR) &&
                    inventory.getArmor().getState() == Armor.State.EQUIPPED)
            {
                human.setArmor(0);
                inventory.removeItem(Item.ItemID.ARMOR);
            }

            ui.updateStatus();
        }
    }

    private void update()
    {
        if (human.isTurn() && humanUpdated)
        {
            checkTreasure();
            checkCombat();
            human.setTurn(false);
            humanUpdated = false;
            goblin.setTurn(true);
        }
        else if (goblin.isTurn())
        {
            updateGoblin();
            checkCombat();
            goblin.setTurn(false);
            human.setTurn(true);
        }
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        // background
        graphics.drawImage(background.getBackground(), 0, 0, this);

        // human
        graphics.drawImage(human.getSprite(scale), human.getX(), human.getY(), this);

        // goblin
        graphics.drawImage(goblin.getSprite(scale), goblin.getX(), goblin.getY(), this);

        // treasure
        graphics.drawImage(treasure.getSprite(scale), treasure.getX(), treasure.getY(), this);

        // etc
        if (human.getInventory().containsItem(Item.ItemID.SWORD))
        {
            graphics.drawImage(human.getInventory().getSword().
                    getSprite(1, human.getInventory().getSword().getState()),
                    screenWidth - tileSize * 2, screenHeight - tileSize, this);

        }
        if (human.getInventory().containsItem(Item.ItemID.ARMOR))
        {
            graphics.drawImage(human.getInventory().getArmor()
                    .getSprite(1, human.getInventory().getArmor().getState()),
                    screenWidth - tileSize, screenHeight - tileSize, this);
        }

        graphics.dispose();
    }

    /**
     *
     */
    @Override
    public void run()
    {
        double interval = 1000.0/fps;
        double delta = 0;
        long lastDrawTime = System.currentTimeMillis();
        long currentTime;
        long timer = 0;

        while (thread != null)
        {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastDrawTime) / interval;
            timer += currentTime - lastDrawTime;
            lastDrawTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                currentFPS++;
            }
            if (timer >= 1000)
            {
                currentFPS = 0;
                timer = 0;
            }
        }
    }
}
