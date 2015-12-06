package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.MahjongTile.SHA;

/**
 * @author yu1ro
 */
public class MahjongHandsChitoitsuTest {
    private MahjongHands actual;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            2, 2, 0, 2, 2, 0, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0,
            0, 0, 0
        };

        actual = new MahjongHands(tiles, SHA);
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        assertEquals(0, actual.getMentsuCompList().size());
    }

    @Test
    public void testGetWinType() throws Exception {
        assertEquals(WinTypeEnum.CHITOITSU, actual.getWinType());
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actual.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(SHA, actual.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            2, 2, 0, 2, 2, 0, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0,
            0, 0, 0
        };

        assertArrayEquals(expected, actual.getHandsComp());
    }
}