package org.entity;

import org.game.GameScreen;
import org.game.KeyboardInput;
import org.junit.Before;
import org.junit.Test;
import org.object.*;


import static org.junit.Assert.*;

public class PlayerTest{
    private Player player;
    private GameScreen screen;
    private KeyboardInput keyboardInput;

    @Before
    public void setUp() {
        screen = new GameScreen();
        keyboardInput  = new KeyboardInput(null);
        player = new Player(screen, keyboardInput);
    }

    public void testStart() {
    }
    @Test
    public void testSetDefaultVals() {
        setUp();
        player.setDefaultVals();
        assertEquals(screen.tileSize, player.worldX);
        assertEquals(screen.tileSize, player.worldY);
        assertEquals(3, player.speed);
        assertEquals("down", player.side);
        assertEquals(6, player.maxLife);
        assertEquals(6, player.life);
    }
    @Test
    public void testSetDefaultPositions() {
        player.setDefaultPositions();
        assertEquals(screen.tileSize, player.worldX);
        assertEquals(screen.tileSize, player.worldY);
        assertEquals("down", player.side);
    }
    @Test
    public void testRestoreLife() {
        player.restoreLife();
        assertEquals(player.maxLife, player.life);
    }
    @Test
    public void testGetImage() {
        assertNotNull(player.f1);
        assertNotNull(player.f2);
        assertNotNull(player.b1);
        assertNotNull(player.b2);
        assertNotNull(player.l1);
        assertNotNull(player.l2);
        assertNotNull(player.r1);
        assertNotNull(player.r2);
    }
    @Test
    public void testUpdate() {

        //move player to (2,9) to allow for full movement
        player.worldX=2*screen.tileSize;
        player.worldY=9*screen.tileSize;
        keyboardInput.upKey = true;
        player.update();
        assertEquals("up", player.side);
        assertEquals(9*screen.tileSize - player.speed, player.worldY);

        keyboardInput.upKey = false;
        keyboardInput.leftKey = true;
        player.update();
        assertEquals("left", player.side);
        assertEquals(2*screen.tileSize - player.speed, player.worldX);

        keyboardInput.leftKey = false;
        keyboardInput.downKey = true;
        player.update();
        assertEquals("down", player.side);
        assertEquals(9*screen.tileSize, player.worldY);

        keyboardInput.downKey = false;
        keyboardInput.rightKey = true;
        player.update();
        assertEquals("right", player.side);
        assertEquals(2*screen.tileSize, player.worldX);
    }
    @Test
    public void testConsumeObject() {
        SuperObject regularReward = new RegularReward();
        screen.obj[0] = regularReward;
        player.consumeObject(0);
        assertEquals(1, player.hasPlanet);
        assertEquals(200, player.score);

        SuperObject bonusReward = new BonusReward();
        screen.obj[1] = bonusReward;
        player.consumeObject(1);
        assertEquals(400, player.score);

        SuperObject powerUp = new PowerUp();
        screen.obj[3] = powerUp;
        player.consumeObject(3);

        assertEquals(600, player.score);

        SuperObject rocket = new Rocket();
        screen.obj[4] = rocket;
        player.consumeObject(4);
        //assertEquals(screen.winState, screen.latestPlayState);
    }
    @Test
    public void testCheckWin() {
        player.hasPlanet = 12;
        assertFalse(screen.ui.endGame);
        player.checkWin();
        assertTrue(screen.ui.endGame);
        screen.ui.endGame = false;
        player.hasPlanet = 2;
        assertFalse(screen.ui.endGame);
        screen.gameState = 1;
        assertFalse(screen.ui.endGame);
        player.checkWin();
        assertTrue(screen.ui.endGame);
    }
    @Test
    public void testCheckLoss() {
        setUp();
        player.life = 0;
        player.checkLoss();
        assertTrue(screen.ui.endGame);

        setUp();
        player.life = 1;
        player.score = -10;
        player.checkLoss();
        assertTrue(screen.ui.endGame);

        setUp();
        player.life = 1; // Resetting life
        player.score = 10;
        player.checkLoss();
        assertFalse(screen.ui.endGame);

        setUp();
        player.life = 0; // Resetting life
        player.score = 10;
        player.checkLoss();
        assertTrue(screen.ui.endGame);
    }
}