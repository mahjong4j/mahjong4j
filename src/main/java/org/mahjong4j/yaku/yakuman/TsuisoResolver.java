package org.mahjong4j.yaku.yakuman;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

public class TsuisoResolver implements MahjongResolver {

    public int howHan() {
        return 0;
    }

    /*
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

    /*
     * 七対子用
     */
    public boolean isTsuiso(MahjongTile[] toistu) {
        for (int i = 0; i < toistu.length; i++) {
            if (toistu[i].getNumber() != 0) {
                return false;
            }
        }
        return true;
    }
}
