package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.RYUISO;

/**
 * @author yu1ro
 *         小四喜判定クラス
 */
public class ShosushiResolver implements YakumanResolver {
    private MahjongYakumanEnum yakuman = RYUISO;

    public ShosushiResolver(MentsuComp hands) {

    }

    public MahjongYakumanEnum getYakuman() {
        return yakuman;
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
}
