package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         四暗刻判定クラス
 */
public class SuankoResolver implements YakumanResolver {

    public SuankoResolver(MahjongHands hands) {

    }

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isSuanko(MahjongTile[] kotsu) {
        return kotsu[3] != null;
    }

    public MahjongYakumanEnum getYakuman() {
        return null;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
