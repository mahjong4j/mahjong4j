package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;

/**
 * 純チャン判定クラス
 * 123の順子と789の順子、および111、999といった老頭牌の刻子もしくは槓子
 * のみの場合に成立
 *
 * @author yu1ro
 */
public class JunchanResolver implements NormalYakuResolver {

    final int HAN = MahjongYakuEnum.JUNCHAN.getHan();
    final int KUISAGARI = MahjongYakuEnum.JUNCHAN.getHan();
    int[] shuntsuHands = {
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 1, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 0,
        0, 0, 0};
    int[] kotsuHands = {
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 0, 0, 0, 0, 0, 0, 1,
        0, 0, 0, 0,
        0, 0, 0};

    public JunchanResolver(MahjongHands hands) {

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

    public boolean isJunchan(MahjongTile[] shuntsu, MahjongTile[] kotsu, MahjongTile janto) {
        int count = 0;//これが4になれば全部么九牌を含み、字牌は含まない

        //まずは順子
        for (int i = 0; i < shuntsu.length && shuntsu[i] != null; i++) {
            if (shuntsuHands[shuntsu[i].getCode()] == 1) {
                count++;
            }
        }

        //次に刻子
        for (int i = 0; i < kotsu.length && kotsu[i] != null; i++) {
            if (kotsuHands[kotsu[i].getCode()] == 1) {
                count++;
            }
        }

        //4になってるかと雀頭もチェック
        return count == 4 && kotsuHands[janto.getCode()] == 1;
    }
}
