package org.object;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.game.GameScreen;


/**Parent class to represent all stationary objects in the game 
 * @author Ryan Chong
*/
public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,44,44);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public SuperObject(){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/transparent.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }
    public void draw(Graphics2D myGraphics, GameScreen screen){

        myGraphics.drawImage(image, worldX, worldY, screen.tileSize, screen.tileSize, null);
    }

    public int update(){
        return 0;
    }
}
