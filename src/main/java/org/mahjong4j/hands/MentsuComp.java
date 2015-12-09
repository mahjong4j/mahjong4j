package org.mahjong4j.hands;

import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.Mahjong4jException;

import java.util.ArrayList;
import java.util.List;

/**
 * 上がれる面子を整理したクラスです
 *
 * @author yu1ro
 */
public class MentsuComp {

    private List<Toitsu> toitsuList = new ArrayList<>(7);
    private List<Shuntsu> shuntsuList = new ArrayList<>(4);
    private List<Kotsu> kotsuList = new ArrayList<>(4);
    private List<Kantsu> kantsuList = new ArrayList<>(4);

    /**
     * @param mentsuList 上がりとなった面子のリスト
     * @throws IllegalMentsuSizeException 和了れる形になっていなければthrow
     */
    public MentsuComp(List<MahjongMentsu> mentsuList) throws Mahjong4jException {
        for (MahjongMentsu mentsu : mentsuList) {
            setMentsu(mentsu);
        }

        //整合性を確認する
        int checkSum = shuntsuList.size() + kotsuList.size() + kantsuList.size();
        boolean isNormal = checkSum == 4 && toitsuList.size() == 1;
        boolean isChitoitsu = toitsuList.size() == 7 && checkSum == 0;
        if (!isNormal && !isChitoitsu) {
            throw new IllegalMentsuSizeException(mentsuList);
        }
    }

    /**
     * どの面子が入っても対応可能なセッター
     *
     * @param mentsu 入力したい面子
     */
    private void setMentsu(MahjongMentsu mentsu) {
        if (mentsu instanceof Toitsu) {
            toitsuList.add((Toitsu) mentsu);
        } else if (mentsu instanceof Shuntsu) {
            shuntsuList.add((Shuntsu) mentsu);
        } else if (mentsu instanceof Kotsu) {
            kotsuList.add((Kotsu) mentsu);
        } else if (mentsu instanceof Kantsu) {
            kantsuList.add((Kantsu) mentsu);
        }
    }

    public List<Toitsu> getToitsuList() {
        return toitsuList;
    }

    public int getToitsuNum() {
        return toitsuList.size();
    }

    public List<Shuntsu> getShuntsuList() {
        return shuntsuList;
    }

    public int getShuntsuNum() {
        return shuntsuList.size();
    }

    public List<Kotsu> getKotsuList() {
        return kotsuList;
    }

    public int getKotsuNum() {
        return kotsuList.size();
    }

    public List<Kantsu> getKantsuList() {
        return kantsuList;
    }

    public int getKantsuNum() {
        return kantsuList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MentsuComp)) return false;

        MentsuComp that = (MentsuComp) o;

        if (toitsuList != null ? !toitsuList.equals(that.toitsuList) : that.toitsuList != null) return false;
        if (shuntsuList != null ? !shuntsuList.equals(that.shuntsuList) : that.shuntsuList != null) return false;
        if (kotsuList != null ? !kotsuList.equals(that.kotsuList) : that.kotsuList != null) return false;
        return !(kantsuList != null ? !kantsuList.equals(that.kantsuList) : that.kantsuList != null);

    }

    @Override
    public int hashCode() {
        int result = toitsuList != null ? toitsuList.hashCode() : 0;
        result = 31 * result + (shuntsuList != null ? shuntsuList.hashCode() : 0);
        result = 31 * result + (kotsuList != null ? kotsuList.hashCode() : 0);
        result = 31 * result + (kantsuList != null ? kantsuList.hashCode() : 0);
        return result;
    }
}
