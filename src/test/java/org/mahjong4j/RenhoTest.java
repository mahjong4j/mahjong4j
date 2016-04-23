package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @author yu1ro
 */
public class RenhoTest {
    private Mahjong mahjong;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            3, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 2, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = MahjongTile.M9;
        MahjongHands hands = new MahjongHands(tiles, last);
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(MahjongTile.CHN);

        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(MahjongTile.M2);
        GeneralSituation generalSituation;
        generalSituation = new GeneralSituation(true, false, MahjongTile.SHA, dora, uradora);
        PersonalSituation personalSituation;
        personalSituation = new PersonalSituation(false, false, false, false, false, false, MahjongTile.NAN);

        mahjong = new Mahjong(hands, generalSituation, personalSituation);
        mahjong.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjong.getYakumanList();

        assertEquals(1, actual.size());
        assertThat(actual, hasItems(MahjongYakumanEnum.RENHO));
    }
}
