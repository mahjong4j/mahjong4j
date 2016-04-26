package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MentsuComp;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.SUKANTSU;

/**
 * 四暗刻判定クラス
 * 暗刻を4つ作って和了した場合成立
 * 暗槓が含まれても良い
 *
 * @author yu1ro
 */
public class SukantsuResolver implements YakumanResolver {
    private final int kantsuCount;
    private final MahjongYakumanEnum yakuman = SUKANTSU;

    public SukantsuResolver(MentsuComp comp) {
        kantsuCount = comp.getKantsuCount();
    }

    public MahjongYakumanEnum getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        return kantsuCount == 4;
    }
}
