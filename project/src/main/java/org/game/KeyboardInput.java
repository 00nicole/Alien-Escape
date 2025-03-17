package org.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Class that manages the user's keyboard input
 * 
 * @author Nicole Malku
 */
public class KeyboardInput implements KeyListener{

    GameScreen screen;
    public boolean upKey, downKey, leftKey, rightKey, enterPressed;
    //rivate boolean musicON = false;

    public KeyboardInput(GameScreen screen) {
        this.screen = screen;
    }
   
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /* 
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(screen.gameState == screen.titleState){
            if(code == KeyEvent.VK_A){
                screen.ui.commandNum--;
                if(screen.ui.commandNum < 0){
                    screen.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_D){
                screen.ui.commandNum++;
                if(screen.ui.commandNum > 1){
                    screen.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                if(screen.ui.commandNum == 0){
                    screen.stopMusic();
                    screen.startSFX(6);
                    try {
                        Thread.sleep(700);              
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    screen.gameState = screen.playState1;
                    screen.player.score=0;
                    screen.latestPlayState = screen.playState1;
                    screen.player.hasPlanet = 0;
                    screen.mapM.loadMap(screen.mapM.maptxt[1]);
                    screen.currentMapIMGpath = screen.mapM.mapIMG[1];
                    screen.aSetter.clear();
                    screen.aSetter.setObjectMap(1);
                    
                
                    screen.startMusic(0);
            
                }
            if(screen.ui.commandNum == 1){
                screen.stopMusic();
                screen.startSFX(7);
                try {
                    Thread.sleep(700);              
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.exit(0);
            }
            }
        }

        if(screen.playState1<=screen.gameState && screen.gameState <= screen.playState6 || screen.gameState == screen.pauseState){
            if(code == KeyEvent.VK_W){
                upKey = true;
            }
            else if(code == KeyEvent.VK_A){
                leftKey = true; 
            }
            else if(code == KeyEvent.VK_S){
                downKey = true;
            }
            else if(code == KeyEvent.VK_D){
                rightKey = true;
            }
            else if(code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE){
                screen.gameState = screen.pauseState;
            }
        if(screen.gameState == screen.pauseState){
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            screen.startSFX(5);
            int maxCmmdNum =0;
            switch(screen.ui.subState){
            case 0: 
                maxCmmdNum =4;
                break;
            case 2: 
                maxCmmdNum =1;
                break;

            }
            if(code == KeyEvent.VK_W){
                screen.ui.commandNum--;
                screen.startSFX(5);
                if(screen.ui.commandNum <0){
                    screen.ui.commandNum = maxCmmdNum;
                }
            }
            if(code == KeyEvent.VK_S){
                screen.ui.commandNum ++;
                screen.startSFX(5);
                if(screen.ui.commandNum > maxCmmdNum){
                    screen.ui.commandNum =0;
                }
            }
            if(code == KeyEvent.VK_DOWN){
                if(screen.ui.subState ==0){
                    if(screen.ui.commandNum  == 0 && screen.music.volSlider>0){
                        screen.music.volSlider --;
                        screen.music.volume();
                        screen.startSFX(5);
                    }
                    if(screen.ui.commandNum  == 1 && screen.sfx.volSlider>0){
                        screen.sfx.volSlider --;
                        screen.sfx.volume();
                        screen.startSFX(5);
                    }
                }
            }
            if(code == KeyEvent.VK_UP){
                if(screen.ui.subState ==0){
                    if(screen.ui.commandNum == 0 && screen.music.volSlider<5){
                        screen.music.volSlider ++;
                        screen.music.volume();
                        screen.startSFX(5);
                    }
                    if(screen.ui.commandNum  == 1 && screen.sfx.volSlider<5){
                        screen.sfx.volSlider ++;
                        screen.sfx.volume();
                        screen.startSFX(5);
                    }
                }
            }
        }
    }
    if(screen.gameState == screen.lossState){
        if(code == KeyEvent.VK_W){
            screen.ui.commandNum--;
            if(screen.ui.commandNum < 0){
                screen.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            screen.ui.commandNum++;
            if(screen.ui.commandNum > 1){
                screen.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(screen.ui.commandNum == 0){
                //screen.stopMusic();
                screen.gameState = screen.titleState;
                
                screen.restart();
                
            }
            else if(screen.ui.commandNum == 1){
                screen.stopMusic();
                screen.startSFX(7);
                try {
                    Thread.sleep(700);              
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.exit(0);
        }
        }
    }
    
    } */

     /**
     * Main method that calls the appropriate handlers for each game state.
     * 
     * @author Nicole Malku
     * @param e the key hit
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (screen.gameState == screen.titleState) {
            handleTitleStateInput(code);
        } 
        else if (screen.playState1 <= screen.gameState && screen.gameState <= screen.playState6 || screen.gameState == screen.pauseState) {
            handleGameInput(code);
            if (screen.gameState == screen.pauseState) {
                handlePauseInput(code);
            }
        } 
        else if (screen.gameState == screen.lossState) {
            handleLossStateInput(code);
        }
    }

    /**
     * Handles keyboard input on the title screen.
     * <p>
     * Adjusts the command number based on 'A' and 'D' key presses
     * by calling {@link #adjustCommandNum(int, int)}.
     * Calls {@link #handleTitleStateEnter()} to handle 'Enter' or 'Space' key presses.
     *
     * @author Nicole Malku
     * @param code The appropriate key code input
     * @see #adjustCommandNum(int, int)
     * @see #handleTitleStateEnter()
     */
    private void handleTitleStateInput(int code) {
        if (code == KeyEvent.VK_A) {
            adjustCommandNum(-1, 1);
        } else if (code == KeyEvent.VK_D) {
            adjustCommandNum(1, 1);
        } else if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            handleTitleStateEnter();
        }
    }

    /**
     * Handles the action triggered by the 'Enter' or 'Space' key press on the title screen.
     * <p>
     * If the current command number is 0, stops the music, plays a sound effect,
     * and transitions to the play state 1 after a short delay.
     * If the command number is 1, stops the music, plays a different sound effect,
     * and exits the game after a short delay.
     * 
     * @author Nicole Malku
     */
    private void handleTitleStateEnter() {
        if (screen.ui.commandNum == 0) {
            screen.stopMusic();
            screen.startSFX(6);
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            screen.gameState = screen.playState1;
            screen.player.score = 0;
            screen.latestPlayState = screen.playState1;
            screen.player.hasPlanet = 0;
            screen.mapM.loadMap(screen.mapM.maptxt[1]);
            screen.currentMapIMGpath = screen.mapM.mapIMG[1];
            screen.aSetter.clear();
            screen.aSetter.setObjectMap(1);
            screen.startMusic(0);
        } else if (screen.ui.commandNum == 1) {
            screen.stopMusic();
            screen.startSFX(7);
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        }
    }

    /**
     * Handles keyboard input during the gameplay state.
     * <p>
     * Sets corresponding movement flags based on 'W', 'A', 'S', 'D' key presses,
     * and triggers the pause state on 'P' or 'Escape' key press.
     *
     * @author Nicole Malku
     * @param code The appropriate key code input
     */
    private void handleGameInput(int code) {
        if (code == KeyEvent.VK_W) {
            upKey = true;
        } else if (code == KeyEvent.VK_A) {
            leftKey = true;
        } else if (code == KeyEvent.VK_S) {
            downKey = true;
        } else if (code == KeyEvent.VK_D) {
            rightKey = true;
        } else if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
            screen.gameState = screen.pauseState;
        }
    }

    /**
     * Handles keyboard input during the pause state.
     * <p>
     * Sets the 'Enter' key flag on 'Enter' key press, triggers a sound effect,
     * and adjusts the command number based on 'W' and 'S' key presses.
     * Also handles music and SFX adjustments on 'Up' and 'Down' arrow key presses.
     *
     * @author Nicole Malku
     * @param code The appropriate key code input
     * @see #adjustCommandNum(int, int)
     * @see #handleVolumeAdjustment(int)
     */
    private void handlePauseInput(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        screen.startSFX(5);
        int maxCommandNum = 0;
        switch (screen.ui.subState) {
            case 0:
                maxCommandNum = 4;
                break;
            case 2:
                maxCommandNum = 1;
                break;
        }
        if (code == KeyEvent.VK_W) {
            adjustCommandNum(-1, maxCommandNum);
        } 
        else if (code == KeyEvent.VK_S) {
            adjustCommandNum(1, maxCommandNum);
        } 
        else if (code == KeyEvent.VK_DOWN) {
            handleVolumeAdjustment(code);
        } 
        else if (code == KeyEvent.VK_UP) {
            handleVolumeAdjustment(code);
        }
    }

    /**
     * Handles both music and SFX adjustments based on 'Up' and 'Down' arrow key presses.
     * <p>
     * Adjusts the volume sliders for music and SFX and plays selection sound effects on each click.
     *
     * @author Nicole Malku
     * @param code The appropriate key code input
     */
    private void handleVolumeAdjustment(int code) {
        if(code == KeyEvent.VK_DOWN){
                if(screen.ui.subState ==0){
                    if(screen.ui.commandNum  == 0 && screen.music.volSlider>0){
                        screen.music.volSlider --;
                        screen.music.volume();
                        screen.startSFX(5);
                    }
                    if(screen.ui.commandNum  == 1 && screen.sfx.volSlider>0){
                        screen.sfx.volSlider --;
                        screen.sfx.volume();
                        screen.startSFX(5);
                    }
                }
            }
            if(code == KeyEvent.VK_UP){
                if(screen.ui.subState ==0){
                    if(screen.ui.commandNum == 0 && screen.music.volSlider<5){
                        screen.music.volSlider ++;
                        screen.music.volume();
                        screen.startSFX(5);
                    }
                    if(screen.ui.commandNum  == 1 && screen.sfx.volSlider<5){
                        screen.sfx.volSlider ++;
                        screen.sfx.volume();
                        screen.startSFX(5);
                    }
                }
            }
    }

    /**
     * Handles keyboard input during the loss screen
     * <p>
     * Adjusts the command number based on 'A' and 'D' key presses
     * and calls handleLossStateEnter() to deal with the 'Enter' key press.
     *
     * @author Nicole Malku
     * @param code The appropriate key code input
     * @see #adjustCommandNum(int, int)
     * @see #handleLossStateEnter()
     */
    private void handleLossStateInput(int code) {
        if (code == KeyEvent.VK_W) {
            adjustCommandNum(-1, 1);
        } else if (code == KeyEvent.VK_S) {
            adjustCommandNum(1, 1);
        } else if (code == KeyEvent.VK_ENTER) {
            handleLossStateEnter();
        }
    }

    /**
     * Handles the action made by the 'Enter' key press during the loss screen
     * <p>
     * If the command number is 0, it switches to the title screen and restarts the game.
     * If the command number is 1, stops the music, plays a sound effect, and exits the game.
     * 
     * @author Nicole Malku
     */
    private void handleLossStateEnter() {
        if (screen.ui.commandNum == 0) {
            screen.gameState = screen.titleState;
            screen.restart();
        } else if (screen.ui.commandNum == 1) {
            screen.stopMusic();
            screen.startSFX(7);
            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        }
    }

     /**
     * Adjusts the command number by a specified amount within a given max.
     * <p>
     * Increments or decrements the command number by the adjustment value
     * Triggers a sound effect upon adjustment.
     *
     * @param adjustment The amount by which to adjust the command number
     * @param max The max for the command number
     */
    private void adjustCommandNum(int adjustment, int max) {
        screen.ui.commandNum += adjustment;
        screen.startSFX(5);
        if (screen.ui.commandNum < 0) {
            screen.ui.commandNum = max;
        } else if (screen.ui.commandNum > max) {
            screen.ui.commandNum = 0;
        }
    }

    /**
     * Handles the release of clicking a key
     * 
     * @author Nicole Malku
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upKey = false;
        }
        if(code == KeyEvent.VK_A){
            leftKey = false;   
        }
        if(code == KeyEvent.VK_S){
            downKey = false;  
        }
        if(code == KeyEvent.VK_D){
            rightKey = false;  
        }

    }

}