package org.mahjong4j.mahjong;


public class SuankoResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }

}
