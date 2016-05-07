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
public class ChinitsuResolverTest {
    private ChinitsuResolver chinitsuResolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsuList = new ArrayList<>(5);
        mentsuList.add(new Toitsu(S3));
        mentsuList.add(new Shuntsu(true, S2));
        mentsuList.add(new Shuntsu(false, S3));
        mentsuList.add(new Kotsu(false, S7));
        mentsuList.add(new Kantsu(true, S9));

        MentsuComp comp = new MentsuComp(mentsuList, S2);
        chinitsuResolver = new ChinitsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHINITSU, chinitsuResolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chinitsuResolver.isMatch());
    }
}
