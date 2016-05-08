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
import static org.mahjong4j.Score.SCORE8000;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.*;

/**
 * @author yu1ro
 */
public class DoubleNanReachIppatsuTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            0, 0, 2, 0, 0, 0, 1, 1, 1,
            0, 3, 0, 0,
            0, 0, 0
        };
        Tile last = M9;
        Hands hands = new Hands(tiles, last);
        List<Tile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<Tile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation general;
        general = new GeneralSituation(false, false, NAN, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(false, true, true, false, false, false, NAN);

        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertEquals(4, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(JIKAZE, BAKAZE, REACH, IPPATSU));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(38, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE8000, player.getScore());
    }
}
