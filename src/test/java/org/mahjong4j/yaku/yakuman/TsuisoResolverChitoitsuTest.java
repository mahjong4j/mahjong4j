package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
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
public class TsuisoResolverChitoitsuTest {
    private TsuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> normalList = new ArrayList<>(5);
        normalList.add(new Toitsu(TON));
        normalList.add(new Toitsu(NAN));
        normalList.add(new Toitsu(SHA));
        normalList.add(new Toitsu(PEI));
        normalList.add(new Toitsu(HAK));
        normalList.add(new Toitsu(HAT));
        normalList.add(new Toitsu(CHN));

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