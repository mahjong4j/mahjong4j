package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHJUN;

/**
 * 三色同順判定クラス
 * 萬子・索子・筒子それぞれの色で同じ並びの順子を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohjunResolver extends SanshokuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SANSHOKUDOHJUN;
    private final int shuntsuCount;
    private final List<Shuntsu> shuntsuList;

    public SanshokudohjunResolver(MentsuComp comp) {
        shuntsuCount = comp.getShuntsuCount();
        shuntsuList = comp.getShuntsuList();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 3) {
            return false;
        }

        Shuntsu candidate = null;

        for (Shuntsu shuntsu : shuntsuList) {
            TileType shuntsuType = shuntsu.getTile().getType();
            int shuntsuNum = shuntsu.getTile().getNumber();

            if (candidate == null) {
                candidate = shuntsu;
                continue;
            }

            if (candidate.getTile().getNumber() == shuntsuNum) {
                detectType(shuntsuType);
                detectType(candidate.getTile().getType());
            } else {
                candidate = shuntsu;
            }
        }
        return manzu && pinzu && sohzu;
    }
}
