package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * 槓子に関するクラスです
 * 暗槓と明槓の両方を扱います
 *
 * @author yu1ro
 */
public class Kantsu implements MahjongMentsu {
    private MahjongTile identifierTile;

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     */
    private boolean isMentsu;

    /**
     * 明槓の場合はtrue
     * 暗槓の場合はfalse
     */
    private boolean isOpen;

    /**
     * 槓子が完成していることを前提にしているため
     * 槓子であるかのチェックは伴いません。
     *
     * @param isOpen         暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param identifierTile どの牌の槓子なのか
     */
    public Kantsu(boolean isOpen, MahjongTile identifierTile) {
        this.isOpen = isOpen;
        this.identifierTile = identifierTile;
        this.isMentsu = true;
    }

    /**
     * 槓子であるかのチェックも伴います
     * すべての牌(tile1~4)が同じ場合にisMentsuがtrueになります
     *
     * @param isOpen 暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     * @param tile4  4枚目
     */
    public Kantsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3, MahjongTile tile4) {
        this.isOpen = isOpen;
        if (this.isMentsu = check(tile1, tile2, tile3, tile4)) {
            identifierTile = tile1;
        }
    }

    /**
     * tile1~4が同一の牌かを調べます
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @param tile3 3枚目
     * @param tile4 4枚目
     * @return 槓子の場合true 槓子でない場合false
     */
    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3, MahjongTile tile4) {
        return tile1 == tile2 && tile2 == tile3 && tile3 == tile4;
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
