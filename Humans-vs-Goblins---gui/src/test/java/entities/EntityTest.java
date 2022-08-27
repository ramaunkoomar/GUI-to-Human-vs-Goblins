package entities;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EntityTest
{
    Entity entity;

    @BeforeEach
    void setUp()
    {
        entity = new Human( 4, 3, 48);
    }

    @Test
    void testLocation()
    {
        // initial entity location is random based on col and row and can't be tested.

        entity.setX(0);
        entity.setY(48);

        assertEquals(entity.getX(), 0);
        assertEquals(entity.getY(), 48);
    }

    @Test
    void testName()
    {
        entity.setName("name");
        assertEquals(entity.getName(), "name");
    }

    @Test
    void testHealth()
    {
        entity.setHealth(20);
        assertEquals(entity.getHealth(), 20);
    }

    @Test
    void testArmor()
    {
        entity.setArmor(10);
        assertEquals(entity.getArmor(), 10);
    }

    @Test
    void testStrength()
    {
        entity.setStrength(10);
        assertEquals(entity.getStrength(), 10);
    }

    @Test
    void testDexterity()
    {
        entity.setDexterity(10);
        assertEquals(entity.getDexterity(), 10);
    }

    @Test
    void testTurn()
    {
        entity.setTurn(true);
        assertEquals(entity.isTurn(), true);
    }

    @Test
    void testDirection()
    {
        entity.setDirection(Entity.Direction.SOUTH);
        assertEquals(entity.getDirection(), Entity.Direction.SOUTH);
    }

    @AfterEach
    void tearDown()
    {
    }
}