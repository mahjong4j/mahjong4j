package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.DAISANGEN;

/**
 * @author yu1ro
 *         大三元判定クラス
 */
public class DaisangenResolver implements YakumanResolver {

    public DaisangenResolver(MahjongHands hands) {

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

    public MahjongYakumanEnum getYakuman() {
        return DAISANGEN;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
