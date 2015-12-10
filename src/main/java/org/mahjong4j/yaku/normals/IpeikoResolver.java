package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

/**
 * 一盃口判定クラス
 * 223344など、同種同数の順子が2組ある場合に成立
 * 二盃口の場合は二盃口のみとなる
 *
 * @author yu1ro
 */
public class IpeikoResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.IPEIKO.getHan();
    final int KUISAGARI = MahjongYakuEnum.IPEIKO.getKuisagari();

    public IpeikoResolver(MentsuComp hands) {

    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public MahjongYakuEnum getNormalYaku() {
        return null;
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
