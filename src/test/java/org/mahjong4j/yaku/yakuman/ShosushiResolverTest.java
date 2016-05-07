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
 * @author knn
 */
public class ShosushiResolverTest {
    private ShosushiResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(NAN));
        list.add(new Kotsu(true, TON));
        list.add(new Kotsu(true, M3));
        list.add(new Kotsu(true, SHA));
        list.add(new Kantsu(false, PEI));
        MentsuComp comp = new MentsuComp(list, TON);
        resolver = new ShosushiResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.SHOSUSHI, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
