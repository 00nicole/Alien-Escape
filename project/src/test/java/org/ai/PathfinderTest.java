package org.ai;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.game.GameScreen;
import org.junit.Test;

public class PathfinderTest {

    private GameScreen screen;
    private Pathfinder pathfinder;

    @Before
    public void setUp() {
        // Set up a GameScreen for testing
        screen = new GameScreen();
        pathfinder = new Pathfinder(screen);
    }

    @Test
    public void testNodeInitialization() {
        // Arrange
        int startCol = 0;
        int startRow = 0;
        int goalCol = 5;
        int goalRow = 5;

        // Act
        pathfinder.setNodes(startCol, startRow, goalCol, goalRow);

        // Assert
        assertNotNull(pathfinder.startNode);
        assertNotNull(pathfinder.goalNode);
        assertEquals(startCol, pathfinder.startNode.col);
        assertEquals(startRow, pathfinder.startNode.row);
        assertEquals(goalCol, pathfinder.goalNode.col);
        assertEquals(goalRow, pathfinder.goalNode.row);
    }

    @Test
    public void testResetNodes() {
        // Arrange
        int startCol = 0;
        int startRow = 0;
        int goalCol = 5;
        int goalRow = 5;

        pathfinder.setNodes(startCol, startRow, goalCol, goalRow);
        pathfinder.search(); // Perform a search to modify internal states

        // Act
        pathfinder.resetNodes();

        // Assert
        assertNull(pathfinder.startNode);
        assertNull(pathfinder.goalNode);
        assertTrue(pathfinder.openList.isEmpty());
        assertTrue(pathfinder.pathList.isEmpty());
        assertFalse(pathfinder.goalReached);
        assertEquals(0, pathfinder.step);

        // Ensure that node states are reset
        for (int col = 0; col < screen.maxScreenCol; col++) {
            for (int row = 0; row < screen.maxScreenRow; row++) {
                assertFalse(pathfinder.node[col][row].open);
                assertFalse(pathfinder.node[col][row].checked);
                assertFalse(pathfinder.node[col][row].solid);
            }
        }
    }

    @Test
    public void testSetNodesValidCoordinates() {
        // Arrange
        int startCol = 1;
        int startRow = 2;
        int goalCol = 3;
        int goalRow = 4;

        // Act
        pathfinder.setNodes(startCol, startRow, goalCol, goalRow);

        // Assert
        assertNotNull(pathfinder.startNode);
        assertNotNull(pathfinder.currentNode);
        assertNotNull(pathfinder.goalNode);
        assertFalse(pathfinder.openList.isEmpty());
    }

    @Test
    public void testSetNodesInvalidStartCoordinates() {
        // Arrange
        int startCol = -1;
        int startRow = 2;
        int goalCol = 3;
        int goalRow = 4;

        // Act and Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            pathfinder.setNodes(startCol, startRow, goalCol, goalRow);
        });
    }

    @Test
    public void testSetNodesInvalidGoalCoordinates() {
        // Arrange
        int startCol = 1;
        int startRow = 2;
        int goalCol = -10;
        int goalRow = 4;

        // Act and Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            pathfinder.setNodes(startCol, startRow, goalCol, goalRow);
        });
    }

    @Test
    public void testSetNodesMaxCoordinates() {
        // Arrange
        int startCol = 20;
        int startRow = 11;
        int goalCol = 20;
        int goalRow = 11;

        // Act
        pathfinder.setNodes(startCol, startRow, goalCol, goalRow);

        // Assert
        assertNotNull(pathfinder.startNode);
        assertNotNull(pathfinder.currentNode);
        assertNotNull(pathfinder.goalNode);
        assertFalse(pathfinder.openList.isEmpty());
    }

    @Test
    public void testGetCost() {
        // Arrange
        Node startNode = new Node(0, 0);
        Node goalNode = new Node(5, 5);
        Node node1 = new Node(2, 2);
        Node node2 = new Node(3, 4);

        // Ensure startNode is set before calling getCost
        pathfinder.startNode = startNode;
        pathfinder.goalNode = goalNode;

        // Act
        pathfinder.getCost(node1);
        pathfinder.getCost(node2);

        // Assert
        assertEquals(4, node1.gCost);
        assertEquals(6, node1.hCost);
        assertEquals(10, node1.fCost);

        assertEquals(7, node2.gCost);
        assertEquals(3, node2.hCost);
        assertEquals(10, node2.fCost);
    }
    
    @Test
    public void testTrackThePath() {
        // Arrange
        Node start = new Node(0, 0);
        Node goal = new Node(3, 3);
        pathfinder.setNodes(start.col, start.row, goal.col, goal.row);
        pathfinder.search();  // Ensure search has been performed

        // Act
        boolean goalReached = pathfinder.search();  // Ensure goal has been reached
        if (goalReached) {
            pathfinder.trackThePath();
        } else {
            System.out.println("Goal not reached. Cannot track the path.");
        }

        // Use the new getPathList method to access pathList
        ArrayList<Node> actualPathList = pathfinder.getPathList();

        // Validate the order of nodes in the path
        for (int i = 1; i < actualPathList.size(); i++) {
            Node current = actualPathList.get(i);
            Node previous = actualPathList.get(i - 1);
            assertNotNull(previous);
            assertEquals(previous.parent, current);
        }
    }

}