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
    private MentsuComp actual, expected;

    private List<Shuntsu> eShuntsu = new ArrayList<>(2);
    private List<Kotsu> eKotsu = new ArrayList<>(1);
    private List<Kantsu> eKantsu = new ArrayList<>(1);

    private Toitsu toitsu = new Toitsu(MahjongTile.M7);
    private MahjongMentsu a, b, c, d;
    private Shuntsu shuntsu1;
    private Shuntsu shuntsu2;
    private Kotsu kotsu;
    private Kantsu kantsu;

    @Before
    public void setUp() throws Exception {
        actual = new MentsuComp(new Toitsu(MahjongTile.M7));
        expected = new MentsuComp(new Toitsu(MahjongTile.M7));

        a = new Kotsu(false, M1);
        b = new Shuntsu(false, M5);
        c = new Kantsu(false, M2);
        d = new Shuntsu(true, S4);

        shuntsu1 = new Shuntsu(true, P2);
        shuntsu2 = new Shuntsu(false, S5);
        kotsu = new Kotsu(false, M4);
        kantsu = new Kantsu(true, CHN);
    }

    @Test
    public void testSetMentsu() throws Exception {
        actual.setMentsu(a);
        actual.setMentsu(b);
        actual.setMentsu(c);
        actual.setMentsu(d);

        eKotsu = new ArrayList<>(1);
        eKantsu = new ArrayList<>(1);
        eShuntsu = new ArrayList<>(2);

        eKotsu.add(new Kotsu(false, M1, M1, M1));
        eKantsu.add(new Kantsu(false, M2, M2, M2, M2));
        eShuntsu.add(new Shuntsu(false, M4, M5, M6));
        eShuntsu.add(new Shuntsu(true, S4, S5, S3));


        assertEquals(toitsu, actual.getToitsu());
        assertEquals(eKotsu, actual.getKotsuList());
        assertEquals(eKantsu, actual.getKantsuList());
        assertEquals(eShuntsu, actual.getShuntsuList());
    }

    @Test
    public void testSetEachMentsu() throws Exception {
        actual.setShuntsu(shuntsu1);
        actual.setShuntsu(shuntsu2);
        actual.setKotsu(kotsu);
        actual.setKantsu(kantsu);

        eKotsu.add(new Kotsu(false, M4, M4, M4));
        eKantsu.add(new Kantsu(true, CHN, CHN, CHN, CHN));
        eShuntsu.add(new Shuntsu(true, P1, P2, P3));
        eShuntsu.add(new Shuntsu(false, S4, S5, S6));

        assertEquals(toitsu, actual.getToitsu());

        assertEquals(eShuntsu, actual.getShuntsuList());
        assertEquals(eKotsu, actual.getKotsuList());
        assertEquals(eKantsu, actual.getKantsuList());

        assertEquals(2, actual.getShuntsuNum());
        assertEquals(1, actual.getKantsuNum());
        assertEquals(1, actual.getKotsuNum());
    }

    @Test
    public void testEqualsAndHashCode() throws Exception {
        actual.setShuntsu(shuntsu1);
        actual.setShuntsu(shuntsu2);
        actual.setKotsu(kotsu);
        actual.setKantsu(kantsu);

        expected.setMentsu(new Shuntsu(true, P2));
        expected.setMentsu(new Kotsu(false, M4));
        expected.setMentsu(new Kantsu(true, CHN));
        expected.setMentsu(new Shuntsu(false, S5));


        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
    }
}