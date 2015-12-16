package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.*;
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
    final int HAN = HONITSU.getHan();

    private List<Toitsu> toitsuList;
    private List<Shuntsu> shuntsuList;
    private List<Kotsu> kotsuList;
    private List<Kantsu> kantsuList;

    private boolean hasJihai = false;
    private MahjongTileType type = null;

    public HonitsuResolver(MentsuComp comp) {
        toitsuList = comp.getToitsuList();
        shuntsuList = comp.getShuntsuList();
        kotsuList = comp.getKotsuList();
        kantsuList = comp.getKantsuList();
    }

    public int getHan() {
        return HAN;
    }

    public MahjongYakuEnum getNormalYaku() {
        return HONITSU;
    }

    public boolean isMatch() {

        for (Toitsu toitsu : toitsuList) {
            if (!hasOnlyOneType(toitsu)) {
                return false;
            }
        }

        for (Shuntsu shuntsu : shuntsuList) {
            if (!hasOnlyOneType(shuntsu)) {
                return false;
            }
        }

        for (Kotsu kotsu : kotsuList) {
            if (!hasOnlyOneType(kotsu)) {
                return false;
            }
        }

        for (Kantsu kantsu : kantsuList) {
            if (!hasOnlyOneType(kantsu)) {
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
