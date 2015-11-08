package org.mahjong4j.mahjong;


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
