package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 *         二盃口判定クラス
 */
public class RyanpeikoResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.RYANPEIKO.getHan();

    private MahjongTile[] shuntsuRevised = new MahjongTile[4];
    private MahjongTile jantoRevesed;

    public MahjongTile getJantoRevised() {
        return jantoRevesed;
    }

    public MahjongTile[] getShuntsuRevised() {
        return shuntsuRevised;
    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch(MahjongHands hands) {
        return false;
    }

    public boolean isMatch() {
        return false;
    }

    /*
     * 通常型用 七対子用と区別するため 不要ではあるが引数にjantoを加えた
     */
    public boolean isRyanpeiko(MahjongTile[] shuntsu, MahjongTile janto) {
        int peikoCount = 0;

        MahjongTile stock;
        for (int i = 0; i < shuntsu.length - 1 && shuntsu[i] != null; i++) {
            stock = shuntsu[i];
            for (int k = i + 1; k < shuntsu.length && shuntsu[k] != null; k++) {
                if (stock == shuntsu[k]) {
                    peikoCount++;
                    break;
                }
            }
        }

        return peikoCount == 2;
    }

    /*
     * 七対子の形用 ソートしてから使って下さい。
     */
    public boolean isRyanpeiko(MahjongTile[] toitsu) {
        // 作業変数
        int peikoCount = 0;
        int[] head = {1, 1, 1, 1, 1, 1, 1};

        for (int i = 1; i < toitsu.length - 1; i++) {
            if (Shuntsu.check(toitsu[i - 1], toitsu[i], toitsu[i + 1])) {
                // 順子に修正する
                shuntsuRevised[peikoCount * 2] = toitsu[i];
                shuntsuRevised[peikoCount * 2 + 1] = toitsu[i];

                // 雀頭の候補から外す
                head[i - 1] = 0;
                head[i] = 0;
                head[i + 1] = 0;

                peikoCount++;
                i += 2;
            }
        }
        if (peikoCount == 2) {
            for (int i = 0; i < head.length; i++) {
                if (head[i] == 1) {
                    jantoRevesed = toitsu[i];
                    break;
                }
            }
            return true;
        }
        return false;
    }
}
