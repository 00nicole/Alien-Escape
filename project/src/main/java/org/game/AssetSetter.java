package org.game;

import org.alien.ALIEN_green;
import org.object.BonusReward;
import org.object.Entrance;
import org.object.PowerUp;
import org.object.Punishment;
import org.object.RegularReward;
import org.object.Rocket;
import java.util.*;

/**
 * The AssetSetter class is responsible for setting up and updating game assets based on the current game state.
 * It initializes random time intervals for bonus rewards and provides methods to clear and update game objects
 * and set the object map for different game states.
 * <p>
 * The class includes methods for clearing objects, updating the game state based on time intervals,
 * and setting the initial object map for different game levels.
 * 
 * @author Curtis Pu
 * @author Nicole Malku
 * @author Usman Aziz
 * @author Ryan Chong
 */
public class AssetSetter {

    Random r = new Random();
    int time1, time2, time3,time4,time5,time6,counter;
    GameScreen screen;

    /**
     * Asset setter constructor, determines start times for each maps' bonus reward
     * 
     * 
     * @param screen
     */
    public AssetSetter(GameScreen screen){
        this.screen = screen;
        time1 = r.nextInt(3,15) * 60;
        time2 = r.nextInt(3,15) * 60;
        time3 = r.nextInt(3,15) * 60;
        time4 = r.nextInt(3,15) * 60;
        time5 = r.nextInt(3,15) * 60;
        time6 = r.nextInt(3,15) * 60;
    }
    
    /**
     * Clears the arrays of objects, enemies and portals for the current map
     * 
     * 
     */
    public void clear(){
        for(int i = 0; i <20; i ++){
            screen.obj[i] = null;
            screen.alien[i] = null;
            screen.portal[i] = null;
        }
    }

    /**
     * Helper function to update the bonus rewards on each level, creates the bonus rewards
     * 
     * 
     * @param gameState
     */
    public void update(int gameState){
        counter++;
        if(gameState == 1 && counter == time1){
            screen.obj[19]= new BonusReward(7,5);
        }
        if(gameState == 2 && counter == time2){
            screen.obj[19]= new BonusReward(19,8);
        }
        if(gameState == 3 && counter == time3){
            screen.obj[19]= new BonusReward(19,4);
        }
        if(gameState == 4 && counter == time4){
            screen.obj[19]= new BonusReward(10,5);
        }
        if(gameState == 5 && counter == time5){
            screen.obj[19]= new BonusReward(12,6);
        }
        if(gameState == 6 && counter == time6){
            screen.obj[19]= new BonusReward(8,5);
        }
    }
    
    /**
     * Main Asset Setter function to generate all objects in the game on each level;
     * 
     * 
     * @param gameState current gamestate
     */
    public void setObjectMap(int gameState){
        counter = 0;
        switch(gameState){
        case 1:
        //MAP1
        screen.player.worldX = 2 *screen.tileSize;
        screen.player.worldY = 1 *screen.tileSize;

        screen.obj[0] = new RegularReward(6,2);

        screen.obj[1] = new RegularReward(15,9);

        screen.obj[2] = new Rocket(19,1);

        screen.obj[3] = new PowerUp(11,6);

        screen.obj[4] = new Entrance(1,1);
        
        screen.obj[5] = new Punishment(10,9);

        screen.alien[0] = new ALIEN_green(screen,6,2);
        screen.alien[1] = new ALIEN_green(screen,19,7);

        screen.portal[0] = new Portal(11,3);
        screen.portal[1] = new Portal(14,6);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];
        break;

        case 2:

        screen.player.worldX = 1 *screen.tileSize;
        screen.player.worldY = 2 *screen.tileSize;

        screen.obj[0] = new RegularReward(1,8);

        screen.obj[1] = new RegularReward(12,1);

        screen.obj[2] = new Rocket(19,1);
        
        screen.obj[3] = new PowerUp(8,6);

        screen.obj[4] = new Entrance(1,1);

        screen.obj[5] = new Punishment(10,6);
        screen.obj[6] = new Punishment(11,6);
        
        screen.alien[0] = new ALIEN_green(screen,11,4);
        screen.alien[1] = new ALIEN_green(screen,17,1);


        screen.portal[0] = new Portal(6,1);
        screen.portal[1] = new Portal(7,8);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];
        break;

        case 3:

        screen.player.worldX = 1 *screen.tileSize;
        screen.player.worldY = 2 *screen.tileSize;

        screen.obj[0] = new RegularReward(10,3);

        screen.obj[1] = new RegularReward(10,7);

        screen.obj[2] = new Rocket(19,9);

        screen.obj[3] = new PowerUp(6,6);

        screen.obj[4] = new Entrance(1,1);
        
        screen.obj[5] = new Punishment(5,6);
        screen.obj[6] = new Punishment(14,6);
        screen.obj[7] = new Punishment(17,2);

        screen.alien[0] = new ALIEN_green(screen,6,4);
        screen.alien[1] = new ALIEN_green(screen,13,5);

        screen.portal[0] = new Portal(2,3);
        screen.portal[1] = new Portal(2,7);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];
        break;
        case 4:

        screen.player.worldX = 1 *screen.tileSize;
        screen.player.worldY = 4 *screen.tileSize;
        
        screen.obj[0] = new RegularReward(19,1);

        screen.obj[1] = new RegularReward(19,10);

        screen.obj[2] = new Rocket(6,5);

        screen.obj[3] = new PowerUp(1,7);

        screen.obj[4] = new Entrance(1,3);
        
        screen.obj[5] = new Punishment(9,4);
        screen.obj[6] = new Punishment(11,5);
        screen.obj[7] = new Punishment(9,6);
        screen.obj[8] = new Punishment(11,7);

        screen.alien[0] = new ALIEN_green(screen,16,1);
        screen.alien[1] = new ALIEN_green(screen,16,10);
        screen.alien[1] = new ALIEN_green(screen,6,6);

        screen.portal[0] = new Portal(15,5);
        screen.portal[1] = new Portal(13,5);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];

        break;

        case 5:

        screen.player.worldX = 2 *screen.tileSize;
        screen.player.worldY = 5 *screen.tileSize;
        
        screen.obj[0] = new RegularReward(1,8);

        screen.obj[1] = new RegularReward(19,1);

        screen.obj[2] = new Rocket(11,10);

        screen.obj[3] = new PowerUp(10,5);

        screen.obj[4] = new Entrance(1,5);

        screen.obj[5] = new Punishment(4,2);
        screen.obj[6] = new Punishment(17,2);
        screen.obj[7] = new Punishment(19,6);
        screen.obj[8] = new Punishment(7,7);
        screen.obj[9] = new Punishment(7,6);
        
        screen.alien[0] = new ALIEN_green(screen,5,2);
        screen.alien[1] = new ALIEN_green(screen,7,7);
        screen.alien[2] = new ALIEN_green(screen,19,4);

        screen.portal[0] = new Portal(8,2);
        screen.portal[1] = new Portal(5,6);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];

        break;    

        case 6:

        screen.player.worldX = 2 *screen.tileSize;
        screen.player.worldY = 1 *screen.tileSize;
        
        screen.obj[0] = new RegularReward(19,1);

        screen.obj[1] = new RegularReward(1,10);

        screen.obj[2] = new Rocket(19,10);

        screen.obj[3] = new PowerUp(19,5);

        screen.obj[4] = new Entrance(1,1);
        
        screen.obj[5] = new Punishment(10,2);
        screen.obj[6] = new Punishment(11,3);
        screen.obj[7] = new Punishment(13,9);
        screen.obj[8] = new Punishment(7,5);
        screen.obj[9] = new Punishment(14,5);
        screen.obj[10] = new Punishment(4,5);

        screen.alien[0] = new ALIEN_green(screen,8,5);
        screen.alien[1] = new ALIEN_green(screen,13,5);
        screen.alien[2] = new ALIEN_green(screen,19,10);
        screen.alien[2].speed = 2;

        screen.portal[0] = new Portal(13,1);
        screen.portal[1] = new Portal(8,10);
        screen.portal[0].partner = screen.portal[1];
        screen.portal[1].partner = screen.portal[0];

        break;
    }


    }
    
}
