package org.game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PortalTest {

    private Portal portal1;
    private Portal portal2;

    @Before
    public void setUp() {
        portal1 = new Portal(1,1);
        portal2 = new Portal(2,2);
        portal1.partner = portal2;
        portal2.partner = portal1;
    }

    @Test
    public void testPortalConstructor() {
        assertEquals(8, portal1.rectDefaultX);
        assertEquals(5, portal1.rectDefaultY);
        assertEquals(16, portal1.Rectangle.width);
        assertEquals(22, portal1.Rectangle.height);
        assertEquals(8, portal1.Rectangle.x);
        assertEquals(5, portal1.Rectangle.y);
        assertNotNull(portal1.image);
    }

    @Test
    public void testPortalDraw() {
        assertNotNull(portal1.image);
    }

    @Test
    public void testPortalConstructorWithPosition() {
        Portal portalWithPosition = new Portal(2, 3);
        assertEquals(88, portalWithPosition.worldX); // 2 * 44
        assertEquals(132, portalWithPosition.worldY); // 3 * 44
    }
}