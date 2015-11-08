package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;
import org.mahjong4j.yaku.MahjongResolver;

public class ShosushiResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    public boolean isShosushi(MahjongTile[] kotsu, MahjongTile janto) {
        if (janto.getType() != MahjongTileType.FONPAI) {
            return false;
        }

        int fonpaiCount = 1;

        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getType() == MahjongTileType.FONPAI) {
                fonpaiCount++;
            }
        }

        return fonpaiCount == 4;
    }
}
