package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;
import org.mahjong4j.yaku.normals.ChitoitsuResolver;
import org.mahjong4j.yaku.yakuman.KokushimusoResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 手牌に関する操作全般を扱います
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * 現在：何もできません
 * <p>
 * TODO: ツモって牌を捨てるオペレーションメソッド
 * TODO: otherTilesから面子に変換していく
 * TODO: 変換時に上がりの形ならばcanWinをtrueにする
 * TODO: ありえない場合(同じ牌5枚など)はthrow Exception
 *
 * @author yu1ro
 */
public class MahjongHands {
    // -------------------------確定系-----------------------

    //最後の牌
    private MahjongTile last;

    // TODO: 14をありうる最大の数にしたい
    //確定した上がりの形のリスト
    private List<List<MahjongMentsu>> winMentsuList = new ArrayList<List<MahjongMentsu>>(14);

    //確定した各牌の数一覧
    private int[] handsComp = new int[34];

    //あがれるか
    private boolean canWin = false;

    // TODO: 良き型
    private String winType;

    // ------------------------ストック系----------------------

    // コンストラクタで入力された面子リスト
    private List<MahjongMentsu> inputtedMentsuList = new ArrayList<MahjongMentsu>();

    // 操作する用のストック
    private int[] handStocks = new int[34];

    //
    private int[] inputtedTiles;

    // 雀頭の候補をストック
    private List<Janto> jantoStock = new ArrayList<Janto>(14);


    // TODO: throw exception
    public MahjongHands(int[] otherTiles, MahjongTile last, List<MahjongMentsu> mentsuList) {
        inputtedTiles = otherTiles;
        this.last = last;
        inputtedMentsuList = mentsuList;
    }

    public MahjongHands(int[] otherTiles, MahjongTile last, MahjongMentsu... mentsus) {
        inputtedTiles = otherTiles;
        this.last = last;
        Collections.addAll(inputtedMentsuList, mentsus);
    }

    /**
     * @param otherTiles lastの牌も含めて下さい合計14になるはずです
     * @param last       この牌もotherTilesに含めてください
     */
    public MahjongHands(int[] otherTiles, MahjongTile last) {
        inputtedTiles = otherTiles;
        this.last = last;

        findMentsu();
    }

    public List<List<MahjongMentsu>> getWinMentsuList() {
        return winMentsuList;
    }

    public String getWinType() {
        return winType;
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

    public void initStock() {
        System.arraycopy(inputtedTiles, 0, handStocks, 0, inputtedTiles.length);
    }

    /**
     * 槓子は見つけません
     */
    public void findMentsu() {
        // 同じ牌が5個以上有ったらfalse
        for (int hand : inputtedTiles) {
            if (hand > 4) {
                // TODO: throw exception
                canWin = false;
                return;
            }
        }

        // 国士無双型の判定
        initStock();
        if (KokushimusoResolver.isMatch(handStocks)) {
            winType = "Kokushimuso";
            canWin = true;
            return;
        }

        // 七対子の判定
        initStock();
        if (ChitoitsuResolver.isMatch(handStocks)) {
            winType = "Chitoitsu";
            canWin = true;
            return;
        }

        // その他の判定
        // 雀頭の候補を探してストックしておく
        initStock();
        jantoStock = Janto.findJantoCandidate(handStocks);


        // 雀頭が一つも見つからなければfalse
        if (jantoStock.size() == 0) {
            canWin = false;
            return;
        }

        //雀頭候補から探す
        List<MahjongMentsu> winCandidate = new ArrayList<MahjongMentsu>(4);
        for (Janto janto : jantoStock) {
            // 操作変数を初期化
            init(winCandidate, janto);

            //刻子優先検索
            //検索
            winCandidate.addAll(findKotsuCandidate());
            winCandidate.addAll(findShuntsuCandidate());
            //全て0かチェック
            checkWin(winCandidate);


            init(winCandidate, janto);
            //検索
            winCandidate.addAll(findShuntsuCandidate());
            winCandidate.addAll(findKotsuCandidate());
            checkWin(winCandidate);
        }

    }

    private void init(List<MahjongMentsu> winCandidate, Janto janto) {
        // 操作変数を初期化
        initStock();
        winCandidate.clear();
        //ストックから雀頭を減らす
        handStocks[janto.getTile().getCode()] -= 2;
        winCandidate.add(janto);
    }

    private void checkWin(List<MahjongMentsu> winCandidate) {
        //全て0かチェック
        if (isAllZero(handStocks)) {
            winType = "Normal";
            winMentsuList.add(winCandidate);
            canWin = true;
        }
    }

    private boolean isAllZero(int[] stocks) {
        for (int i : stocks) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private List<MahjongMentsu> findShuntsuCandidate() {
        List<MahjongMentsu> resultList = new ArrayList<MahjongMentsu>(4);
        //字牌などはチェックしないので26まで
        for (int j = 1; j < 26; j++) {
            // whileにしたのは一盃口などがあるから
            while (handStocks[j - 1] > 0 && handStocks[j] > 0 && handStocks[j + 1] > 0) {
                Shuntsu shuntsu = new Shuntsu(
                        false,
                        MahjongTile.valueOf(j - 1),
                        MahjongTile.valueOf(j),
                        MahjongTile.valueOf(j + 1));
                if (shuntsu.getIsMentsu()) {
                    resultList.add(shuntsu);
                    handStocks[j - 1]--;
                    handStocks[j]--;
                    handStocks[j + 1]--;
                } else {
                    // コードが並んでいるけど、順子じゃない時はwhileを抜ける
                    // M9,P1,P2などを想定
                    break;
                }
            }
        }
        return resultList;
    }

    private List<MahjongMentsu> findKotsuCandidate() {
        List<MahjongMentsu> resultList = new ArrayList<MahjongMentsu>(4);
        for (int i = 0; i < handStocks.length; i++) {
            if (handStocks[i] >= 3) {
                resultList.add(new Kotsu(false, MahjongTile.valueOf(i)));
                handStocks[i] -= 3;
            }
        }
        return resultList;
    }
}
