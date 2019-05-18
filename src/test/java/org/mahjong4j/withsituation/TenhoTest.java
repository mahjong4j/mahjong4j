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
import static org.mahjong4j.Score.SCORE48000;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.TENHO;

/**
 * @author yu1ro
 */
public class TenhoTest {
    private Player player;
    private Player playerMultiMentsuComp;
    

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            3, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 1, 1, 1,
            0, 0, 2, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M1;
        Hands hands = new Hands(tiles, last);
        List<Tile> dora = new ArrayList<>(1);
        dora.add(CHN);

        List<Tile> uradora = new ArrayList<>(1);
        uradora.add(M2);
        GeneralSituation generalSituation;
        generalSituation = new GeneralSituation(true, false, TON, dora, uradora);
        PersonalSituation personalSituation;
        personalSituation = new PersonalSituation(true, true, false, false, false, false, TON);

        player = new Player(hands, generalSituation, personalSituation);
        player.calculate();
        
        // two MentsuComp：七対子、二盃口 
        int[] tiles2 = {
	        2, 2, 2, 0, 0, 0, 0, 0, 0,
	        2, 2, 2, 0, 0, 0, 0, 0, 0,
	        0, 0, 2, 0, 0, 0, 0, 0, 0,
	        0, 0, 0, 0,
	        0, 0, 0
	    };
	    Tile last2 = M1;
	    Hands hands2 = new Hands(tiles2, last2);
	    List<Tile> dora2 = new ArrayList<>(1);
	    dora.add(CHN);
	
	    List<Tile> uradora2 = new ArrayList<>(1);
	    uradora.add(M2);
	    GeneralSituation generalSituation2;
	    generalSituation2 = new GeneralSituation(true, false, TON, dora2, uradora2);
	    PersonalSituation personalSituation2;
	    personalSituation2 = new PersonalSituation(true, true, false, false, false, false, TON);
	
	    playerMultiMentsuComp = new Player(hands2, generalSituation2, personalSituation2);
	    playerMultiMentsuComp.calculate();
    }

    @Test
    public void testGetYakumanListSize() throws Exception {
        List<Yakuman> actual = player.getYakumanList();
        assertEquals(1, actual.size());
        
        actual = playerMultiMentsuComp.getYakumanList();
        assertEquals(1, actual.size());
    }

    @Test
    public void testGetYakumanListItem() throws Exception {
        List<Yakuman> actual = player.getYakumanList();
        assertThat(actual, hasItems(TENHO));
        
        actual = playerMultiMentsuComp.getYakumanList();
        assertThat(actual, hasItems(TENHO));
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();
        assertEquals(0, actual.size());
        
        actual = playerMultiMentsuComp.getNormalYakuList();
        assertEquals(0, actual.size());
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(0, player.getFu());
        
        assertEquals(0, playerMultiMentsuComp.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE48000, player.getScore());
        assertEquals(SCORE48000, playerMultiMentsuComp.getScore());
    }
}
