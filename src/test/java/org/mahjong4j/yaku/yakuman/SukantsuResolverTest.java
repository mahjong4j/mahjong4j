package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.MahjongMentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.SUKANTSU;

/**
 * @author yu1ro
 */
public class SukantsuResolverTest {
    private SukantsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<MahjongMentsu> mentsu = new ArrayList<>(5);
        mentsu.add(new Toitsu(M3));
        mentsu.add(new Kantsu(false, M2));
        mentsu.add(new Kantsu(true, S5));
        mentsu.add(new Kantsu(true, P9));
        mentsu.add(new Kantsu(false, SHA));
        MentsuComp comp = new MentsuComp(mentsu, M3);
        resolver = new SukantsuResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(SUKANTSU, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}