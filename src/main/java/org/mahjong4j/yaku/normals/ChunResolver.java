package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

/**
 * 中判定クラス
 * 中の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class ChunResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = MahjongYakuEnum.CHUN;
    private List<Kotsu> kotsuList;

    public ChunResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == MahjongTile.CHN) {
                return true;
            }
        }
        return false;
    }
}
