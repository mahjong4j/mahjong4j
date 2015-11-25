package org.mahjong4j.hands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author yu1ro
 */
public class MahjongHandsTest {
    MahjongHands hands;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
                1, 1, 1, 1, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 2, 0
        };
        MahjongTile last = MahjongTile.M6;
        hands = new MahjongHands(tiles, last);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindMentsu() throws Exception {
        assertEquals("Normal", hands.getWinType());
        List<List<MahjongMentsu>> winSetList = hands.getWinMentsuList();
        assertEquals(2, winSetList.size());
    }

    @Test
    public void testWinMentsuList() throws Exception {
        List<List<MahjongMentsu>> winSetList = hands.getWinMentsuList();
        for (List<MahjongMentsu> winMentsuSet : winSetList) {
            assertEquals(5, winMentsuSet.size());
        }
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(hands.getCanWin());
    }
}