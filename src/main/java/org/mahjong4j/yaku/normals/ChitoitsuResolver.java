package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

public class ChitoitsuResolver implements MahjongResolver {
    /*
     * 七対子のクラス
     */

    final int HAN = MahjongYakuList.CHIITOITSU.getHan();
    int[] hands = new int[34];
    MahjongTile[] toitsu = new MahjongTile[7];

    public ChitoitsuResolver(int[] hands) {
        for (int i = 0; i < hands.length; i++) {
            this.hands[i] = hands[i];
        }
    }

    public MahjongTile[] getToitsu() {
        return toitsu;
    }

    public int howHan() {

        return HAN;
    }

    public boolean isChitoi() {
        int toitsuCount = 0;
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] == 2) {
                //対子のを保持しておく
                toitsu[toitsuCount] = MahjongTile.getTile(i);

                toitsuCount++;
            }
        }
        if (toitsuCount == 7) {
            return true;
        } else {
            return false;
        }
    }

}
