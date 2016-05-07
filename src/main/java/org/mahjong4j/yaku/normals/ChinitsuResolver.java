package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.tile.TileType.FONPAI;
import static org.mahjong4j.tile.TileType.SANGEN;
import static org.mahjong4j.yaku.normals.NormalYaku.CHINITSU;

/**
 * 清一色判定クラス
 * 萬子、索子、筒子のどれか一種の牌だけで構成された場合成立
 *
 * @author yu1ro
 */
public class ChinitsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = CHINITSU;
    private final MentsuComp comp;

    public ChinitsuResolver(MentsuComp comp) {
        this.comp = comp;
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        List<Mentsu> allMentsu = comp.getAllMentsu();
        TileType firstType = allMentsu.get(0).getTile().getType();

        if (firstType == FONPAI || firstType == SANGEN) {
            return false;
        }

        for (Mentsu mentsu : allMentsu) {
            TileType checkType = mentsu.getTile().getType();
            if (firstType != checkType) {
                return false;
            }
        }

        return true;
    }
}
