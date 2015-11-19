package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         自風判定クラス
 */
public class JikazeResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.JIKAZE.getHan();

    MahjongTile jikaze;

    public JikazeResolver(MahjongTile jikaze) {
        this.jikaze = jikaze;
    }

    public int howHan() {
        return HAN;
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
