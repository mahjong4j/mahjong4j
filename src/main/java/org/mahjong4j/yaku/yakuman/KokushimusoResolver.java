package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;

/**
 * 国士無双判定クラス
 * 特殊な形で和了るため
 * YakumanResolverはimplementsせず、
 * 別のルートで判定します
 * 具体的にはMahjongHandsクラスでこのクラスのisMatchで判定し
 * 国士無双の形で和了ったことを保持しておき、
 * Mahjongクラスでそのことを呼び出すことで
 *
 * @author yu1ro
 * @see Hands
 * @see Player
 */
public class KokushimusoResolver {
    private static final int[] kokushi = {
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1
    };
    private final int[] hands;

    public KokushimusoResolver(int[] hands) {
        this.hands = hands;
    }

    public boolean isMatch() {
        //国士の形一個ずつ減らす
        int count = 0;
        for (int i = 0; i < hands.length; i++) {
            hands[i] -= kokushi[i];

            //么九牌が無ければ(-1になったら)false
            if (hands[i] == -1) {
                return false;
            }

            //么九牌以外が含まれていたらfalse
            if (kokushi[i] == 0 && hands[i] > 0) {
                return false;
            }
            if (hands[i] == 1) {
                count++;
            }
        }
        if (count == 1) {
            //残ってるのが么九牌1つならtrue
            if (hands[0] == 1 ||
                hands[8] == 1 ||
                hands[9] == 1 ||
                hands[17] == 1 ||
                hands[18] == 1 ||
                hands[26] == 1 ||
                hands[27] == 1 ||
                hands[28] == 1 ||
                hands[29] == 1 ||
                hands[30] == 1 ||
                hands[31] == 1 ||
                hands[32] == 1 ||
                hands[33] == 1) {
                return true;
            }
        }
        //残ってるのが１個以外ならfalse
        return false;
    }
}
