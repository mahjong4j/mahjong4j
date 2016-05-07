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
public class ChantaResolverTest {
    private ChantaResolver chantaResolver;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> matchList = new ArrayList<>(5);
        matchList.add(new Toitsu(NAN));
        matchList.add(new Shuntsu(true, M8));
        matchList.add(new Shuntsu(true, S2));
        matchList.add(new Kotsu(false, P1));
        matchList.add(new Kantsu(false, SHA));

        MentsuComp match = new MentsuComp(matchList, S3);
        chantaResolver = new ChantaResolver(match);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHANTA, chantaResolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chantaResolver.isMatch());
    }
}