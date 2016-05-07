package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * @author yu1ro
 */
public class IppatsuResolver extends SituationResolver implements NormalYakuResolver {

    public IppatsuResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.IPPATSU;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        return personalSituation.isIppatsu();
    }
}
