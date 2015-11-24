package org.mahjong4j.yaku.normals;


import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * @author yu1ro
 *         小三元判定クラス
 */
public class ShosangenResolver implements YakuResolver {
    final int HAN = MahjongYakuEnum.SHOSANGEN.getHan();

    boolean haku;
    boolean hatsu;
    boolean chun;

    public int getHan() {
        return HAN;
    }

    public boolean isMatch() {
        return false;
    }

    public void switching(MahjongTile s) {
        switch (s) {
            case HAK:
                haku = true;
                break;
            case HAT:
                hatsu = true;
                break;
            case CHN:
                chun = true;
                break;

            default:
                break;
        }
    }

    public boolean isShosangen(MahjongTile[] kohtsu, MahjongTile janto) {
        if (janto.getType() != MahjongTileType.SANGEN) {
            return false;
        }
        //初期化
        haku = false;
        hatsu = false;
        chun = false;

        switching(janto);

        for (int i = 0; i < kohtsu.length && kohtsu[i] != null; i++) {
            switching(kohtsu[i]);
        }

        return haku && hatsu && chun;
    }
}
