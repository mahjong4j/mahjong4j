package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.Tile;

import java.util.List;

/**
 * 中判定クラス
 * 中の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class ChunResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = NormalYaku.CHUN;
    private final List<Kotsu> kotsuList;

    public ChunResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == Tile.CHN) {
                return true;
            }
        }
        return false;
    }
}
