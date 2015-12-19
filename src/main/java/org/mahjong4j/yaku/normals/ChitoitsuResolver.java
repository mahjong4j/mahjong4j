package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.CHITOITSU;

/**
 * 七対子判定クラス
 * 対子のみで構成された場合成立
 *
 * @author yu1ro
 */
public class ChitoitsuResolver implements NormalYakuResolver {
    static MahjongTile[] toitsu = new MahjongTile[7];
    /*
     * 七対子のクラス
     */
    private MahjongYakuEnum yakuEnum = CHITOITSU;
    int[] hands = new int[34];

    public ChitoitsuResolver(MentsuComp hands) {

    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return false;
    }

    /**
     * TODO:槓子でない4枚揃いの場合を調べる
     *
     * @param hands
     * @return
     */
    public static boolean isMatch(int[] hands) {
        int toitsuCount = 0;

        for (int i = 0; i < hands.length; i++) {
            if (hands[i] == 2) {
                //対子のを保持しておく
                toitsu[toitsuCount] = MahjongTile.valueOf(i);

                toitsuCount++;
            }
        }
        return toitsuCount == 7;
    }

    public MahjongTile[] getToitsu() {
        return toitsu;
    }

    public boolean isChitoi() {
        int toitsuCount = 0;
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] == 2) {
                //対子のを保持しておく
                toitsu[toitsuCount] = MahjongTile.valueOf(i);

                toitsuCount++;
            }
        }
        return toitsuCount == 7;
    }
}
