package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.tile.MahjongTileType.*;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.SANSHOKUDOHJUN;

/**
 * 三色同順判定クラス
 * 萬子・索子・筒子それぞれの色で同じ並びの順子を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohjunResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = SANSHOKUDOHJUN;
    private int shuntsuCount;
    private List<Shuntsu> shuntsuList;

    private boolean manzu = false;
    private boolean pinzu = false;
    private boolean sohzu = false;


    public SanshokudohjunResolver(MentsuComp comp) {
        shuntsuCount = comp.getShuntsuCount();
        shuntsuList = comp.getShuntsuList();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 3) {
            return false;
        }

        Shuntsu candidate = null;

        for (Shuntsu shuntsu : shuntsuList) {
            MahjongTileType shuntsuType = shuntsu.getTile().getType();
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

    private void detectType(MahjongTileType shuntsuType) {
        if (shuntsuType == MANZU) {
            manzu = true;
        } else if (shuntsuType == PINZU) {
            pinzu = true;
        } else if (shuntsuType == SOHZU) {
            sohzu = true;
        }
    }
}
