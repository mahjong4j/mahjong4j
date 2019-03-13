package org.mahjong4j.hands;

import org.junit.Test;
import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;

/**
 * can show the bug in the original method
 *
 * @author hundun
 * Created on 2018年11月2日
 */
public class FindShuntsuCandidateTest {

    // the "timeout" shows the endless-loop
    @Test(timeout = 3000)
    public void testShouldEndLoopWhenTilesHave8M9M1P() throws HandsOverFlowException, MahjongTileOverFlowException, IllegalMentsuSizeException {
        // error when tiles haveS 8M,9M,1P(or others similar case), the rest tiles is unconcerned
        int[] tiles = {
            0, 0, 0, 0, 0, 0, 0, 1, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 0, 0, 0,
            3, 3, 3
        };
        Tile last = Tile.CHN;
        Hands hands = new Hands(tiles, last);
        hands.findMentsu();
    }
}
 