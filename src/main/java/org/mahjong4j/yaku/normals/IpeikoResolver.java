package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.IPEIKO;

/**
 * 一盃口判定クラス
 * 223344など、同種同数の順子が2組ある場合に成立
 * 二盃口の場合は二盃口のみとなる
 *
 * @author yu1ro
 */
public class IpeikoResolver extends PeikoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = IPEIKO;
    private final List<Shuntsu> shuntsuList;

    public IpeikoResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        //二盃口とは複合しない
        return peiko(shuntsuList) == 1;
    }
}
