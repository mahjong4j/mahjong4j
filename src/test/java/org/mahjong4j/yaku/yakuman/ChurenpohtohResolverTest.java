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
public class ChurenpohtohResolverTest {
    private ChurenpohtohResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(M1));
        list.add(new Shuntsu(false, M2));
        list.add(new Shuntsu(false, M5));
        list.add(new Kotsu(false, M9));
        list.add(new Shuntsu(false, M7));
        MentsuComp comp = new MentsuComp(list, M1);
        resolver = new ChurenpohtohResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.CHURENPOHTO, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
