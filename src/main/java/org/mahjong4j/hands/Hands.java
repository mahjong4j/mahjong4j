package org.mahjong4j.hands;

import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.yakuman.KokushimusoResolver;

import java.util.*;

/**
 * 手牌に関する操作全般を扱います
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * TODO: ツモって牌を捨てるオペレーションメソッド
 *
 * @author yu1ro
 */
public class Hands {
    // -------------------------確定系-----------------------

    //確定した上がりの形のリスト
    private Set<MentsuComp> mentsuCompSet = new HashSet<>();

    //確定した各牌の数一覧
    private int[] handsComp = new int[34];

    //最後の牌
    private Tile last;

    //あがれるか
    private boolean canWin = false;

    //食い下がりかどうか
    private boolean isOpen = false;

    // ------------------------ストック系----------------------

    // コンストラクタで入力された面子リスト
    private List<Mentsu> inputtedMentsuList = new ArrayList<>();

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
    public Hands(int[] otherTiles, Tile last, List<Mentsu> mentsuList) throws MahjongTileOverFlowException, IllegalMentsuSizeException {
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
    public Hands(int[] otherTiles, Tile last, Mentsu... mentsu) throws MahjongTileOverFlowException, IllegalMentsuSizeException {
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
    public Hands(int[] allTiles, Tile last) throws HandsOverFlowException, MahjongTileOverFlowException, IllegalMentsuSizeException {
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
    private void setHandsComp(int[] otherTiles, List<Mentsu> mentsuList) {
        System.arraycopy(otherTiles, 0, handsComp, 0, otherTiles.length);
        for (Mentsu mentsu : mentsuList) {
            int code = mentsu.getTile().getCode();

            if (mentsu.isOpen()) {
                isOpen = true;
            }

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

    public Set<MentsuComp> getMentsuCompSet() {
        return mentsuCompSet;
    }

    public boolean getCanWin() {
        return canWin;
    }

    public Tile getLast() {
        return last;
    }

    public int[] getHandsComp() {
        return handsComp;
    }

    public boolean getIsKokushimuso() {
        return isKokushimuso;
    }

    public boolean isOpen() {
        return isOpen;
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
    public void findMentsu() throws MahjongTileOverFlowException, IllegalMentsuSizeException {
        checkTileOverFlow();

        // 国士無双型の判定
        initStock();
        KokushimusoResolver kokushimuso = new KokushimusoResolver(handStocks);
        if (kokushimuso.isMatch()) {
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
            List<Mentsu> mentsuList = new ArrayList<>(7);
            mentsuList.addAll(toitsuList);
            MentsuComp comp = new MentsuComp(mentsuList, last);
            mentsuCompSet.add(comp);
        }

        // その他の判定
        //雀頭候補から探す
        List<Mentsu> winCandidate = new ArrayList<>(4);
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
     * 同じ牌が5個以上はありえないので、Exception をthrow
     *
     * @throws MahjongTileOverFlowException
     */
    private void checkTileOverFlow() throws MahjongTileOverFlowException {
        //
        for (int i = 0; i < handsComp.length; i++) {
            int hand = handsComp[i];
            if (hand > 4) {
                canWin = false;
                throw new MahjongTileOverFlowException(i, hand);
            }
        }
    }

    /**
     * 操作変数・面子の候補を初期化し
     * 雀頭の分をストックから減らします
     *
     * @param winCandidate 面子の候補
     * @param toitsu       この検索サイクルの雀頭候補
     */
    private void init(List<Mentsu> winCandidate, Toitsu toitsu) {
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
    private void convertToMentsuComp(List<Mentsu> winCandidate) throws IllegalMentsuSizeException {
        //全て0かチェック
        if (isAllZero(handStocks)) {
            canWin = true;
            winCandidate.addAll(inputtedMentsuList);
            MentsuComp mentsuComp = new MentsuComp(winCandidate, last);
            if (!mentsuCompSet.contains(mentsuComp)) {
                mentsuCompSet.add(mentsuComp);
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

    private List<Mentsu> findShuntsuCandidate() {
        List<Mentsu> resultList = new ArrayList<>(4);
        //字牌などはチェックしないので26まで
        for (int j = 1; j < 26; j++) {
            // whileにしたのは一盃口などがあるから
            while (handStocks[j - 1] > 0 && handStocks[j] > 0 && handStocks[j + 1] > 0) {
                Shuntsu shuntsu = new Shuntsu(
                    false,
                    Tile.valueOf(j - 1),
                    Tile.valueOf(j),
                    Tile.valueOf(j + 1)
                );

                //3つ並んでいても順子であるとは限らないので調べる
                if (shuntsu.isMentsu()) {
                    resultList.add(shuntsu);
                    handStocks[j - 1]--;
                    handStocks[j]--;
                    handStocks[j + 1]--;
                }
            }
        }
        return resultList;
    }

    private List<Mentsu> findKotsuCandidate() {
        List<Mentsu> resultList = new ArrayList<>(4);
        for (int i = 0; i < handStocks.length; i++) {
            if (handStocks[i] >= 3) {
                resultList.add(new Kotsu(false, Tile.valueOf(i)));
                handStocks[i] -= 3;
            }
        }
        return resultList;
    }
}
