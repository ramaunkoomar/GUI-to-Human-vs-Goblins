package inventory;


import items.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest
{
    Inventory inventory;

    @BeforeEach
    void setUp()
    {
        inventory = new Inventory();
    }

    @Test
    void testItems()
    {
        Sword sword = new Sword();
        inventory.addItem(sword);

        ArrayList<Item> list = new ArrayList();
        list.add(sword);
        assertEquals(inventory.getItems(), list);

        inventory.removeItem(Item.ItemID.SWORD);
        list.remove(0);
        assertEquals(inventory.getItems(), list);
    }

    @Test
    void getArmor()
    {
        Armor armor = new Armor();
        inventory.addItem(armor);

        ArrayList<Item> list = new ArrayList();
        list.add(armor);
        assertEquals(inventory.getItems(), list);

        assertEquals(inventory.getArmor(), armor);
    }

    @Test
    void getSword()
    {
        Sword sword = new Sword();
        inventory.addItem(sword);

        ArrayList<Item> list = new ArrayList();
        list.add(sword);
        assertEquals(inventory.getItems(), list);

        assertEquals(inventory.getSword(), sword);
    }

    @Test
    void testGold()
    {
        inventory.addGold(10);
        assertEquals(inventory.getGold(), 10);
    }

    @Test
    void testContainsItem()
    {
        inventory.addItem(new Sword());
        assertEquals(inventory.containsItem(Item.ItemID.SWORD), true);
        assertEquals(inventory.containsItem(Item.ItemID.ARMOR), false);

        assertEquals(inventory.containsItem(new Sword()), true);
        assertEquals(inventory.containsItem(new Armor()), false);
    }

    @AfterEach
    void tearDown()
    {

    }
}