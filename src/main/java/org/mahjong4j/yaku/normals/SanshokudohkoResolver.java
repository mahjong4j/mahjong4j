package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.SANSHOKUDOHKO;

/**
 * 三色同刻判定クラス
 * 萬子・索子・筒子それぞれの色で同じ数字の刻子（槓子も含む）を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohkoResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = SANSHOKUDOHKO;
    private int kotsuCount;
    private List<Kotsu> kotsuList;

    public SanshokudohkoResolver(MentsuComp comp) {
        kotsuCount = comp.getKotsuCount() + comp.getKantsuCount();
        kotsuList = comp.getKotsuKantsu();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (kotsuCount < 3) {
            return false;
        }

        boolean manzu = false;
        boolean pinzu = false;
        boolean sohzu = false;
        for (Kotsu kotsu : kotsuList) {
            MahjongTileType kotsuType = kotsu.getTile().getType();
            if (kotsuType == MahjongTileType.MANZU) {
                manzu = true;
            } else if (kotsuType == MahjongTileType.PINZU) {
                pinzu = true;
            } else if (kotsuType == MahjongTileType.SOHZU) {
                sohzu = true;
            }
        }
        return manzu && pinzu && sohzu;
    }
}
