package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class DoubleReachResolver implements NormalYakuResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public DoubleReachResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.DOUBLE_REACH;
    }

    @Override
    public boolean isMatch() {
        if ((generalSituation == null || personalSituation == null)) {
            return false;
        }
        return personalSituation.isDoubleReach() && personalSituation.isReach();
    }
}
