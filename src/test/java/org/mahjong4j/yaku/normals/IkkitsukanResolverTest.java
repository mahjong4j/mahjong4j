package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class IkkitsukanResolverTest {
    private IkkitsukanResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsu = new ArrayList<>(5);
        mentsu.add(new Toitsu(M3));
        mentsu.add(new Shuntsu(true, S4));
        mentsu.add(new Shuntsu(false, P2));
        mentsu.add(new Shuntsu(false, P8));
        mentsu.add(new Shuntsu(true, P5));
        MentsuComp comp = new MentsuComp(mentsu, P3);
        resolver = new IkkitsukanResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.IKKITSUKAN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}