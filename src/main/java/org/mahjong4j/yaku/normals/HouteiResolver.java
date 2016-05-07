package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * 最後の牌でロン和了した場合に成立
 *
 * @author yu1ro
 */
public class HouteiResolver extends SituationResolver implements NormalYakuResolver {

    public HouteiResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.HOUTEI;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        return generalSituation.isHoutei() && !personalSituation.isTsumo();
    }
}
