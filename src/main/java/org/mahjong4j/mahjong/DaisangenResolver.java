package org.mahjong4j.mahjong;


public class DaisangenResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isDaisangen(MahjongTile[] kotsu) {
        //作業変数
        int sangenCount = 0;

        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getType() == MahjongTileType.SANGEN) {
                sangenCount++;
            }
        }
        return sangenCount == 3;
    }
}
