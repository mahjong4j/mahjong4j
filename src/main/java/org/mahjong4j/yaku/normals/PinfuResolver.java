package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.PINFU;

/**
 * 平和判定クラス
 * 面子が全て順子で、雀頭が役牌でなく、待ちが両面待ちになっている場合に成立
 *
 * @author yu1ro
 */
public class PinfuResolver implements NormalYakuResolver {
    private MahjongYakuEnum yakuEnum = PINFU;

    private MahjongTile jikazeHai, bakazeHai;
    //boolean naki = false;


    public PinfuResolver(MentsuComp hands) {

    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
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
