package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.Yakuman.TSUISO;

/**
 * 字一色判定クラス
 * 字牌のみで構成された場合に成立
 *
 * @author yu1ro
 */
public class TsuisoResolver implements YakumanResolver {
    private final Yakuman yakuman = TSUISO;

    private final Toitsu janto;
    private final List<Shuntsu> shuntsuList;
    private final List<Toitsu> toitsuList;
    private final List<Kotsu> kotsuList;

    public TsuisoResolver(MentsuComp comp) {
        janto = comp.getJanto();
        shuntsuList = comp.getShuntsuList();
        toitsuList = comp.getToitsuList();
        kotsuList = comp.getKotsuKantsu();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (shuntsuList.size() > 0) {
            return false;
        }
        if (janto == null) {
            for (Toitsu toitsu : toitsuList) {
                if (toitsu.getTile().getNumber() != 0) {
                    return false;
                }
            }
            return true;
        }

        if (janto.getTile().getNumber() != 0) {
            return false;
        }

        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile().getNumber() != 0) {
                return false;
            }
        }

        return true;
    }
}
