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
public class RyanpeikoResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = RYANPEIKO;

    private List<Shuntsu> shuntsuList;
    private int shuntsuCount;

    public RyanpeikoResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
        shuntsuCount = comp.getShuntsuCount();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 4) {
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
        return ipeiko && ryanpeiko;
    }
}
