package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;

import static org.mahjong4j.yaku.normals.NormalYaku.TOITOIHO;

/**
 * 対々和判定クラス
 * 刻子を4つ作って和了した場合に成立（槓子が含まれていてもよい）
 *
 * @author yu1ro
 */
public class ToitoihoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = TOITOIHO;
    private final int kotsuCount;

    public ToitoihoResolver(MentsuComp comp) {
        kotsuCount = comp.getKantsuCount() + comp.getKotsuCount();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return kotsuCount == 4;
    }
}
