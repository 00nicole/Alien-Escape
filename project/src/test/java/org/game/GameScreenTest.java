package org.game;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * JUnit test class for the {@code GameScreen} class.
 * The class tests the functionality of the GameScreen class
 *
 * @author Usman Aziz
 */
public class GameScreenTest{

    /**
     * Following method checks if the fundamental game variables are initialized
     */
    @Test
    public void testGameInitially(){
        GameScreen testScreen = new GameScreen();

        assertNotNull(testScreen);
        System.out.println(testScreen.screenWidth);
        assertEquals((testScreen.tileSize*testScreen.maxScreenCol), testScreen.screenWidth);
        assertEquals(528, testScreen.screenHeight);
        assertNotNull(testScreen.keyInput);
        assertNotNull(testScreen.music);
        assertNotNull(testScreen.sfx);
        assertNotNull(testScreen.cChecker);
        assertNotNull(testScreen.aSetter);
        assertNotNull(testScreen.portalHandler);
        assertNotNull(testScreen.pFinder);
        assertNotNull(testScreen.player);
        assertNotNull(testScreen.mapM);
        assertNotNull(testScreen.obj);
        assertNotNull(testScreen.alien);
        assertNotNull(testScreen.portal);
        assertEquals(60, testScreen.fps);
        assertEquals(0, testScreen.gameState);
        assertEquals(0, testScreen.latestPlayState);
        assertNotNull(testScreen.ui);
    }
    /**
     * Following method check if the gameState and music clips work as per functionality
     */
    @Test
    public void testSetupGame() {
        GameScreen testScreen = new GameScreen();
        testScreen.setupGame();
        assertNotNull(testScreen.music.clip);
        assertEquals(0, testScreen.gameState);
        assertEquals(testScreen.titleState, testScreen.gameState);
    }
    /**
     * Following method checks if the game is restarted properly
     */
    @Test
    public void testRestart() {
        GameScreen testScreen = new GameScreen();
        testScreen.player.worldX=3*testScreen.tileSize;
        testScreen.player.worldY=9*testScreen.tileSize;

        assertNotEquals(testScreen.tileSize, testScreen.player.worldX);
        assertNotEquals(testScreen.tileSize, testScreen.player.worldY);

        testScreen.restart();
        assertEquals(testScreen.tileSize, testScreen.player.worldX);
        assertEquals(testScreen.tileSize, testScreen.player.worldY);
    }
    /**
     * Following method checks for the proper initialization of the game thread
     */
    @Test
    public void testStartGameThread() {
        GameScreen testScreen = new GameScreen();
        assertNull(testScreen.gameThread);
        testScreen.startGameThread();
        assertNotNull(testScreen.gameThread);
    }
    /**
     * Following method checks the run functionality of the gameScreen,
     * but due to its high complexity it is not yet tested
     */
    @Test
    public void testTestRun() {
    }
    @Test
    public void testUpdate() {
    }
    @Test
    public void testPaintComponent() {
    }
    @Test
    public void testStartMusic() {
    }
    @Test
    public void testStopMusic() {
    }
    @Test
    public void testStartSFX() {
    }
}