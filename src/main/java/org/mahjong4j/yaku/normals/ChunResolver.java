package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         中判定クラス
 */
public class ChunResolver implements MahjongResolver {
    private final int HAN = MahjongYakuList.CHUN.getHan();

    public int howHan() {
        return HAN;
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
