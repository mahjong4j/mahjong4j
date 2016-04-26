package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;

/**
 * 最後の牌でツモ和了した場合に成立
 *
 * @author yu1ro
 */
public class HaiteiResolver implements NormalYakuResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public HaiteiResolver(GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.HAITEI;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }

        return generalSituation.isHoutei() && personalSituation.isTsumo();
    }
}
