package org.game;

import static org.junit.Assert.*;

import org.alien.ALIEN_green;
import org.junit.Before;
import org.junit.Test;
import org.object.*;

/**
 * JUnit test class for the {@code AssetSetter} class.
 * Following class verifies the functionality of the AssetSetterClass
 *
 * @author Usman Aziz
 */
public class AssetSetterTest {

    private GameScreen screen;
    private AssetSetter assetSetter;

    /**
     * Initialing the initial components needed by the class
     */
    @Before
    public void setUp() {
        screen = new GameScreen();
        assetSetter = new AssetSetter(screen);
    }
    /**
     * Following method check if the assets are refreshed to null
     */
    @Test
    public void testClear() {
        assetSetter.setObjectMap(1);
        assertNotNull(screen);
        assetSetter.clear();

        for (int i = 0; i < 20; i++) {
            assertNull(screen.obj[i]);
            assertNull(screen.alien[i]);
            assertNull(screen.portal[i]);
        }
    }

    @Test
    public void testUpdate(){

        screen.gameState=1;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time1-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(7*44,screen.obj[19].worldX);
        assertEquals(5*44,screen.obj[19].worldY);
        screen.aSetter.clear();

        screen.gameState=2;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time2-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(19*44,screen.obj[19].worldX);
        assertEquals(8*44,screen.obj[19].worldY);
        screen.aSetter.clear();

        screen.gameState=3;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time3-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(19*44,screen.obj[19].worldX);
        assertEquals(4*44,screen.obj[19].worldY);
        screen.aSetter.clear();

        screen.gameState=4;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time4-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(10*44,screen.obj[19].worldX);
        assertEquals(5*44,screen.obj[19].worldY);
        screen.aSetter.clear();

        screen.gameState=5;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time5-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(12*44,screen.obj[19].worldX);
        assertEquals(6*44,screen.obj[19].worldY);
        screen.aSetter.clear();

        screen.gameState=6;
        screen.aSetter.counter = 0;
        screen.aSetter.update(screen.gameState);
        assertNull(screen.obj[19]);
        screen.aSetter.counter = screen.aSetter.time6-1;
        screen.aSetter.update(screen.gameState);
        assertTrue(screen.obj[19] instanceof BonusReward);
        assertEquals(8*44,screen.obj[19].worldX);
        assertEquals(5*44,screen.obj[19].worldY);
        screen.aSetter.clear();
    }

    /**
     * Following method checks the object placements
     */
    @Test
    public void testSetObjectMap() {
        assetSetter.setObjectMap(1);
        assertEquals(2 * screen.tileSize, screen.player.worldX);
        assertEquals(screen.tileSize, screen.player.worldY);
        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);
        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);
        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);
        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertNotNull(screen.alien[0]);
        assertTrue(screen.alien[0] instanceof ALIEN_green);
        assertNotNull(screen.alien[1]);
        assertTrue(screen.alien[1] instanceof ALIEN_green);
        assertNotNull(screen.portal[0]);
        assertTrue(screen.portal[0] instanceof Portal);
        assertNotNull(screen.portal[1]);
        assertTrue(screen.portal[1] instanceof Portal);

        assetSetter.clear();
        setUp();
        //Testing Case 2
        assetSetter.setObjectMap(2);

        assertEquals(screen.player.worldX, screen.tileSize);
        assertEquals(screen.player.worldY, 2*screen.tileSize);

        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertEquals(screen.tileSize, screen.obj[0].worldX);
        assertEquals(8*screen.tileSize, screen.obj[0].worldY);



        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);

        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);

        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);

        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertEquals(screen.tileSize, screen.obj[4].worldX);

        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertEquals(screen.obj[5].worldX,10*screen.tileSize);

        assertNotNull(screen.obj[6]);
        assertTrue(screen.obj[6] instanceof Punishment);
        assertEquals(screen.obj[6].worldX,11*screen.tileSize);
        assertNotNull(screen.alien[0]);

        //case 3:
        assetSetter.clear();
        setUp();
        assetSetter.setObjectMap(3);

        assertEquals(screen.player.worldX, screen.tileSize);
        assertEquals(screen.player.worldY, 2*screen.tileSize);

        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertEquals(10*screen.tileSize, screen.obj[0].worldX);
        assertEquals(3*screen.tileSize, screen.obj[0].worldY);

        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);
        assertEquals(10*screen.tileSize, screen.obj[1].worldX);
        assertEquals(7*screen.tileSize, screen.obj[1].worldY);

        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);
        assertEquals(19*screen.tileSize, screen.obj[2].worldX);
        assertEquals(9*screen.tileSize, screen.obj[2].worldY);

        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);
        assertEquals(6*screen.tileSize, screen.obj[3].worldX);
        assertEquals(6*screen.tileSize, screen.obj[3].worldY);

        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertEquals(screen.tileSize, screen.obj[4].worldX);
        assertEquals(screen.tileSize, screen.obj[4].worldY);

        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertEquals(5*screen.tileSize, screen.obj[5].worldX);
        assertEquals(270, screen.obj[5].worldY);

        assertNotNull(screen.obj[6]);
        assertTrue(screen.obj[6] instanceof Punishment);
        assertEquals(14*screen.tileSize, screen.obj[6].worldX);
        assertEquals(270, screen.obj[6].worldY);

        assertNotNull(screen.obj[7]);
        assertTrue(screen.obj[7] instanceof Punishment);
        assertEquals(17*screen.tileSize, screen.obj[7].worldX);
        assertEquals(94, screen.obj[7].worldY);

        assertNotNull(screen.alien[0]);
        assertTrue(screen.alien[0] instanceof ALIEN_green);
        assertEquals(6*screen.tileSize, screen.alien[0].worldX);
        assertEquals(4*screen.tileSize, screen.alien[0].worldY);

        assertNotNull(screen.alien[1]);
        assertTrue(screen.alien[1] instanceof ALIEN_green);
        assertEquals(13*screen.tileSize, screen.alien[1].worldX);
        assertEquals(5*screen.tileSize, screen.alien[1].worldY);


        //case 4:
        assetSetter.clear();
        setUp();
        assetSetter.setObjectMap(4);

        assertEquals(screen.player.worldX, screen.tileSize);
        assertEquals(screen.player.worldY, 4*screen.tileSize);

        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertEquals(19*screen.tileSize, screen.obj[0].worldX);
        assertEquals(screen.tileSize, screen.obj[0].worldY);

        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);
        assertEquals(19*screen.tileSize, screen.obj[1].worldX);
        assertEquals(10*screen.tileSize, screen.obj[1].worldY);

        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);
        assertEquals(6*screen.tileSize, screen.obj[2].worldX);
        assertEquals(5*screen.tileSize, screen.obj[2].worldY);

        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);
        assertEquals(screen.tileSize, screen.obj[3].worldX);
        assertEquals(7*screen.tileSize, screen.obj[3].worldY);

        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertEquals(screen.tileSize, screen.obj[4].worldX);
        assertEquals(3*screen.tileSize, screen.obj[4].worldY);

        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertEquals(9*screen.tileSize, screen.obj[5].worldX);

        assertNotNull(screen.obj[6]);
        assertTrue(screen.obj[6] instanceof Punishment);
        assertEquals(11*screen.tileSize, screen.obj[6].worldX);
        assertEquals(226, screen.obj[6].worldY);

        assertNotNull(screen.obj[7]);
        assertTrue(screen.obj[7] instanceof Punishment);
        assertEquals(9*screen.tileSize, screen.obj[7].worldX);

        assertNotNull(screen.alien[0]);
        assertTrue(screen.alien[0] instanceof ALIEN_green);
        assertEquals(16*screen.tileSize, screen.alien[0].worldX);
        assertEquals(screen.tileSize, screen.alien[0].worldY);

        assertNotNull(screen.alien[1]);
        assertTrue(screen.alien[1] instanceof ALIEN_green);
        assertEquals(6* screen.tileSize, screen.alien[1].worldX);
        assertEquals(6* screen.tileSize, screen.alien[1].worldY);



        //case 5:
        assetSetter.clear();
        setUp();
        assetSetter.setObjectMap(5);

        assertEquals(screen.player.worldX/2, screen.tileSize);
        assertEquals(screen.player.worldY/5, screen.tileSize);

        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertEquals(screen.tileSize, screen.obj[0].worldX);
        assertEquals(8*screen.tileSize, screen.obj[0].worldY);

        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);
        assertEquals(19*screen.tileSize, screen.obj[1].worldX);
        assertEquals(screen.tileSize, screen.obj[1].worldY);

        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);
        assertEquals(11*screen.tileSize, screen.obj[2].worldX);
        assertEquals(10*screen.tileSize, screen.obj[2].worldY);

        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);
        assertEquals(10*screen.tileSize, screen.obj[3].worldX);
        assertEquals(5*screen.tileSize, screen.obj[3].worldY);

        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertEquals(screen.tileSize, screen.obj[4].worldX);
        assertEquals(5*screen.tileSize, screen.obj[4].worldY);

        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertEquals(4*screen.tileSize, screen.obj[5].worldX);
        assertEquals(94, screen.obj[5].worldY);

        assertNotNull(screen.obj[6]);
        assertTrue(screen.obj[6] instanceof Punishment);
        assertEquals(17*screen.tileSize, screen.obj[6].worldX);
        assertEquals(94, screen.obj[6].worldY);

        assertNotNull(screen.obj[7]);
        assertTrue(screen.obj[7] instanceof Punishment);
        assertEquals(19*screen.tileSize, screen.obj[7].worldX);
        assertEquals(270, screen.obj[7].worldY);

        assertNotNull(screen.alien[0]);
        assertTrue(screen.alien[0] instanceof ALIEN_green);
        assertEquals(5*screen.tileSize, screen.alien[0].worldX);
        assertEquals(2*screen.tileSize, screen.alien[0].worldY);

        assertNotNull(screen.alien[1]);
        assertTrue(screen.alien[1] instanceof ALIEN_green);
        assertEquals(7* screen.tileSize, screen.alien[1].worldX);
        assertEquals(7* screen.tileSize, screen.alien[1].worldY);


        //case 6:
        assetSetter.clear();
        setUp();
        assetSetter.setObjectMap(6);

        assertEquals(screen.player.worldX/2, screen.tileSize);
        assertEquals(screen.player.worldY, screen.tileSize);

        assertNotNull(screen.obj[0]);
        assertTrue(screen.obj[0] instanceof RegularReward);
        assertEquals(19*screen.tileSize, screen.obj[0].worldX);
        assertEquals(screen.tileSize, screen.obj[0].worldY);

        assertNotNull(screen.obj[1]);
        assertTrue(screen.obj[1] instanceof RegularReward);
        assertEquals(screen.tileSize, screen.obj[1].worldX);
        assertEquals(10*screen.tileSize, screen.obj[1].worldY);

        assertNotNull(screen.obj[2]);
        assertTrue(screen.obj[2] instanceof Rocket);
        assertEquals(19*screen.tileSize, screen.obj[2].worldX);
        assertEquals(10*screen.tileSize, screen.obj[2].worldY);

        assertNotNull(screen.obj[3]);
        assertTrue(screen.obj[3] instanceof PowerUp);
        assertEquals(19*screen.tileSize, screen.obj[3].worldX);
        assertEquals(5*screen.tileSize, screen.obj[3].worldY);

        assertNotNull(screen.obj[4]);
        assertTrue(screen.obj[4] instanceof Entrance);
        assertEquals(screen.tileSize, screen.obj[4].worldX);
        assertEquals(screen.tileSize, screen.obj[4].worldY);

        assertNotNull(screen.obj[5]);
        assertTrue(screen.obj[5] instanceof Punishment);
        assertEquals(10*screen.tileSize, screen.obj[5].worldX);
        assertEquals(94, screen.obj[5].worldY);

        assertNotNull(screen.obj[6]);
        assertTrue(screen.obj[6] instanceof Punishment);
        assertEquals(11*screen.tileSize, screen.obj[6].worldX);
        assertEquals(138, screen.obj[6].worldY);

        assertNotNull(screen.obj[7]);
        assertTrue(screen.obj[7] instanceof Punishment);
        assertEquals(13*screen.tileSize, screen.obj[7].worldX);
        assertEquals(402, screen.obj[7].worldY);

        assertNotNull(screen.alien[0]);
        assertTrue(screen.alien[0] instanceof ALIEN_green);
        assertEquals(8*screen.tileSize, screen.alien[0].worldX);
        assertEquals(5*screen.tileSize, screen.alien[0].worldY);

        assertNotNull(screen.alien[1]);
        assertTrue(screen.alien[1] instanceof ALIEN_green);
        assertEquals(13* screen.tileSize, screen.alien[1].worldX);
        assertEquals(5* screen.tileSize, screen.alien[1].worldY);
    }
}
