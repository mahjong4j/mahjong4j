package org.mahjong4j;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mahjong4j.tile.MahjongTile.*;

/**
 * @author yu1ro
 */
public class NoYakuTest {
    MahjongHands hands;
    MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            3, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M6;
        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(PEI);
        List<MahjongTile> uradora = new ArrayList<>(2);
        uradora.add(M9);
        uradora.add(P4);

        GeneralSituation general = new GeneralSituation(false, false, TON, dora, uradora);
        PersonalSituation personal = new PersonalSituation(false, false, false, true, false, false, false, NAN);
        hands = new MahjongHands(tiles, last, new Kotsu(true, PEI), new Shuntsu(true, P4));

        mahjongPlayer = new MahjongPlayer(hands, general, personal);
        mahjongPlayer.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuList() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(0, mahjongPlayer.getFu());
    }
}