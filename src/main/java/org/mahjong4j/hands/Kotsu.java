package org.mahjong4j.hands;

import org.mahjong4j.tile.Tile;

/**
 * 刻子に関するクラスです
 * 暗刻と明刻の両方を扱います
 *
 * @author yu1ro
 */
public class Kotsu extends Mentsu {

    /**
     * 刻子であることがわかっている場合に利用します
     *
     * @param isOpen         暗刻ならばfalse 明刻ならばtrue
     * @param identifierTile どの牌の刻子なのか
     */
    public Kotsu(boolean isOpen, Tile identifierTile) {
        this.identifierTile = identifierTile;
        this.isOpen = isOpen;
        this.isMentsu = true;
    }

    /**
     * 刻子であるかのチェックも伴います
     * すべての牌(tile1~3)が同じ場合にisMentsuがtrueになります
     *
     * @param isOpen 暗刻の場合false, 明刻の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    public Kotsu(boolean isOpen, Tile tile1, Tile tile2, Tile tile3) {
        this.isOpen = isOpen;
        if (this.isMentsu = check(tile1, tile2, tile3)) {
            identifierTile = tile1;
        }
    }

    /**
     * 刻子であるかの判定を行ないます
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @param tile3 3枚目
     * @return 刻子であればtrue 刻子でなければfalse
     */
    public static boolean check(Tile tile1, Tile tile2, Tile tile3) {
        return tile1 == tile2 && tile2 == tile3;
    }

    @Override
    public int getFu() {
        int mentsuFu = 2;
        if (!isOpen) {
            mentsuFu *= 2;
        }
        if (identifierTile.isYaochu()) {
            mentsuFu *= 2;
        }
        return mentsuFu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kotsu)) return false;

        Kotsu kotsu = (Kotsu) o;

        if (isMentsu != kotsu.isMentsu) return false;
        if (isOpen != kotsu.isOpen) return false;
        return identifierTile == kotsu.identifierTile;

    }

    @Override
    public int hashCode() {
        int result = identifierTile != null ? identifierTile.hashCode() : 0;
        result = 31 * result + (isMentsu ? 1 : 0);
        result = 31 * result + (isOpen ? 1 : 0);
        return result;
    }
}
