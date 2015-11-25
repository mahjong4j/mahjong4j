package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         清老頭判定クラス
 */
public class ChinrohtohResolver implements YakumanResolver {

    public ChinrohtohResolver(MahjongHands hands) {

    }

    public int getHan() {
        return 0;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isChinroto(MahjongTile[] kotsu, MahjongTile janto) {
        //刻子が4個無いとfalse
        if (kotsu[3] == null) {
            return false;
        }

        if (janto.getNumber() != 1 && janto.getNumber() != 9) {
            return false;
        }

        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getNumber() != 1 && kotsu[i].getNumber() != 9) {
                return false;
            }
        }
        return true;
    }

    public MahjongYakumanEnum getYakuman() {
        return null;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
