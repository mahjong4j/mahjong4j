package org.mahjong4j.hands;

import org.junit.Test;
import org.mahjong4j.tile.MahjongTile;

import static org.junit.Assert.assertFalse;

/**
 * @author yu1ro
 */
public class JantoTest {

    @Test
    public void testCheck() throws Exception {

    }

    @Test
    public void testGetTile() throws Exception {

    }

    @Test
    public void testGetIsMentsu() throws Exception {

    }

    @Test
    public void testGetIsOpen() throws Exception {
        assertFalse(new Janto(MahjongTile.M1).getIsOpen());
    }

    @Test
    public void testFindJantoCandidate() throws Exception {

    }
}