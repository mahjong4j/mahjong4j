package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.RYUISO;

/**
 * @author yu1ro
 *         緑一色判定クラス
 */
public class RyuisoResolver implements YakumanResolver {

    final int[] ryuisoHhands = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 0, 1, 0, 1, 0,
            0, 0, 0, 0,
            0, 1, 0
    };

    int[] hands;

    public RyuisoResolver(MahjongHands hands) {

    }

    public boolean isRyuiso(int[] hands) {
        for (int i = 0; i < hands.length; i++) {
            if (ryuisoHhands[i] == 0 && hands[i] > 0) {
                return false;
            } else if (ryuisoHhands[i] == 1 && hands[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public MahjongYakumanEnum getYakuman() {
        return RYUISO;
    }

    public boolean isMatch() {
        return false;
    }
}
