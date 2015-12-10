package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;

/**
 * 断么九判定クラス
 * 么九牌（一九字牌）を一切使わず、中張牌（数牌の2〜8）のみを使って手牌を完成させた場合に成立
 *
 * @author yu1ro
 */
public class TanyaoResolver implements NormalYakuResolver {
    private final int HAN = MahjongYakuEnum.TANYAO.getHan();
    private final int[] tanyao = {
        0, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 1, 1, 1, 1, 1, 1, 1, 0,
        0, 0, 0, 0,
        0, 0, 0
    };

    public TanyaoResolver(MentsuComp hands) {

    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    public boolean isTanyao(int[] hands) {
        /*
         * 七対子と複合するが、
         * 同じくこれで判定可能
         */

        for (int i = 0; i < hands.length; i++) {
            if (tanyao[i] == 0 && hands[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
