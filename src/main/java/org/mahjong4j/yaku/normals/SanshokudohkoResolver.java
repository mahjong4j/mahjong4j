package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;

/**
 * @author yu1ro
 *         三色同刻判定クラス
 */
public class SanshokudohkoResolver implements YakuResolver {
    final int HAN = MahjongYakuEnum.SANSHOKUDOHKO.getKuisagari();

    boolean manzu;
    boolean pinzu;
    boolean sohzu;

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public boolean isSanshokudoko(MahjongTile[] kotsu) {
        //刻子が3つ以上ないと三色はありえない
        if (kotsu[2] == null) {
            return false;
        }

        //初期化
        manzu = false;
        pinzu = false;
        sohzu = false;

        //処理用の変数
        ArrayList<MahjongTile> categoryA = new ArrayList<MahjongTile>(0);
        ArrayList<MahjongTile> categoryB = new ArrayList<MahjongTile>(0);

        categoryA.add(kotsu[0]);

        for (int i = 1; i < kotsu.length && kotsu[i] != null; i++) {
            if (categoryA.get(0).getNumber() == kotsu[i].getNumber()) {
                categoryA.add(kotsu[i]);
            } else if (categoryB.size() == 0) {
                categoryB.add(kotsu[i]);
            } else if (categoryB.get(0).getNumber() == kotsu[i].getNumber()) {
                categoryB.add(kotsu[i]);
            } else {
                return false;
            }
        }


        if (categoryA.size() >= 3) {
            for (int k = 0; k < categoryA.size(); k++) {
                switch (categoryA.get(k).getType()) {
                    case MANZU:
                        manzu = true;
                        break;
                    case PINZU:
                        pinzu = true;
                        break;
                    case SOHZU:
                        sohzu = true;
                        break;

                    default:
                        break;
                }
            }
        } else if (categoryB.size() >= 3) {
            for (int k = 0; k < categoryB.size(); k++) {
                switch (categoryB.get(k).getType()) {
                    case MANZU:
                        manzu = true;
                        break;
                    case PINZU:
                        pinzu = true;
                        break;
                    case SOHZU:
                        sohzu = true;
                        break;

                    default:
                        break;
                }
            }

        }
        return manzu && pinzu && sohzu;
    }
}
