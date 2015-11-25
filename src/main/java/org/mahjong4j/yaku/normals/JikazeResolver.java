package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         自風判定クラス
 */
public class JikazeResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.JIKAZE.getHan();

    MahjongTile jikaze;

    public JikazeResolver(MahjongTile jikaze) {
        this.jikaze = jikaze;
    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isJikaze(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == jikaze) {
                return true;
            }

        }
        return false;
    }
}
