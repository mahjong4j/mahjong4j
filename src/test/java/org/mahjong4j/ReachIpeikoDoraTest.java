package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author yu1ro
 */
public class ReachIpeikoDoraTest {
    MahjongHands hands;
    Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1, 1, 1, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            2, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = MahjongTile.M4;
        hands = new MahjongHands(tiles, last);

        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(MahjongTile.TON);
        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(MahjongTile.M9);
        GeneralSituation generalSituation = new GeneralSituation(false, false, MahjongTile.TON, dora, uradora);
        PersonalSituation personalSituation = new PersonalSituation(false, false, false, true, false, false, false, MahjongTile.NAN);
        mahjong = new Mahjong(hands, generalSituation, personalSituation);
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

        assertEquals(4, actual.size());
        assertThat(actual, hasItems(MahjongYakuEnum.REACHE));
        assertThat(actual, hasItems(MahjongYakuEnum.IPEIKO));
    }
}