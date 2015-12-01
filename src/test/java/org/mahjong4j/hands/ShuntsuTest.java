package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.MahjongTile.M2;
import static org.mahjong4j.tile.MahjongTile.M3;

/**
 * @author yu1ro
 */
public class ShuntsuTest {
    private Shuntsu open;
    private Shuntsu close;
    private Shuntsu fls;

    @Before
    public void setUp() throws Exception {
        open = new Shuntsu(true, M2);
        close = new Shuntsu(false, M3);
    }

    @Test
    public void testCheck() throws Exception {

    }

    @Test
    public void testGetTile() throws Exception {

    }

    @Test
    public void testGetIsMentsu() throws Exception {

    }

    @Test
    public void testGetIsOpenT() throws Exception {
        assertTrue(open.getIsOpen());
    }

    @Test
    public void testGetIsOpenF() throws Exception {
        assertFalse(close.getIsOpen());
    }
}