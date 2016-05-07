package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.tile.MahjongTile.M4;
import static org.mahjong4j.tile.MahjongTile.P7;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.*;

/**
 * @author yu1ro
 */
public class SanankoTanyaoToitoihoTest {
    private MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 3, 0, 0, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 3, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M4;
        MahjongHands hands = new MahjongHands(tiles, last, new Kotsu(true, P7));
        mahjongPlayer = new MahjongPlayer(hands);
        mahjongPlayer.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        int actualSize = mahjongPlayer.getNormalYakuList().size();

        assertEquals(3, actualSize);
    }

    @Test
    public void testGetNormalYakuListHasSanshokudohko() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();

        assertThat(actual, hasItems(SANANKO, TANYAO, TOITOIHO));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(20, mahjongPlayer.getFu());
    }
}