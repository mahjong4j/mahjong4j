package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.TSUISO;

/**
 * 字一色判定クラス
 * 字牌のみで構成された場合に成立
 *
 * @author yu1ro
 */
public class TsuisoResolver implements YakumanResolver {

    private Toitsu janto;
    private List<Toitsu> toitsuList;
    private List<Kotsu> kotsuList;

    public TsuisoResolver(MentsuComp comp) {
        janto = comp.getJanto();
        toitsuList = comp.getToitsuList();
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakumanEnum getYakuman() {
        return TSUISO;
    }

    public boolean isMatch() {
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
