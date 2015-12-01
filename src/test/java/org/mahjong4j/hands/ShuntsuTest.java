package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.MahjongTile.*;

/**
 * @author yu1ro
 */
public class ShuntsuTest {
    private Shuntsu open;
    private Shuntsu close;
    private Shuntsu openTrue;

    @Before
    public void setUp() throws Exception {
        open = new Shuntsu(true, M2);
        close = new Shuntsu(false, M3, M1, M2);
        openTrue = new Shuntsu(true, P4, P3, P2);
    }

    @Test
    public void testCheckFalse() throws Exception {
        assertFalse(Shuntsu.check(P1, M9, P2));
        assertFalse(Shuntsu.check(P9, S2, S1));
        assertFalse(Shuntsu.check(S1, S2, S5));
        assertFalse(Shuntsu.check(HAK, HAT, CHN));
    }

    @Test
    public void testCheckTrue() throws Exception {
        assertTrue(Shuntsu.check(M4, M5, M6));
        assertTrue(Shuntsu.check(S8, S6, S7));
        assertTrue(Shuntsu.check(P4, P3, P2));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(M2, open.getTile());
        assertEquals(M2, close.getTile());
        assertEquals(P3, openTrue.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(open.getIsMentsu());
        assertTrue(close.getIsMentsu());
        assertTrue(openTrue.getIsMentsu());
    }

    @Test
    public void testGetIsOpenT() throws Exception {
        assertTrue(open.getIsOpen());
        assertTrue(openTrue.getIsOpen());
    }

    @Test
    public void testGetIsOpenF() throws Exception {
        assertFalse(close.getIsOpen());
    }
}