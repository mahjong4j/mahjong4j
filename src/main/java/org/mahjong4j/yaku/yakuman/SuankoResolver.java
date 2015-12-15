package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kantsu;
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
    private final List<Kantsu> kantsuList;

    public SuankoResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuList();
        kantsuList = comp.getKantsuList();
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
        for (Kantsu kantsu : kantsuList) {
            if (kantsu.getIsOpen()) {
                return false;
            }
        }

        return true;
    }
}
