package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.*;
import org.mahjong4j.tile.MahjongTileType;

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
    private MentsuComp mentsuComp;

    public ChinitsuResolver(MentsuComp mentsuComp) {
        this.mentsuComp = mentsuComp;
    }

    public int getHan() {
        return HAN;
    }

    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.CHINITSU;
    }

    public boolean isMatch() {
        MahjongTileType type = null;
        for (Toitsu toitsu : mentsuComp.getToitsuList()) {
            MahjongTileType toitsuType = toitsu.getTile().getType();
            if (toitsuType == FONPAI || toitsuType == SANGEN) {
                return false;
            }
            if (type == null) {
                type = toitsuType;
            }

            if (type != toitsuType) {
                return false;
            }
        }

        //順子がこれまでのTypeと違う場合false
        for (Shuntsu shuntsu : mentsuComp.getShuntsuList()) {
            if (type != shuntsu.getTile().getType()) {
                return false;
            }
        }

        //刻子がこれまでのTypeと違う場合false
        for (Kotsu kotsu : mentsuComp.getKotsuList()) {
            if (type != kotsu.getTile().getType()) {
                return false;
            }
        }

        //槓子がこれまでのTypeと違う場合false
        for (Kantsu kantsu : mentsuComp.getKantsuList()) {
            if (type != kantsu.getTile().getType()) {
                return false;
            }
        }

        return true;
    }
}
