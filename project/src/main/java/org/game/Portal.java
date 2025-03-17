package org.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class representing an individual portal which also points to its partner
 * 
 * @author Ryan Chong
 */
public class Portal {

    public BufferedImage image;

    public Rectangle Rectangle = new Rectangle();
    public int rectDefaultX, rectDefaultY;
    public int worldX, worldY;
    public Portal partner;



    public void draw(Graphics2D myGraphics2d, GameScreen screen) {
        
        myGraphics2d.drawImage(image, worldX, worldY, screen.tileSize, screen.tileSize, null);
    }

    /**
     * Portal constructor that also takes it's starting position
     * 
     * 
     * @param worldX
     * @param worldY
     */
    public Portal(int worldX, int worldY){
    
        this.worldX = worldX *44;
        this.worldY = worldY *44;

        rectDefaultX = 8;
        rectDefaultY = 5;
        Rectangle.width = 16;
        Rectangle.height = 22;
        Rectangle.x = rectDefaultX;
        Rectangle.y = rectDefaultY;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/transportation/portal.png"));

        }catch (IOException e){ 

            e.printStackTrace();
        }

    }

}
