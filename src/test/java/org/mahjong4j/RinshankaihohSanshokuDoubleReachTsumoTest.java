package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author yu1ro
 */
public class RinshankaihohSanshokuDoubleReachTsumoTest {
    private Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 2, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = MahjongTile.M9;
        MahjongHands hands = new MahjongHands(tiles, last, new Kantsu(true, MahjongTile.TON));
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(MahjongTile.CHN);

        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(MahjongTile.M2);
        GeneralSituation generalSituation;
        generalSituation = new GeneralSituation(false, false, MahjongTile.SHA, dora, uradora);
        PersonalSituation personalSituation;
        personalSituation = new PersonalSituation(false, true, false, true, true, false, true, MahjongTile.NAN);

        mahjong = new Mahjong(hands, generalSituation, personalSituation);
        mahjong.calculate();
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = mahjong.getNormalYakuList();

        assertEquals(5, actual.size());
        assertThat(actual, hasItems(
            MahjongYakuEnum.RINSHANKAIHOH,
            MahjongYakuEnum.SANSHOKUDOHJUN,
            MahjongYakuEnum.REACHE,
            MahjongYakuEnum.DOUBLE_REACH,
            MahjongYakuEnum.TSUMO
        ));
    }
}
