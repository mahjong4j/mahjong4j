package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;

/**
 * 三色同刻判定クラス
 * 萬子・索子・筒子それぞれの色で同じ数字の刻子（槓子も含む）を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohkoResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.SANSHOKUDOHKO.getKuisagari();

    boolean manzu;
    boolean pinzu;
    boolean sohzu;

    public SanshokudohkoResolver(MentsuComp hands) {

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
            for (MahjongTile aCategoryA : categoryA) {
                switch (aCategoryA.getType()) {
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
            for (MahjongTile aCategoryB : categoryB) {
                switch (aCategoryB.getType()) {
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
