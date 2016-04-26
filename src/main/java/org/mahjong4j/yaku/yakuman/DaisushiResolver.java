package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.DAISUSHI;

/**
 * 大四喜判定クラス
 * 東・南・西・北の四種類をすべて刻子または槓子にして和了した場合に成立
 *
 * @author knn
 */
public class DaisushiResolver implements YakumanResolver {
    private final MahjongYakumanEnum yakuman = DAISUSHI;

    private final List<Kotsu> kotsuList;

    public DaisushiResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakumanEnum getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        int fonpaiCount = 0;
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile().getType() == MahjongTileType.FONPAI) {
                fonpaiCount++;
            }
        }

        return fonpaiCount == 4;
    }
}
