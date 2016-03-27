package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

/**
 * 白判定クラス
 * 白の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HakuResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = MahjongYakuEnum.HAKU;
    private List<Kotsu> kotsuList;

    public HakuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == MahjongTile.HAK) {
                return true;
            }
        }
        return false;
    }

}