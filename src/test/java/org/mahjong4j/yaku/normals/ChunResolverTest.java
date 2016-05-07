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
 * @author knn
 */
public class ChunResolverTest {
    private ChunResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsuList = new ArrayList<>(5);
        mentsuList.add(new Toitsu(HAK));
        mentsuList.add(new Shuntsu(true, S3));
        mentsuList.add(new Shuntsu(true, P3));
        mentsuList.add(new Shuntsu(true, M5));
        mentsuList.add(new Kotsu(false, CHN));
        MentsuComp comp = new MentsuComp(mentsuList, M5);
        resolver = new ChunResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHUN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}