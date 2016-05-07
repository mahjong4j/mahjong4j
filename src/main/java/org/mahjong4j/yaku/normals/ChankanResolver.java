package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

import static org.mahjong4j.yaku.normals.NormalYaku.CHANKAN;

/**
 * @author yu1ro
 */
public class ChankanResolver extends SituationResolver implements NormalYakuResolver {

    public ChankanResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return CHANKAN;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        return personalSituation.isChankan();
    }
}
