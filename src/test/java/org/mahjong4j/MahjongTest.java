package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @author yu1ro
 */
public class MahjongTest {
    MahjongHands hands;
    Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
                3,0,0,0,0,0,0,0,3,
                0,0,0,0,0,0,0,0,3,
                0,0,2,0,0,0,0,0,3,
                0,0,0,0,
                0,0,0
        };
        MahjongTile last = MahjongTile.M6;
        hands = new MahjongHands(tiles, last);
        mahjong = new Mahjong(hands);
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> expected = new ArrayList<MahjongYakumanEnum>(1);
        List<MahjongYakumanEnum> actual = mahjong.getYakumanList();

        assertEquals(expected.size(), actual.size());
    }
}