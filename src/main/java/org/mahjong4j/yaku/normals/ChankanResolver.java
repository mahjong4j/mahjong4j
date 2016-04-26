package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.CHANKAN;

/**
 * @author yu1ro
 */
public class ChankanResolver implements NormalYakuResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public ChankanResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return CHANKAN;
    }

    @Override
    public boolean isMatch() {
        if (personalSituation == null || generalSituation == null) {
            return false;
        }
        return personalSituation.isChankan();
    }
}
