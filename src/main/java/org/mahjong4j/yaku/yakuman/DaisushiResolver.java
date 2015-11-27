package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.DAISUSHI;

/**
 * @author yu1ro
 *         大四喜判定クラス
 */
public class DaisushiResolver implements YakumanResolver {

    public DaisushiResolver(MahjongHands hands) {

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

    public MahjongYakumanEnum getYakuman() {
        return DAISUSHI;
    }

    public boolean isMatch() {
        return false;
    }
}
