package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.TANYAO;

/**
 * 断么九判定クラス
 * 么九牌（一九字牌）を一切使わず、中張牌（数牌の2〜8）のみを使って手牌を完成させた場合に成立
 *
 * @author yu1ro
 */
public class TanyaoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = TANYAO;
    private final List<Mentsu> allMentsu;

    public TanyaoResolver(MentsuComp comp) {
        allMentsu = comp.getAllMentsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Mentsu mentsu : allMentsu) {
            int number = mentsu.getTile().getNumber();
            if (number == 0 || number == 1 || number == 9) {
                return false;
            }

            int shuntsuNum = mentsu.getTile().getNumber();
            boolean isEdgeShuntsu = (shuntsuNum == 2 || shuntsuNum == 8);
            if (mentsu instanceof Shuntsu && isEdgeShuntsu) {
                return false;
            }
        }

        return true;
    }
}
