package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public abstract class SituationResolver {
    protected final GeneralSituation generalSituation;
    protected final PersonalSituation personalSituation;

    protected SituationResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    protected boolean isSituationsNull() {
        return personalSituation == null || generalSituation == null;
    }
}
