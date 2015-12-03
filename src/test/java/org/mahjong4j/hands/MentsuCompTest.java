package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.tile.MahjongTile.*;

/**
 * @author yu1ro
 */
public class MentsuCompTest {
    private MentsuComp comp1;
    private Janto janto;

    @Before
    public void setUp() throws Exception {

        janto = new Janto(MahjongTile.M7);
        comp1 = new MentsuComp(janto);
    }

    @Test
    public void testSet() throws Exception {
        MahjongMentsu a, b, c, d;
        a = new Kotsu(false, M1);
        b = new Shuntsu(false, M5);
        c = new Kantsu(false, M2);
        d = new Shuntsu(false, S4);
        comp1.setMentsu(a);
        comp1.setMentsu(b);
        comp1.setMentsu(c);
        comp1.setMentsu(d);

        assertEquals(janto, comp1.getJanto());
        assertEquals(a, comp1.getKotsuList().get(0));
        assertEquals(b, comp1.getShuntsuList().get(0));
        assertEquals(c, comp1.getKantsuList().get(0));
        assertEquals(d, comp1.getShuntsuList().get(1));
    }
}