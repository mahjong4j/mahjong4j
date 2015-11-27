package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author yu1ro
 */
public class ChinrohtohTest {
    Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] match = {
                3,0,0,0,0,0,0,0,3,
                0,0,0,0,0,0,0,0,3,
                2,0,0,0,0,0,0,0,3,
                0,0,0,0,
                0,0,0
        };
        MahjongHands hands = new MahjongHands(match, MahjongTile.M1);
        mahjong = new Mahjong(hands);
    }

    @Test
    public void testChinrohtoh() throws Exception {
        List<MahjongYakumanEnum> actual = mahjong.getYakumanList();

        assertEquals(1, actual.size());
        assertEquals(MahjongYakumanEnum.CHINROTO, actual.get(0));
    }
}
