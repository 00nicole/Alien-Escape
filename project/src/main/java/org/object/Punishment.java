package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Punishment extends SuperObject {

    public Punishment(int worldX,int worldY){
        this.name = "Punishment";
        this.worldX = worldX * 44;
        this.worldY = worldY * 44 + 6;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/gravity_shift.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }

}
