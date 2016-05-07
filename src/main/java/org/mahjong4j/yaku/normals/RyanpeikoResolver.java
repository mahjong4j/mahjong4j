package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.RYANPEIKO;

/**
 * 二盃口判定クラス
 * 一盃口が２つ含まれる場合に成立
 * 一盃口とは複合しない
 *
 * @author yu1ro
 */
public class RyanpeikoResolver extends PeikoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = RYANPEIKO;

    private final List<Shuntsu> shuntsuList;

    public RyanpeikoResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return peiko(shuntsuList) == 2;
    }
}
