package org.mahjong4j.nosituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.yaku.normals.NormalYaku.IPEIKO;

/**
 * @author yu1ro
 */
public class IpeikoTest {
    Hands hands;
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1, 1, 1, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 2
        };
        Tile last = Tile.M4;
        hands = new Hands(tiles, last);
        player = new Player(hands);
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

        assertEquals(1, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(IPEIKO));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(20, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE0, player.getScore());
    }
}