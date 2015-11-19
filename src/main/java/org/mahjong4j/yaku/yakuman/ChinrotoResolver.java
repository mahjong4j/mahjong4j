package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         清老頭判定クラス
 */
public class ChinrotoResolver implements MahjongResolver {

    public int howHan() {
        return 0;
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
}
