package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MentsuComp;

import static org.mahjong4j.yaku.yakuman.Yakuman.SUKANTSU;

/**
 * 四暗刻判定クラス
 * 暗刻を4つ作って和了した場合成立
 * 暗槓が含まれても良い
 *
 * @author yu1ro
 */
public class SukantsuResolver implements YakumanResolver {
    private final int kantsuCount;
    private final Yakuman yakuman = SUKANTSU;

    public SukantsuResolver(MentsuComp comp) {
        kantsuCount = comp.getKantsuCount();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        return kantsuCount == 4;
    }
}
