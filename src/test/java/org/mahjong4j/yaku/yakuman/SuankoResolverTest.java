package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class SuankoResolverTest {
    private SuankoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsu = new ArrayList<>(5);
        mentsu.add(new Toitsu(M3));
        mentsu.add(new Kotsu(false, M2));
        mentsu.add(new Kotsu(false, S5));
        mentsu.add(new Kotsu(false, P9));
        mentsu.add(new Kotsu(false, SHA));
        MentsuComp comp = new MentsuComp(mentsu, P9);
        resolver = new SuankoResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.SUANKO, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}