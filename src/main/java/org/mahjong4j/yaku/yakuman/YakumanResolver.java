package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;

/**
 * @author yu1ro
 */
public interface YakumanResolver {
    MahjongYakumanEnum getYakuman();

    boolean isMatch(MahjongHands hands);
}
