package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MentsuComp;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.CHINROTO;

/**
 * 清老頭判定クラス
 * 手牌全体が老頭牌（一九牌）だけの場合成立
 *
 * @author yu1ro
 */
public class ChinrohtohResolver implements YakumanResolver {
    private MentsuComp comp;

    public ChinrohtohResolver(MentsuComp comp) {
        this.comp = comp;
    }

    public MahjongYakumanEnum getYakuman() {
        return CHINROTO;
    }

    /**
     * 違うものが見つかったらfalseを返す方針です
     *
     * @return 清老頭かどうか
     */
    public boolean isMatch() {
        int total = comp.getKotsuCount() + comp.getKantsuCount();
        if (total != 4) {
            return false;
        }

        int tileNum = comp.getToitsuList().get(0).getTile().getNumber();
        if (tileNum != 1 && tileNum != 9) {
            return false;
        }

        //刻子が全て一九牌か
        for (Kotsu kotsu : comp.getKotsuList()) {
            tileNum = kotsu.getTile().getNumber();
            if (tileNum != 1 && tileNum != 9) {
                return false;
            }
        }

        //槓子が全て一九牌か
        for (Kantsu kantsu : comp.getKantsuList()) {
            tileNum = kantsu.getTile().getNumber();
            if (tileNum != 1 && tileNum != 9) {
                return false;
            }
        }

        //ここまできたら見つかっている
        return true;
    }
}
