package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.MentsuComp;

/**
 * @author yu1ro
 */
public class BakazeResolver implements NormalYakuResolver {
    private final MentsuComp comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public BakazeResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {

        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    @Override
    public boolean isMatch() {
        return false;
    }
}
