package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;

/**
 * @author yu1ro
 *         断么九判定クラス
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

    public TanyaoResolver(MahjongHands hands) {

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
