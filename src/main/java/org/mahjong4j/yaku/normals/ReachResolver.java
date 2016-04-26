package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class ReachResolver implements NormalYakuResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public ReachResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.REACHE;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }
        return personalSituation.isReach();
    }
}
