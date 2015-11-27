package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;

/**
 * 混老頭判定クラス
 * 刻子を4つ作って和了した場合に成立（槓子が含まれていてもよい）
 *
 * @author yu1ro
 */
public class ToitoihohResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.TOITOIHO.getHan();

    int[] honroHands = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1,
            1, 1, 1};

    public ToitoihohResolver(MahjongHands hands) {

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
}
