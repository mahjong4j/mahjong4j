package org.mahjong4j.mahjong;


public class DaisushiResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isDaisushi(MahjongTile[] kotsu) {
        int sushiCount = 0;

        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getType() == MahjongTileType.FONPAI) {
                sushiCount++;
            }
        }

        return sushiCount == 4;
    }
}
