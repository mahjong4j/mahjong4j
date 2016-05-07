package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

/**
 * @author yu1ro
 */
public class JikazeResolver extends SituationResolver implements NormalYakuResolver {
    private final MentsuComp comp;

    public JikazeResolver(MentsuComp comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
        this.comp = comp;
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.JIKAZE;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
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
