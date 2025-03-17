package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp extends SuperObject {
    /* private Power power;
    private long startTime;
    private long endTime;
    private long powerDuration;
    8*/
    public PowerUp(){
        //this.power = power;
        //powerDuration = duration;
        
        //Reward r = new Reward();
        //int value = 2* r.getValue();
        name = "PowerUp";
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/health/heart.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        
    }

    public PowerUp(int worldX, int worldY){

        this.worldX = worldX * 44;
        this.worldY = worldY * 44;
        
        name = "PowerUp";
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/health/heart.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        
    }
}
