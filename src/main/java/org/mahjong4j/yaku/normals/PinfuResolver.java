package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * 平和判定クラス
 * 面子が全て順子で、雀頭が役牌でなく、待ちが両面待ちになっている場合に成立
 *
 * @author yu1ro
 */
public class PinfuResolver implements NormalYakuResolver {

    final int KUISAGARI = MahjongYakuEnum.PINFU.getHan();
    private final int HAN = MahjongYakuEnum.PINFU.getHan();
    private MahjongTile jikazeHai, bakazeHai;
    //boolean naki = false;


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
            return janto != jikazeHai && janto != bakazeHai && janto.getNumber() != 0;
        }
    }
}
