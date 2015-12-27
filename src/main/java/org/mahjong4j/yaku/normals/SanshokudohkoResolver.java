package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

import static org.mahjong4j.tile.MahjongTileType.MANZU;
import static org.mahjong4j.tile.MahjongTileType.PINZU;
import static org.mahjong4j.tile.MahjongTileType.SOHZU;
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

    private boolean manzu = false;
    private boolean pinzu = false;
    private boolean sohzu = false;

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

        Kotsu candidate = null;
        for (Kotsu kotsu : kotsuList) {
            MahjongTileType shuntsuType = kotsu.getTile().getType();
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
