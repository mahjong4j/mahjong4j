package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.tile.MahjongTile.M8;

/**
 * @author yu1ro
 */
public class RyanpeikoTanyaoPinfuTest {
    MahjongHands hands;
    Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 0, 2, 2, 2, 2, 2, 2, 0,
            0, 0, 0, 0, 2, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M8;
        hands = new MahjongHands(tiles, last);
        mahjong = new Mahjong(hands);
        mahjong.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjong.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = mahjong.getNormalYakuList();

        assertEquals(3, actual.size());
        assertThat(actual, hasItems(MahjongYakuEnum.RYANPEIKO));
        assertThat(actual, hasItems(MahjongYakuEnum.TANYAO));
        assertThat(actual, hasItems(MahjongYakuEnum.PINFU));
    }
}