package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.HATSU;

/**
 * 發判定クラス
 * 發の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HatsuResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = HATSU;
    private List<Kotsu> kotsuList;

    public HatsuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == MahjongTile.HAT) {
                return true;
            }
        }

        return false;
    }
}
