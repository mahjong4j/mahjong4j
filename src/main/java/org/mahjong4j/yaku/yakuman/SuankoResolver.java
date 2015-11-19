package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         四暗刻判定クラス
 */
public class SuankoResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }
}
