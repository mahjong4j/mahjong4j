package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         平和判定クラス
 */
public class PinfuResolver implements NormalYakuResolver {

    final int KUISAGARI = MahjongYakuEnum.PINFU.getHan();
    private final int HAN = MahjongYakuEnum.PINFU.getHan();
    private MahjongTile jikazeHai, bakazeHai;
    //boolean naki = false;

    public PinfuResolver(MahjongTile jikaze, MahjongTile bakaze) {
        this.jikazeHai = jikaze;
        this.bakazeHai = bakaze;
    }

    public PinfuResolver(MahjongHands hands) {

    }

    public int getHan() {
        return HAN;
    }


    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    public boolean isMatch() {
        return false;
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
