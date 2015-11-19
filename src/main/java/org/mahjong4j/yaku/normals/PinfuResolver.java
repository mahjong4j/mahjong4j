package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         平和判定クラス
 */
public class PinfuResolver implements MahjongResolver {

    final int KUISAGARI = MahjongYakuList.PINFU.getHan();
    private final int HAN = MahjongYakuList.PINFU.getHan();
    private MahjongTile jikazeHai, bakazeHai;
    //boolean naki = false;

    public PinfuResolver(MahjongTile jikaze, MahjongTile bakaze) {
        this.jikazeHai = jikaze;
        this.bakazeHai = bakaze;
    }

    public int howHan() {
        return HAN;
    }

    public boolean isPinfu(MahjongTile[] shuntsu, MahjongTile janto) {
        if (shuntsu[3] == null) {
            return false;
        } else {
            if (janto != jikazeHai && janto != bakazeHai && janto.getNumber() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
