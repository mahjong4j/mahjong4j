package org.mahjong4j.withsituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE2400;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.CHITOITSU;

/**
 * @author yu1ro
 */
public class ChitoitsuWithSituationTest {
    Hands hands;
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
        Tile last = M8;
        List<Tile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<Tile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation general;
        general = new GeneralSituation(true, false, PEI, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(false, false, false, false, false, false, TON);
        hands = new Hands(tiles, last);
        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

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