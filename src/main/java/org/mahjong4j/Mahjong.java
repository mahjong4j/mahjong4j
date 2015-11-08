package org.mahjong4j;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.*;
import org.mahjong4j.yaku.yakuman.*;

import java.util.ArrayList;

/*
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出す予定です。
 */
public class Mahjong {
    /*
     * 付いた役のリストを保持する用の変数。 役満の場合は普通の役は見ない
     */
    public ArrayList<MahjongYakuman> yakuman = new ArrayList<MahjongYakuman>(0);
    public ArrayList<MahjongYakuList> normalYaku = new ArrayList<MahjongYakuList>(0);

    public int points = 0;

    public String manName;//倍満 とか 跳満とかを入れる

    // 手牌を保持する。極力いじらない
    private int[] hands = new int[34];

    private int[] hstock = new int[34];// 操作する用のストック
    // 雀頭の候補をストック
    // 二個以上で候補になるのでとりあえず記憶しておく意味でstock
    private MahjongTile[] jantoStock = new MahjongTile[14];

    /*
     * 雀頭・刻子・順子の候補を持っておく 一つ目のインデックスは何個目の組か ２つ目のインデックスは何個目の刻子・順子か
     */
    private int candidateNumber = 0;

    private ArrayList<Integer> completeNumber = new ArrayList<Integer>(1);

    private MahjongTile[] jantoCandidate = new MahjongTile[14];

    private MahjongTile[][] kotsuCandidate = new MahjongTile[14][4];

    private MahjongTile[][] shuntsuCandidate = new MahjongTile[14][4];

    private int kotsuCount = 0;
    private int shuntsuCount = 0;

    /*
     * 七対子の対子7組を持っておく
     */
    private MahjongTile[] toitsu = new MahjongTile[7];


    // 役の判定用の変数
    private int hanSum = 0;
    private MahjongTile jikaze;
    private MahjongTile bakaze;

    private TanyaoResolver tanyao;
    private PinfuResolver pinfu;
    private IpeikoResolver ipeiko;

    private HakuResolver haku;

    private HatsuResolver hatsu;

    private ChunResolver chun;

    private JikazeResolver jikazeRsv;

    private BakazeResolver bakazeRsv;

    private ChantaResolver chanta;

    private JunchanResolver junchan;

    private HonrohtohResolver honroh;

    private KokushiResolver kokushi;

    private ChurenpohtohResolver churen;

    private SanshokudojunResolver dohjun;

    private SanshokudohkoResolver doko;

    private IkkitsukanResolver ittsu;

    private ShosangenResolver shosan;

    private HonitsuResolver honitsu;

    private ChinitsuResolver chinitsu;

    private SuankoResolver suanko;

    private RyanpeikoResolver ryanpeiko;

    private DaisangenResolver daisangen;

    private TsuisoResolver tsuiso;

    private ShosushiResolver shosushi;

    private DaisushiResolver daisushi;

    private ChinrotoResolver chintroto;

    private RyuisoResolver ryuiso;

    private SanankoResolver sananko;

    public Mahjong(int[] hands, MahjongTile jikaze, MahjongTile bakaze) {
        // 手牌を保持しておく。
        this.hands = hands;

        //現在の風牌も保持
        this.jikaze = jikaze;
        this.bakaze = bakaze;
    }

    public static void main(String[] args) {
        int[] test = {
                0, 2, 2, 2, 2, 2, 2, 2, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0};

        Mahjong it = new Mahjong(test, MahjongTile.NAN, MahjongTile.TON);
        if (it.canWin()) {
            if (it.normalYaku.size() != 0) {
                if (it.normalYaku.get(0) == MahjongYakuList.CHIITOITSU) {

                    if (it.calcChitoiYakuman()) {
                        System.out.println(it.yakuman.get(0));
                        if (it.manName != null) {
                            System.out.println(it.manName);
                        }
                    } else {

                        it.calcChitoiYakuman();
                        for (int i = 0; i < it.normalYaku.size(); i++) {
                            System.out.println(it.normalYaku.get(i).toString());
                        }
                        if (it.manName != null) {
                            System.out.println(it.manName);
                        }

                    }
                }
            } else if (it.calcYakuman()) {// 七対子じゃないなら普通の役満判定
                for (int i = 0; i < it.yakuman.size(); i++) {
                    System.out.println(it.yakuman.get(i).toString());

                }
                if (it.manName != null) {
                    System.out.println(it.manName);
                }

            } else {
                // 役満じゃないなら通常の役判定
                it.calcHan();
                for (int i = 0; i < it.normalYaku.size(); i++) {
                    System.out.println(it.normalYaku.get(i).getKanji());

                }
                if (it.manName != null) {
                    System.out.println(it.manName);
                }
            }
        } else {
            System.out.println("上がれません");
        }

    }

    protected void init() {
        // ストックを手牌の元の状態に初期化
        for (int i = 0; i < hands.length; i++) {
            hstock[i] = hands[i];
        }

        // kotsuCount・chuntsuCountを初期化
        kotsuCount = 0;
        shuntsuCount = 0;
    }

    public int[] getHands() {
        // 手牌をゲットする
        return hands;
    }

    public void setHands(int[] set) {
        // 改めて手牌をセットする
        this.hands = set;
    }

    public void initStock() {
        for (int i = 0; i < hands.length; i++) {
            hstock[i] = hands[i];
        }
    }

    public boolean canWin() {
        // 同じ牌が5個以上有ったらfalse
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] > 4) {
                return false;
            }
        }

        /*
         * 和了の手牌かどうか確認する 和了であればツモが付く
         */

        // 国士無双の判定
        kokushi = new KokushiResolver(hands);
        if (kokushi.isKokushi()) {
            // 役満で上がった事を保持
            yakuman.add(MahjongYakuman.KOKUSHIMUSO);

            // 和了れるのでtrueを返す
            return true;
        }

        // 七対子の判定
        ChitoitsuResolver chitoi = new ChitoitsuResolver(hands);
        if (chitoi.isChitoi()) {
            // 和了れるので、ツモが付く
            //normalYaku.add(MahjongYakuList.TSUMO);

            toitsu = chitoi.getToitsu();
            // 二盃口かどうか
            ryanpeiko = new RyanpeikoResolver();
            if (ryanpeiko.isRyanpeiko(toitsu)) {
                // 順子・雀頭を入れておく
                jantoCandidate[0] = ryanpeiko.getJantoRevised();
                shuntsuCandidate[0] = ryanpeiko.getShuntsuRevised();
                completeNumber.add(0);

                /*
                 * 二盃口で上がった事は保持しないでおく 通常型で判定するため
                 */
            } else {
                // 違ったら七対子で上がったことを保持しておく
                normalYaku.add(MahjongYakuList.CHIITOITSU);
            }

            // 和了れるのでtrueを返す。
            return true;
        }

        // その他の判定
        boolean find = false;
        initStock();

        // 雀頭の候補を探してストックしておく
        int jantoNum = 0;
        for (int i = 0; i < hands.length; i++) {
            if (hands[i] >= 2) {
                jantoStock[jantoNum++] = MahjongTile.getTile(i);
            }
        }

        // 雀頭が一つも見つからなければfalse
        if (jantoNum == 0) {
            return false;
        }

        int code;
        for (int i = 0; i < jantoNum; i++) {
            // 操作変数を初期化
            init();

            code = jantoStock[i].getCode();
            hstock[code] -= 2;
            jantoCandidate[candidateNumber] = jantoStock[i];

            // 対子の候補を元に刻子優先で探す
            // まずは刻子を探す
            for (int j = 0; j < hands.length; j++) {
                if (hstock[j] >= 3) {
                    kotsuCandidate[candidateNumber][kotsuCount++] = MahjongTile
                            .getTile(j);
                    hstock[j] -= 3;
                }
            }

            // 次に順子を探す
            for (int j = 1; j < hands.length - 1; j++) {
                while (hstock[j - 1] > 0 && hstock[j] > 0 && hstock[j + 1] > 0) {// whileにしたのは一盃口などがあるから
                    if (MentsuResolver.isShuntsu(MahjongTile.getTile(j - 1),
                            MahjongTile.getTile(j), MahjongTile.getTile(j + 1))) {
                        shuntsuCandidate[candidateNumber][shuntsuCount++] = MahjongTile
                                .getTile(j);
                        hstock[j - 1]--;
                        hstock[j]--;
                        hstock[j + 1]--;
                    } else {
                        // コードが並んでいるけど、順子じゃない時はwhileを抜ける
                        // 字牌とM9,P1,P2などを想定
                        break;
                    }
                }
            }
            // stockがすべてゼロなら和了の型なのでfind=true
            int k;
            for (k = 0; k < hstock.length; k++) {
                if (hstock[k] != 0) {
                    break;
                }
            }
            if (k == hstock.length) {
                // candidateNumberを保存
                completeNumber.add(candidateNumber);
                find = true;
            }

            candidateNumber++;

            // 対子の候補を元に順子優先で探す
            init();// 一旦初期化
            hstock[jantoStock[i].getCode()] -= 2;
            jantoCandidate[candidateNumber] = jantoStock[i];

            // 順子から調べる
            for (int j = 1; j < hands.length - 1; j++) {
                while (hstock[j - 1] > 0 && hstock[j] > 0 && hstock[j + 1] > 0) {// whileにしたのは一盃口などがあるから
                    if (MentsuResolver.isShuntsu(MahjongTile.getTile(j - 1),
                            MahjongTile.getTile(j), MahjongTile.getTile(j + 1))) {
                        shuntsuCandidate[candidateNumber][shuntsuCount++] = MahjongTile
                                .getTile(j);
                        hstock[j - 1]--;
                        hstock[j]--;
                        hstock[j + 1]--;
                    } else {
                        // コードが並んでいるけど、順子じゃない時はwhileを抜ける
                        // 字牌とM9,P1,P2などを想定
                        break;
                    }
                }
            }

            // 次に刻子を調べる
            for (int j = 0; j < hands.length; j++) {
                if (hstock[j] >= 3) {
                    kotsuCandidate[candidateNumber][kotsuCount++] = MahjongTile
                            .getTile(j);
                    hstock[j] -= 3;
                }
            }
            // stockがすべてゼロなら和了の型なのでfind=true
            int n;
            for (n = 0; n < hstock.length; n++) {
                if (hstock[n] != 0) {
                    break;
                }
            }
            if (n == hstock.length) {
                // candidateNumberを保存
                completeNumber.add(candidateNumber);
                find = true;
            }

            candidateNumber++;
        }

        return find;

    }

    public boolean calcYakuman() {
        // 役満の計算をします。

        // KOKUSHIMUSO,
        /*
         * if (kokushi.isKokushi()) { // ここにシングル役満になるようにする処理
         * 国士無双はここで計算する必要がないことが発覚 return true; }
         */

        // CHURENPOTO,
        churen = new ChurenpohtohResolver(hands);
        if (churen.isChuren()) {
            yakuman.add(MahjongYakuman.CHURENPOTO);
            return true;
        }
        // ストックの初期化
        ArrayList<MahjongYakuman> yakumanStock = new ArrayList<MahjongYakuman>(0);
        MahjongTile[] kohtsu;
        MahjongTile janto;
        boolean isYakuman = false;

        for (int i = 0; i < completeNumber.size(); i++) {
            kohtsu = kotsuCandidate[completeNumber.get(i)];
            janto = jantoCandidate[completeNumber.get(i)];

            // SUANKO,
            suanko = new SuankoResolver();
            if (suanko.isSuanko(kohtsu)) {
                yakumanStock.add(MahjongYakuman.SUANKO);
            }

            // DAISANGEN,
            daisangen = new DaisangenResolver();
            if (daisangen.isDaisangen(kohtsu)) {
                yakumanStock.add(MahjongYakuman.DAISANGEN);
            }

            // TSUISO,
            tsuiso = new TsuisoResolver();
            if (tsuiso.isTsuiso(kohtsu, janto)) {
                yakumanStock.add(MahjongYakuman.TSUISO);
            }

            // SHOSUSHI,
            shosushi = new ShosushiResolver();
            if (shosushi.isShosushi(kohtsu, janto)) {
                yakumanStock.add(MahjongYakuman.SHOSUSHI);
            }

            // DAISUSHI,
            daisushi = new DaisushiResolver();
            if (daisushi.isDaisushi(kohtsu)) {
                yakumanStock.add(MahjongYakuman.DAISUSHI);
            }

            // CHINROTO
            chintroto = new ChinrotoResolver();
            if (chintroto.isChinroto(kohtsu, janto)) {
                yakumanStock.add(MahjongYakuman.CHINROTO);
            }

            // RYUISO,
            ryuiso = new RyuisoResolver();
            if (ryuiso.isRyuiso(hands)) {
                yakumanStock.add(MahjongYakuman.RYUISO);
            }

            //役満と役満ストックを比べて大きい方を保持する
            if (yakuman.size() < yakumanStock.size()) {
                yakuman.clear();
                for (int k = 0; k < yakumanStock.size(); k++) {
                    yakuman.add(yakumanStock.get(k));
                }
            }
            yakumanStock.clear();
        }
        isYakuman = yakuman.size() > 0;

        return isYakuman;
    }

    public void calcHan() {

        // 和了型の数だけ繰り返す
        int hanStock;
        MahjongTile[] shuntsu, kohtsu;
        MahjongTile janto;
        /*
         *一旦これに入れて、 正式版のnormalYakuとくらべて 役が多い方をnomalYakuに保持する
         */
        ArrayList<MahjongYakuList> yakuStock = new ArrayList<MahjongYakuList>(
                candidateNumber);

        for (int i = 0; i < completeNumber.size(); i++) {
            // ストックの初期化
            hanStock = 0;
            shuntsu = shuntsuCandidate[completeNumber.get(i)];
            kohtsu = kotsuCandidate[completeNumber.get(i)];
            janto = jantoCandidate[completeNumber.get(i)];

            yakuStock.add(MahjongYakuList.TSUMO);
            hanStock += 1;

            // タンヤオ
            tanyao = new TanyaoResolver();
            if (tanyao.isTanyao(hands)) {
                yakuStock.add(MahjongYakuList.TANYAO);
                hanStock += tanyao.howHan();
            }

            // 平和
            pinfu = new PinfuResolver(jikaze, bakaze);
            if (pinfu.isPinfu(shuntsu, janto)) {
                yakuStock.add(MahjongYakuList.PINFU);
                hanStock += pinfu.howHan();
            }

            // 一盃口・二盃口
            ipeiko = new IpeikoResolver();
            ryanpeiko = new RyanpeikoResolver();
            if (ryanpeiko.isRyanpeiko(shuntsu, janto)) {
                yakuStock.add(MahjongYakuList.RYANPEIKO);
                hanStock += ryanpeiko.howHan();
            } else if (ipeiko.isIpeiko(shuntsu)) {
                yakuStock.add(MahjongYakuList.IPEIKO);
                hanStock += ipeiko.howHan();
            }

            // 各種役牌
            haku = new HakuResolver();
            hatsu = new HatsuResolver();
            chun = new ChunResolver();
            jikazeRsv = new JikazeResolver(jikaze);
            bakazeRsv = new BakazeResolver(bakaze);


            if (haku.isHaku(kohtsu)) {
                yakuStock.add(MahjongYakuList.HAKU);
                hanStock += haku.howHan();
            }
            if (hatsu.isHatsu(kohtsu)) {
                yakuStock.add(MahjongYakuList.HATSU);
                hanStock += hatsu.howHan();
            }
            if (chun.isChun(kohtsu)) {
                yakuStock.add(MahjongYakuList.CHUN);
                hanStock += chun.howHan();
            }
            if (jikazeRsv.isJikaze(kohtsu)) {
                yakuStock.add(MahjongYakuList.JIKAZE);
                hanStock += jikazeRsv.howHan();
            }
            if (bakazeRsv.isBakaze(kohtsu)) {
                yakuStock.add(MahjongYakuList.BAKAZE);
                hanStock += bakazeRsv.howHan();
            }

            // チャンタ
            chanta = new ChantaResolver();
            junchan = new JunchanResolver();
            honroh = new HonrohtohResolver();

            if (honroh.isHonrohtoh(kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.HONROHTOH);
                hanStock += honroh.howHan();
            } else if (junchan.isJunchan(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.JUNCHAN);
                hanStock += junchan.howHan();
            } else if (chanta.isChanta(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.CHANTA);
                hanStock += chanta.howHan();
            }
            //三暗刻
            sananko = new SanankoResolver();
            if (sananko.isSananko(kohtsu)) {
                yakuStock.add(MahjongYakuList.SANANKO);
                hanStock += sananko.howHan();
            }

            // 三色
            dohjun = new SanshokudojunResolver();
            doko = new SanshokudohkoResolver();

            if (dohjun.isSanshokudojun(shuntsu)) {
                yakuStock.add(MahjongYakuList.SANSHOKUDOUJUN);
                hanStock += dohjun.howHan();
            }
            if (doko.isSanshokudoko(kohtsu)) {
                yakuStock.add(MahjongYakuList.SANSHOKUDOKO);
                hanStock += doko.howHan();
            }

            // 一気通貫
            ittsu = new IkkitsukanResolver();
            if (ittsu.isIttsu(shuntsu)) {
                yakuStock.add(MahjongYakuList.ITTSU);
                hanStock += ittsu.howHan();
            }

            // 小三元
            shosan = new ShosangenResolver();
            if (shosan.isShosangen(kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.SHOSANGEN);
                hanStock += shosan.howHan();
            }


            // 混一色
            honitsu = new HonitsuResolver();
            if (honitsu.isHonitsu(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.HONITSU);
                hanStock += honitsu.howHan();
            }

            // 清一色
            chinitsu = new ChinitsuResolver();
            if (chinitsu.isChinitsu(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuList.CHINITSU);
                hanStock += chinitsu.howHan();
            }

            // 最後にhanSumと比べて大きい方を入れておく
            if (hanStock > hanSum) {
                hanSum = hanStock;
                normalYaku.clear();
                for (int n = 0; n < yakuStock.size(); n++) {
                    normalYaku.add(yakuStock.get(n));
                }
            }

            // 役のストックも初期化
            yakuStock.clear();

        }
    }

    public int calcPoint() {

        if (yakuman.size() != 0) {
            switch (yakuman.size()) {
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
            return yakuman.size() * 32000;
        } else if (normalYaku.size() != 0) {
            int han = 0;
            for (int i = 0; i < normalYaku.size(); i++) {
                han += normalYaku.get(i).getHan();
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

    public boolean calcChitoiYakuman() {

        boolean isYakuman = false;

        tsuiso = new TsuisoResolver();
        if (tsuiso.isTsuiso(toitsu)) {
            yakuman.add(MahjongYakuman.TSUISO);
            isYakuman = true;
        }

        return isYakuman;

    }

    public void calcChitoiYaku() {
        //七対子の分と門前清自摸和の分を付けておく
        hanSum += MahjongYakuList.CHIITOITSU.getHan();

        normalYaku.add(MahjongYakuList.TSUMO);
        hanSum += MahjongYakuList.TSUMO.getHan();


        tanyao = new TanyaoResolver();
        if (tanyao.isTanyao(hands)) {
            normalYaku.add(MahjongYakuList.TANYAO);
            hanSum += tanyao.howHan();
        }

        honroh = new HonrohtohResolver();
        if (honroh.isHonrohtoh(toitsu)) {
            normalYaku.add(MahjongYakuList.HONROHTOH);
            hanSum += honroh.howHan();
        }

        honitsu = new HonitsuResolver();
        if (honitsu.isHonitsu(toitsu)) {
            normalYaku.add(MahjongYakuList.HONITSU);
            hanSum += honitsu.howHan();
        }

        chinitsu = new ChinitsuResolver();
        if (chinitsu.isChinitsu(toitsu)) {
            normalYaku.add(MahjongYakuList.CHINITSU);
            hanSum += chinitsu.howHan();
        }
    }

    public int howHan() {
        return hanSum;
    }
}
