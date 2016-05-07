package org.mahjong4j.yaku.normals;


import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.hands.Toitsu;
import org.mahjong4j.tile.Tile;

import java.util.List;

import static org.mahjong4j.tile.TileType.SANGEN;
import static org.mahjong4j.yaku.normals.NormalYaku.PINFU;

/**
 * 平和判定クラス
 * 面子が全て順子で、雀頭が役牌でなく、待ちが両面待ちになっている場合に成立
 *
 * @author yu1ro
 */
public class PinfuResolver extends SituationResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = PINFU;

    private final Toitsu janto;
    private final int shuntsuCount;
    private final List<Shuntsu> shuntsuList;
    private final Tile last;


    public PinfuResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
        janto = comp.getJanto();
        shuntsuCount = comp.getShuntsuCount();
        shuntsuList = comp.getShuntsuList();
        last = comp.getLast();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 4) {
            return false;
        }
        //雀頭が三元牌の場合はfalse
        Tile janto = this.janto.getTile();
        if (janto.getType() == SANGEN) {
            return false;
        }

        if (!isSituationsNull()) {
            if (janto == generalSituation.getBakaze()) {
                return false;
            }
            if (janto == personalSituation.getJikaze()) {
                return false;
            }
        }

        boolean isRyanmen = false;
        for (Shuntsu shuntsu : shuntsuList) {
            //鳴いていた場合もfalse
            if (shuntsu.isOpen()) {
                return false;
            }

            //両面待ちならそれを保存しておく
            if (isRyanmen(shuntsu, last)) {
                isRyanmen = true;
            }
        }

        return isRyanmen;
    }

    /**
     * 両面待ちだったかを判定するため
     * 一つ一つの順子と最後の牌について判定する
     *
     * @param shuntsu 判定したい順子
     * @param last    最後の牌
     * @return 両面待ちだったか
     */
    private boolean isRyanmen(Shuntsu shuntsu, Tile last) {
        //ラスト牌と判定したい順子のtypeが違う場合はfalse
        if (shuntsu.getTile().getType() != last.getType()) {
            return false;
        }

        int shuntsuNum = shuntsu.getTile().getNumber();
        int lastNum = last.getNumber();
        if (shuntsuNum == 2 && lastNum == 1) {
            return true;
        }

        if (shuntsuNum == 8 && lastNum == 9) {
            return true;
        }

        int i = shuntsuNum - lastNum;
        if (i == 1 || i == -1) {
            return true;
        }

        return false;
    }
}
