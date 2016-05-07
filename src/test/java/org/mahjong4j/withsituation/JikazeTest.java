package org.mahjong4j.withsituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE2600;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.JIKAZE;

/**
 * @author yu1ro
 */
public class JikazeTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 0, 1, 1, 1, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 3, 0, 0,
            0, 0, 0
        };
        MahjongTile last = P7;

        MahjongHands hands = new MahjongHands(tiles, last, new Kantsu(false, PEI));

        List<MahjongTile> dora = new ArrayList<>(1);
        dora.add(CHN);
        List<MahjongTile> uradora = new ArrayList<>(1);
        uradora.add(HAK);
        GeneralSituation general;
        general = new GeneralSituation(false, false, SHA, dora, uradora);
        PersonalSituation personal;
        personal = new PersonalSituation(false, false, false, false, false, false, false, NAN);

        player = new Player(hands, general, personal);
        player.calculate();
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<MahjongYakumanEnum> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<MahjongYakuEnum> actual = player.getNormalYakuList();

        assertEquals(1, actual.size());
    }

    @Test
    public void testGetNormalYakuListItem() throws Exception {
        List<MahjongYakuEnum> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(JIKAZE));
    }

    @Test
    public void testGetHan() throws Exception {
        assertEquals(1, player.getHan());

    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(72, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE2600, player.getScore());
    }
}
