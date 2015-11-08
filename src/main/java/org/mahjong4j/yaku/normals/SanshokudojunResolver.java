package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.MahjongResolver;

import java.util.ArrayList;

public class SanshokudojunResolver implements MahjongResolver {

    final int HAN = MahjongYakuList.SANSHOKUDOUJUN.getHan();
    boolean manzu;
    boolean pinzu;
    boolean sohzu;

    public static void main(String[] args) {
        SanshokudojunResolver it = new SanshokudojunResolver();

        MahjongTile shu[] = {
                MahjongTile.HAT,
                MahjongTile.CHN,
                MahjongTile.HAK,
                MahjongTile.S3
        };

        if (it.isSanshokudojun(shu)) {
            System.out.println("True!!");
        } else {
            System.out.println("False...");
        }


    }

    public int howHan() {
        return HAN;
    }

    public boolean isSanshokudojun(MahjongTile[] shuntsu) {
        //順子が3つ以上ないと三色はありえない
        if (shuntsu[2] == null) {
            return false;
        }

        //初期化
        manzu = false;
        pinzu = false;
        sohzu = false;

        //処理用の変数
        ArrayList<MahjongTile> categoryA = new ArrayList<MahjongTile>(0);
        ArrayList<MahjongTile> categoryB = new ArrayList<MahjongTile>(0);

        categoryA.add(shuntsu[0]);

        for (int i = 1; i < shuntsu.length && shuntsu[i] != null; i++) {
            if (categoryA.get(0).getNumber() == shuntsu[i].getNumber()) {
                categoryA.add(shuntsu[i]);
            } else if (categoryB.size() == 0) {
                categoryB.add(shuntsu[i]);
            } else if (categoryB.get(0).getNumber() == shuntsu[i].getNumber()) {
                categoryB.add(shuntsu[i]);
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
