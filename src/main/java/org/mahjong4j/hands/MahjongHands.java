package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

import java.util.List;

/**
 * @author yu1ro
 */
public class MahjongHands {
    private int[] hands;
    private MahjongTile last;
    private List<MahjongMentsu> mentsuList;

    public MahjongHands(int[] hands, MahjongTile last, List<MahjongMentsu> mentsuList) {
        this.hands = hands;
        this.last = last;
        this.mentsuList = mentsuList;
    }
}
