package org.mahjong4j.yaku.normals;

import org.mahjong4j.yaku.yakuman.Yakuman;

/**
 * 役一覧列挙
 * 翻数,食い下がり翻数,日本語
 * 役満は別です
 * TODO:Add English
 *
 * @author yu1ro
 * @see Yakuman
 */
public enum NormalYaku {

    TANYAO(1, 1, "タンヤオ"),
    TSUMO(1, 0, "ツモ"),
    PINFU(1, 0, "平和"),
    IPEIKO(1, 0, "一盃口"),
    HAKU(1, 1, "白"),
    HATSU(1, 1, "發"),
    CHUN(1, 1, "中"),
    JIKAZE(1, 1, "自風牌"),
    BAKAZE(1, 1, "場風牌"),
    IPPATSU(1, 0, "一発"),
    HOUTEI(1, 1, "河底撈魚"),
    HAITEI(1, 1, "海底摸月"),
    REACH(1, 0, "リーチ"),
    DORA(1, 1, "ドラ"),
    URADORA(1, 1, "裏ドラ"),
    RINSHANKAIHOH(1, 1, "嶺上開花"),
    CHANKAN(1, 1, "槍槓"),
    DOUBLE_REACH(1, 0, "ダブルリーチ"),
    CHANTA(2, 1, "チャンタ"),
    HONROHTOH(2, 2, "混老頭"),
    SANSHOKUDOHJUN(2, 1, "三色同順"),
    IKKITSUKAN(2, 1, "一気通貫"),
    TOITOIHO(2, 2, "対々和"),
    SANSHOKUDOHKO(2, 2, "三色同刻"),
    SANANKO(2, 2, "三暗刻"),
    SANKANTSU(2, 2, "三槓子"),
    SHOSANGEN(2, 2, "小三元"),
    CHITOITSU(2, 0, "七対子"),
    RYANPEIKO(3, 0, "二盃口"),
    JUNCHAN(3, 2, "純チャン"),
    HONITSU(3, 2, "混一色"),
    CHINITSU(6, 5, "清一色"),;

    private final int han;
    private final int kuisagari;
    private final String japanese;

    NormalYaku(int han, int kuisagari, String japanese) {
        this.han = han;
        this.kuisagari = kuisagari;
        this.japanese = japanese;
    }

    public int getHan() {
        return han;
    }

    public int getKuisagari() {
        return kuisagari;
    }

    public String getJapanese() {
        return japanese;
    }
}
