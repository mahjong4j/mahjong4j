package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongMentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.HONITSU;

/**
 * 混一色判定クラス
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonitsuResolver implements NormalYakuResolver {
    private final MahjongYakuEnum yakuEnum = HONITSU;

    private List<MahjongMentsu> allMentsu;

    private boolean hasJihai = false;
    private MahjongTileType type = null;

    public HonitsuResolver(MentsuComp comp) {
        allMentsu = comp.getAllMentsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {

        for (MahjongMentsu mentsu : allMentsu) {
            if (!hasOnlyOneType(mentsu)) {
                return false;
            }
        }

        return hasJihai;
    }

    private boolean hasOnlyOneType(MahjongMentsu mentsu) {
        if (mentsu.getTile().getNumber() == 0) {
            hasJihai = true;
        } else if (type == null) {
            type = mentsu.getTile().getType();
        } else if (type != mentsu.getTile().getType()) {
            return false;
        }
        return true;
    }
}
