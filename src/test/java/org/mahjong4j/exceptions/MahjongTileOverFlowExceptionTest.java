package org.mahjong4j.exceptions;

import org.junit.Test;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.mahjong4j.tile.Tile.M2;
import static org.mahjong4j.tile.Tile.P7;

/**
 * TODO : lastの牌が無い場合も
 *
 * @author yu1ro
 */
public class MahjongTileOverFlowExceptionTest {

    @Test(expected = MahjongTileOverFlowException.class)
    public void testManySameMentsu() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M2;
        Hands hands = new Hands(tiles, last, new Kotsu(true, P7), new Kotsu(true, P7), new Kotsu(true, P7), new Kotsu(true, P7));
        new Player(hands);
    }

    @Test(expected = MahjongTileOverFlowException.class)
    public void testManySameMentsuList() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = M2;
        List<Mentsu> mentsuList = new ArrayList<>(4);
        mentsuList.add(new Kotsu(true, P7));
        mentsuList.add(new Kotsu(true, P7));
        mentsuList.add(new Kotsu(true, P7));
        mentsuList.add(new Kotsu(true, P7));
        Hands hands = new Hands(tiles, last, mentsuList);
        new Player(hands);
    }
}