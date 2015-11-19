package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;
import org.mahjong4j.yaku.MahjongResolver;

/**
 * @author yu1ro
 *         混一色判定クラス
 */
public class HonitsuResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.HONITSU.getHan();
    private int colorCount;

    public int howHan() {
        return HAN;
    }

    /*
     * 通常の形用の混一色チェッカー
     */
    public boolean isHonitsu(MahjongTile[] shuntsu, MahjongTile[] kotsu,
                             MahjongTile janto) {
        // 初期化する。これが1でのみtrueを返す
        colorCount = 0;
        boolean hasJihai = false;

        //字牌があるかチェック
        //雀頭から
        if (janto.getNumber() == 0) {
            hasJihai = true;
        }

        if (!hasJihai) {
            for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                if (kotsu[i].getNumber() == 0) {
                    hasJihai = true;
                    break;
                }
            }
        }

        manzuCheck(shuntsu, kotsu, janto);
        pinzuCheck(shuntsu, kotsu, janto);
        sozuCheck(shuntsu, kotsu, janto);

        if (colorCount == 1 && hasJihai) {
            return true;
        }
        return false;
    }

    /*
     * 七対子用の混一色チェッカー
     */
    public boolean isHonitsu(MahjongTile[] toitsu) {
        // 初期化する。これが1ならtrue
        colorCount = 0;
        boolean hasJihai = false;
        /*
         * 字牌が含まれるかチェック
         * 最後の方に字牌が来ると思うので後ろからチェック
         */
        for (int i = toitsu.length - 1; i >= 0; i--) {
            if (toitsu[i].getNumber() == 0) {
                hasJihai = true;
                break;
            }
        }

        manzuCheck(toitsu);
        pinzuCheck(toitsu);
        sozuCheck(toitsu);

        if (colorCount == 1) {
            return hasJihai;
        }
        return false;
    }

    /*
     * ここからそれぞれの色が含まれるかをチェックするメソッド
     * 七対子用か通常用かは引数を見て判断して下さい。
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
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.MANZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }
            }

        }
    }

    private void manzuCheck(MahjongTile[] toitsu) {
        for (int i = 0; i < toitsu.length && toitsu[i] != null; i++) {
            if (toitsu[i].getType() == MahjongTileType.MANZU) {
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
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.PINZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }
            }
        }
    }

    private void pinzuCheck(MahjongTile[] toitsu) {
        for (int i = 0; i < toitsu.length; i++) {
            if (toitsu[i].getType() == MahjongTileType.PINZU) {
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
            if (flag) {
                for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
                    if (kotsu[i].getType() == MahjongTileType.SOHZU) {
                        colorCount++;
                        flag = false;
                        break;
                    }
                }

            }
        }
    }

    private void sozuCheck(MahjongTile[] toitsu) {
        for (int i = 0; i < toitsu.length && toitsu[i] != null; i++) {
            if (toitsu[i].getType() == MahjongTileType.SOHZU) {
                colorCount++;
                break;
            }
        }
    }
}
