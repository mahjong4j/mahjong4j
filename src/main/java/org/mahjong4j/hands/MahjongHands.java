package org.mahjong4j.hands;

import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.Mahjong4jException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.yakuman.KokushimusoResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 手牌に関する操作全般を扱います
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * TODO: ツモって牌を捨てるオペレーションメソッド
 *
 * @author yu1ro
 */
public class MahjongHands {
    // -------------------------確定系-----------------------

    // TODO: 14をありうる最大の数にしたい
    //確定した上がりの形のリスト
    private List<MentsuComp> mentsuCompList = new ArrayList<>(14);

    //確定した各牌の数一覧
    private int[] handsComp = new int[34];

    //最後の牌
    private MahjongTile last;

    //あがれるか
    private boolean canWin = false;

    // ------------------------ストック系----------------------

    // コンストラクタで入力された面子リスト
    private List<MahjongMentsu> inputtedMentsuList = new ArrayList<>();

    // 操作する用のストック
    private int[] handStocks = new int[34];

    // コンストラクタで入力された各牌の数の配列
    private int[] inputtedTiles;
    private boolean isKokushimuso = false;

    /**
     * @param otherTiles
     * @param last
     * @param mentsuList
     * @throws MahjongTileOverFlowException
     */
    public MahjongHands(int[] otherTiles, MahjongTile last, List<MahjongMentsu> mentsuList) throws Mahjong4jException {
        inputtedTiles = otherTiles;
        this.last = last;
        inputtedMentsuList = mentsuList;
        setHandsComp(otherTiles, mentsuList);
        findMentsu();
    }

    /**
     * @param otherTiles
     * @param last
     * @param mentsu
     * @throws MahjongTileOverFlowException
     */
    public MahjongHands(int[] otherTiles, MahjongTile last, MahjongMentsu... mentsu) throws Mahjong4jException {
        inputtedTiles = otherTiles;
        setHandsComp(otherTiles, Arrays.asList(mentsu));
        this.last = last;
        Collections.addAll(inputtedMentsuList, mentsu);
        findMentsu();
    }

    /**
     * @param allTiles lastの牌も含めて下さい合計14になるはずです
     * @param last     この牌もotherTilesに含めてください
     */
    public MahjongHands(int[] allTiles, MahjongTile last) throws Mahjong4jException {
        inputtedTiles = allTiles;
        this.last = last;

        //整合性をチェック
        checkTiles(allTiles);

        handsComp = allTiles;

        findMentsu();
    }

    /**
     * コンストラクタで面子を入力した場合に
     * 面子を各牌の数に変換します
     *
     * @param otherTiles 各牌の数
     * @param mentsuList 面子のリスト
     */
    private void setHandsComp(int[] otherTiles, List<MahjongMentsu> mentsuList) {
        System.arraycopy(otherTiles, 0, handsComp, 0, otherTiles.length);
        for (MahjongMentsu mentsu : mentsuList) {
            int code = mentsu.getTile().getCode();
            if (mentsu instanceof Shuntsu) {
                handsComp[code - 1] += 1;
                handsComp[code] += 1;
                handsComp[code + 1] += 1;
            } else if (mentsu instanceof Kotsu) {
                handsComp[code] += 3;
            } else if (mentsu instanceof Kantsu) {
                handsComp[code] += 4;
            } else if (mentsu instanceof Toitsu) {
                handsComp[code] += 2;
            }
        }
    }

    public List<MentsuComp> getMentsuCompList() {
        return mentsuCompList;
    }

    public boolean getCanWin() {
        return canWin;
    }

    public MahjongTile getLast() {
        return last;
    }

    public int[] getHandsComp() {
        return handsComp;
    }

    private void checkTiles(int[] allTiles) throws HandsOverFlowException {
        int num = 0;
        for (int tileNum : allTiles) {
            num += tileNum;
            if (num > 14) {
                throw new HandsOverFlowException();
            }
        }
    }

    public void initStock() {
        System.arraycopy(inputtedTiles, 0, handStocks, 0, inputtedTiles.length);
    }

    /**
     * 槓子は見つけません
     */
    public void findMentsu() throws Mahjong4jException {
        // 同じ牌が5個以上有ったらfalse
        for (int i = 0; i < inputtedTiles.length; i++) {
            int hand = inputtedTiles[i];
            if (hand > 4) {
                canWin = false;
                throw new MahjongTileOverFlowException(i, hand);
            }
        }

        // 国士無双型の判定
        initStock();
        if (KokushimusoResolver.isMatch(handStocks)) {
            isKokushimuso = true;
            canWin = true;
            return;
        }

        // 雀頭の候補を探してストックしておく
        initStock();
        List<Toitsu> toitsuList = Toitsu.findJantoCandidate(handStocks);

        // 雀頭が一つも見つからなければfalse
        if (toitsuList.size() == 0) {
            canWin = false;
            return;
        }

        //七対子なら保存しておく
        if (toitsuList.size() == 7) {
            canWin = true;
            List<MahjongMentsu> mentsuList = new ArrayList<>(7);
            mentsuList.addAll(toitsuList);
            MentsuComp comp = new MentsuComp(mentsuList);
            mentsuCompList.add(comp);
        }

        // その他の判定
        //雀頭候補から探す
        List<MahjongMentsu> winCandidate = new ArrayList<>(4);
        for (Toitsu toitsu : toitsuList) {
            // 操作変数を初期化
            init(winCandidate, toitsu);

            //刻子優先検索
            //検索
            winCandidate.addAll(findKotsuCandidate());
            winCandidate.addAll(findShuntsuCandidate());
            //全て0かチェック
            convertToMentsuComp(winCandidate);

            init(winCandidate, toitsu);
            //順子優先検索
            winCandidate.addAll(findShuntsuCandidate());
            winCandidate.addAll(findKotsuCandidate());
            convertToMentsuComp(winCandidate);
        }

    }

    /**
     * 操作変数・面子の候補を初期化し
     * 雀頭の分をストックから減らします
     *
     * @param winCandidate 面子の候補
     * @param toitsu        この検索サイクルの雀頭候補
     */
    private void init(List<MahjongMentsu> winCandidate, Toitsu toitsu) {
        // 操作変数を初期化
        initStock();
        winCandidate.clear();
        //ストックから雀頭を減らす
        handStocks[toitsu.getTile().getCode()] -= 2;
        winCandidate.add(toitsu);
    }

    /**
     * handsStockが全て0の場合
     * winCandidateは完成しているので
     * mentsuCompに代入します
     *
     * @param winCandidate mentsuCompに代入するかもしれない
     */
    private void convertToMentsuComp(List<MahjongMentsu> winCandidate) throws Mahjong4jException {
        //全て0かチェック
        if (isAllZero(handStocks)) {
            canWin = true;
            winCandidate.addAll(inputtedMentsuList);
            MentsuComp mentsuComp = new MentsuComp(winCandidate);
            if (!mentsuCompList.contains(mentsuComp)) {
                mentsuCompList.add(mentsuComp);
            }
        }
    }

    /**
     * 入力の配列が全て0かを調べます
     *
     * @param stocks 調べたい配列
     * @return すべて0ならtrue ひとつでも0でなければfalse
     */
    private boolean isAllZero(int[] stocks) {
        for (int i : stocks) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private List<MahjongMentsu> findShuntsuCandidate() {
        List<MahjongMentsu> resultList = new ArrayList<>(4);
        //字牌などはチェックしないので26まで
        for (int j = 1; j < 26; j++) {
            // whileにしたのは一盃口などがあるから
            while (handStocks[j - 1] > 0 && handStocks[j] > 0 && handStocks[j + 1] > 0) {
                Shuntsu shuntsu = new Shuntsu(
                    false,
                    MahjongTile.valueOf(j - 1),
                    MahjongTile.valueOf(j),
                    MahjongTile.valueOf(j + 1)
                );

                //3つ並んでいても順子であるとは限らないので調べる
                if (shuntsu.getIsMentsu()) {
                    resultList.add(shuntsu);
                    handStocks[j - 1]--;
                    handStocks[j]--;
                    handStocks[j + 1]--;
                }
            }
        }
        return resultList;
    }

    private List<MahjongMentsu> findKotsuCandidate() {
        List<MahjongMentsu> resultList = new ArrayList<>(4);
        for (int i = 0; i < handStocks.length; i++) {
            if (handStocks[i] >= 3) {
                resultList.add(new Kotsu(false, MahjongTile.valueOf(i)));
                handStocks[i] -= 3;
            }
        }
        return resultList;
    }

    public boolean getIsKokushimuso() {
        return isKokushimuso;
    }
}
