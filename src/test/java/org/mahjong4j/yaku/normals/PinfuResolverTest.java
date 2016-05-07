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
public class PinfuResolverTest {

    private PinfuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsuList = new ArrayList<>(5);
        mentsuList.add(new Toitsu(M9));
        mentsuList.add(new Shuntsu(false, S2));
        mentsuList.add(new Shuntsu(false, S3));
        mentsuList.add(new Shuntsu(false, P3));
        mentsuList.add(new Shuntsu(false, M5));

        MentsuComp comp = new MentsuComp(mentsuList, S3);
        resolver = new PinfuResolver(comp, null, null);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.PINFU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}