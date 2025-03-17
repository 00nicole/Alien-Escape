package org.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.game.GameScreen;

public class Entity {

    public GameScreen screen;
    public String name;
    public int worldX, worldY;
    public int speed;
    public BufferedImage f1, f2, b1,b2, l1, l2, r1, r2;
    public String side = "down";
    public int sideSwitcher = 0;
    public int sideNum = 1;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    public int maxLife, life;

    public void move(String side){
        switch (side){
            case"up":   worldY -= speed;    break;
            case"down": worldY += speed;    break;
            case"left": worldX -= speed;    break;
            case"right":worldX += speed;    break;
        }
    }
}
