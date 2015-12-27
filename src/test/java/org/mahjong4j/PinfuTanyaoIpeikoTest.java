package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author yu1ro
 */
public class PinfuTanyaoIpeikoTest {
    MahjongHands hands;
    Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 1, 1, 1, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = MahjongTile.M4;
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
    public void testGetNotmalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = mahjong.getNormalYakuList();

        assertEquals(3, actual.size());
        assertThat(actual, hasItems(MahjongYakuEnum.PINFU));
        assertThat(actual, hasItems(MahjongYakuEnum.TANYAO));
        assertThat(actual, hasItems(MahjongYakuEnum.IPEIKO));
    }
}