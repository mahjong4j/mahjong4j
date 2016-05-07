package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * 最後の牌でツモ和了した場合に成立
 *
 * @author yu1ro
 */
public class HaiteiResolver extends SituationResolver implements NormalYakuResolver {

    public HaiteiResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.HAITEI;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }

        return generalSituation.isHoutei() && personalSituation.isTsumo();
    }
}
