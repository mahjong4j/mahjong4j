package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import static org.mahjong4j.yaku.normals.NormalYaku.CHANTA;

/**
 * チャンタ判定クラス
 * 123の順子と789の順子、および一九字牌の対子と刻子
 * のみで構成された場合成立
 *
 * @author yu1ro
 */
public class ChantaResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = CHANTA;

    private MentsuComp comp;

    public ChantaResolver(MentsuComp comp) {
        this.comp = comp;
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        //雀頭がnullなら七対子なのでfalse
        if (comp.getJanto() == null) {
            return false;
        }
        //雀頭が一九字牌以外ならfalse
        int jantoNum = comp.getJanto().getTile().getNumber();
        if (jantoNum != 1 && jantoNum != 9 && jantoNum != 0) {
            return false;
        }

        //順子が無ければfalse
        if (comp.getShuntsuCount() == 0) {
            return false;
        }

        //順子が123の順子と789の順子でなければfalse
        for (Shuntsu shuntsu : comp.getShuntsuList()) {
            int shuntsuNum = shuntsu.getTile().getNumber();
            if (shuntsuNum != 2 && shuntsuNum != 8) {
                return false;
            }
        }

        //刻子・槓子が一九字牌以外ならfalse
        for (Kotsu kotsu : comp.getKotsuKantsu()) {
            int kotsuNum = kotsu.getTile().getNumber();
            if (kotsuNum != 1 && kotsuNum != 9 && kotsuNum != 0) {
                return false;
            }
        }

        //ここまでくればtrue
        return true;
    }
}
