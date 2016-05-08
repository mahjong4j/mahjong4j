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
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE32000;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.CHIHO;

/**
 * @author yu1ro
 */
public class ChihoTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            1, 1, 1, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 2, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M1;
        Hands hands = new Hands(tiles, last);
        List<Tile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<Tile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation general;
        general = new GeneralSituation(true, false, PEI, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(true, false, false, false, false, false, NAN);

        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();
        assertEquals(0, actual.size());
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(1, actual.size());
    }

    @Test
    public void testGetYakumanListItem() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertThat(actual, hasItems(CHIHO));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(0, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE32000, player.getScore());

    }
}
