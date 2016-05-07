package org.mahjong4j.nosituation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.Player;
import org.mahjong4j.Score;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.tile.Tile.M1;
import static org.mahjong4j.yaku.yakuman.Yakuman.KOKUSHIMUSO;

/**
 * @author yu1ro
 */
public class KokushimusoTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] match = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1,
            1, 2, 1
        };
        Hands hands = new Hands(match, M1);
        player = new Player(hands);
        player.calculate();
    }

    @Test
    public void testChinrohtoh() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(1, actual.size());
        assertEquals(KOKUSHIMUSO, actual.get(0));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(0, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        Assert.assertEquals(Score.SCORE0, player.getScore());
    }
}
