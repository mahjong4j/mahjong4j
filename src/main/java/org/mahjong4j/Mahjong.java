package org.mahjong4j;

import org.mahjong4j.hands.MahjongHands;
import org.mahjong4j.hands.MentsuComp;
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

    //翻
    private int han;

    private MahjongHands hands;

    public Mahjong(MahjongHands hands) {
        this.hands = hands;
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
     *
     * @return 役満が見つかったか
     */
    private boolean findYakuman() {
        //役満をストックしておき、見つかったら先にこちらに保存する
        List<MahjongYakumanEnum> yakumanStock = new ArrayList<>(4);

        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompList()) {
            List<YakumanResolver> yakumanResolverList
                = Mahjong4jYakuConfig.getYakumanResolverList(comp);

            for (YakumanResolver resolver : yakumanResolverList) {
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

    public List<MahjongYakumanEnum> getYakumanList() {
        return yakumanList;
    }

    public List<MahjongYakuEnum> getNormalYakuList() {
        return normalYakuList;
    }


    public void findNormalYaku() {
        //役をストックしておく
        List<MahjongYakuEnum> yakuStock = new ArrayList<>(7);
        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompList()) {
            List<NormalYakuResolver> resolverList
                = Mahjong4jYakuConfig.getNormalYakuResolverList(comp);
            for (NormalYakuResolver resolver : resolverList) {
                if (resolver.isMatch()) {
                    yakuStock.add(resolver.getNormalYaku());
                }
            }
            int hanSum = 0;
            for (MahjongYakuEnum yaku : yakuStock) {
                hanSum += yaku.getHan();
            }
            if (hanSum > this.han) {
                han = hanSum;
                normalYakuList = yakuStock;
            }
        }
    }
}
