package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;

/**
 * @author yu1ro
 *         混老頭判定クラス
 *         刻子だけなら混老頭が付く時って
 *         四暗刻になっちゃうからこれ使わないかも
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
