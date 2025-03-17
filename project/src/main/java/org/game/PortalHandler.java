package org.game;

/**
 * Class dedicated to handling the timing and behaviour of portals
 * @author Ryan Chong
 */
public class PortalHandler {
    
    String name;
    GameScreen screen;
    
    /**
     * PortalHandler constructor
     * 
     * 
     * @param screen
     */
    public PortalHandler(GameScreen screen){
        this.screen = screen;
    }

    /**
     * Main portal handler helper function that runs on each game tick.
     * Checks to see if the player is contact with a portal.
     * If so, telports the player.
     * @param portal
     */
    public void checkportal(Portal portal) {
        
        if(hit(portal)==true){
        {
        int tpX = portal.partner.worldX;
        int tpY = portal.partner.worldY;

        screen.player.worldX = tpX;
        screen.player.worldY = tpY;
        screen.player.canPortal=60;
        screen.startSFX(9);
        }
    }
    }

    /**
     * Helper function to checkportal() to see if player is in collision with a portal.
     * 
     * @param portal
     * @return boolean
     * 
     */
    public boolean hit(Portal portal){
        //System.out.println("Checking hit");
        boolean hit = false;

        screen.player.solidArea.x = screen.player.worldX + screen.player.solidArea.x;
        screen.player.solidArea.y = screen.player.worldY + screen.player.solidArea.y;
        portal.Rectangle.x = portal.worldX + portal.Rectangle.x;
        portal.Rectangle.y = portal.worldY + portal.Rectangle.y;

        if(screen.player.solidArea.intersects(portal.Rectangle)){
            hit = true;
    
        }

        screen.player.solidArea.x = screen.player.solidAreaDefaultX;
        screen.player.solidArea.y = screen.player.solidAreaDefaultY;
        portal.Rectangle.x = portal.rectDefaultX;
        portal.Rectangle.y = portal.rectDefaultY;

        return hit; 
    }
}