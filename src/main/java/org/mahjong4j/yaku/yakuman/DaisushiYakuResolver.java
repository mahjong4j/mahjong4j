package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;
import org.mahjong4j.yaku.normals.NormalYakuResolver;

/**
 * @author yu1ro
 *         大四喜判定クラス
 */
public class DaisushiYakuResolver implements NormalYakuResolver {

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
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
