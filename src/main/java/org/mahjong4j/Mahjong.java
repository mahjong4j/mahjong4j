package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;
import org.mahjong4j.yaku.yakuman.YakumanResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出します
 *
 * @author yu1ro
 */
public class Mahjong {

    //付いた役満リスト
    public List<MahjongYakumanEnum> yakumanList = new ArrayList<>(1);

    //付いた通常役リスト
    public List<MahjongYakuEnum> normalYakuList = new ArrayList<>(0);

    //役満判定用に利用する
    List<YakumanResolver> yakumanResolverList;

    //通常役判定用に利用する
    List<NormalYakuResolver> normalYakuResolverList;

    public Mahjong(MahjongHands hands) {
        yakumanResolverList = Mahjong4jYakuConfig.getYakumanResolverList(hands);
        normalYakuResolverList = Mahjong4jYakuConfig.getNormalYakuResolverList(hands);

        calcYakuman();

        //役満が見つからなかった場合のみ調べる
        if (yakumanList.size() == 0) {
            calcNormalYaku();
        }
    }


    public List<MahjongYakumanEnum> getYakumanList() {
        return yakumanList;
    }

    public List<MahjongYakuEnum> getNormalYakuList() {
        return normalYakuList;
    }

    public void calcYakuman() {
        for (YakumanResolver yakumanResolver : yakumanResolverList) {
            if (yakumanResolver.isMatch()) {
                yakumanList.add(yakumanResolver.getYakuman());
            }
        }
    }

    public void calcNormalYaku() {
        for (NormalYakuResolver yakuResolver : normalYakuResolverList) {
            if (yakuResolver.isMatch()) {
                normalYakuList.add(yakuResolver.getNormalYaku());
            }
        }
    }
}
