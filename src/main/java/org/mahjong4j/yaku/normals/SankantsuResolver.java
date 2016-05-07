package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;

import static org.mahjong4j.yaku.normals.NormalYaku.SANKANTSU;

/**
 * 三槓子判定クラス
 * 槓子が３つ存在する場合に成立
 *
 * @author yu1ro
 */
public class SankantsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SANKANTSU;
    private final int kantsuCount;

    public SankantsuResolver(MentsuComp comp) {
        kantsuCount = comp.getKantsuCount();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return kantsuCount == 3;
    }
}
