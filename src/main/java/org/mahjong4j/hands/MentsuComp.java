package org.mahjong4j.hands;

import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.tile.MahjongTile;

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
    private MahjongTile last;

    /**
     * @param mentsuList 上がりとなった面子のリスト
     * @param last
     * @throws IllegalMentsuSizeException 和了れる形になっていなければthrow
     */
    public MentsuComp(List<Mentsu> mentsuList, MahjongTile last) throws IllegalMentsuSizeException {
        this.last = last;
        for (Mentsu mentsu : mentsuList) {
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
    private void setMentsu(Mentsu mentsu) {
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

    /**
     * 七対子の場合はnullを返します
     *
     * @return 雀頭を返します
     */
    public Toitsu getJanto() {
        if (getToitsuCount() == 1) {
            return toitsuList.get(0);
        }
        return null;
    }

    public List<Toitsu> getToitsuList() {
        return toitsuList;
    }

    /**
     * 対子の数を返します
     * 1もしくは7以外を返すことはありません
     *
     * @return 通常の型の場合1 七対子の型の場合7
     */
    public int getToitsuCount() {
        return toitsuList.size();
    }

    public List<Shuntsu> getShuntsuList() {
        return shuntsuList;
    }

    /**
     * 順子の数を返します
     * 0~4のどれかです
     *
     * @return 順子の数
     */
    public int getShuntsuCount() {
        return shuntsuList.size();
    }

    public List<Kotsu> getKotsuList() {
        return kotsuList;
    }

    /**
     * 刻子でも槓子でも役の判定に影響しない場合に利用します
     * 刻子と槓子をまとめて返します。
     * TODO:good name
     *
     * @return 刻子と槓子をまとめたリスト
     */
    public List<Kotsu> getKotsuKantsu() {
        List<Kotsu> kotsuList = new ArrayList<>(this.kotsuList);
        for (Kantsu kantsu : kantsuList) {
            kotsuList.add(new Kotsu(kantsu.isOpen(), kantsu.getTile()));
        }
        return kotsuList;
    }

    /**
     * 刻子の数を返します
     * 0~4のどれかです
     *
     * @return 刻子の数
     */
    public int getKotsuCount() {
        return kotsuList.size();
    }

    public List<Kantsu> getKantsuList() {
        return kantsuList;
    }

    /**
     * 槓子の数を返します
     * 0~4のどれかです
     *
     * @return 槓子の数
     */
    public int getKantsuCount() {
        return kantsuList.size();
    }

    /**
     * 対子も含めて全ての面子をリストにして返します
     *
     * @return 構成する全ての面子のリスト
     */
    public List<Mentsu> getAllMentsu() {
        List<Mentsu> allMentsu = new ArrayList<>(7);
        allMentsu.addAll(getToitsuList());
        allMentsu.addAll(getShuntsuList());
        allMentsu.addAll(getKotsuList());
        allMentsu.addAll(getKantsuList());

        return allMentsu;
    }

    public MahjongTile getLast() {
        return last;
    }

    public boolean isRyanmen(MahjongTile last) {
        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.isOpen()) {
                continue;
            }
            if (shuntsu.getTile().getType() != last.getType()) {
                continue;
            }

            int number = shuntsu.getTile().getNumber();
            if (number == 8 || number == 2) {
                continue;
            }

            if (number - 1 == last.getNumber() || number + 1 == last.getNumber()) {
                return true;
            }
        }

        return false;
    }

    public boolean isTanki(MahjongTile last) {
        return getJanto().getTile() == last;
    }

    public boolean isNobetan(MahjongTile last) {
        if (getJanto().getTile() != last) {
            return false;
        }
        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.isOpen() || shuntsu.getTile().getType() != last.getType()) {
                continue;
            }
            int shuntsuNum = shuntsu.getTile().getNumber();
            int lastNum = last.getNumber();
            if (shuntsuNum + 2 == lastNum || shuntsuNum - 2 == lastNum) {
                return true;
            }
        }
        return false;
    }

    public boolean isKanchan(MahjongTile last) {
        if (isRyanmen(last)) {
            return false;
        }
        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.isOpen() || shuntsu.getTile().getType() != last.getType()) {
                continue;
            }
            if (shuntsu.getTile().getNumber() == last.getNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPenchan(MahjongTile last) {
        if (isRyanmen(last)) {
            return false;
        }
        for (Shuntsu shuntsu : shuntsuList) {
            if (shuntsu.isOpen() || shuntsu.getTile().getType() != last.getType()) {
                continue;
            }
            int number = shuntsu.getTile().getNumber();
            if (number == 8 && last.getNumber() == 7) {
                return true;
            }
            if (number == 2 && last.getNumber() == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * 各面子のリストの順番は関係ないので、
     * 面子の順番が違っていてもtrueになります
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MentsuComp)) return false;

        MentsuComp that = (MentsuComp) o;

        if (last != that.last) return false;
        if (toitsuList.size() != that.toitsuList.size()) return false;
        if (shuntsuList.size() != that.shuntsuList.size()) return false;
        if (kotsuList.size() != that.kotsuList.size()) return false;
        if (kantsuList.size() != that.kantsuList.size()) return false;
        for (Toitsu toitsu : toitsuList) {
            if (!that.toitsuList.contains(toitsu)) return false;
        }
        for (Shuntsu shuntsu : shuntsuList) {
            if (!that.shuntsuList.contains(shuntsu)) return false;
        }
        for (Kotsu kotsu : kotsuList) {
            if (!that.kotsuList.contains(kotsu)) return false;
        }
        for (Kantsu kantsu : kantsuList) {
            if (!that.kantsuList.contains(kantsu)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        int result;

        result = last.hashCode();

        if (toitsuList != null) {
            for (Toitsu toitsu : toitsuList) {
                tmp += toitsu.hashCode();
            }
        }
        result = 31 * result + tmp;

        tmp = 0;
        if (shuntsuList != null) {
            for (Shuntsu shuntsu : shuntsuList) {
                tmp += shuntsu.hashCode();
            }
        }

        result = 31 * result + tmp;

        tmp = 0;
        if (kotsuList != null) {
            for (Kotsu kotsu : kotsuList) {
                tmp += kotsu.hashCode();
            }
        }

        result = 31 * result + tmp;

        tmp = 0;
        if (kantsuList != null) {
            for (Kantsu kantsu : kantsuList) {
                tmp += kantsu.hashCode();
            }
        }

        return 31 * result + tmp;
    }
}
