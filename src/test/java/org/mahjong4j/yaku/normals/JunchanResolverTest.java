package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.JUNCHAN;

/**
 * @author yu1ro
 */
public class JunchanResolverTest {
    private JunchanResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(M1));
        list.add(new Shuntsu(true, S2));
        list.add(new Shuntsu(false, P2));
        list.add(new Kantsu(false, P9));
        list.add(new Kotsu(true, P1));
        MentsuComp comp = new MentsuComp(list, P1);
        resolver = new JunchanResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(JUNCHAN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}