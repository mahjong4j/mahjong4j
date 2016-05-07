package org.mahjong4j.nosituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.yaku.yakuman.Yakuman.SUANKO;

/**
 * @author yu1ro
 */
public class SuankoTest {
    Hands hands;
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            3, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 2, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = Tile.M6;
        hands = new Hands(tiles, last);
        player = new Player(hands);
        player.calculate();
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(1, actual.size());
    }

    @Test
    public void testGetYakumanListItem() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertThat(actual, hasItems(SUANKO));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(0, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE0, player.getScore());
    }
}