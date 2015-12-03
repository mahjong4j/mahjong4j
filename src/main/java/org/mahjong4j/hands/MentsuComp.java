package org.mahjong4j.hands;

import java.util.ArrayList;
import java.util.List;

/**
 * 上がれる面子を整理したクラスです
 * TODO:面子合計が5より大きくなったらthrow
 * TODO:同じ面子の場合に判定しなくていいようにequalsを実装したい
 *
 * @author yu1ro
 */
public class MentsuComp {

    private Janto janto;
    private List<Shuntsu> shuntsuList = new ArrayList<>(4);
    private List<Kotsu> kotsuList = new ArrayList<>(4);
    private List<Kantsu> kantsuList = new ArrayList<>(4);

    /**
     * いらないかもしれない
     * 雀頭だけ先に入れておいて、
     * あとから他の面子を入れていくスタイル用を想定
     *
     * @param janto
     */
    public MentsuComp(Janto janto) {
        this.janto = janto;
    }

    /**
     * TODO:引数のリストのサイズが5以外はthrow
     *
     * @param mentsuList 上がりとなった面子のリスト
     */
    public MentsuComp(List<MahjongMentsu> mentsuList) {
        for (MahjongMentsu mentsu : mentsuList) {
            setMentsu(mentsu);
        }
    }

    /**
     * どの面子が入っても対応可能なセッター
     * ループで入れるのを想定
     * 比較処理を挟むので、できれば避けたい
     *
     * @param mentsu 入力したい面子
     */
    public void setMentsu(MahjongMentsu mentsu) {
        if (mentsu instanceof Janto) {
            janto = (Janto) mentsu;
        } else if (mentsu instanceof Shuntsu) {
            shuntsuList.add((Shuntsu) mentsu);
        } else if (mentsu instanceof Kotsu) {
            kotsuList.add((Kotsu) mentsu);
        } else if (mentsu instanceof Kantsu) {
            kantsuList.add((Kantsu) mentsu);
        }
    }

    public Janto getJanto() {
        return janto;
    }

    public void setJanto(Janto janto) {
        this.janto = janto;
    }

    public void setShuntsu(Shuntsu shuntsu) {
        shuntsuList.add(shuntsu);
    }

    public List<Shuntsu> getShuntsuList() {
        return shuntsuList;
    }

    public int getShuntsuNum() {
        return shuntsuList.size();
    }

    public void setKotsu(Kotsu kotsu) {
        kotsuList.add(kotsu);
    }

    public int getKotsuNum() {
        return kotsuList.size();
    }

    public List<Kotsu> getKotsuList() {
        return kotsuList;
    }

    public void setKantsu(Kantsu kantsu) {
        kantsuList.add(kantsu);
    }

    public int getKantsuNum() {
        return kantsuList.size();
    }

    public List<Kantsu> getKantsuList() {
        return kantsuList;
    }
}
