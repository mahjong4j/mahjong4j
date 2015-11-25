package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * @author yu1ro
 *         小四喜判定クラス
 */
public class ShosushiResolver implements YakumanResolver {

    public ShosushiResolver(MahjongHands hands) {

    }

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
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

    public MahjongYakumanEnum getYakuman() {
        return null;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
