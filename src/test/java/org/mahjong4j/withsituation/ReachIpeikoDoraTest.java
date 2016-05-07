package org.mahjong4j.withsituation;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.MahjongPlayer;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Score;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.*;

/**
 * @author yu1ro
 */
public class ReachIpeikoDoraTest {
    MahjongHands hands;
    MahjongPlayer mahjongPlayer;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 1, 1, 1, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            2, 0, 0, 0,
            0, 0, 0
        };
        MahjongTile last = M4;
        hands = new MahjongHands(tiles, last);

        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(TON);
        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(M9);
        GeneralSituation general = new GeneralSituation(false, false, TON, dora, uradora);
        PersonalSituation personal = new PersonalSituation(false, false, false, true, false, false, false, NAN);
        mahjongPlayer = new MahjongPlayer(hands, general, personal);
        mahjongPlayer.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<MahjongYakumanEnum> actual = mahjongPlayer.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();

        assertEquals(4, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<MahjongYakuEnum> actual = mahjongPlayer.getNormalYakuList();

        assertThat(actual, hasItems(REACHE, IPEIKO, DORA));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(32, mahjongPlayer.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        TestCase.assertEquals(Score.SCORE8000, mahjongPlayer.getScore());
    }
}