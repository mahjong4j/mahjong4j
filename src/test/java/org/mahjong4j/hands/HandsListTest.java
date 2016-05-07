package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class HandsListTest {
    private Hands actualHands;

    @Before
    public void setUp() throws Exception {
        int[] otherTiles = {
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0,
            0, 0, 0
        };
        List<Mentsu> mentsuList = new ArrayList<>(2);
        mentsuList.add(new Kotsu(true, P4));
        mentsuList.add(new Kantsu(true, CHN));

        actualHands = new Hands(otherTiles, TON, mentsuList);
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        List<Mentsu> expectedMentsuList = new ArrayList<>(5);
        expectedMentsuList.add(new Toitsu(TON));
        expectedMentsuList.add(new Shuntsu(false, M4));
        expectedMentsuList.add(new Shuntsu(false, P4));
        expectedMentsuList.add(new Kotsu(true, P4));
        expectedMentsuList.add(new Kantsu(true, CHN));
        MentsuComp expected = new MentsuComp(expectedMentsuList, TON);

        assertEquals(1, actualHands.getMentsuCompSet().size());
        assertThat(actualHands.getMentsuCompSet(), hasItems(expected));
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actualHands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        Tile expected = TON;
        assertEquals(expected, actualHands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            0, 0, 1, 1, 1, 0, 0, 0, 0,
            0, 0, 1, 4, 1, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0,
            0, 0, 4
        };

        assertArrayEquals(expected, actualHands.getHandsComp());
    }
}