package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class MentsuCompChitoitsuTest {
    private MentsuComp actual;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> toitsuList = new ArrayList<>(7);
        toitsuList.add(new Toitsu(M1));
        toitsuList.add(new Toitsu(P2));
        toitsuList.add(new Toitsu(P4));
        toitsuList.add(new Toitsu(P9));
        toitsuList.add(new Toitsu(CHN));
        toitsuList.add(new Toitsu(NAN));
        toitsuList.add(new Toitsu(PEI));

        actual = new MentsuComp(toitsuList, PEI);
    }

    @Test
    public void testGetJanto() throws Exception {
        assertNull(actual.getJanto());
    }

    @Test
    public void testGetToitsuList() throws Exception {
        assertThat(actual.getToitsuList(), hasItems(
            new Toitsu(NAN),
            new Toitsu(P4),
            new Toitsu(M1),
            new Toitsu(P9),
            new Toitsu(P2),
            new Toitsu(PEI),
            new Toitsu(CHN)
        ));
    }

    @Test
    public void testGetToitsuCount() throws Exception {
        assertEquals(7, actual.getToitsuCount());
    }

    @Test
    public void testGetShuntsuCount() throws Exception {
        assertEquals(0, actual.getShuntsuCount());
    }

    @Test
    public void testGetKotsuCount() throws Exception {
        assertEquals(0, actual.getKantsuCount());
    }

    @Test
    public void testGetKantsuCount() throws Exception {
        assertEquals(0, actual.getKantsuCount());
    }

    @Test
    public void testGetAllMentsu() throws Exception {
        List<Mentsu> actualAllMentsu = actual.getAllMentsu();
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(M1)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(CHN)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(NAN)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(P9)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(P4)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(P2)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(PEI)));
    }

    @Test
    public void testEquals() throws Exception {
        List<Mentsu> expectedList = new ArrayList<>(7);
        expectedList.add(new Toitsu(M1));
        expectedList.add(new Toitsu(NAN));
        expectedList.add(new Toitsu(P9));
        expectedList.add(new Toitsu(P4));
        expectedList.add(new Toitsu(PEI));
        expectedList.add(new Toitsu(P2));
        expectedList.add(new Toitsu(CHN));

        MentsuComp expected = new MentsuComp(expectedList, PEI);
        assertTrue(actual.equals(expected));
        assertEquals(actual.hashCode(), expected.hashCode());
    }
}