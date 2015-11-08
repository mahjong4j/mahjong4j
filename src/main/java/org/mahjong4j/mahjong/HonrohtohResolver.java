package org.mahjong4j.mahjong;


/*
 * 刻子だけなら混老頭が付く時って
 * 四暗刻になっちゃうからこれ使わないかも
 */
public class HonrohtohResolver implements MahjongResolver {
    final int HAN = MahjongYakuList.HONROHTOH.getHan();

    int[] honroHands = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 1, 1, 1,
            1, 1, 1};


    public int howHan() {
        return HAN;
    }


    public boolean isHonrohtoh(MahjongTile[] kotsu, MahjongTile janto) {
        /*
		 * 通常型用
		 * 七対子用は別
		 */
        //全部刻子で無ければfalse
        if (kotsu[3] == null) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (honroHands[kotsu[i].getCode()] == 1) {
                count++;
            }
        }

        if (count == 4 && honroHands[janto.getCode()] == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isHonrohtoh(MahjongTile[] toitsu) {
		/*
		 * 七対子用
		 * 字一色でもtrueになっちゃいます
		 */
        if (toitsu[6] == null) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (honroHands[toitsu[i].getCode()] == 1) {
                count++;
            }
        }
        if (count == 7) {
            return true;
        }

        return false;
    }

}
