package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

public class HakuResolver implements MahjongResolver {
    private final int HAN = MahjongYakuList.HAKU.getHan();

    public int howHan() {
        return HAN;
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
