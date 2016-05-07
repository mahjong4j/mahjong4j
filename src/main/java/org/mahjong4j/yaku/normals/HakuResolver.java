package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

import java.util.List;

import static org.mahjong4j.tile.Tile.HAK;
import static org.mahjong4j.yaku.normals.NormalYaku.HAKU;

/**
 * 白判定クラス
 * 白の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HakuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HAKU;
    private final List<Kotsu> kotsuList;

    public HakuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
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