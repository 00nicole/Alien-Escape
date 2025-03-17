package org.game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UITest {
    private UI ui;
    private GameScreen screen;
    private BufferedImage bufferedImage;
    Graphics2D graphics;


    @Before
    public void setUp() {
        screen = new GameScreen();
        ui = new UI(screen);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();
        ui.myGraphics = graphics;
    }

    @Test
    public void testDraw() {
        screen.gameState = screen.titleState;
        screen.player.score = 100;
        screen.player.life = 3;
        screen.player.hasPlanet = 5;
        screen.music.volSlider = 4;
        screen.sfx.volSlider = 6;
        screen.latestPlayState = screen.playState1;

        ui.draw(graphics);
        assertEquals(Color.YELLOW, ui.myGraphics.getColor());

        screen.gameState = screen.playState1;

        ui.draw(graphics);

        //assertEquals(ui.pixelmix, ui.myGraphics.getFont());
        assertEquals( Color.WHITE, ui.myGraphics.getColor());

        
    }

    @Test
    public void testShowMessage() {
        ui.showMessage("Message Test!!!");

        assertTrue(ui.textFlag); 
        assertEquals("Message Test!!!", ui.text);
    }

    @Test
    public void testDrawLife() {
        ui.drawLife();
        assertNotNull(ui.full);
        assertNotNull(ui.half);
        assertNotNull(ui.empty);
    }

    @Test
    public void testDisplayTime() {
        ui.onTime = 0;
        ui.textFlag = false;
        ui.text = "";
        ui.textCounter = 0;
        ui.displayTime();
        assertEquals( 1.0 / 60, ui.onTime, 0.001);
        assertEquals(Color.WHITE, ui.myGraphics.getColor());
    }

    @Test
    public void testDisplayScore() {
        screen.player.score = 100;
        ui.displayScore();

        //assertEquals( ui.pixelmix, ui.myGraphics.getFont());
        assertEquals(Color.WHITE, ui.myGraphics.getColor());
    }

    @Test
    public void testDisplayPlanetReward() {
        ui.displayPlanetReward();
        assertNotNull(ui.regRewardImg);
    }

    @Test
    public void testDrawTitleScreen() {
        ui.commandNum = 0;
        ui.drawTitleScreen();
        assertNotNull(ui.titleImg);
        ui.commandNum =1;
    }

    @Test
    public void testDrawGameOverScreen() {
        ui.commandNum = 0;
        ui.drawGameOverScreen();
        ui.commandNum =1;
        ui.drawGameOverScreen();
        
    }

    @Test
    public void testDisplayWinState(){
        screen.player.hasPlanet = 5;
        ui.onTime = 120.0;
        ui.displayWinState();
        /* assertEquals(45, ui.myGraphics.getFont().getSize());
        assertEquals("Game Won!", ui.myGraphics.getFontMetrics().getStringBounds("Game Won!", ui.myGraphics).getContents());
        assertEquals(Color.yellow, ui.myGraphics.getColor());

        assertEquals((16 * screen.tileSize) - (6 * screen.tileSize), ui.myGraphics.getFontMetrics().getStringBounds("Game Won!", ui.myGraphics).getX(), 0.001);
        assertEquals((12 * screen.tileSize) - (3 * screen.tileSize), ui.myGraphics.getFontMetrics().getStringBounds("Game Won!", ui.myGraphics).getY(), 0.001);

   
        assertEquals( (12 * screen.tileSize) - (2 * screen.tileSize), ui.myGraphics.getFontMetrics().getStringBounds("Planets Collected: 5", ui.myGraphics).getY(), 0.001);

        assertEquals( (12 * screen.tileSize) - screen.tileSize, ui.myGraphics.getFontMetrics().getStringBounds("You Played: 120.0 seconds.", ui.myGraphics).getY(), 0.001);
        assertEquals( (12 * screen.tileSize) + (2 * screen.tileSize), ui.myGraphics.getFontMetrics().getStringBounds("Congratulations!!", ui.myGraphics).getY(), 0.001);
 */
        assertNull(screen.gameThread);
    }

    @Test
    public void testEndGame_Quit() {
        ui.commandNum =0;
        ui.end_game();
        //assertTrue(ui.myGraphics.getFont().getSize() == 25F);
        assertEquals(Color.cyan, ui.myGraphics.getColor());
        ui.commandNum =1;
        ui.end_game();
        assertEquals(Color.cyan, ui.myGraphics.getColor());
        }
    


    @Test
    public void testDisplayPauseMenuTitle() {
        ui.displayPause();
        ui.commandNum =0;
        ui.displayPause();
        ui.commandNum =1;
        ui.displayPause();
        ui.commandNum =2;
        ui.displayPause();
        ui.commandNum =3;
        ui.displayPause();
        ui.commandNum =4;
        ui.displayPause();
    }

    @Test
    public void testDisplayPauseMenuHowToPlay() {
        ui.subState = 1;
        ui.displayPause();

    }

    @Test
    public void testDisplayPauseMenuEndGame() {
        ui.subState = 2;
        ui.displayPause();
    }


}
