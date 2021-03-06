package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SANKANTSU;

/**
 * @author yu1ro
 */
public class SankantsuResolverTest {
    private SankantsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(CHN));
        list.add(new Shuntsu(false, P2));
        list.add(new Kantsu(false, HAT));
        list.add(new Kantsu(true, HAK));
        list.add(new Kantsu(false, SHA));
        MentsuComp comp = new MentsuComp(list, P3);
        resolver = new SankantsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SANKANTSU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}