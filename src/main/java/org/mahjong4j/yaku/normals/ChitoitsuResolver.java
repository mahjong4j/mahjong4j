package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.MahjongTile;

import static org.mahjong4j.yaku.normals.MahjongYakuEnum.CHITOITSU;

/**
 * 七対子判定クラス
 * 対子のみで構成された場合成立
 *
 * @author yu1ro
 */
public class ChitoitsuResolver implements NormalYakuResolver {
    static MahjongTile[] toitsu = new MahjongTile[7];

    private MahjongYakuEnum yakuEnum = CHITOITSU;
    private int toitsuCount;

    public ChitoitsuResolver(MentsuComp comp) {
        toitsuCount = comp.getToitsuCount();
    }

    public MahjongYakuEnum getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return toitsuCount == 7;
    }

    public MahjongTile[] getToitsu() {
        return toitsu;
    }
}
