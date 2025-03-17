package org.ai;

import java.util.ArrayList;

import org.game.GameScreen;

/**
 * The Pathfinder class is responsible for pathfinding in a grid-based environment,
 * utilizing the A* search algorithm. It manages nodes, open and closed lists,
 * and calculates the path from a starting node to a goal node on a grid.
 * 
 * @author Curtis Pu
 * @author Ryan Chong
 */
 public class Pathfinder {
    GameScreen screen;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    private static final int maxStep = 500;

    /**
     * Constructs a Pathfinder instance associated with a specific GameScreen
     * 
     * 
     * @param screen The GameScreen where the pathfinding will occur.
     */
    public Pathfinder(GameScreen screen){
        this.screen = screen;
        instantiateNode();
    }

    /**
     * Initializes the 2D array of nodes based on the dimensions of the GameScreen.
     *
     * 
     */
    public void instantiateNode() {
        node = new Node[screen.maxScreenCol][screen.maxScreenRow];
    
        for (int row = 0; row < screen.maxScreenRow; row++) {
            for (int col = 0; col < screen.maxScreenCol; col++) {
                node[col][row] = new Node(col, row);
            }
        }
    }
    /**
     * Resets the state of nodes, lists, and flags for a new pathfinding operation.
     *
     * 
     */
    public void resetNodes(){
        int col = 0;
        int row = 0;

        while(col < screen.maxScreenCol && row < screen.maxScreenRow){
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == screen.maxScreenCol){
                col = 0;
                row++;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
        startNode = null;
        goalNode = null;
    }

    /**
     * Sets up the nodes on the grid based on the given start and goal positions.
     * 
     * 
     * 
     * @param startCol The column index of the starting node.
     * @param startRow The row index of the starting node.
     * @param goalCol The column index of the goal node.
     * @param goalRow The row index of the goal node.
     * @param entity The entity associated with the pathfinding.
     */
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        
        resetNodes();
    
        startNode = node[startCol][startRow];
        
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        
        int col = 0;
        int row = 0;

        while(col < screen.maxScreenCol && row < screen.maxScreenRow){  
            int tileNum = screen.mapM.mapNum[col][row];
            if(screen.mapM.map[tileNum].collision == true){
                node[col][row].solid = true;
            }

            getCost(node[col][row]);

            col++;
            if(col == screen.maxScreenCol){
            col = 0;
            row++;
            }
            if(row == screen.maxWorldRow){
            row = 0;
            }
        }
    }

    /**
     * Calculates the gCost, hCost, and fCost for a given node.
     * 
     * @param node
     */
    public void getCost(Node node){
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;
    }

    /**
     * Initiates the A* search algorithm until the goal is reached or a step limit is reached.
     * 
     */
    public boolean search(){

        while(goalReached == false && step < maxStep){

            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if(row - 1 >= 0){
                openNode(node[col][row - 1]);
            }
            if(col - 1 >= 0){
                openNode(node[col - 1][row]);
            }
            if(row + 1 < screen.maxScreenRow){
                openNode(node[col][row + 1]);
            }
            if(col + 1 < screen.maxScreenCol){
                openNode(node[col + 1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0;i < openList.size();i++){

                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            if(openList.size() == 0){
                break;
            }

            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        
        }
        return goalReached;
    }

    /**
     * Adds a node to the open list if it satisfies the conditions for exploration.
     * 
     * @param node The node to be potentially added to the open list.
     */
    public void openNode(Node node){

        if(node.open == false && node.checked == false && node.solid == false){

            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Traces the final path from the goal node back to the start node.
     * 
     * 
     */
    public void trackThePath(){ 
        Node current = goalNode;

        while(current != startNode){
            pathList.add(0, current);
            current = current.parent;
        }
    }

    /**
    * Gets the list of nodes representing the path from the start node to the goal node.
    *
    * 
    */
    public ArrayList<Node> getPathList() {
        return new ArrayList<>(pathList);
    }
}
