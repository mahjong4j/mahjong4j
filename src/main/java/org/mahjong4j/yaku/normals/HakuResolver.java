package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

import java.util.List;

import static org.mahjong4j.tile.MahjongTile.HAK;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.HAKU;

/**
 * 白判定クラス
 * 白の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HakuResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = HAKU;
    private List<Kotsu> kotsuList;

    public HakuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == HAK) {
                return true;
            }
        }
        return false;
    }

}