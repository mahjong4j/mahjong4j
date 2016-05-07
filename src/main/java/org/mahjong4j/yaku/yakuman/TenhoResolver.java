package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class TenhoResolver implements YakumanResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public TenhoResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public Yakuman getYakuman() {
        return Yakuman.TENHO;
    }

    @Override
    public boolean isMatch() {
        // avoid NullPointerException
        if (generalSituation == null || personalSituation == null) return false;

        return generalSituation.isFirstRound() && personalSituation.isParent() && personalSituation.isTsumo();
    }
}
