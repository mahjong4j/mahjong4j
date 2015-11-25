package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.YakuResolver;

/**
 * @author yu1ro
 *         四暗刻判定クラス
 */
public class SuankoResolver implements YakuResolver {

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }
}
