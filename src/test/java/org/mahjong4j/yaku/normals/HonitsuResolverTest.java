package org.mahjong4j.yaku.normals;

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
public class HonitsuResolverTest {
    private HonitsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsuList = new ArrayList<>(5);
        mentsuList.add(new Toitsu(SHA));
        mentsuList.add(new Shuntsu(true, M5));
        mentsuList.add(new Shuntsu(true, M5));
        mentsuList.add(new Kotsu(false, TON));
        mentsuList.add(new Kantsu(false, HAT));
        MentsuComp comp = new MentsuComp(mentsuList, TON);
        resolver = new HonitsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.HONITSU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}