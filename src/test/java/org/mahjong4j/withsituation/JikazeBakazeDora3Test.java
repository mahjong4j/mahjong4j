package org.mahjong4j.withsituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.Player;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE8000;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.BAKAZE;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.DORA;

/**
 * @author yu1ro
 */
public class JikazeBakazeDora3Test {
    private Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 0, 1, 1, 1, 0, 0,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 3, 0, 0,
            0, 0, 2
        };
        MahjongTile last = S7;
        MahjongHands hands = new MahjongHands(tiles, last);
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(NAN);

        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(M9);
        GeneralSituation general;
        general = new GeneralSituation(false, false, NAN, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(false, false, false, false, false, false, false, SHA);

        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<MahjongYakumanEnum> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<MahjongYakuEnum> actual = player.getNormalYakuList();

        assertEquals(4, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<MahjongYakuEnum> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(BAKAZE, DORA));
    }

    @Test
    public void testGetHan() throws Exception {
        assertEquals(4, player.getHan());

    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(42, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE8000, player.getScore());
    }
}
