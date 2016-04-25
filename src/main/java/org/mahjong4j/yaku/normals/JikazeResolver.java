package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

/**
 * @author yu1ro
 */
public class JikazeResolver implements NormalYakuResolver {
    private final MentsuComp comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public JikazeResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.JIKAZE;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }
        for (Kotsu kotsu : comp.getKotsuKantsu()) {
            if (kotsu.getTile() == personalSituation.getJikaze()) {
                return true;
            }
        }
        return false;
    }
}
