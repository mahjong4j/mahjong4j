package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * 清一色判定クラス
 * 萬子、索子、筒子のどれか一種の牌だけで構成された場合成立
 *
 * @author yu1ro
 */
public class ChinitsuResolver implements NormalYakuResolver {

    final int HAN = MahjongYakuEnum.CHINITSU.getHan();
    private int colorCount;

    public ChinitsuResolver(MahjongHands hands) {

    }

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public MahjongYakuEnum getNormalYaku() {
        return null;
    }

    //通常型用
    public boolean isChinitsu(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                              MahjongTile janto) {

        //字牌が含まれていたらfalse
        if (janto.getNumber() == 0) {
            return false;
        }
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsu[i].getNumber() == 0) {
                return false;
            }
        }

        colorCount = 0;

        manzuCheck(shuntsu, kotsu, janto);
        pinzuCheck(shuntsu, kotsu, janto);
        sozuCheck(shuntsu, kotsu, janto);


        return colorCount == 1;

    }

    //七対子用
    public boolean isChinitsu(MahjongTile[] toitsu) {
        //字牌が含まれていたらfalse
        for (int i = toitsu.length - 1; i >= 0; i--) {
            if (toitsu[i].getNumber() == 0) {
                return false;
            }
        }

        colorCount = 0;

        manzuCheck(toitsu);
        pinzuCheck(toitsu);
        sozuCheck(toitsu);

        return colorCount == 1;
    }

    /*
     * ここからそれぞれの色が含まれるかをチェックするメソッド 七対子用か通常用かは引数を見て判断して下さい。
     */

    private void manzuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                            MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.MANZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.MANZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
        } else if (flag) {
            for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                if (kotsu[i].getType() == MahjongTileType.MANZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }

        }
    }

    private void manzuCheck(MahjongTile[] toitsu) {
        for (MahjongTile aToitsu : toitsu) {
            if (aToitsu.getType() == MahjongTileType.MANZU) {
                colorCount++;
                break;
            }
        }

    }

    private void pinzuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                            MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.PINZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.PINZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
        } else if (flag) {
            for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                if (kotsu[i].getType() == MahjongTileType.PINZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }

        }
    }

    private void pinzuCheck(MahjongTile[] toitsu) {
        for (MahjongTile aToitsu : toitsu) {
            if (aToitsu.getType() == MahjongTileType.PINZU) {
                colorCount++;
                break;
            }
        }

    }

    private void sozuCheck(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                           MahjongTile janto) {
        boolean flag = true;
        if (janto.getType() == MahjongTileType.SOHZU) {
            colorCount++;
            flag = false;
        } else if (flag) {
            for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
                if (shuntsu[i].getType() == MahjongTileType.SOHZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }
        } else if (flag) {
            for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                if (kotsu[i].getType() == MahjongTileType.SOHZU) {
                    colorCount++;
                    flag = false;
                    break;
                }
            }

        }
    }

    private void sozuCheck(MahjongTile[] toitsu) {
        for (MahjongTile aToitsu : toitsu) {
            if (aToitsu.getType() == MahjongTileType.SOHZU) {
                colorCount++;
                break;
            }
        }
    }
}
