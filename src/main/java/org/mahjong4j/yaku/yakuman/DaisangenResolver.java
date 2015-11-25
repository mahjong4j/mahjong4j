package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * @author yu1ro
 *         大三元判定クラス
 */
public class DaisangenResolver implements YakumanResolver {

    public DaisangenResolver(MahjongHands hands) {

    }

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
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
        return null;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
