package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Entrance extends SuperObject {
    public Entrance(){
        name = "Entrance";

        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/transportation/entrance.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        collision=true;

    }

    public Entrance(int worldX, int worldY){
        name = "Entrance";
        this.worldX = worldX * 44;
        this.worldY = worldY * 44;
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/transportation/entrance.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        collision=true;

    }
}
