package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author yu1ro
 */
public class ChinrohtohResolverTest {
    private ChinrohtohResolver chinrohtoh;
    private ChinrohtohResolver notchin;

    @Before
    public void setUp() throws Exception {
        int[] match = {
                3,0,0,0,0,0,0,0,3,
                0,0,0,0,0,0,0,0,3,
                2,0,0,0,0,0,0,0,3,
                0,0,0,0,
                0,0,0
        };

        int[] notMatch = {
                3,0,0,0,0,0,0,0,3,
                0,0,0,0,0,0,0,0,0,
                2,2,2,2,0,0,0,0,0,
                0,0,0,0,
                0,0,0
        };
        MahjongHands hands = new MahjongHands(match, MahjongTile.M1);
        chinrohtoh = new ChinrohtohResolver(hands);
        hands = new MahjongHands(notMatch, MahjongTile.M1);
        notchin = new ChinrohtohResolver(hands);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(MahjongYakumanEnum.CHINROTO, chinrohtoh.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chinrohtoh.isMatch());
        assertFalse(notchin.isMatch());
    }
}