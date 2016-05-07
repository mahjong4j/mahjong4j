package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.JUNCHAN;

/**
 * 純チャン判定クラス
 * 123の順子と789の順子、および111、999といった老頭牌の刻子もしくは槓子
 * のみの場合に成立
 *
 * @author yu1ro
 */
public class JunchanResolver implements NormalYakuResolver {
    private final Toitsu janto;
    private final List<Shuntsu> shuntsuList;
    private final List<Kotsu> kotsuList;
    private NormalYaku yakuEnum = JUNCHAN;

    public JunchanResolver(MentsuComp comp) {
        janto = comp.getJanto();
        shuntsuList = comp.getShuntsuList();
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (janto == null) {
            return false;
        }
        for (Shuntsu shuntsu : shuntsuList) {
            int num = shuntsu.getTile().getNumber();
            if (num != 2 && num != 8) {
                return false;
            }
        }

        for (Kotsu kotsu : kotsuList) {
            int num = kotsu.getTile().getNumber();
            if (num != 1 && num != 9) {
                return false;
            }
        }

        return true;
    }
}
