package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to represent bonus reward object
 * @author Ryan Chong
 */
public class BonusReward extends Reward{
    
    private int downCounter;
    
    /**
     * Bonus reward constructor function
     * 
     * 
     */
    public BonusReward(){
        //super(value);
       
        this.downCounter = 300;
        this.name = "BonusReward";
        this.regular= false;

        //get image
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/2xstar.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }

    /**
     * Bonus Reward constructor function that takes in it's starting location
     *
     * @param worldX
     * @param worldY
     * @author Ryan Chong
     */
    public BonusReward(int worldX, int worldY){
        //super(value);
        this.worldX = worldX *44;
        this.worldY = worldY *44;
        this.downCounter = 300;
        
        this.name = "BonusReward";
        this.regular= false;

        //get image
                try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/2xstar.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }

    /**
     * Update function for each bonus reward that runs down it's timer and tells system to delete it after time has run out
     */
    @Override
    public int update(){
        downCounter--;
        
        if(downCounter == 0){
            return 1;
        }

        return 0;
    }
}
