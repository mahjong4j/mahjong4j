package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHKO;

/**
 * 三色同刻判定クラス
 * 萬子・索子・筒子それぞれの色で同じ数字の刻子（槓子も含む）を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohkoResolver extends SanshokuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SANSHOKUDOHKO;
    private final int kotsuCount;
    private final List<Kotsu> kotsuList;

    public SanshokudohkoResolver(MentsuComp comp) {
        kotsuCount = comp.getKotsuCount() + comp.getKantsuCount();
        kotsuList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (kotsuCount < 3) {
            return false;
        }

        Kotsu candidate = null;
        for (Kotsu kotsu : kotsuList) {
            TileType shuntsuType = kotsu.getTile().getType();
            int shuntsuNum = kotsu.getTile().getNumber();

            if (candidate == null) {
                candidate = kotsu;
                continue;
            }

            if (candidate.getTile().getNumber() == shuntsuNum) {
                detectType(shuntsuType);
                detectType(candidate.getTile().getType());
            } else {
                candidate = kotsu;
            }
        }
        return manzu && pinzu && sohzu;
    }
}
