package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MentsuComp;

/**
 * @author yu1ro
 */
public class RinshankaihohResolver implements NormalYakuResolver {
    private final MentsuComp comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public RinshankaihohResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.RINSHANKAIHOH;
    }

    @Override
    public boolean isMatch() {
        if (personalSituation == null) {
            return false;
        }

        if (comp.getKantsuCount() == 0) {
            return false;
        }

        return personalSituation.isRinshankaihoh();
    }
}
