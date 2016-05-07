package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HONITSU;

/**
 * 混一色判定クラス
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonitsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HONITSU;

    private List<Mentsu> allMentsu;

    private boolean hasJihai = false;
    private TileType type = null;

    public HonitsuResolver(MentsuComp comp) {
        allMentsu = comp.getAllMentsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Mentsu mentsu : allMentsu) {
            if (!hasOnlyOneType(mentsu)) {
                return false;
            }
        }

        return hasJihai;
    }

    private boolean hasOnlyOneType(Mentsu mentsu) {
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
