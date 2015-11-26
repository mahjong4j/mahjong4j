package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.TSUISO;

/**
 * @author yu1ro
 *         字一色判定クラス
 */
public class TsuisoResolver implements YakumanResolver {

    public TsuisoResolver(MahjongHands hands) {

    }

    /**
     * 通常型用
     */
    public boolean isTsuiso(MahjongTile[] kotsu, MahjongTile janto) {
        if (janto.getNumber() != 0) {
            return false;
        }

        int jihaiCount = 0;
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getNumber() == 0) {
                jihaiCount++;
            } else {
                return false;
            }
        }

        return jihaiCount == 4;
    }

    /**
     * 七対子用
     */
    public boolean isTsuiso(MahjongTile[] toistu) {
        for (MahjongTile aToistu : toistu) {
            if (aToistu.getNumber() != 0) {
                return false;
            }
        }
        return true;
    }

    public MahjongYakumanEnum getYakuman() {
        return TSUISO;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }
}
