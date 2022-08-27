package entities;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TreasureTest
{
    Treasure treasure;

    @BeforeEach
    void setUp()
    {
        treasure = new Treasure(3, 3, 48);
    }

    @Test
    void testLocation()
    {
        treasure.setX(48);
        treasure.setY(96);
        assertEquals(treasure.getX(), 48);
        assertEquals(treasure.getY(), 96);
    }

    @Test
    void testState()
    {
        treasure.setState(1);
        assertEquals(treasure.getState(), 1);
    }

    @Test
    void isEmpty()
    {
        assertEquals(treasure.isEmpty(), false);
        treasure.removeTreasure();
        assertEquals(treasure.isEmpty(), true);
    }

    @AfterEach
    void tearDown()
    {

    }
}