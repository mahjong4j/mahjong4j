package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.TSUISO;

/**
 * 字一色判定クラス
 * 字牌のみで構成された場合に成立
 *
 * @author yu1ro
 */
public class TsuisoResolver implements YakumanResolver {

    private MentsuComp comp;

    public TsuisoResolver(MentsuComp comp) {
        this.comp = comp;
    }

    public MahjongYakumanEnum getYakuman() {
        return TSUISO;
    }

    public boolean isMatch() {
        if (comp.getJanto() == null) {
            for (Toitsu toitsu : comp.getToitsuList()) {
                if (toitsu.getTile().getNumber() != 0) {
                    return false;
                }
            }
            return true;
        }

        if (comp.getJanto().getTile().getNumber() != 0) {
            return false;
        }

        for (Kotsu kotsu : comp.getKotsuList()) {
            if (kotsu.getTile().getNumber() != 0) {
                return false;
            }
        }

        for (Kantsu kantsu : comp.getKantsuList()) {
            if (kantsu.getTile().getNumber() != 0) {
                return false;
            }
        }

        return true;
    }
}
