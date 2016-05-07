package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.TANYAO;

/**
 * @author yu1ro
 */
public class TanyaoResolverTest {
    private TanyaoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(M3));
        list.add(new Kantsu(false, S4));
        list.add(new Shuntsu(true, P7));
        list.add(new Shuntsu(false, M3));
        list.add(new Kotsu(true, P2));
        MentsuComp comp = new MentsuComp(list, P2);
        resolver = new TanyaoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(TANYAO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}