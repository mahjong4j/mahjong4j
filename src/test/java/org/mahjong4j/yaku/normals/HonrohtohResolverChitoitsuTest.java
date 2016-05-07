package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class HonrohtohResolverChitoitsuTest {
    private HonrohtohResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(M1));
        list.add(new Toitsu(M9));
        list.add(new Toitsu(S1));
        list.add(new Toitsu(S9));
        list.add(new Toitsu(SHA));
        list.add(new Toitsu(TON));
        list.add(new Toitsu(CHN));
        MentsuComp comp = new MentsuComp(list, CHN);
        resolver = new HonrohtohResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.HONROHTOH, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}