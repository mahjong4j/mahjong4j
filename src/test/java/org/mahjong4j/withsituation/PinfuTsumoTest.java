package org.mahjong4j.withsituation;

import junit.framework.TestCase;
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
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE2000;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.PINFU;
import static org.mahjong4j.yaku.normals.NormalYaku.TSUMO;

/**
 * @author yu1ro
 */
public class PinfuTsumoTest {
    Hands hands;
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            1, 2, 2, 1, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 1, 1, 1, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M3;
        hands = new Hands(tiles, last);
        List<Tile> dora = new ArrayList<>(1);
        dora.add(NAN);

        List<Tile> uradora = new ArrayList<>(1);
        uradora.add(M9);
        GeneralSituation general;
        general = new GeneralSituation(false, false, NAN, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(true, false, false, false, false, false, TON);

        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertEquals(2, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(PINFU, TSUMO));
    }

    @Test
    public void testGetHan() throws Exception {
        assertEquals(2, player.getHan());
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(20, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        TestCase.assertEquals(SCORE2000, player.getScore());
    }
}