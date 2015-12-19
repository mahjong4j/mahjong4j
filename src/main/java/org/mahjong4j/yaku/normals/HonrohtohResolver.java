package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

/**
 * 混老頭判定クラス
 * 么九牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonrohtohResolver implements NormalYakuResolver {
    final MahjongYakuEnum yakuEnum = MahjongYakuEnum.HONROHTOH;

    int[] honroHands = {
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 1, 1, 1,
        1, 1, 1};

    public HonrohtohResolver(MentsuComp hands) {

    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isHonrohtoh(MahjongTile[] kotsu, MahjongTile janto) {
        /*
         * 通常型用
         * 七対子用は別
         */
        //全部刻子で無ければfalse
        if (kotsu[3] == null) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (honroHands[kotsu[i].getCode()] == 1) {
                count++;
            }
        }

        return count == 4 && honroHands[janto.getCode()] == 1;
    }

    public boolean isHonrohtoh(MahjongTile[] toitsu) {
        /*
         * 七対子用
         * 字一色でもtrueになっちゃいます
         */
        if (toitsu[6] == null) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (honroHands[toitsu[i].getCode()] == 1) {
                count++;
            }
        }
        return count == 7;
    }
}
