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
    private MahjongMentsu a, b, c, d;
    private Shuntsu shuntsu1;
    private Shuntsu shuntsu2;
    private Kotsu kotsu = new Kotsu(false, M4);
    private Kantsu kantsu = new Kantsu(true, CHN);

    public MentsuCompTest() throws Exception{
        shuntsu1 = new Shuntsu(true, P2);
        shuntsu2 = new Shuntsu(false, S5);
    }

    @Before
    public void setUp() throws Exception {

        janto = new Janto(MahjongTile.M7);
        comp1 = new MentsuComp(janto);

        a = new Kotsu(false, M1);
        b = new Shuntsu(false, M5);
        c = new Kantsu(false, M2);
        d = new Shuntsu(true, S4);
    }

    @Test
    public void testSetMentsu() throws Exception {
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

    @Test
    public void testSetEachMentsu() throws Exception {
        comp1.setShuntsu(shuntsu1);
        comp1.setShuntsu(shuntsu2);
        comp1.setKotsu(kotsu);
        comp1.setKantsu(kantsu);

        assertEquals(janto, comp1.getJanto());

        assertEquals(shuntsu1, comp1.getShuntsuList().get(0));
        assertEquals(shuntsu2, comp1.getShuntsuList().get(1));
        assertEquals(kotsu, comp1.getKotsuList().get(0));
        assertEquals(kantsu, comp1.getKantsuList().get(0));

        assertEquals(2, comp1.getShuntsuNum());
        assertEquals(1, comp1.getKantsuNum());
        assertEquals(1, comp1.getKotsuNum());
    }
}