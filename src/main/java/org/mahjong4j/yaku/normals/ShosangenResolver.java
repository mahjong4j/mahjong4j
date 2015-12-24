package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Toitsu;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.SHOSANGEN;

/**
 * 小三元判定クラス
 * 三元牌のいずれかを雀頭とし、残り2つを刻子もしくは槓子にすることで成立
 *
 * @author yu1ro
 */
public class ShosangenResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = SHOSANGEN;

    private Toitsu janto;
    private List<Kotsu> kotsuList;

    public ShosangenResolver(MentsuComp comp) {
        janto = comp.getJanto();
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        //七対子の場合はありえないのでfalse
        if (janto == null) {
            return false;
        }

        if (janto.getTile().getType() != MahjongTileType.SANGEN) {
            return false;
        }
        int count = 0;
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile().getType() == MahjongTileType.SANGEN) {
                count++;
            }
            if (count == 2) {
                return true;
            }
        }

        return false;
    }
}
