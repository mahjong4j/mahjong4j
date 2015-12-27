package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;

import java.util.List;

import static org.mahjong4j.tile.MahjongTileType.FONPAI;
import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.SHOSUSHI;

/**
 * 小四喜判定クラス
 * 4つの風牌東 南 西 北のうち3つを刻子1つを雀頭に含めて和了した時に成立
 * 4つのうち3つを刻子にし残る1つを雀頭にした場合
 *
 * @author yu1ro
 */
public class ShosushiResolver implements YakumanResolver {
    private MahjongYakumanEnum yakuman = SHOSUSHI;
    private Toitsu janto;
    private List<Kotsu> kotsuList;
    private int kotsuCount;

    public ShosushiResolver(MentsuComp comp) {
        janto = comp.getJanto();
        kotsuList = comp.getKotsuKantsu();
        kotsuCount = comp.getKotsuCount() + comp.getKantsuCount();
    }

    public MahjongYakumanEnum getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (janto == null) {
            return false;
        }

        if (janto.getTile().getType() != FONPAI) {
            return false;
        }
        if (kotsuCount < 3) {
            return false;
        }

        int shosushiCount = 0;
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile().getType() == FONPAI) {
                shosushiCount++;
            }
        }
        return shosushiCount == 3;
    }
}
