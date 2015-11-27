package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;

/**
 * @author yu1ro
 *         一気通貫判定クラス
 */
public class IkkitsukanResolver implements NormalYakuResolver {

    final int HAN = MahjongYakuEnum.IKKITSUKAN.getHan();

    boolean number2;
    boolean number5;
    boolean number8;

    public IkkitsukanResolver(MahjongHands hands) {

    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }

    public boolean isIttsu(MahjongTile[] shuntsu) {
        //順子が3つ以上ないと三色はありえない
        if (shuntsu[2] == null) {
            return false;
        }


        //初期化
        number2 = false;
        number5 = false;
        number8 = false;

        //処理用の変数
        //同じ色のメンツを入れる。
        ArrayList<MahjongTile> categoryA = new ArrayList<MahjongTile>(0);
        ArrayList<MahjongTile> categoryB = new ArrayList<MahjongTile>(0);

        categoryA.add(shuntsu[0]);

        for (int i = 1; i < shuntsu.length && shuntsu[i] != null; i++) {
            if (categoryA.get(0).getType() == shuntsu[i].getType()) {
                categoryA.add(shuntsu[i]);
            } else if (categoryB.size() == 0) {
                categoryB.add(shuntsu[i]);
            } else if (categoryB.get(0).getType() == shuntsu[i].getType()) {
                categoryB.add(shuntsu[i]);
            } else {
                return false;
            }
        }

        if (categoryA.size() >= 3) {
            for (int k = 0; k < categoryA.size(); k++) {
                switch (categoryA.get(k).getNumber()) {
                    case 2:
                        number2 = true;
                        break;
                    case 5:
                        number5 = true;
                        break;
                    case 8:
                        number8 = true;
                        break;

                    default:
                        break;
                }
            }
        } else if (categoryB.size() >= 3) {
            for (int k = 0; k < categoryB.size(); k++) {
                switch (categoryB.get(k).getNumber()) {
                    case 2:
                        number2 = true;
                        break;
                    case 5:
                        number5 = true;
                        break;
                    case 8:
                        number8 = true;
                        break;

                    default:
                        break;
                }
            }

        }
        return number2 && number5 && number8;
    }
}
