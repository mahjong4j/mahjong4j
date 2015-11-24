package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         七対子判定クラス
 */
public class ChitoitsuResolver implements YakuResolver {
    /*
     * 七対子のクラス
     */
    final int HAN = MahjongYakuEnum.CHITOITSU.getHan();
    int[] hands = new int[34];
    MahjongTile[] toitsu = new MahjongTile[7];

    public ChitoitsuResolver(int[] hands) {
        System.arraycopy(hands, 0, this.hands, 0, hands.length);
    }

    public MahjongTile[] getToitsu() {
        return toitsu;
    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
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
        return toitsuCount == 7;
    }
}
