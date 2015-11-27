package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.SUANKO;

/**
 * @author yu1ro
 *         四暗刻判定クラス
 */
public class SuankoResolver implements YakumanResolver {

    public SuankoResolver(MahjongHands hands) {

    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }

    public MahjongYakumanEnum getYakuman() {
        return SUANKO;
    }

    public boolean isMatch() {
        return false;
    }
}
