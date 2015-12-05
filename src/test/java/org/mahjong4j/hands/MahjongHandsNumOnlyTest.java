package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author yu1ro
 */
public class MahjongHandsNumOnlyTest {
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

    @Test
    public void testFindMentsu() throws Exception {
        assertEquals(WinTypeEnum.NORMAL, hands.getWinType());
        List<MentsuComp> winSetList = hands.getMentsuCompList();
        assertEquals(1, winSetList.size());
    }

    @Test
    public void testWinMentsuList() throws Exception {
        List<MentsuComp> winSetList = hands.getMentsuCompList();
        for (MentsuComp winMentsuSet : winSetList) {
            assertEquals(0, winMentsuSet.getKantsuNum());
            assertEquals(0, winMentsuSet.getKotsuNum());
            assertEquals(4, winMentsuSet.getShuntsuNum());
            assertEquals(MahjongTile.HAT, winMentsuSet.getJanto().getTile());
        }
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(hands.getCanWin());
    }
}