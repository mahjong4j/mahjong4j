package org.mahjong4j.yaku.normals;

public enum MahjongYakuList {
    /*
     * 役の一覧です
     * 役満は別です
     */
    TANYAO(1, 1, "タンヤオ"),
    TSUMO(1, 0, "ツモ"),
    PINFU(1, 0, "平和"),
    IPEIKO(1, 0, "一盃口"),
    HAKU(1, 1, "白"),
    HATSU(1, 1, "發"),
    CHUN(1, 1, "中"),
    JIKAZE(1, 1, "自風牌"),
    BAKAZE(1, 1, "場風牌"),
    CHANTA(2, 1, "チャンタ"),
    HONROHTOH(2, 2, "混老頭"),
    SANSHOKUDOUJUN(2, 1, "三色同順"),
    ITTSU(2, 1, "一気通貫"),
    TOITOI(2, 2, "対々和"),
    SANSHOKUDOKO(2, 2, "三色同刻"),
    SANANKO(2, 2, "三暗刻"),
    SHOSANGEN(2, 2, "小三元"),
    CHIITOITSU(2, 0, "七対子"),
    RYANPEIKO(3, 0, "二盃口"),
    JUNCHAN(3, 2, "純チャン"),
    HONITSU(3, 2, "混一色"),
    CHINITSU(6, 5, "清一色"),;

    private int han;
    private int kuisagari;
    private String kanji;

    private MahjongYakuList(int han, int kuisagari, String kanji) {
        this.han = han;
        this.kuisagari = kuisagari;
        this.kanji = kanji;
    }

    public int getHan() {
        return han;
    }

    public int getKuisagari() {
        return kuisagari;
    }

    public String getKanji() {
        return kanji;

    }
}
