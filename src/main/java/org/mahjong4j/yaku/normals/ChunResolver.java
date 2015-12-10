package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

/**
 * 中判定クラス
 * 中の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class ChunResolver implements NormalYakuResolver {
    private final int HAN = MahjongYakuEnum.CHUN.getHan();

    public ChunResolver(MentsuComp hands) {

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

    public boolean isChun(MahjongTile[] kotsu) {
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i] == MahjongTile.CHN) {
                return true;
            }

        }
        return false;
    }
}
