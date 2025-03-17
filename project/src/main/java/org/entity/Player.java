package org.entity;

import org.game.KeyboardInput;
import org.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.game.GameScreen;

/**
 * The Player class represents the player character in the game. It extends the base Entity class and
 * includes methods for updating the player's position, handling user input, and drawing the character on the screen.
 * Additionally, it contains methods for loading player character images and managing interactions with game objects.
 *
 * @author Nicole Malku
 * @author Usman Aziz
 * @author Curtis Pu
 * @author Ryan Chong
 */

public class Player extends Entity {
    KeyboardInput keyInput;
    public int hasPlanet = 0;
    public int canPortal = 0;
    public int score = 0;

    /**
     * Constructs a Player object associated with GameScreen and KeyboardInput instance.
     * This constructor initializes the player's screen coordinates, collision area, and default values.
     * It also sets up the player's starting position and loads the player character images.
     *
     *
     *
     * @param screen The GameScreen instance to associate with this Player.
     * @param keyInput The KeyboardInput instance to handle user input.
     */
    public Player(GameScreen screen, KeyboardInput keyInput){
        this.screen = screen;
        this.keyInput = keyInput;

        solidArea.x = 6;
        solidArea.y = 2;
        solidArea.width = 20;
        solidArea.height = 30;

        //solidArea.y + solidArea.height = 32!!!

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultVals();
        getImage();
    }

    public void start(int map){
        switch(map){
            case 1:
        }

    }
    public void setDefaultVals(){
        worldX = (screen.tileSize);
        worldY = (screen.tileSize);
        speed = 3;
        //player status
        maxLife = 6;
        life = maxLife;
    }

    public void setDefaultPositions(){
        worldX = (screen.tileSize);
        worldY = (screen.tileSize);
    }

    public void restoreLife(){
        life = maxLife;
    }

    /**
     * Loads images for different directions and animation frames of the player character.
     * <p>
     * This method uses the ImageIO class to read images from the specified file paths for
     * various directions (left, right, up, down) and animation frames (1, 2). The loaded images
     * are assigned to the corresponding variables (l1, l2, r1, r2, f1, f2, b1, b2) for
     * later use in drawing the player's character.
     * 
     */
    public void getImage(){
        try {
            // Load player character images for different directions and frames
            l1 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_l1.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_l2.png"));
            r1 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_r1.png"));
            r2 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_r2.png"));
            f1 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_f1.png"));
            f2 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_f2.png"));
            b1 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_b1.png"));
            b2 = ImageIO.read(getClass().getResourceAsStream("/astronauts/gray/gray_b2.png"));
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the position and state of the player object based on user input and collisions.
     * <p>
     * This method checks the state of W,A,S,D keys (up, left, down, right) and updates
     * the player's position accordingly. It then performs collision detection with the game world
     * tiles and other objects. If a collision occurs with an object, it triggers the corresponding
     * action, such as consumption or loss checking.
     * <p>
     * The method also manages a side-switching mechanism for animation purposes when the player
     * is in motion.
     *
     */
    public void update(){
        if(keyInput.upKey)  { side = "up"; }
        if(keyInput.leftKey){ side = "left"; }
        if(keyInput.downKey){ side = "down"; }
        if(keyInput.rightKey){side = "right"; }

        collisionOn = false;
        screen.cChecker.checkTile((this));

        //Handling Object collision
        int otherIndex = screen.cChecker.checkOther(this,true);
        consumeObject(otherIndex);
        checkLoss();

        if(canPortal > 0){
            canPortal--;
        }
        for(int i = 0; screen.portal[i] != null; i++ )
        {   
            if(canPortal == 0){
            screen.portalHandler.checkportal(screen.portal[i]);
            }
        }

        for(int i = 0; i < screen.obj.length; i++ )
        {   
            if(screen.obj[i]!=null){
                if(screen.obj[i].update() == 1){
                screen.obj[i] = new SuperObject();
                }
            }
        }

        if(!collisionOn){
            if(keyInput.upKey || keyInput.leftKey || keyInput.downKey || keyInput.rightKey){
                this.move(side);

                sideSwitcher++;
                if(sideSwitcher >15){
                    if(sideNum == 1){
                        sideNum =2;
                    }
                    else if(sideNum == 2){
                        sideNum= 1;
                    }
                    sideSwitcher =0;
        
                }
            }
            
        }
    }
    /**
     * Consumes game objects based on collision index and updates the player's status accordingly.
     *
     * @param index The index of the collided object in the game object array.
     */
    public void consumeObject(int index){
        if(index != 999){
            String objectName =screen.obj[index].name;
            switch(objectName){
            case "RegularReward":
                screen.startSFX(1);
                hasPlanet ++;
                score+=200;
                screen.obj[index] = null;
                screen.ui.showMessage("Regular Reward Collected.");
                //checkWin();
                break;
            case "BonusReward":
                screen.startSFX(1);
                score= score*2;
                screen.obj[index] = null;
                screen.ui.showMessage("Double Score!!");
                break;
            case "Punishment":
                screen.startSFX(10);
                score-=150;
                screen.obj[index] = null;
                screen.ui.showMessage("PUNISHMENT!");
                break;
            case "PowerUp":
                screen.startSFX(1);
                score+=200;
                if(life<6)
                    life++;
                screen.obj[index] = null;
                screen.ui.showMessage("Life +1");
                //checkWin();
                break;
            case "Rocket":
                checkWin();
                break;
            }

        }
    }

    /**
     * 
     * Helper function to continually check if the player has won the game/level. Facilitiates transition between maps.
     * 
     * 
     */
    public void checkWin(){ 

        if(hasPlanet == 12){
            screen.stopMusic();
            try {
                Thread.sleep(700);              
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            screen.startMusic(2);
            screen.gameState=screen.winState;
            screen.ui.endGame=true;
            return;
        }
        if(hasPlanet == 2 * screen.gameState && hasPlanet >= 2){
        screen.gameState++;
        screen.latestPlayState= screen.gameState;
        screen.ui.endGame=true;
        screen.mapM.loadMap(screen.mapM.maptxt[screen.gameState]);

        screen.currentMapIMGpath = screen.mapM.mapIMG[screen.gameState];
        screen.aSetter.clear();
        screen.aSetter.setObjectMap(screen.gameState);
        }
    }

    /**
     * Helper function to check if the player has lost the game
     * 
     *
     */
    public void checkLoss(){ 
        if(life <= 0 || score <0){
            screen.stopMusic();
            //screen.startSFX(8);
            try {
                Thread.sleep(700);              
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            screen.startMusic(11);
            //screen.player.score=0;
            screen.gameState = screen.lossState;
            screen.latestPlayState = 1;
            screen.ui.commandNum = -1;
            screen.ui.endGame=true;

        }
    }

    /**
     * Draws the player character using the correct images based onthe current direction and animation frame.
     * 
     * 
     * @param myGraphics The Graphics2D context on which to draw the player character.
     */
    public void draw(Graphics2D myGraphics){
       BufferedImage img = null;
       if(side == "up"){
            if(sideNum ==1){
                img = b1;
            }
            else if(sideNum ==2){
                img = b2;
            }
       }
       else if(side == "down"){
            if(sideNum ==1){
                img = f1;
            }
            else if(sideNum ==2){
                img = f2;
            }
       }
       else if(side == "left"){
            if(sideNum ==1){
                img = l1;
            }
            else if(sideNum ==2){
                img = l2;
            }
       }
       else if(side == "right"){
            if(sideNum ==1){
                img = r1;
            }
            else if(sideNum ==2){
                img = r2;
            }
       }
       myGraphics.drawImage(img, worldX, worldY, screen.tileSize, screen.tileSize, null);
    }
}
