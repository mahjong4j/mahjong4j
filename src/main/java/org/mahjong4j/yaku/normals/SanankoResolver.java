package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;
import org.mahjong4j.yaku.normals.MahjongYakuList;

public class SanankoResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.SANANKO.getHan();

    public int howHan() {
        return HAN;
    }

    public boolean isSananko(MahjongTile[] kotsu) {
        return kotsu[2] != null;
    }

}
