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
public class DaisangenResolverTest {
    private DaisangenResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(M1));
        list.add(new Shuntsu(true, S3));
        list.add(new Kotsu(true, CHN));
        list.add(new Kotsu(true, HAK));
        list.add(new Kantsu(false, HAT));
        MentsuComp comp = new MentsuComp(list, HAK);
        resolver = new DaisangenResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.DAISANGEN, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
