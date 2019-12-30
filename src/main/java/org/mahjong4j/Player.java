package org.mahjong4j;

import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Mentsu;
import org.mahjong4j.hands.MentsuComp;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.normals.NormalYakuResolver;
import org.mahjong4j.yaku.yakuman.Yakuman;
import org.mahjong4j.yaku.yakuman.YakumanResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.tile.TileType.SANGEN;
import static org.mahjong4j.yaku.normals.NormalYaku.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.KOKUSHIMUSO;

/**
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出します
 *
 * @author yu1ro
 */
public class Player {

    //付いた役満リスト
    private List<Yakuman> yakumanList = new ArrayList<>(1);

    //付いた通常役リスト
    private List<NormalYaku> normalYakuList = new ArrayList<>(0);

    //その時の面子の組
    private MentsuComp comp;

    // 翻
    private int han = 0;
    // 符
    private int fu = 0;
    // 点数
    private Score score = SCORE0;

    private Hands hands;
    private GeneralSituation generalSituation;
    private PersonalSituation personalSituation;


    public Player(Hands hands) {
        this.hands = hands;
    }

    public Player(Hands hands, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.hands = hands;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }


    public List<Yakuman> getYakumanList() {
        return yakumanList;
    }

    public List<NormalYaku> getNormalYakuList() {
        return normalYakuList;
    }

    public int getFu() {
        return fu;
    }

    public Score getScore() {
        return score;
    }

    public int getHan() {
        return han;
    }

    public void calculate() {
        //和了れない場合は即座に終了
        if (!hands.getCanWin()) return;

        

        //役満を探し見つかれば通常役は調べずに終了
        if (findYakuman()) {
            if (personalSituation == null) {
                score = SCORE0;
                return;
            }
            score = Score.calculateYakumanScore(personalSituation.isParent(), yakumanList.size());
            return;
        }

        findNormalYaku();
    }

    /**
     * @return 役満が見つかったか
     */
    private boolean findYakuman() {
        
        //国士無双の場合はそれ以外ありえないので保存して即座に終了
        if (hands.getIsKokushimuso()) {
            yakumanList.add(KOKUSHIMUSO);
            return true;
        }


        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompSet()) {
            //役満をストックしておき、見つかったら先にこちらに保存する
            List<Yakuman> yakumanStock = new ArrayList<>(4);

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

    private void findNormalYaku() {
        //それぞれの面子の完成形で判定する
        for (MentsuComp comp : hands.getMentsuCompSet()) {
            //役をストックしておく
            List<NormalYaku> yakuStock = new ArrayList<>(7);
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
            calcDora(hands.getHandsComp(), generalSituation, normalYakuList.contains(REACH));
        }
        calcScore();
    }

    private void calcScore() {
        fu = calcFu();
        if (personalSituation == null) {
            return;
        }
        score = Score.calculateScore(personalSituation.isParent(), han, fu);
    }

    /**
     * 符計算をします
     * 役なしの場合は0
     * Situationが無い場合は一律で20
     */
    private int calcFu() {
        if (normalYakuList.size() == 0) {
            return 0;
        }
        if (personalSituation == null || generalSituation == null) {
            return 20;
        }
        // 特例の平和ツモと七対子の符
        if (normalYakuList.contains(PINFU) && normalYakuList.contains(TSUMO)) {
            return 20;
        }
        if (normalYakuList.contains(CHITOITSU)) {
            return 25;
        }

        int tmpFu = 20;
        // 門前ロンなら+10
        tmpFu += calcFuByAgari();

        // 各メンツの種類による加符
        for (Mentsu mentsu : comp.getAllMentsu()) {
            tmpFu += mentsu.getFu();
        }

        tmpFu += calcFuByWait(comp, hands.getLast());
        tmpFu += calcFuByJanto();

        return tmpFu;
    }

    /**
     * 雀頭の種類による加符
     * 連風牌の場合は+4とします
     *
     * @return
     */
    private int calcFuByJanto() {
        Tile jantoTile = comp.getJanto().getTile();
        int tmp = 0;
        if (jantoTile == generalSituation.getBakaze()) {
            tmp += 2;
        }
        if (jantoTile == personalSituation.getJikaze()) {
            tmp += 2;
        }
        if (jantoTile.getType() == SANGEN) {
            tmp += 2;
        }
        return tmp;
    }

    private int calcFuByAgari() {
        // ツモ
        if (personalSituation.isTsumo()) {
            return 2;
        }
        // 門前ロン
        if (!hands.isOpen()) {
            return 10;
        }
        return 0;
    }

    /**
     * 待ちの種類による可符
     *
     * @param comp
     * @param last
     * @return
     */
    private int calcFuByWait(MentsuComp comp, Tile last) {
        if (comp.isKanchan(last) || comp.isPenchan(last) || comp.isTanki(last)) {
            return 2;
        }

        return 0;
    }

    private void calcDora(int[] handsComp, GeneralSituation generalSituation, boolean isReach) {
        if (generalSituation == null) {
            return;
        }
        int dora = 0;
        for (Tile tile : generalSituation.getDora()) {
            dora += handsComp[tile.getCode()];
        }
        for (int i = 0; i < dora; i++) {
            normalYakuList.add(DORA);
            han += DORA.getHan();
        }

        if (isReach) {
            int uradora = 0;
            for (Tile tile : generalSituation.getUradora()) {
                uradora += handsComp[tile.getCode()];
            }
            for (int i = 0; i < uradora; i++) {
                normalYakuList.add(URADORA);
                han += URADORA.getHan();
            }
        }
    }

    /**
     * 手牌が食い下がる形かも判定し、翻の合計を計算し、返します
     *
     * @param yakuStock 役のストック
     * @return 翻数の合計
     */
    private int calcHanSum(List<NormalYaku> yakuStock) {
        int hanSum = 0;
        if (hands.isOpen()) {
            for (NormalYaku yaku : yakuStock) {
                hanSum += yaku.getKuisagari();
            }
        } else {
            for (NormalYaku yaku : yakuStock) {
                hanSum += yaku.getHan();
            }
        }
        return hanSum;
    }
}
