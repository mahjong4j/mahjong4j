package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.tile.MahjongTile.M1;
import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.CHINROTO;

/**
 * @author yu1ro
 */
public class ChinrohtohTest {
    MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] match = {
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            2, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongHands hands = new MahjongHands(match, M1, new Kantsu(true, M1));
        mahjongPlayer = new MahjongPlayer(hands);
        mahjongPlayer.calculate();
    }

    @Test
    public void testChinrohtoh() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(1, actual.size());
        assertEquals(CHINROTO, actual.get(0));
    }
}
