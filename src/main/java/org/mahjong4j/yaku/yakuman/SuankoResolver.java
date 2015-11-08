package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

public class SuankoResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }

}
