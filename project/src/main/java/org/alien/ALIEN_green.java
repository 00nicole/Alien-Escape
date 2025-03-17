package org.alien;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.game.GameScreen;

/**
 * Alien class to represent Green aliens
 * 
 * @author Ryan Chong
 * 
 */
public class ALIEN_green extends Alien{

    /**
     * Green Alien constructor that takes in it's starting location
     * 
     * @param screen the gamescreen
     * @param worldX the x position of the alien
     * @param worldY the y position of the alien
     */
    public ALIEN_green(GameScreen screen, int worldX, int worldY){
        super(screen, worldX, worldY);
        getImage();
    }
    
    @Override
    public void getImage() {

        try{

        b1 = ImageIO.read(getClass().getResourceAsStream("/aliens/green/alien_green_d1.png"));
        b2 = ImageIO.read(getClass().getResourceAsStream("/aliens/green/alien_green_d2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}