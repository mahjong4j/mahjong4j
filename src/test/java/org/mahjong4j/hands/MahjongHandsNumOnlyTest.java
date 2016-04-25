package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.MahjongTile.M6;

/**
 * @author yu1ro
 */
public class MahjongHandsNumOnlyTest {
    MahjongHands hands;

    @Before
    public void setUp() throws Exception {
        int[] tiles = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        MahjongTile last = M6;
        hands = new MahjongHands(tiles, last);
    }

    @Test
    public void testFindMentsu() throws Exception {
        List<MentsuComp> winSetList = hands.getMentsuCompList();
        assertEquals(1, winSetList.size());
    }

    @Test
    public void testWinMentsuList() throws Exception {
        List<MentsuComp> winSetList = hands.getMentsuCompList();
        for (MentsuComp winMentsuSet : winSetList) {
            assertEquals(0, winMentsuSet.getKantsuCount());
            assertEquals(0, winMentsuSet.getKotsuCount());
            assertEquals(4, winMentsuSet.getShuntsuCount());
            assertEquals(MahjongTile.HAT, winMentsuSet.getToitsuList().get(0).getTile());
        }
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(hands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(M6, hands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };

        assertArrayEquals(expected, hands.getHandsComp());
    }
}