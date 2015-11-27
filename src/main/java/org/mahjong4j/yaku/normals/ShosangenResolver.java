package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.tile.MahjongTileType;

/**
 * 小三元判定クラス
 * 三元牌のいずれかを雀頭とし、残り2つを刻子もしくは槓子にすることで成立
 *
 * @author yu1ro
 */
public class ShosangenResolver implements NormalYakuResolver {
    final int HAN = MahjongYakuEnum.SHOSANGEN.getHan();

    boolean haku;
    boolean hatsu;
    boolean chun;

    public ShosangenResolver(MahjongHands hands) {

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
