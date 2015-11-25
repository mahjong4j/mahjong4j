package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         場風判定クラス
 */
public class BakazeResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.BAKAZE.getHan();

    MahjongTile bakaze;

    public BakazeResolver(MahjongTile bakaze) {
        this.bakaze = bakaze;
    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }

    public boolean isBakaze(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == bakaze) {
                return true;
            }
        }
        return false;
    }
}
