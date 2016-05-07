package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHKO;

/**
 * @author yu1ro
 */
public class SanshokudohkoResolverTest {
    private SanshokudohkoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(CHN));
        list.add(new Kantsu(false, M3));
        list.add(new Kantsu(false, S3));
        list.add(new Kantsu(false, P3));
        list.add(new Kotsu(true, HAK));
        MentsuComp comp = new MentsuComp(list, HAT);
        resolver = new SanshokudohkoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SANSHOKUDOHKO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}