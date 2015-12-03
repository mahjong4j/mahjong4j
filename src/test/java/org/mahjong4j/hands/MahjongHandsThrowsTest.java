package org.mahjong4j.hands;

import org.junit.Test;
import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.MahjongTile;

import static org.junit.Assert.assertEquals;

/**
 * @author yu1ro
 */
public class MahjongHandsThrowsTest {

    @Test(expected = HandsOverFlowException.class)
    public void testTahai() throws Exception {
        int[] tiles = {
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 2, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        new MahjongHands(tiles, MahjongTile.M1);
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
}