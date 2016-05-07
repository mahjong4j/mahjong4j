package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;

/**
 * @author yu1ro
 */
public class TsumoResolver implements NormalYakuResolver {
    private final MentsuComp comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public TsumoResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.TSUMO;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }
        boolean isOpen = false;
        for (Mentsu mentsu : comp.getAllMentsu()) {
            if (mentsu.isOpen()) {
                isOpen = true;
            }
        }
        return personalSituation.isTsumo() && !isOpen;
    }
}
