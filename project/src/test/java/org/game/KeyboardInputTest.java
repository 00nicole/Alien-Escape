package org.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.event.KeyEvent;

public class KeyboardInputTest {

    private GameScreen screen;
    private KeyboardInput keyboardInput;

    @Before
    public void setUp() {
        screen = new GameScreen(); // Assuming you have a default constructor for GameScreen
        keyboardInput = new KeyboardInput(screen);
    }

    @Test
    public void testKeyPressedTitleState() {
        screen.gameState = screen.titleState;
        KeyEvent keyEventA = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyboardInput.keyPressed(keyEventA);
        assertTrue(screen.ui.commandNum == 1);

        KeyEvent keyEventD = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyboardInput.keyPressed(keyEventD);
        assertTrue(screen.ui.commandNum == 0);

    }

    @Test
    public void testKeyPressedTitleEnter(){
        screen.gameState = screen.titleState;
        screen.ui.commandNum =0;
        KeyEvent keyEventEnter = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n');
        keyboardInput.keyPressed(keyEventEnter);
        assertEquals(screen.playState1, screen.gameState);
    }

    @Test
    public void testKeyPressedTitleExit(){
        screen.gameState = screen.titleState;
        screen.ui.commandNum =1;
        //KeyEvent keyEventEnter = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n');
        //keyboardInput.keyPressed(keyEventEnter);
        
    }

    @Test
    public void testKeyPressedPlayState() {
        screen.gameState = screen.playState1;
        KeyEvent keyEventW = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyboardInput.keyPressed(keyEventW);
        assertTrue(keyboardInput.upKey);

        KeyEvent keyEventA = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyboardInput.keyPressed(keyEventA);
        assertTrue(keyboardInput.leftKey);

        KeyEvent keyEventS = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'S');
        keyboardInput.keyPressed(keyEventS);
        assertTrue(keyboardInput.leftKey);

        KeyEvent keyEventD = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'D');
        keyboardInput.keyPressed(keyEventD);
        assertTrue(keyboardInput.leftKey);
    }

    @Test
    public void testKeyPressedPauseState() {
        screen.gameState = screen.pauseState;
        screen.ui.subState = 0;
        KeyEvent keyEventE = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n');
        keyboardInput.keyPressed(keyEventE);
        assertTrue(keyboardInput.enterPressed);

        KeyEvent keyEventS = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyboardInput.keyPressed(keyEventS);
        assertEquals(screen.ui.commandNum, 1);

        KeyEvent keyEventW = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyboardInput.keyPressed(keyEventW);
        assertEquals(screen.ui.commandNum, 0);

        //KeyEvent keyEventDown = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, '↓');
        //keyboardInput.keyPressed(keyEventDown);

        //KeyEvent keyEventUp = new KeyEvent(screen, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, '↑');
        //keyboardInput.keyPressed(keyEventUp);

        keyboardInput.keyPressed(keyEventS);
        //keyboardInput.keyPressed(keyEventDown);
        //keyboardInput.keyPressed(keyEventUp);




    }
    
    @Test
    public void testKeyReleasedW() {
        screen.gameState = screen.playState1;
        //KeyEvent keyEventW = new KeyEvent(screen, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        //keyboardInput.keyPressed(keyEventW);
        //assertTrue(keyboardInput.upKey);

        //keyboardInput.keyReleased(keyEventW);
        assertFalse(keyboardInput.upKey);
    }

    @Test
    public void testKeyReleasedA() {
        screen.gameState = screen.playState1;
        KeyEvent keyEventA = new KeyEvent(screen, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyboardInput.keyPressed(keyEventA);
        assertTrue(keyboardInput.leftKey);

        keyboardInput.keyReleased(keyEventA);
        assertFalse(keyboardInput.upKey);
    }

    @Test
    public void testKeyReleasedS() {
        screen.gameState = screen.playState1;
        KeyEvent keyEventS = new KeyEvent(screen, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyboardInput.keyPressed(keyEventS);
        assertTrue(keyboardInput.downKey);

        keyboardInput.keyReleased(keyEventS);
        assertFalse(keyboardInput.upKey);
    }

    @Test
    public void testKeyReleasedD() {
        screen.gameState = screen.playState1;
        KeyEvent keyEventD = new KeyEvent(screen, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyboardInput.keyPressed(keyEventD);
        assertTrue(keyboardInput.rightKey);

        keyboardInput.keyReleased(keyEventD);
        assertFalse(keyboardInput.upKey);
    }
   

    @Test
    public void testKeyTyped() {
        // No specific functionality for keyTyped, just checking for no exceptions
        KeyEvent keyEvent = new KeyEvent(screen, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'a');
        keyboardInput.keyTyped(keyEvent);
    }



}

