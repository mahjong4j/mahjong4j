package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

import java.util.ArrayList;
import java.util.List;

/**
 * 刻子に関するクラスです
 * 暗刻と明刻の両方を扱います
 *
 * @author yu1ro
 */
public class Kotsu implements MahjongMentsu {
    private MahjongTile identifierTile;

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     */
    private boolean isMentsu;

    /**
     * 明順子の場合はtrue
     * 暗順子の場合はfalse
     */
    private boolean isOpen;

    /**
     * 刻子であることがわかっている場合に利用します
     *
     * @param isOpen         暗刻ならばfalse 明刻ならばtrue
     * @param identifierTile どの牌の刻子なのか
     */
    public Kotsu(boolean isOpen, MahjongTile identifierTile) {
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
    public Kotsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
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
    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
        return tile1 == tile2 && tile2 == tile3;
    }

    public MahjongTile getTile() {
        return identifierTile;
    }

    public boolean getIsMentsu() {
        return isMentsu;
    }

    public boolean getIsOpen() {
        return isOpen;
    }
}
