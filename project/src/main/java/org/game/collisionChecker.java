package org.game;

import org.entity.Entity;

/**
 * The {@code collisionChecker} class is responsible for checking collisions between entities
 * and the game environment or other game entities on the {@code GameScreen}.
 * It provides methods to check collisions with tiles and other objects, facilitating
 * collision detection and response in the game.
 *
 * @author Usman Aziz
 * @author Ryan Chong
 * @author Nicole Malku
 */
public class collisionChecker {
    GameScreen screen;
    private static final int noCollision = 999;
    private static final int minute = 60;
    public collisionChecker(GameScreen screen){ this.screen = screen;}

    /**
     * Checks for tile collisions based on the provided entity's position and movement direction.
     *
     * @author Usman Aziz
     * @author Ryan Chong
     * @author Nicole Malku
     *
     * @param entity The entity for which to check tile collisions.
     */
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ screen.tileSize;
        int entityRightCol = entityRightWorldX/screen.tileSize;
        int entityTopRow = entityTopWorldY/screen.tileSize;
        int entityBottomRow = entityBottomWorldY/screen.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.side) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / screen.tileSize;
                tileNum1 = screen.mapM.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = screen.mapM.mapNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / screen.tileSize;
                tileNum1 = screen.mapM.mapNum[entityLeftCol][entityBottomRow];
                tileNum2 = screen.mapM.mapNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / screen.tileSize;
                tileNum1 = screen.mapM.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = screen.mapM.mapNum[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / screen.tileSize;
                tileNum1 = screen.mapM.mapNum[entityRightCol][entityTopRow];
                tileNum2 = screen.mapM.mapNum[entityRightCol][entityBottomRow];
                break;
            default:
                return;
        }
            if(screen.mapM.map[tileNum1].collision ||
                    screen.mapM.map[tileNum2].collision) {   entity.collisionOn = true;   }
        
    }
    /**
     * Checks for collisions with other entities, either player or non-player, based on the provided
     * entity's position, movement direction, and type.
     *
     * @author Ryan Chong
     * @author Usman Aziz
     * @author Nicole Malku
     *
     * @param entity The entity for which to check collisions with.
     * @param player Indicates whether the provided entity is a player entity.
     * @return The index of the collided object in the array of objects, or noCollision if no collision occurred.
     */
    public int checkOther(Entity entity, boolean player){
        int index = noCollision;
        for (int i=0; i<screen.obj.length; i++){
            if (screen.obj[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                screen.obj[i].solidArea.x = screen.obj[i].worldX + screen.obj[i].solidArea.x;
                screen.obj[i].solidArea.y = screen.obj[i].worldY + screen.obj[i].solidArea.y;
                switch (entity.side){
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                        if(screen.obj[i].collision){
                            entity.collisionOn = true;
                        }
                        if(player){ index = i; }
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                        if(screen.obj[i].collision){
                            entity.collisionOn = true;
                        }
                        if(player){ index = i;  }
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                        if(screen.obj[i].collision){
                            entity.collisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(screen.obj[i].solidArea)){
                        if(screen.obj[i].collision){
                            entity.collisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + entity.side);
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                screen.obj[i].solidArea.x = screen.obj[i].solidAreaDefaultX;
                screen.obj[i].solidArea.y= screen.obj[i].solidAreaDefaultY;
            }
        }

        for (int i=0; i<screen.alien.length; i++){
            if (screen.alien[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                screen.alien[i].solidArea.x = screen.alien[i].worldX + screen.alien[i].solidArea.x;
                screen.alien[i].solidArea.y = screen.alien[i].worldY + screen.alien[i].solidArea.y;
                switch (entity.side){
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(screen.alien[i].solidArea) &&
                            (player && screen.alien[i].attackTimer==0 && (screen.player.life > 0))) {
                        screen.player.life--;
                        screen.startSFX(3);
                        screen.alien[i].attackTimer=minute;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(screen.alien[i].solidArea) && (player &&
                            screen.alien[i].attackTimer==0 && (screen.player.life > 0))) {
                        screen.player.life--;
                        screen.startSFX(3);
                        screen.alien[i].attackTimer=minute;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(screen.alien[i].solidArea) && (player &&
                            screen.alien[i].attackTimer==0) && (screen.player.life > 0)) {
                        screen.player.life--;
                        screen.startSFX(3);
                        screen.alien[i].attackTimer=minute;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(screen.alien[i].solidArea) && ((player &&
                            screen.alien[i].attackTimer==0) && screen.player.life > 0)){
                        screen.player.life--;
                        screen.startSFX(3);
                        screen.alien[i].attackTimer=minute;
                    }
                    break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + entity.side);
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                screen.alien[i].solidArea.x = screen.alien[i].solidAreaDefaultX;
                screen.alien[i].solidArea.y= screen.alien[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
