package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class ReachResolver extends SituationResolver implements NormalYakuResolver {

    public ReachResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.REACH;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        return personalSituation.isReach();
    }
}
