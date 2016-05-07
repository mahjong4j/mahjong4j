package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class MentsuCompNormalTest {
    private MentsuComp actual;

    @Before
    public void setUp() throws Exception {
        List<Mentsu> mentsuList = new ArrayList<>(5);
        mentsuList.add(new Toitsu(P5));
        mentsuList.add(new Shuntsu(false, P2));
        mentsuList.add(new Shuntsu(true, S3));
        mentsuList.add(new Kotsu(false, NAN));
        mentsuList.add(new Kantsu(false, CHN));

        actual = new MentsuComp(mentsuList, NAN);
    }

    @Test
    public void testGetJanto() throws Exception {
        assertEquals(new Toitsu(P5, P5), actual.getJanto());
        assertNotEquals(new Toitsu(P5, S4), actual.getJanto());
    }

    @Test
    public void testGetToitsuList() throws Exception {
        assertThat(actual.getToitsuList(), hasItems(new Toitsu(P5)));
    }

    @Test
    public void testGetToitsuCount() throws Exception {
        assertEquals(1, actual.getToitsuCount());
    }

    @Test
    public void testGetShuntsuList() throws Exception {
        assertThat(actual.getShuntsuList(), hasItems(new Shuntsu(true, S3), new Shuntsu(false, P2)));
    }

    @Test
    public void testGetShuntsuCount() throws Exception {
        assertEquals(2, actual.getShuntsuCount());
    }

    @Test
    public void testGetKotsuList() throws Exception {
        assertThat(actual.getKotsuList(), hasItems(new Kotsu(false, NAN)));
    }

    @Test
    public void testGetKotsuKantsu() throws Exception {
        assertThat(actual.getKotsuKantsu(), hasItems(new Kotsu(false, NAN)));
        assertThat(actual.getKotsuKantsu(), hasItems(new Kotsu(false, CHN)));

        //刻子の数はそのまま
        assertEquals(1, actual.getKotsuCount());
    }

    @Test
    public void testGetAllMentsu() throws Exception {
        List<Mentsu> actualAllMentsu = actual.getAllMentsu();
        assertThat(actualAllMentsu, hasItems((Mentsu) new Kotsu(false, NAN)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Shuntsu(true, S3)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Kantsu(false, CHN)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Shuntsu(false, P2)));
        assertThat(actualAllMentsu, hasItems((Mentsu) new Toitsu(P5)));

    }

    @Test
    public void testGetKotsuCount() throws Exception {
        assertEquals(1, actual.getKotsuCount());
    }

    @Test
    public void testGetKantsuList() throws Exception {
        assertThat(actual.getKantsuList(), hasItems(new Kantsu(false, CHN)));
    }

    @Test
    public void testGetKantsuCount() throws Exception {
        assertEquals(1, actual.getKantsuCount());
    }

    @Test
    public void testEquals() throws Exception {
        List<Mentsu> trList = new ArrayList<>(5);
        trList.add(new Toitsu(P5));
        trList.add(new Shuntsu(true, S3));
        trList.add(new Kotsu(false, NAN));
        trList.add(new Kantsu(false, CHN));
        trList.add(new Shuntsu(false, P2));


        MentsuComp tr = new MentsuComp(trList, NAN);
        assertTrue(actual.equals(tr));
        assertEquals(actual.hashCode(), tr.hashCode());
    }

    @Test
    public void testNotEquals() throws Exception {
        List<Mentsu> flsList = new ArrayList<>(5);
        flsList.add(new Toitsu(P5));
        flsList.add(new Kotsu(false, NAN));
        flsList.add(new Shuntsu(false, P2));
        flsList.add(new Kantsu(true, CHN));
        flsList.add(new Shuntsu(true, S3));

        MentsuComp fls = new MentsuComp(flsList, S4);

        assertFalse(actual.equals(fls));
        assertNotEquals(actual.hashCode(), fls.hashCode());
    }
}