package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class KotsuTest {
    private Kotsu open;
    private Kotsu close;
    private Kotsu closeFls;
    private Kotsu equal;

    @Before
    public void setUp() throws Exception {
        open = new Kotsu(true, TON);
        close = new Kotsu(false, SHA, SHA, SHA);
        closeFls = new Kotsu(false, HAK, HAK, CHN);
        equal = new Kotsu(true, TON, TON, TON);
    }

    @Test
    public void testCheckTrue() throws Exception {
        assertTrue(Kotsu.check(S2, S2, S2));
        assertTrue(Kotsu.check(HAK, HAK, HAK));
        assertTrue(Kotsu.check(NAN, NAN, NAN));
    }

    @Test
    public void testCheckFalse() throws Exception {
        assertFalse(Kotsu.check(P2, P2, P4));
        assertFalse(Kotsu.check(HAK, CHN, CHN));
        assertFalse(Kotsu.check(TON, NAN, SHA));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(TON, open.getTile());
        assertEquals(SHA, close.getTile());
        assertNull(closeFls.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(open.isMentsu());
        assertTrue(close.isMentsu());
        assertFalse(closeFls.isMentsu());
    }

    @Test
    public void testGetIsOpen() throws Exception {
        assertTrue(open.isOpen());
        assertFalse(close.isOpen());
        assertFalse(closeFls.isOpen());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(open.equals(equal));
        assertFalse(open.equals(close));
        assertFalse(open.equals(closeFls));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(open.hashCode(), equal.hashCode());
        assertNotEquals(open.hashCode(), close.hashCode());
        assertNotEquals(open.hashCode(), closeFls.hashCode());
    }
}