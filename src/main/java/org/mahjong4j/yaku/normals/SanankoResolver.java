package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         三暗刻判定クラス
 */
public class SanankoResolver implements YakuResolver {
    final int HAN = MahjongYakuEnum.SANANKO.getHan();

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isSananko(MahjongTile[] kotsu) {
        return kotsu[2] != null;
    }
}
