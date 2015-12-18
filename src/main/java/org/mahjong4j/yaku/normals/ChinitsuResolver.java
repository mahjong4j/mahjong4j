package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.*;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.tile.MahjongTileType.FONPAI;
import static org.mahjong4j.tile.MahjongTileType.SANGEN;

/**
 * 清一色判定クラス
 * 萬子、索子、筒子のどれか一種の牌だけで構成された場合成立
 *
 * @author yu1ro
 */
public class ChinitsuResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.CHINITSU.getHan();
    private MentsuComp comp;

    public ChinitsuResolver(MentsuComp comp) {
        this.comp = comp;
    }

    public int getHan() {
        return HAN;
    }

    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.CHINITSU;
    }

    public boolean isMatch() {
        List<MahjongMentsu> allMentsu = comp.getAllMentsu();
        MahjongTileType firstType= allMentsu.get(0).getTile().getType();
        
        if (firstType == FONPAI || firstType == SANGEN) {
            return false;
        }

        for (MahjongMentsu mentsu : allMentsu) {
            MahjongTileType checkType = mentsu.getTile().getType();
            if (firstType != checkType){
                return false;
            }
        }

        return true;
    }
}
