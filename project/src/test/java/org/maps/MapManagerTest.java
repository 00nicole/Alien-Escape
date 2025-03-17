package org.maps;

import org.game.GameScreen;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for the {@code MapManager} class.
 * Test cases for methods related to successful loading of maps.
 *
 * @author Usman Aziz
 */
public class MapManagerTest {
    /**
     * Following method checks if the map array and individual map elements are not null.
     */
    @Test
    public void testGetMapImg() {
        GameScreen screen = new GameScreen();
        MapManager mapManager = new MapManager(screen);
        assertNotNull(mapManager.map);
        assertNotNull(mapManager.map[0]);
        assertNotNull(mapManager.map[1]);
    }
    /**
     * Following method checks if map loading and their collision properties behave as per functionality.
     */
    @Test
    public void testLoadMap() {
        GameScreen screen = new GameScreen();
        MapManager mapManager = new MapManager(screen);

        boolean flag = mapManager.map[0].collision;
        boolean flag1 = mapManager.map[1].collision;

        assertEquals(true,flag);
        assertEquals(false,flag1);
        assertEquals("/maps/map1.txt", mapManager.maptxt[1]);
        assertEquals("/maps/map2.txt", mapManager.maptxt[2]);
        assertEquals("/maps/map3.txt", mapManager.maptxt[3]);
        assertEquals("/maps/map4.txt", mapManager.maptxt[4]);
        assertEquals("/maps/map5.txt", mapManager.maptxt[5]);
        assertEquals("/maps/map6.txt", mapManager.maptxt[6]);
    }
}