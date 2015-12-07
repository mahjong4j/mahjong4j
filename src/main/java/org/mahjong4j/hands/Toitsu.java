package org.mahjong4j.hands;

import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu1ro
 */
public class Toitsu implements MahjongMentsu {
    private MahjongTile identifierTile;
    private boolean isMentsu;

    /**
     * 対子であることがわかっている場合に使います
     *
     * @param identifierTile 対子の種類
     */
    public Toitsu(MahjongTile identifierTile) {
        this.identifierTile = identifierTile;
        this.isMentsu = true;
    }

    /**
     * 対子であるかのチェックを伴います
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     */
    public Toitsu(MahjongTile tile1, MahjongTile tile2) {
        if (this.isMentsu = Toitsu.check(tile1, tile2)) {
            this.identifierTile = tile1;
        }
    }

    /**
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @return 2枚が一致すればtrue
     */
    public static boolean check(MahjongTile tile1, MahjongTile tile2) {
        return tile1 == tile2;
    }

    /**
     * 対子になりうる牌をリストにして返す
     *
     * @param tiles 手牌
     * @return 雀頭候補の対子リスト
     */
    public static List<Toitsu> findJantoCandidate(int[] tiles) throws MahjongTileOverFlowException {
        List<Toitsu> result = new ArrayList<>(7);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] > 4) {
                throw new MahjongTileOverFlowException(i, tiles[i]);
            }
            if (tiles[i] >= 2) {
                result.add(new Toitsu(MahjongTile.valueOf(i)));
            }
        }
        return result;
    }

    /**
     * 対子として成立していなければnullを返します
     *
     * @return 対子の牌の種類
     */
    public MahjongTile getTile() {
        return identifierTile;
    }

    /**
     * @return 面子(対子)として成立していればtrue
     */
    public boolean getIsMentsu() {
        return isMentsu;
    }

    /**
     * 対子は常に見せないので常にfalse
     *
     * @return false
     */
    public boolean getIsOpen() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toitsu)) return false;

        Toitsu toitsu = (Toitsu) o;

        if (isMentsu != toitsu.isMentsu) return false;
        return identifierTile == toitsu.identifierTile;

    }

    @Override
    public int hashCode() {
        int result = identifierTile != null ? identifierTile.hashCode() : 0;
        result = 31 * result + (isMentsu ? 1 : 0);
        return result;
    }
}
