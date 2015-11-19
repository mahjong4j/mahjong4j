package org.mahjong4j;


import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         順子・刻子・（槓子）・雀頭かどうかを判定します。
 *         なので、MahjongResolverインターフェースは実装しません
 *         雀頭は面子とは言いませんがこのクラスに含めます
 */
public class MentsuResolver {

    public static boolean isShuntsu(MahjongTile a, MahjongTile b, MahjongTile c) {
        if (a.getType() != b.getType() || b.getType() != c.getType()) {
            //Typeが違えば無条件でfalse
            return false;
        }

        if (a.getNumber() == 0 || b.getNumber() == 0 || c.getNumber() == 0) {
            //字牌だったら絶対false
            return false;
        }

        /*
         * ソートする
         * a->c
         * 1->9
         * になるようにする
         */

        MahjongTile s;

        if (a.getNumber() > b.getNumber()) {
            s = a;
            a = b;
            b = s;
        }
        if (a.getNumber() > c.getNumber()) {
            s = a;
            a = c;
            c = s;
        }
        if (b.getNumber() > c.getNumber()) {
            s = b;
            b = c;
            c = s;
        }

        //ソート後のチェック
        //並んでるか調べる
        if (a.getNumber() + 1 == b.getNumber() && b.getNumber() + 1 == c.getNumber()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isKohtsu(MahjongTile a, MahjongTile b, MahjongTile c) {
        //刻子かどうか判定
        if (a == b && b == c) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isJantoh(MahjongTile a, MahjongTile b) {
        /*
         * 雀頭かどうかの判定
         * 平和用ではないです。
         * 平和用は必要にならば作る方針
         */
        if (a == b) {
            return true;
        } else {
            return false;
        }
    }
}
