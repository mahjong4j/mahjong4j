package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

public class IpeikoResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.IPEIKO.getHan();
    final int KUISAGARI = MahjongYakuList.IPEIKO.getKuisagari();

    public int howHan() {
        return HAN;
    }

    public boolean isIpeiko(MahjongTile[] shuntsu) {

        /*
         * 一盃口でかつ二盃口の時もtrueになります 呼び出し側で二盃口の時は減らしてから使って下さい。
         */

        MahjongTile stock;
        for (int i = 0; i < shuntsu.length - 1 && shuntsu[i] != null; i++) {
            stock = shuntsu[i];
            for (int k = i + 1; k < shuntsu.length && shuntsu[k] != null; k++) {
                if (stock == shuntsu[k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
