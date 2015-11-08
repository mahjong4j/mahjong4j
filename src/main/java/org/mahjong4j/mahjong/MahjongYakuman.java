package org.mahjong4j.mahjong;

public enum MahjongYakuman {
    /*
     * 役満一覧です
     * 役一覧と別にすることで
     * 四暗刻ドラ13とかでダブル役満にならないようにします
     */
    KOKUSHIMUSO("国士無双"),
    SUANKO("四暗刻"),
    CHURENPOTO("九蓮宝燈"),
    DAISANGEN("大三元"),
    TSUISO("字一色"),
    SHOSUSHI("小四喜"),
    DAISUSHI("大四喜"),
    RYUISO("緑一色"),
    CHINROTO("清老頭");

    private String kanji;

    private MahjongYakuman(String kanji) {
        this.kanji = kanji;
    }

    public String getKanji() {
        return kanji;
    }
}
