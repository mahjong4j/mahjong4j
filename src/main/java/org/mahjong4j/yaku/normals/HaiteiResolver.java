package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MentsuComp;

/**
 * 最後の牌でツモ和了した場合に成立
 * @author yu1ro
 */
public class HaiteiResolver implements NormalYakuResolver{
    private final MentsuComp comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public HaiteiResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.HAITEI;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation ==null) {
            return false;
        }

        return generalSituation.isHoutei() && personalSituation.isTsumo();
    }
}
