package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Kantsu;
import org.mahjong4j.hands.Kotsu;
import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.hands.MentsuComp;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.CHINROTO;

/**
 * @author yu1ro
 *         清老頭判定クラス
 */
public class ChinrohtohResolver implements YakumanResolver {
    List<MentsuComp> compList;

    public ChinrohtohResolver(MahjongHands hands) {
        this.compList = hands.getMentsuCompList();
    }

    public MahjongYakumanEnum getYakuman() {
        return CHINROTO;
    }

    /**
     * @return 清老頭かどうか
     */
    public boolean isMatch() {
        //全ての上がり型を調べる
        comploop:
        for (MentsuComp comp : compList) {
            int total = comp.getKotsuNum() + comp.getKantsuNum();
            if (total != 4) {
                continue;
            }

            int tileNum = comp.getJanto().getTile().getNumber();
            if (tileNum != 1 && tileNum != 9) {
                continue;
            }

            //刻子が全て一九牌か
            for (Kotsu kotsu : comp.getKotsuList()) {
                tileNum = kotsu.getTile().getNumber();
                if (tileNum != 1 && tileNum != 9) {
                    continue comploop;
                }
            }

            //槓子が全て一九牌か
            for (Kantsu kantsu : comp.getKantsuList()) {
                tileNum = kantsu.getTile().getNumber();
                if (tileNum != 1 && tileNum != 9) {
                    continue comploop;
                }
            }

            //ここまできたら見つかっている
            return true;
        }
        return false;
    }
}
