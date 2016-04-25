package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MentsuComp;

/**
 * 最後の牌でロン和了した場合に成立
 *
 * @author yu1ro
 */
public class HouteiResolver implements NormalYakuResolver {
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public HouteiResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.HOUTEI;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }
        return generalSituation.isHoutei() && !personalSituation.isTsumo();
    }
}
