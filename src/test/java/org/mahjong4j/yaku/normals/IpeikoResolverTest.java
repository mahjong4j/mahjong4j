package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.MahjongMentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.IPEIKO;

/**
 * @author yu1ro
 */
public class IpeikoResolverTest {
    private IpeikoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<MahjongMentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(CHN));
        list.add(new Shuntsu(false, M3));
        list.add(new Shuntsu(false, M3));
        list.add(new Shuntsu(false, P3));
        list.add(new Shuntsu(false, P5));
        MentsuComp comp = new MentsuComp(list, CHN);
        resolver = new IpeikoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(IPEIKO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}