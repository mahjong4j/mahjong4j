package org.mahjong4j.mahjong;


public class HatsuResolver implements MahjongResolver {
    private final int HAN = MahjongYakuList.HATSU.getHan();

    public int howHan() {
        return HAN;
    }

    public boolean isHatsu(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == MahjongTile.HAT) {
                return true;
            }

        }
        return false;
    }

}
