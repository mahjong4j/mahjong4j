package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.SANANKO;

/**
 * 三暗刻判定クラス
 * 暗刻が３つ存在する場合に成立
 *
 * @author yu1ro
 */
public class SanankoResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = SANANKO;

    public SanankoResolver(MentsuComp hands) {

    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isSananko(MahjongTile[] kotsu) {
        return kotsu[2] != null;
    }
}
