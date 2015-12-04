package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.tile.MahjongTile.*;

/**
 * @author yu1ro
 */
public class MentsuCompTest {
    List<Kotsu> eKotsu = new ArrayList<>(1);
    List<Kantsu> eKantsu = new ArrayList<>(1);
    List<Shuntsu> eShuntsu = new ArrayList<>(2);
    private MentsuComp comp1;
    private Janto janto;
    private MahjongMentsu a, b, c, d;
    private Shuntsu shuntsu1;
    private Shuntsu shuntsu2;
    private Kotsu kotsu = new Kotsu(false, M4);
    private Kantsu kantsu = new Kantsu(true, CHN);

    public MentsuCompTest() throws Exception {
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

        eKotsu = new ArrayList<>(1);
        eKantsu = new ArrayList<>(1);
        eShuntsu = new ArrayList<>(2);

        eKotsu.add(new Kotsu(false, M1, M1, M1));
        eKantsu.add(new Kantsu(false, M2, M2, M2, M2));
        eShuntsu.add(new Shuntsu(false, M4, M5, M6));
        eShuntsu.add(new Shuntsu(true, S4, S5, S3));


        assertEquals(janto, comp1.getJanto());
        assertEquals(eKotsu, comp1.getKotsuList());
        assertEquals(eKantsu, comp1.getKantsuList());
        assertEquals(eShuntsu, comp1.getShuntsuList());
    }

    @Test
    public void testSetEachMentsu() throws Exception {
        comp1.setShuntsu(shuntsu1);
        comp1.setShuntsu(shuntsu2);
        comp1.setKotsu(kotsu);
        comp1.setKantsu(kantsu);

        eKotsu.add(new Kotsu(false, M4, M4, M4));
        eKantsu.add(new Kantsu(true, CHN, CHN, CHN, CHN));
        eShuntsu.add(new Shuntsu(true, P1, P2, P3));
        eShuntsu.add(new Shuntsu(false, S4, S5, S6));

        assertEquals(janto, comp1.getJanto());

        assertEquals(eShuntsu, comp1.getShuntsuList());
        assertEquals(eKotsu, comp1.getKotsuList());
        assertEquals(eKantsu, comp1.getKantsuList());

        assertEquals(2, comp1.getShuntsuNum());
        assertEquals(1, comp1.getKantsuNum());
        assertEquals(1, comp1.getKotsuNum());
    }
}