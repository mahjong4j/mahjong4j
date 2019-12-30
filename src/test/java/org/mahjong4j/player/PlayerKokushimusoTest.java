package org.mahjong4j.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.CHN;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;

/**
 * @author hundun
 * Created on 2019/12/30
 */
public class PlayerKokushimusoTest {
    
    private Player actual;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 2, 1, 1,
            1, 1, 1,
        };

        Hands hands = new Hands(tiles, CHN);
        PersonalSituation personalSituation = new PersonalSituation();
        GeneralSituation generalSituation = new GeneralSituation();
        actual = new Player(hands, generalSituation, personalSituation);
        actual.calculate();
    }
    
    @Test
    public void testRonScore() throws Exception {
        assertTrue(actual.getScore().getRon() > 0);
    }

}
