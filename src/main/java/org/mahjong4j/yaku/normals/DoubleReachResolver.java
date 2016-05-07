package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class DoubleReachResolver extends SituationResolver implements NormalYakuResolver {

    public DoubleReachResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.DOUBLE_REACH;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        return personalSituation.isDoubleReach() && personalSituation.isReach();
    }
}
