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
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.CHIHO;

/**
 * @author yu1ro
 */
public class ChihoTest {
    private MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            1, 1, 1, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 2, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M1;
        MahjongHands hands = new MahjongHands(tiles, last);
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation general;
        general = new GeneralSituation(true, false, PEI, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(false, true, false, false, false, false, false, NAN);

        mahjongPlayer = new MahjongPlayer(hands, general, personal);
        mahjongPlayer.calculate();
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();
        assertEquals(0, actual.size());
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(1, actual.size());
    }

    @Test
    public void testGetYakumanListItem() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertThat(actual, hasItems(CHIHO));
    }
}
