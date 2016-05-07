package org.mahjong4j.nosituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.tile.Tile.M1;
import static org.mahjong4j.yaku.yakuman.Yakuman.CHINROTO;

/**
 * @author yu1ro
 */
public class ChinrohtohTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] match = {
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            2, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        Hands hands = new Hands(match, M1, new Kantsu(true, M1));
        player = new Player(hands);
        player.calculate();
    }

    @Test
    public void testChinrohtoh() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(1, actual.size());
        assertEquals(CHINROTO, actual.get(0));
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
