package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.IPEIKO;

/**
 * 一盃口判定クラス
 * 223344など、同種同数の順子が2組ある場合に成立
 * 二盃口の場合は二盃口のみとなる
 *
 * @author yu1ro
 */
public class IpeikoResolver implements NormalYakuResolver {
    private final MahjongYakuEnum yakuEnum = IPEIKO;
    private final int shuntsuCount;
    private final List<Shuntsu> shuntsuList;

    public IpeikoResolver(MentsuComp comp) {
        shuntsuCount = comp.getShuntsuCount();
        shuntsuList = comp.getShuntsuList();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 2) {
            return false;
        }

        Shuntsu stockOne = null;
        Shuntsu stockTwo = null;
        boolean ipeiko = false;
        boolean ryanpeiko = false;

        for (Shuntsu shuntsu : shuntsuList) {
            //鳴いている場合はfalse
            if (shuntsu.getIsOpen()) {
                return false;
            }

            if (stockOne == null) {
                stockOne = shuntsu;
                continue;
            }

            //１つ目の盃口が見つかった
            if (stockOne.equals(shuntsu) && !ipeiko) {
                ipeiko = true;
                continue;
            }

            if (stockTwo == null) {
                stockTwo = shuntsu;
                continue;
            }

            if (stockTwo.equals(shuntsu)) {
                ryanpeiko = true;
            }
        }

        //二盃口とは複合しない
        return ipeiko && !ryanpeiko;
    }
}
