package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.MahjongTile.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.TOITOIHO;

/**
 * @author yu1ro
 */
public class ToitoihoResolverTest {
    private ToitoihoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<MahjongMentsu> list = new ArrayList<>(5);
        list.add(new Toitsu(CHN));
        list.add(new Kantsu(false, HAT));
        list.add(new Kantsu(true, P3));
        list.add(new Kantsu(false, M3));
        list.add(new Kotsu(true, HAK));
        MentsuComp comp = new MentsuComp(list, HAK);
        resolver = new ToitoihoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(TOITOIHO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}