package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.Tile;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HATSU;

/**
 * 發判定クラス
 * 發の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HatsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HATSU;
    private final List<Kotsu> kotsuList;

    public HatsuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == Tile.HAT) {
                return true;
            }
        }

        return false;
    }
}
