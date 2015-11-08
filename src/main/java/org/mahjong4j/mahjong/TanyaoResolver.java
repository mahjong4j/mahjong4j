package org.mahjong4j.mahjong;

public class TanyaoResolver implements MahjongResolver {
    private final int HAN = MahjongYakuList.TANYAO.getHan();
    private final int[] tanyao = {
            0, 1, 1, 1, 1, 1, 1, 1, 0,
            0, 1, 1, 1, 1, 1, 1, 1, 0,
            0, 1, 1, 1, 1, 1, 1, 1, 0,
            0, 0, 0, 0,
            0, 0, 0
    };

    public int howHan() {
        return HAN;
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
