package org.mahjong4j;


import org.mahjong4j.tile.MahjongTile;

public class MentsuResolver {
    /*
	 * 順子・刻子・（槓子）・雀頭かどうかを判定します。
	 * なので、MahjongResolverインターフェースは実装しません
	 * 雀頭は面子とは言いませんがこのクラスに含めちゃいます。
	 */


    public static boolean isShuntsu(MahjongTile a, MahjongTile b, MahjongTile c) {
//		System.out.println("check :"+a.toString()+" "+b.toString()+" "+c.toString());

        if (a.getType() != b.getType() || b.getType() != c.getType()) {
            //Typeが違えば無条件でfalse

//			System.out.println("return false. Because wrong type");
            return false;
        }

        if (a.getNumber() == 0 || b.getNumber() == 0 || c.getNumber() == 0) {
            //字牌だったら絶対false

//			System.out.println("return false. Because there is Jihai");
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
//		System.out.println("after sort:"+a.toString()+" "+b.toString()+" "+c.toString());


        //並んでるか調べる
        if (a.getNumber() + 1 == b.getNumber() && b.getNumber() + 1 == c.getNumber()) {
//			System.out.println("return true : "+a.toString()+" "+b.toString()+" "+c.toString());
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
