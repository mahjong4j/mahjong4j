package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import java.util.List;

/**
 * 發判定クラス
 * 發の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HatsuResolver implements NormalYakuResolver {
    private final int HAN = MahjongYakuEnum.HATSU.getHan();
    List<Kotsu> kotsuList;
    List<Kantsu> kantsuList;

    public HatsuResolver(MentsuComp comp) {
        kotsuList = comp.getKotsuList();
        kantsuList = comp.getKantsuList();
    }

    public int getHan() {
        return HAN;
    }

    public MahjongYakuEnum getNormalYaku() {
        return MahjongYakuEnum.HATSU;
    }

    public boolean isMatch() {
        for (Kotsu kotsu : kotsuList) {
            if (kotsu.getTile() == MahjongTile.HAT){
                return true;
            }
        }

        for (Kantsu kantsu : kantsuList) {
            if(kantsu.getTile() == MahjongTile.HAT) {
                return true;
            }
        }

        return false;
    }
}
