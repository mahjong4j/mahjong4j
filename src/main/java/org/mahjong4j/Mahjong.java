package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;
import org.mahjong4j.yaku.yakuman.YakumanResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //倍満や跳満などを入れる
    public String manName;

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
        Stream<YakumanResolver> stream = yakumanResolverList.stream();
        Stream<YakumanResolver> yakumanResolverStream = stream.filter(YakumanResolver::isMatch);
        Stream<MahjongYakumanEnum> mahjongYakumanEnumStream = yakumanResolverStream.map(YakumanResolver::getYakuman);
        yakumanList.addAll(mahjongYakumanEnumStream.collect(Collectors.toList())
        );
    }

    public void calcNormalYaku() {
        Stream<NormalYakuResolver> stream = normalYakuResolverList.stream();
        Stream<NormalYakuResolver> normalYakuResolverStream = stream.filter(NormalYakuResolver::isMatch);
        Stream<MahjongYakuEnum> mahjongYakuEnumStream = normalYakuResolverStream.map(NormalYakuResolver::getNormalYaku);
        normalYakuList.addAll(mahjongYakuEnumStream.collect(Collectors.toList()));
    }

    public int calcPoint() {

        if (yakumanList.size() != 0) {
            switch (yakumanList.size()) {
                case 1:
                    manName = "役満";
                    break;
                case 2:
                    manName = "ダブル役満";
                    break;
                case 3:
                    manName = "トリプル役満";
                    break;
            }
            return yakumanList.size() * 32000;
        } else if (normalYakuList.size() != 0) {
            int han = 0;
            for (MahjongYakuEnum aNormalYaku : normalYakuList) {
                han += aNormalYaku.getHan();
            }

            switch (han) {
                case 1:
                    return 1000;
                case 2:
                    return 2000;
                case 3:
                    return 4000;
                case 4:
                case 5:
                    manName = "満貫";
                    return 8000;
                case 6:
                case 7:
                    manName = "跳満";
                    return 12000;
                case 8:
                case 9:
                case 10:
                    manName = "倍満";
                    return 16000;
                case 11:
                case 12:
                    manName = "三倍満";
                    return 24000;
                default:
                    if (han > 13) {
                        manName = "役満";
                        return 32000;
                    }
            }
        }
        return 0;
    }
}
