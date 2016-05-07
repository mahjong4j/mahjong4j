package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class TsuisoResolverNormalTest {
    private TsuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> normalList = new ArrayList<>(5);
        normalList.add(new Toitsu(NAN));
        normalList.add(new Kantsu(false, SHA));
        normalList.add(new Kotsu(true, HAK));
        normalList.add(new Kotsu(false, HAT));
        normalList.add(new Kantsu(true, TON));
        MentsuComp normal = new MentsuComp(normalList, SHA);
        resolver = new TsuisoResolver(normal);
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.TSUISO, resolver.getYakuman());
    }
}