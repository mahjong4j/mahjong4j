package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 手牌に関する操作全般を扱います。
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * 現在：何もできません。
 *
 * TODO: ツモって牌を捨てるオペレーションメソッド
 * TODO: otherTilesから面子に変換していく
 * TODO: 変換時に上がりの形ならばcanWinをtrueにする
 * TODO: ありえない場合(同じ牌5枚など)はthrow Exception
 *
 * @author yu1ro
 */
public class MahjongHands {

    private int[] otherTiles;

    //最後の牌
    private MahjongTile last;

    //4は面子の最大数
    private List<MahjongMentsu> mentsuList = new ArrayList<MahjongMentsu>(4);

    private List<Shuntsu> shuntsuList = new ArrayList<Shuntsu>(4);
    private List<Kotsu> kotsuList = new ArrayList<Kotsu>(4);
    private List<Kantsu> kantsuList = new ArrayList<Kantsu>(4);

    private MahjongTile janto;


    private boolean canWin = false;


    // TODO: throw exception
    public MahjongHands(int[] otherTiles, MahjongTile last, List<MahjongMentsu> mentsuList) {
        this.otherTiles = otherTiles;
        this.last = last;
        this.mentsuList = mentsuList;
    }

    public MahjongHands(int[] otherTiles, MahjongTile last, MahjongMentsu... mentsus) {
        this.otherTiles = otherTiles;
        this.last = last;
        Collections.addAll(mentsuList, mentsus);
    }

    public MahjongHands(int[] otherTiles, MahjongTile last) {
        this.otherTiles = otherTiles;
        this.last = last;
    }

    public void findMentsu() {

    }

    public List<Shuntsu> getShuntsuList() {
        return shuntsuList;
    }

    public List<Kotsu> getKotsuList() {
        return kotsuList;
    }

    public List<Kantsu> getKantsuList() {
        return kantsuList;
    }

    public boolean isCanWin() {
        return canWin;
    }
}
