package org.mahjong4j.withsituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Player;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE2400;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.CHITOITSU;

/**
 * @author yu1ro
 */
public class ChitoitsuWithSituationTest {
    MahjongHands hands;
    Player player;

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
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation general;
        general = new GeneralSituation(true, false, PEI, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(true, false, false, false, false, false, false, SHA);
        hands = new MahjongHands(tiles, last);
        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = player.getNormalYakuList();

        assertEquals(1, actual.size());
        assertThat(actual, hasItems(CHITOITSU));
    }

    @Test
    public void testGetHan() throws Exception {
        assertEquals(2, player.getHan());
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(25, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE2400, player.getScore());
    }
}