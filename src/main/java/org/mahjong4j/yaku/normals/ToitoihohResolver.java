package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.TOITOIHO;

/**
 * 混老頭判定クラス
 * 刻子を4つ作って和了した場合に成立（槓子が含まれていてもよい）
 *
 * @author yu1ro
 */
public class ToitoihohResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = TOITOIHO;

    int[] honroHands = {
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1};

    public ToitoihohResolver(MahjongHands hands) {

    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return false;
    }
}
