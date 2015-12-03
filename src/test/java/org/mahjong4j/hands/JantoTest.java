package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yu1ro
 */
public class JantoTest {
    Janto janto1;
    Janto janto2;
    Janto jantoF;

    @Before
    public void setUp() throws Exception {
        janto1 = new Janto(MahjongTile.M1);
        janto2 = new Janto(MahjongTile.M1, MahjongTile.M1);
        jantoF = new Janto(MahjongTile.M1, MahjongTile.M2);
    }

    @Test
    public void testCheck() throws Exception {
        assertTrue(Janto.check(MahjongTile.P1, MahjongTile.P1));
        assertFalse(Janto.check(MahjongTile.P1, MahjongTile.P4));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(MahjongTile.M1, janto1.getTile());
        assertEquals(MahjongTile.M1, janto2.getTile());
        assertEquals(null, jantoF.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(janto1.getIsMentsu());
        assertTrue(janto2.getIsMentsu());
        assertFalse(jantoF.getIsMentsu());
    }

    @Test
    public void testGetIsOpen() throws Exception {
        assertFalse(janto1.getIsOpen());
        assertFalse(janto2.getIsOpen());
        assertFalse(jantoF.getIsOpen());
    }

    @Test
    public void testFindJantoCandidate() throws Exception {
        int[] tiles = {
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        List<Janto> expected, actual = Janto.findJantoCandidate(tiles);

        assertEquals(1, actual.size());

        expected = new ArrayList<Janto>(7);
        expected.add(new Janto(MahjongTile.HAK));
        assertEquals(MahjongTile.HAK, expected.get(0).getTile());
    }


    @Test
    public void testCatchException() throws Exception {
        int[] tiles = {
            1, 5, 1, 1, 1, 1, 1, 0, 0,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };

        try {
            new MahjongHands(tiles, MahjongTile.M6);
        } catch (MahjongTileOverFlowException e) {//messageもテストするためにcatch
            assertEquals("麻雀の牌は4枚までしかありません", e.getMessage());
            assertEquals("M2(code = 1)が5枚見つかりました", e.getAdvice());
        }
    }

    @Test(expected = MahjongTileOverFlowException.class)
    public void testThrow() throws Exception {
        int[] tiles = {
            0, 2, 3, 4, 5, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Janto.findJantoCandidate(tiles);
    }
}