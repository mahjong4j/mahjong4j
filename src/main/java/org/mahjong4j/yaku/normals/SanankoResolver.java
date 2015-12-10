package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

/**
 * 三暗刻判定クラス
 * 暗刻が３つ存在する場合に成立
 *
 * @author yu1ro
 */
public class SanankoResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.SANANKO.getHan();

    public SanankoResolver(MentsuComp hands) {

    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    public boolean isSananko(MahjongTile[] kotsu) {
        return kotsu[2] != null;
    }
}
