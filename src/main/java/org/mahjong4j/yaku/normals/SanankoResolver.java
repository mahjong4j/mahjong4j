package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.SANANKO;

/**
 * 三暗刻判定クラス
 * 暗刻が３つ存在する場合に成立
 *
 * @author yu1ro
 */
public class SanankoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SANANKO;
    private final List<Kotsu> kotsuList;
    private final int kotsuCount;

    public SanankoResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
        kotsuCount = comp.getKotsuCount() + comp.getKantsuCount();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (kotsuCount < 3) {
            return false;
        }

        int ankoCount = 0;
        for (Kotsu kotsu : kotsuList) {
            if (!kotsu.isOpen()) {
                ankoCount++;
            }
        }
        return ankoCount == 3;
    }
}
