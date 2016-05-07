package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HONROHTOH;

/**
 * 混老頭判定クラス
 * 么九牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonrohtohResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HONROHTOH;

    private List<Shuntsu> shuntsuList;
    private List<Toitsu> toitsuList;
    private List<Kotsu> kotsuList;

    public HonrohtohResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
        toitsuList = comp.getToitsuList();
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    /**
     * 么九牌以外を見つけたらfalseを返す
     *
     * @return 混老頭かどうか
     */
    public boolean isMatch() {
        if (shuntsuList.size() > 0) {
            return false;
        }
        for (Toitsu toitsu : toitsuList) {
            int num = toitsu.getTile().getNumber();
            if (1 < num && num < 9) {
                return false;
            }
        }

        for (Kotsu kotsu : kotsuList) {
            int num = kotsu.getTile().getNumber();
            if (1 < num && num < 9) {
                return false;
            }
        }
        return true;
    }
}
