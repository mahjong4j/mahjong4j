package org.mahjong4j.yaku.yakuman;

/**
 * 役満の列挙
 * 役一覧と別にすることで
 * 四暗刻ドラ13などでダブル役満にならないようにします
 * TODO:English
 */
public enum MahjongYakumanEnum {
    KOKUSHIMUSO("国士無双"),
    SUANKO("四暗刻"),
    CHURENPOHTO("九蓮宝燈"),
    DAISANGEN("大三元"),
    TSUISO("字一色"),
    SHOSUSHI("小四喜"),
    DAISUSHI("大四喜"),
    RYUISO("緑一色"),
    CHINROTO("清老頭"),
    SUKANTSU("四槓子");

    private String japanese;

    MahjongYakumanEnum(String japanese) {
        this.japanese = japanese;
    }

    public String getJapanese() {
        return japanese;
    }
}
