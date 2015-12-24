package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.MahjongTileType;

import java.util.List;

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
        boolean manzu = false;
        boolean pinzu = false;
        boolean sohzu = false;

        for (Shuntsu shuntsu : shuntsuList) {
            MahjongTileType shuntsuType = shuntsu.getTile().getType();
            if (shuntsuType == MahjongTileType.MANZU) {
                manzu = true;
            } else if (shuntsuType == MahjongTileType.PINZU){
                pinzu = true;
            } else if (shuntsuType == MahjongTileType.SOHZU){
                sohzu = true;
            }
        }
        return manzu && pinzu && sohzu;
    }
}
