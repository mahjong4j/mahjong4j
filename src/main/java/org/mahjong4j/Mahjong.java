package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;
import org.mahjong4j.yaku.yakuman.YakumanResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出します
 *
 * @author yu1ro
 */
public class Mahjong {

    //付いた役満リスト
    private List<MahjongYakumanEnum> yakumanList = new ArrayList<>(1);

    //付いた通常役リスト
    private List<MahjongYakuEnum> normalYakuList = new ArrayList<>(0);

    //翻
    private int han;

    private MahjongHands hands;
    private GeneralSituation generalSituation;
    private PersonalSituation personalSituation;

    public Mahjong(MahjongHands hands) {
        this.hands = hands;
    }

    public Mahjong(MahjongHands hands, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.hands = hands;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }


    public List<MahjongYakumanEnum> getYakumanList() {
        return yakumanList;
    }

    public List<MahjongYakuEnum> getNormalYakuList() {
        return normalYakuList;
    }

    public void calculate() {
        //和了れない場合は即座に終了
        if (!hands.getCanWin()) return;

        //国士無双の場合はそれ以外ありえないので保存して即座に終了
        if (hands.getIsKokushimuso()) {
            yakumanList.add(MahjongYakumanEnum.KOKUSHIMUSO);
            return;
        }

        //役満を探し見つかれば通常役は調べずに終了
        if (findYakuman()) return;

        findNormalYaku();
    }

    /**
     * @return 役満が見つかったか
     */
    private boolean findYakuman() {
        //役満をストックしておき、見つかったら先にこちらに保存する
        List<MahjongYakumanEnum> yakumanStock = new ArrayList<>(4);

        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompList()) {
            Set<YakumanResolver> yakumanResolverSet
                = Mahjong4jYakuConfig.getYakumanResolverSet(comp, generalSituation, personalSituation);

            for (YakumanResolver resolver : yakumanResolverSet) {
                if (resolver.isMatch()) {
                    yakumanStock.add(resolver.getYakuman());
                }
            }

            //ストックと保存する役満リストと比較し大きい方を保存する
            if (yakumanList.size() < yakumanStock.size()) {
                yakumanList = yakumanStock;
            }
        }

        return yakumanList.size() > 0;
    }

    /**
     *
     */
    private void findNormalYaku() {
        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompList()) {
            //役をストックしておく
            List<MahjongYakuEnum> yakuStock = new ArrayList<>(7);
            Set<NormalYakuResolver> resolverSet
                = Mahjong4jYakuConfig.getNormalYakuResolverSet(comp);
            for (NormalYakuResolver resolver : resolverSet) {
                if (resolver.isMatch()) {
                    yakuStock.add(resolver.getNormalYaku());
                }
            }

            int hanSum = calcHanSum(yakuStock);
            if (hanSum > this.han) {
                han = hanSum;
                normalYakuList = yakuStock;
            }
        }
    }

    /**
     * 手牌が食い下がる形かも判定し、翻の合計を計算し、返します
     *
     * @param yakuStock 役のストック
     * @return 翻数の合計
     */
    private int calcHanSum(List<MahjongYakuEnum> yakuStock) {
        int hanSum = 0;
        if (hands.getIsKuisagari()) {
            for (MahjongYakuEnum yaku : yakuStock) {
                hanSum += yaku.getKuisagari();
            }
        } else {
            for (MahjongYakuEnum yaku : yakuStock) {
                hanSum += yaku.getHan();
            }
        }
        return hanSum;
    }
}
