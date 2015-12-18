package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.SUANKO;

/**
 * 四暗刻判定クラス
 * 暗刻を4つ作って和了した場合成立
 * 暗槓が含まれても良い
 *
 * @author yu1ro
 */
public class SuankoResolver implements YakumanResolver {

    private final int count;
    private final List<Kotsu> kotsuList;

    public SuankoResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
        count = comp.getKotsuCount() + comp.getKantsuCount();
    }

    public MahjongYakumanEnum getYakuman() {
        return SUANKO;
    }

    public boolean isMatch() {
        if (count < 4) {
            return false;
        }
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getIsOpen()) {
                return false;
            }
        }

        return true;
    }
}
