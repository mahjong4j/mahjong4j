package org.mahjong4j;

import org.mahjong4j.hands.*;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.MahjongYakuEnum;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.MahjongYakumanEnum;
import org.mahjong4j.yaku.yakuman.YakumanResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mahjong4j.tile.MahjongTileType.SANGEN;
import static org.mahjong4j.yaku.normals.MahjongYakuEnum.*;
import static org.mahjong4j.yaku.yakuman.MahjongYakumanEnum.KOKUSHIMUSO;

/**
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出します
 *
 * @author yu1ro
 */
public class MahjongPlayer {

    //付いた役満リスト
    private List<MahjongYakumanEnum> yakumanList = new ArrayList<>(1);

    //付いた通常役リスト
    private List<MahjongYakuEnum> normalYakuList = new ArrayList<>(0);

    //その時の面子の組
    private MentsuComp comp;

    //翻
    private int han = 0;
    //符
    private int fu = 20;

    private MahjongHands hands;
    private GeneralSituation generalSituation;
    private PersonalSituation personalSituation;


    public MahjongPlayer(MahjongHands hands) {
        this.hands = hands;
    }

    public MahjongPlayer(MahjongHands hands, GeneralSituation generalSituation, PersonalSituation personalSituation) {
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
            yakumanList.add(KOKUSHIMUSO);
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
        for (MentsuComp comp : hands.getMentsuCompSet()) {
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
                this.comp = comp;
            }
        }

        return yakumanList.size() > 0;
    }

    /**
     *
     */
    private void findNormalYaku() {
        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompSet()) {
            //役をストックしておく
            List<MahjongYakuEnum> yakuStock = new ArrayList<>(7);
            Set<NormalYakuResolver> resolverSet
                = Mahjong4jYakuConfig.getNormalYakuResolverSet(comp, generalSituation, personalSituation);
            for (NormalYakuResolver resolver : resolverSet) {
                if (resolver.isMatch()) {
                    yakuStock.add(resolver.getNormalYaku());
                }
            }

            int hanSum = calcHanSum(yakuStock);
            if (hanSum > this.han) {
                han = hanSum;
                normalYakuList = yakuStock;
                this.comp = comp;
            }
        }

        if (han > 0) {
            calcDora(hands.getHandsComp(), generalSituation, normalYakuList.contains(REACHE));
        }
        calcScore();
    }

    private void calcScore() {
        fu = calcFu();

        // TODO: ここで点数計算
    }

    /**
     * 符計算をします
     * 役満以外は計算しちゃいます
     */
    private int calcFu() {
        if (personalSituation == null || generalSituation == null || comp == null) {
            return 0;
        }
        int tmpFu = 20;
        // 特例の平和ツモと七対子の符
        if (normalYakuList.contains(PINFU) && normalYakuList.contains(TSUMO)) {
            return 20;
        }
        if (normalYakuList.contains(CHITOITSU)) {
            return 25;
        }

        // 門前ロンなら+10
        if (!hands.isOpen() && !personalSituation.isTsumo()) {
            tmpFu += 10;
        } else if (personalSituation.isTsumo()) { // ツモアガリなら+2
            tmpFu += 2;
        }

        // 各メンツの種類による加符
        for (MahjongMentsu mentsu : comp.getAllMentsu()) {
            if (mentsu instanceof Shuntsu) continue;
            if (mentsu instanceof Toitsu) continue;

            int mentsuFu = 2;
            if (!mentsu.isOpen()) {
                mentsuFu *= 2;
            }
            if (mentsu.getTile().isYaochu()) {
                mentsuFu *= 2;
            }
            if (mentsu instanceof Kantsu) {
                mentsuFu *= 4;
            }
            tmpFu += mentsuFu;
        }

        // 待ちの種類による可符
        tmpFu += calcFuByWait(comp, hands.getLast());

        // 雀頭の種類による加符
        // 連風牌の場合は+4とします
        MahjongTile jantoTile = comp.getJanto().getTile();
        if (jantoTile == generalSituation.getBakaze()) {
            tmpFu += 2;
        }
        if (jantoTile == personalSituation.getJikaze()) {
            tmpFu += 2;
        }
        if (jantoTile.getType() == SANGEN) {
            tmpFu += 2;
        }

        return tmpFu;
    }

    private int calcFuByWait(MentsuComp comp, MahjongTile last) {
        if (comp.isRyanmen(last)) {
            if (comp.isNobetan(last)) {
                return 2;
            }
        } else {
            if (comp.isKanchan(last) || comp.isPenchan(last) || comp.isTanki(last)) {
                return 2;
            }
        }

        return 0;
    }

    private void calcDora(int[] handsComp, GeneralSituation generalSituation, boolean isReach) {
        if (generalSituation == null) {
            return;
        }
        int dora = 0;
        for (MahjongTile tile : generalSituation.getDora()) {
            dora += handsComp[tile.getCode()];
        }
        for (int i = 0; i < dora; i++) {
            normalYakuList.add(DORA);
        }

        if (isReach) {
            int uradora = 0;
            for (MahjongTile tile : generalSituation.getUradora()) {
                uradora += handsComp[tile.getCode()];
            }
            for (int i = 0; i < uradora; i++) {
                normalYakuList.add(URADORA);
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
        if (hands.isOpen()) {
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
