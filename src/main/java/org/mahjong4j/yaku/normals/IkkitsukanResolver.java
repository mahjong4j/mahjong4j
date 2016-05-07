package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.hands.Shuntsu;
import org.mahjong4j.tile.TileType;

import java.util.ArrayList;
import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.IKKITSUKAN;

/**
 * 一気通貫判定クラス
 * 同種の数牌で123・456・789と揃えると成立
 *
 * @author yu1ro
 */
public class IkkitsukanResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = IKKITSUKAN;

    private List<Shuntsu> shuntsuList;
    private int shuntsuCount;

    public IkkitsukanResolver(MentsuComp comp) {
        shuntsuList = comp.getShuntsuList();
        shuntsuCount = comp.getShuntsuCount();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 3) {
            return false;
        }

        List<Shuntsu> manzu = new ArrayList<>(4);
        List<Shuntsu> sohzu = new ArrayList<>(4);
        List<Shuntsu> pinzu = new ArrayList<>(4);

        //各タイプに振り分ける
        for (Shuntsu shuntsu : shuntsuList) {
            TileType type = shuntsu.getTile().getType();
            if (type == TileType.MANZU) {
                manzu.add(shuntsu);
            } else if (type == TileType.SOHZU) {
                sohzu.add(shuntsu);
            } else if (type == TileType.PINZU) {
                pinzu.add(shuntsu);
            }
        }

        if (manzu.size() >= 3) {
            return isIkkitsukan(manzu);
        }
        if (sohzu.size() >= 3) {
            return isIkkitsukan(sohzu);
        }
        if (pinzu.size() >= 3) {
            return isIkkitsukan(pinzu);
        }
        return false;
    }

    /**
     * 123 456 789が全て含まれるかを判定します
     * 例えば萬子の順子のみが含まれる場合に正しく動作します
     * 逆に、萬子123 筒子 456 789の場合もtrueになってしまいます
     *
     * @param oneTypeShuntsuList 単一のタイプの順子リスト
     * @return 123 456 789が全て含まれるか
     */
    private boolean isIkkitsukan(List<Shuntsu> oneTypeShuntsuList) {
        //この3つが全てtrueになれば一気通貫
        boolean number2 = false;
        boolean number5 = false;
        boolean number8 = false;

        for (Shuntsu shuntsu : oneTypeShuntsuList) {
            int num = shuntsu.getTile().getNumber();
            if (num == 2) {
                number2 = true;
            } else if (num == 5) {
                number5 = true;
            } else if (num == 8) {
                number8 = true;
            }
        }
        return number2 && number5 && number8;
    }
}
