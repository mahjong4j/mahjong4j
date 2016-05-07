package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;
import org.mahjong4j.tile.Tile;

import java.util.List;

import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.RYUISO;

/**
 * 緑一色判定クラス
 * 全ての牌が緑色の場合成立
 * すなわち索子の23468 發のみの場合成立
 *
 * @author yu1ro
 */
public class RyuisoResolver implements YakumanResolver {
    private final Yakuman yakuman = RYUISO;
    private final List<Toitsu> toitsuList;
    private final List<Shuntsu> shuntsuList;
    private final List<Kotsu> kotsuList;

    public RyuisoResolver(MentsuComp hands) {
        toitsuList = hands.getToitsuList();
        shuntsuList = hands.getShuntsuList();
        kotsuList = hands.getKotsuKantsu();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        for (Toitsu toitsu : toitsuList) {
            if (!isGreen(toitsu.getTile())) {
                return false;
            }
        }
        for (Kotsu kotsu : kotsuList) {
            if (!isGreen(kotsu.getTile())) {
                return false;
            }
        }

        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.getTile() != S3) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param tile 評価する牌
     * @return 緑の牌かどうか
     */
    private boolean isGreen(Tile tile) {
        return tile == HAT
            || tile == S2
            || tile == S3
            || tile == S4
            || tile == S6
            || tile == S8;
    }
}
