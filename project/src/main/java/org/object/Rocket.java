package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocket extends SuperObject {
    public Rocket(){
        name = "Rocket";

        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/transportation/exit.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        collision=true;

    }

    public Rocket(int worldX, int worldY){
        name = "Rocket";

        this.worldX = worldX * 44;
        this.worldY = worldY * 44;
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/transportation/exit.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
        collision=true;

    }
}
