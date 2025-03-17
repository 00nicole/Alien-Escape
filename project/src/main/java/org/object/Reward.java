package org.object;

public class Reward extends SuperObject {
    
    private int value;
    public boolean regular;

    public Reward(){

        name = "Reward";
        value = 100;
    }

    public Reward(int worldX, int worldY){

        name = "Reward";
        value = 100;
        this.worldX = worldX * 44;
        this.worldY = worldY * 44;
    }

    public int getValue(){
        return this.value;
    }

    public boolean isRegular(){
        return this.regular;
    }
}
