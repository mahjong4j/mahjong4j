package org.mahjong4j.mahjong;


public class JunchanResolver implements MahjongResolver {

    final int HAN = MahjongYakuList.JUNCHAN.getHan();
    final int KUISAGARI = MahjongYakuList.JUNCHAN.getHan();
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

    @Override
    public int howHan() {
        return HAN;
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
        if (count == 4 && kotsuHands[janto.getCode()] == 1) {
            return true;
        } else {
            return false;
        }

    }

}
