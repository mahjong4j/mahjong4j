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
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.CHITOITSU;

/**
 * @author yu1ro
 */
public class ChitoitsuTest {
    MahjongHands hands;
    MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            2, 0, 0, 0, 2, 0, 0, 2, 0,
            0, 0, 0, 0, 2, 0, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M8;
        hands = new MahjongHands(tiles, last);
        mahjongPlayer = new MahjongPlayer(hands);
        mahjongPlayer.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();

        assertEquals(1, actual.size());
        assertThat(actual, hasItems(CHITOITSU));
    }

    /**
     * Situationが無い場合は常に20
     * @throws Exception
     */
    @Test
    public void testGetFu() throws Exception {
        assertEquals(20, mahjongPlayer.getFu());
    }
}