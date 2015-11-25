package org.mahjong4j;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.*;
import org.mahjong4j.yaku.yakuman.*;

import java.util.ArrayList;

/**
 * TODO:役満をまとめて判定する
 * TODO:通常役をまとめて判定
 *
 * @author yu1ro
 *         和了判定に関するクラスです。
 *         役の判定は別のクラスで行うがここから呼び出します
 */
public class Mahjong {
    /*
     * 付いた役のリストを保持する用の変数。 役満の場合は普通の役は見ない
     */
    public ArrayList<MahjongYakuman> yakuman = new ArrayList<MahjongYakuman>(0);
    public ArrayList<MahjongYakuEnum> normalYaku = new ArrayList<MahjongYakuEnum>(0);

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

    private HonrohtohResolver honrohtoh;

    private KokushimusoResolver kokushimuso;

    private ChurenpohtohResolver churenpohtoh;

    private SanshokudohjunResolver dohjun;

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

    private ChinrohtohResolver chinrohtoh;

    private RyuisoResolver ryuiso;

    private SanankoResolver sananko;

    public Mahjong(int[] hands, MahjongTile jikaze, MahjongTile bakaze) {
        // 手牌を保持しておく。
        this.hands = hands;

        //現在の風牌も保持
        this.jikaze = jikaze;
        this.bakaze = bakaze;
    }

    public int[] getHands() {
        return hands;
    }

    public void setHands(int[] set) {
        this.hands = set;
    }

    public boolean calcYakuman() {
        // 役満の計算をします。

        // 九蓮宝燈
        churenpohtoh = new ChurenpohtohResolver(hands);
        if (churenpohtoh.isChuren()) {
            yakuman.add(MahjongYakuman.CHURENPOHTO);
            return true;
        }
        // ストックの初期化
        ArrayList<MahjongYakuman> yakumanStock = new ArrayList<MahjongYakuman>(0);
        MahjongTile[] kohtsu;
        MahjongTile janto;
        boolean isYakuman;

        for (Integer aCompleteNumber : completeNumber) {
            kohtsu = kotsuCandidate[aCompleteNumber];
            janto = jantoCandidate[aCompleteNumber];

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
            chinrohtoh = new ChinrohtohResolver();
            if (chinrohtoh.isChinroto(kohtsu, janto)) {
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
                for (MahjongYakuman aYakumanStock : yakumanStock) {
                    yakuman.add(aYakumanStock);
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
        ArrayList<MahjongYakuEnum> yakuStock = new ArrayList<MahjongYakuEnum>(
                candidateNumber);

        for (Integer aCompleteNumber : completeNumber) {
            // ストックの初期化
            hanStock = 0;
            shuntsu = shuntsuCandidate[aCompleteNumber];
            kohtsu = kotsuCandidate[aCompleteNumber];
            janto = jantoCandidate[aCompleteNumber];

            yakuStock.add(MahjongYakuEnum.TSUMO);
            hanStock += 1;

            // タンヤオ
            tanyao = new TanyaoResolver();
            if (tanyao.isTanyao(hands)) {
                yakuStock.add(MahjongYakuEnum.TANYAO);
                hanStock += tanyao.getHan();
            }

            // 平和
            pinfu = new PinfuResolver(jikaze, bakaze);
            if (pinfu.isPinfu(shuntsu, janto)) {
                yakuStock.add(MahjongYakuEnum.PINFU);
                hanStock += pinfu.getHan();
            }

            // 一盃口・二盃口
            ipeiko = new IpeikoResolver();
            ryanpeiko = new RyanpeikoResolver();
            if (ryanpeiko.isRyanpeiko(shuntsu, janto)) {
                yakuStock.add(MahjongYakuEnum.RYANPEIKO);
                hanStock += ryanpeiko.getHan();
            } else if (ipeiko.isIpeiko(shuntsu)) {
                yakuStock.add(MahjongYakuEnum.IPEIKO);
                hanStock += ipeiko.getHan();
            }

            // 各種役牌
            haku = new HakuResolver();
            hatsu = new HatsuResolver();
            chun = new ChunResolver();
            jikazeRsv = new JikazeResolver(jikaze);
            bakazeRsv = new BakazeResolver(bakaze);


            if (haku.isHaku(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.HAKU);
                hanStock += haku.getHan();
            }
            if (hatsu.isHatsu(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.HATSU);
                hanStock += hatsu.getHan();
            }
            if (chun.isChun(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.CHUN);
                hanStock += chun.getHan();
            }
            if (jikazeRsv.isJikaze(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.JIKAZE);
                hanStock += jikazeRsv.getHan();
            }
            if (bakazeRsv.isBakaze(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.BAKAZE);
                hanStock += bakazeRsv.getHan();
            }

            // チャンタ
            chanta = new ChantaResolver();
            junchan = new JunchanResolver();
            honrohtoh = new HonrohtohResolver();

            if (honrohtoh.isHonrohtoh(kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.HONROHTOH);
                hanStock += honrohtoh.getHan();
            } else if (junchan.isJunchan(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.JUNCHAN);
                hanStock += junchan.getHan();
            } else if (chanta.isChanta(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.CHANTA);
                hanStock += chanta.getHan();
            }
            //三暗刻
            sananko = new SanankoResolver();
            if (sananko.isSananko(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.SANANKO);
                hanStock += sananko.getHan();
            }

            // 三色
            dohjun = new SanshokudohjunResolver();
            doko = new SanshokudohkoResolver();

            if (dohjun.isSanshokudojun(shuntsu)) {
                yakuStock.add(MahjongYakuEnum.SANSHOKUDOHJUN);
                hanStock += dohjun.getHan();
            }
            if (doko.isSanshokudoko(kohtsu)) {
                yakuStock.add(MahjongYakuEnum.SANSHOKUDOHKO);
                hanStock += doko.getHan();
            }

            // 一気通貫
            ittsu = new IkkitsukanResolver();
            if (ittsu.isIttsu(shuntsu)) {
                yakuStock.add(MahjongYakuEnum.IKKITSUKAN);
                hanStock += ittsu.getHan();
            }

            // 小三元
            shosan = new ShosangenResolver();
            if (shosan.isShosangen(kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.SHOSANGEN);
                hanStock += shosan.getHan();
            }


            // 混一色
            honitsu = new HonitsuResolver();
            if (honitsu.isHonitsu(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.HONITSU);
                hanStock += honitsu.getHan();
            }

            // 清一色
            chinitsu = new ChinitsuResolver();
            if (chinitsu.isChinitsu(shuntsu, kohtsu, janto)) {
                yakuStock.add(MahjongYakuEnum.CHINITSU);
                hanStock += chinitsu.getHan();
            }

            // 最後にhanSumと比べて大きい方を入れておく
            if (hanStock > hanSum) {
                hanSum = hanStock;
                normalYaku.clear();
                for (MahjongYakuEnum aYakuStock : yakuStock) {
                    normalYaku.add(aYakuStock);
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
            for (MahjongYakuEnum aNormalYaku : normalYaku) {
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
        hanSum += MahjongYakuEnum.CHITOITSU.getHan();

        normalYaku.add(MahjongYakuEnum.TSUMO);
        hanSum += MahjongYakuEnum.TSUMO.getHan();


        tanyao = new TanyaoResolver();
        if (tanyao.isTanyao(hands)) {
            normalYaku.add(MahjongYakuEnum.TANYAO);
            hanSum += tanyao.getHan();
        }

        honrohtoh = new HonrohtohResolver();
        if (honrohtoh.isHonrohtoh(toitsu)) {
            normalYaku.add(MahjongYakuEnum.HONROHTOH);
            hanSum += honrohtoh.getHan();
        }

        honitsu = new HonitsuResolver();
        if (honitsu.isHonitsu(toitsu)) {
            normalYaku.add(MahjongYakuEnum.HONITSU);
            hanSum += honitsu.getHan();
        }

        chinitsu = new ChinitsuResolver();
        if (chinitsu.isChinitsu(toitsu)) {
            normalYaku.add(MahjongYakuEnum.CHINITSU);
            hanSum += chinitsu.getHan();
        }
    }

    public int howHan() {
        return hanSum;
    }
}
