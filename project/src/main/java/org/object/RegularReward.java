package org.object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The RegularReward class represents a specific type of reward in the game.
 * It extends the abstract class Reward and provides additional functionality
 * specific to regular rewards.
 * <p>
 * The class includes constructors for creating RegularReward objects with default
 * values or with specified world coordinates. It attempts to load an image
 * from the "/rewardspunishments/planet_blue.png" resource, and if the loading
 * fails, the stack trace is printed.
 *
 * @author Curtis Pu
 */
public class RegularReward extends Reward{
    
    /**
     * Default constructor for RegularReward
     * Initialize the object with default values
     * Attempt to load the image from the res folder
     * If load fails, the stack trace is printed
     * 
     * 
     */
    public RegularReward(){
        super();

        name = "RegularReward";
        regular = true;
        

        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/planet_blue.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }

    /**
     * Initialize  the RegularRward object with the specified world coordinates
     * Attempt to load image from the res folder
     * If load fails, the stack trace is printed
     * 
     * 
     * @param worldX
     * @param worldY
     */
    public RegularReward(int worldX, int worldY){
        super(worldX,worldY);

        name = "RegularReward";
        regular = true;
        
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rewardspunishments/planet_blue.png"));

        }catch (IOException e){ 

            e.printStackTrace();

        }
    }
}
