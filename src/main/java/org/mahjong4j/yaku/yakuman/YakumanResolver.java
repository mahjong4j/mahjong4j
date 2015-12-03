package org.mahjong4j.yaku.yakuman;

/**
 * @author yu1ro
 */
public interface YakumanResolver {
    MahjongYakumanEnum getYakuman();
    boolean isMatch();
}
