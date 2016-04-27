package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.RYANPEIKO;

/**
 * 二盃口判定クラス
 * 一盃口が２つ含まれる場合に成立
 * 一盃口とは複合しない
 *
 * @author yu1ro
 */
public class RyanpeikoResolver extends PeikoResolver implements NormalYakuResolver {
    private final MahjongYakuEnum yakuEnum = RYANPEIKO;

    private final List<Shuntsu> shuntsuList;

    public RyanpeikoResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return peiko(shuntsuList) == 2;
    }
}
