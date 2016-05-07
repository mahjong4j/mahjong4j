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
public class RyuisoResolverTest {
    private RyuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsu = new ArrayList<>(5);
        mentsu.add(new Toitsu(HAT));
        mentsu.add(new Shuntsu(false, S3));
        mentsu.add(new Shuntsu(false, S3));
        mentsu.add(new Kotsu(false, S8));
        mentsu.add(new Kantsu(false, S6));
        MentsuComp comp = new MentsuComp(mentsu, S4);
        resolver = new RyuisoResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.RYUISO, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}