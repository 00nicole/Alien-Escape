package org.object;

import java.io.IOException;
import javax.imageio.ImageIO;

import org.game.GameScreen;

/**
 * Represents a heart object in a game, extending the SuperObject class.
 * <p>
 * The {@code Heart} class inherits from the {@code SuperObject} class and is used
 * to create heart objects in the game. It includes images for a full heart,
 * a half heart, and an empty heart to represent different health states.
 * 
 * @author Nicole Malku
 * @version 1.0
 */
public class Heart extends SuperObject{

    /**
     * Constructs a new Heart object with the specified GameScreen.
     * <p>
     * Initializes the name of the heart object and loads the images for a full heart,
     * a half heart, and an empty heart from the "/health/" directory.
     * </p>
     *
     * 
     * @param screen The GameScreen to which the heart object belongs.
     */
    public Heart(GameScreen screen){
        name = "Heart";
        //get image;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/health/heart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/health/half-heart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/health/empty-heart.png"));
        }catch (IOException e){ 

            e.printStackTrace();

        }
        

    }
}
