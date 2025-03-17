package org.alien;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;
import org.entity.Entity;
import org.game.GameScreen;
import java.awt.*;
/**
 * Alien class to represent enemies in the game
 * 
 * @author Ryan Chong
 */
public class Alien extends Entity{
    private int counter = 0;
    public int attackTimer = 0;
    public boolean onPath = true;
    public int possible;
    Random r = new Random();

    /**
     * Alien constructor that takes in it's starting position
     * 
     * @param screen the gamescreen
     * @param worldX the x position of the Alien
     * @param worldY the y position of the Alien
     */
    public Alien(GameScreen screen,int worldX, int worldY){
        this.screen = screen;

        this.worldX = worldX * 44;
        this.worldY = worldY * 44;

        speed = 1;
        solidArea.x = 2;
        solidArea.y = 2;
        solidArea.width = 30;
        solidArea.height = 30;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        try{

        b1 = ImageIO.read(getClass().getResourceAsStream("/alien/green/alien_green_d1"));
        b2 = ImageIO.read(getClass().getResourceAsStream("/alien/green/alien_green_d2"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int searchPath(int goalCol, int goalRow){

        int startCol = (worldX + solidArea.x) / screen.tileSize;
        int startRow = (worldY + solidArea.y) / screen.tileSize;
        screen.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(screen.pFinder.search() == true){
            int nextX = screen.pFinder.pathList.get(0).col * screen.tileSize;
            int nextY = screen.pFinder.pathList.get(0).row * screen.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + screen.tileSize){
                side = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + screen.tileSize){
                side = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + screen.tileSize){
                if(enLeftX > nextX){
                    side = "left";
                }
                if(enLeftX < nextX){
                    side = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                side = "up";
                collisionOn = false;
                screen.cChecker.checkTile(this);
                if(collisionOn == true){
                    side = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                side = "up";
                collisionOn = false;
                screen.cChecker.checkTile(this);
                if(collisionOn == true){
                    side = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                side = "down";
                collisionOn = false;
                screen.cChecker.checkTile(this);
                if(collisionOn == true){
                    side = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                side = "down";
                collisionOn = false;
                screen.cChecker.checkTile(this);
                if(collisionOn == true){
                    
                    side = "right";
                }
            }
            return 1;
        }
        else
        {
            return 0;
        }
    }  

    public void update(){

        if(onPath == true){
            int goalCol = (screen.player.worldX + screen.player.solidArea.x) / screen.tileSize;
            int goalRow = (screen.player.worldY + screen.player.solidArea.y) / screen.tileSize;

            possible = searchPath(goalCol,goalRow);
            collisionOn = false;
            screen.cChecker.checkTile((this));

            if(possible == 0)
            {   
                counter++;
                if(counter >= 60){
                int random = r.nextInt(4);
                counter = 0;
                
                    if(random == 0){
                        side = "up";
                    }
                    else if (random == 1){
                        side = "down";
                    }
                    else if (random == 2){
                        side = "left";
                    }
                    else if (random == 3){
                        side = "right";
                    }
                }
            }

            if(collisionOn == false){
                move(side);
            }
        
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

        if(attackTimer > 0){
            attackTimer --;
        }
    }

    public void draw(Graphics2D myGraphics,GameScreen screen){
        BufferedImage img = null;
        
        if(sideNum ==1)
                 img = b1;
             
        else if(sideNum ==2)
                 img = b2;
        
        myGraphics.drawImage(img, worldX, worldY, screen.tileSize, screen.tileSize, null);
     }
}
