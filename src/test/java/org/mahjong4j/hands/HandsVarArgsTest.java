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
public class HandsVarArgsTest {
    private Hands actualHands;

    @Before
    public void setUp() throws Exception {
        int[] otherTiles = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            2, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };

        actualHands = new Hands(otherTiles, P7,
            new Kotsu(false, S2), new Shuntsu(true, M7), new Kantsu(false, SHA));
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        List<Mentsu> expectedMentsuList = new ArrayList<>(5);
        expectedMentsuList.add(new Toitsu(S1));
        expectedMentsuList.add(new Shuntsu(false, P7));
        expectedMentsuList.add(new Shuntsu(true, M7));
        expectedMentsuList.add(new Kotsu(false, S2));
        expectedMentsuList.add(new Kantsu(false, SHA));
        MentsuComp expected = new MentsuComp(expectedMentsuList, P7);

        assertEquals(1, actualHands.getMentsuCompSet().size());
        assertThat(actualHands.getMentsuCompSet(), hasItems(expected));
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actualHands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        Tile expected = P7;
        assertEquals(expected, actualHands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            2, 3, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 4, 0,
            0, 0, 0
        };

        assertArrayEquals(expected, actualHands.getHandsComp());
    }
}