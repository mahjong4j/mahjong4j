package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         白判定クラス
 */
public class HakuResolver implements NormalYakuResolver {
    private final int HAN = MahjongYakuEnum.HAKU.getHan();

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isHaku(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == MahjongTile.HAK) {
                return true;
            }

        }
        return false;
    }
}
