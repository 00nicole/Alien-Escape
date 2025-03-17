package org.alien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.entity.Player;
import org.game.GameScreen;
import org.game.KeyboardInput;
import org.junit.Before;
import org.junit.Test;

public class AlienTest {
    private Alien alien;
    private GameScreen screen;
    private Player player;

    @Before
    public void setUp() {
        screen = new GameScreen();
        screen.alien[0]= alien = new ALIEN_green(screen, 2, 2);
        alien = screen.alien[0];
        KeyboardInput keyboardInput = new KeyboardInput(null);
        screen.player= new Player(screen, keyboardInput);
        player = screen.player;
        
    }

    @Test
    public void testAlienConstructor(){
        
        assertEquals(88,alien.worldX); //2 * 44
        assertEquals(88,alien.worldY); //2 * 44
        assertEquals("down",alien.side);
        assertEquals(1,alien.speed);
        assertNotNull(alien.b1);
        assertNotNull(alien.b2);
    }

    @Test
    public void testAlienPlayerCollision(){

        player.worldX = 88;
        player.worldY = (9*44);
        alien.worldX = 88;
        alien.worldY = (9*44);
        player.side = "down";
        player.update();
        assertEquals(60,alien.attackTimer);
        alien.update();
        assertEquals(5,player.life);
        assertTrue(player.solidArea.intersects(alien.solidArea));

        player.side = "up";
        alien.attackTimer =0;
        player.update();
        assertEquals(60,alien.attackTimer);
        alien.update();
        assertEquals(4,player.life);
        assertTrue(player.solidArea.intersects(alien.solidArea));

        player.side = "left";
        alien.attackTimer =0;
        player.update();
        assertEquals(60,alien.attackTimer);
        alien.update();
        assertEquals(3,player.life);
        assertTrue(player.solidArea.intersects(alien.solidArea));

        player.side = "right";
        alien.attackTimer =0;
        player.update();
        assertEquals(60,alien.attackTimer);
        alien.update();
        assertEquals(2,player.life);
        assertTrue(player.solidArea.intersects(alien.solidArea)); 
    }

    @Test
    public void testAlienUpdate(){
        //test random movement:
        alien.worldX = 2 * 44;
        alien.worldY = 9 * 44;

        screen.player.worldX = 6 * 44;
        screen.player.worldY = 1 * 44;

        alien.update();
        assertTrue(alien.onPath);
        assertEquals(0,alien.possible);

        //check pathfinding UP
        alien.worldX = 2 * 44;
        alien.worldY = 9 * 44;
        player.worldX = 2 * 44;
        player.worldY = 8 * 44;
        alien.update();
        assertEquals(1,alien.possible);
        assertEquals("up",alien.side);
        assertEquals((2*44),alien.worldX);
        assertEquals((9*44-alien.speed),alien.worldY);

        //check pathfinding DOWN
        alien.worldX = 2 * 44;
        alien.worldY = 9 * 44;
        player.worldX = 2 * 44;
        player.worldY = 10 * 44;
        alien.update();
        assertEquals(1,alien.possible);
        assertEquals("down",alien.side);
        assertEquals((2*44),alien.worldX);
        assertEquals((9*44+alien.speed),alien.worldY);

        //check pathfinding LEFT
        alien.worldX = 2 * 44;
        alien.worldY = 9 * 44;
        player.worldX = 1 * 44;
        player.worldY = 9 * 44;
        alien.update();
        assertEquals(1,alien.possible);
        assertEquals("left",alien.side);
        assertEquals((2*44-alien.speed),alien.worldX);
        assertEquals((9*44),alien.worldY);

        //check pathfinding LEFT
        alien.worldX = 2 * 44;
        alien.worldY = 9 * 44;
        player.worldX = 3 * 44;
        player.worldY = 9 * 44;
        alien.update();
        assertEquals(1,alien.possible);
        assertEquals("right",alien.side);
        assertEquals((2*44+alien.speed),alien.worldX);
        assertEquals((9*44),alien.worldY);
    }
}
