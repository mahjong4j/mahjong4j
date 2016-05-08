package org.mahjong4j.withsituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class NoYakuTest {
    Hands hands;
    Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            3, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M6;
        List<Tile> dora = new ArrayList<>(1);
        dora.add(PEI);
        List<Tile> uradora = new ArrayList<>(2);
        uradora.add(M9);
        uradora.add(P4);

        GeneralSituation general = new GeneralSituation(false, false, TON, dora, uradora);
        PersonalSituation personal = new PersonalSituation(false, false, true, false, false, false, NAN);
        hands = new Hands(tiles, last, new Kotsu(true, PEI), new Shuntsu(true, P4));

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

        assertEquals(0, actual.size());
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