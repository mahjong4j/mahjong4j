package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         中判定クラス
 */
public class ChunResolver implements YakuResolver {
    private final int HAN = MahjongYakuEnum.CHUN.getHan();

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isChun(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == MahjongTile.CHN) {
                return true;
            }

        }
        return false;
    }
}
