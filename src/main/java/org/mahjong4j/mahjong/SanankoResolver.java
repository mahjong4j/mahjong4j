package org.mahjong4j.mahjong;


public class SanankoResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.SANANKO.getHan();

    public int howHan() {
        return HAN;
    }

    public boolean isSananko(MahjongTile[] kotsu) {
        return kotsu[2] != null;
    }

}
