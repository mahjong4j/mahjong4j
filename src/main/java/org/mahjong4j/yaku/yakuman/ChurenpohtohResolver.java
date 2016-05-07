package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.Yakuman.CHURENPOHTO;

/**
 * 九蓮宝燈判定クラス
 * 門前で「1112345678999+X」の形をあがった場合に成立
 *
 * @author yu1ro
 */
public class ChurenpohtohResolver implements YakumanResolver {
    private final int[] churenManzu = {3, 1, 1, 1, 1, 1, 1, 1, 3};

    private final Yakuman yakuman = CHURENPOHTO;
    private final Toitsu janto;
    private final List<Shuntsu> shuntsuList;
    private final List<Kotsu> kotsuList;

    public ChurenpohtohResolver(MentsuComp comp) {
        janto = comp.getJanto();
        shuntsuList = comp.getShuntsuList();
        kotsuList = comp.getKotsuList();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (janto == null) {
            return false;
        }
        if (janto.getTile().getNumber() == 0) {
            return false;
        }
        TileType type = janto.getTile().getType();

        int[] churen = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        churen[janto.getTile().getNumber() - 1] = 2;

        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.getTile().getType() != type) {
                return false;
            }
            churen[shuntsu.getTile().getNumber() - 2]++;
            churen[shuntsu.getTile().getNumber() - 1]++;
            churen[shuntsu.getTile().getNumber()]++;
        }

        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile().getType() != type) {
                return false;
            }
            churen[kotsu.getTile().getNumber() - 1] += 3;
        }

        boolean restOne = false;
        for (int i = 0; i < churen.length; i++) {
            int num = churen[i] - churenManzu[i];
            if (num == 1 && !restOne) {
                restOne = true;
                continue;
            }

            if (num == 1) {
                return false;
            }

            if (num < 0 || num > 1) {
                return false;
            }
        }
        return true;
    }
}
