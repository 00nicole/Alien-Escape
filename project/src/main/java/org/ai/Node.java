package org.ai;

/**
 * Represents a node in a grid, typically used in pathfinding algorithms.
 * Each node has a position in terms of column and row, and various attributes
 * such as cost values, solid status, and flags indicating whether it's open or checked.
 * 
 *@author Curtis Pu
 */
public class Node {
    Node parent;
    public int col;
    public int row;
    int gCost; //The movement cost to move from the starting point to a given square on the grid
    int hCost; //The estimated movement cost to move from that given square on the grid to the final destination
    int fCost; //gCost + hCost
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
}
