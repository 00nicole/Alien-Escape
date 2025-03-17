package org.ai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class NodeTest {

    @Test
    public void testNodeInitialization() {
        // Arrange
        int col = 3;
        int row = 5;

        // Act
        Node node = new Node(col, row);

        // Assert
        assertEquals(col, node.col);
        assertEquals(row, node.row);
        assertNull(node.parent);
        assertEquals(0, node.gCost);
        assertEquals(0, node.hCost);
        assertEquals(0, node.fCost);
        assertFalse(node.solid);
        assertFalse(node.open);
        assertFalse(node.checked);
    }

    @Test
    public void testNodeWithParent() {
        // Arrange
        Node parent = new Node(1, 2);
        int col = 3;
        int row = 5;

        // Act
        Node node = new Node(col, row);
        node.parent = parent;

        // Assert
        assertEquals(parent, node.parent);
    }
}