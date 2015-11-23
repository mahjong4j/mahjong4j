package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * 順子に関するクラスです
 * 暗順子と明順子の両方を扱います
 *
 * @author yu1ro
 */
public class Shuntsu implements MahjongMentsu {

    /**
     * 3つの牌のうち2番めの牌
     */
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
     * 順子であることがわかっている場合に利用します
     *
     * @param isOpen         明順子ならばtrue 暗順子ならばfalse
     * @param identifierTile 順子の組の二番目
     */
    public Shuntsu(boolean isOpen, MahjongTile identifierTile) {
        // TODO: centerTileがありえない数ならthrow
        this.identifierTile = identifierTile;
        this.isOpen = isOpen;
        this.isMentsu = true;
    }

    /**
     * 順子であるかの判定も伴います
     *
     * @param isOpen 明順子ならばtrue 暗順子ならばfalseを入力して下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    public Shuntsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
        this.isOpen = isOpen;

        // TODO: 共通化
        //ソートする
        MahjongTile s;
        if (tile1.getNumber() > tile2.getNumber()) {
            s = tile1;
            tile1 = tile2;
            tile2 = s;
        }
        if (tile1.getNumber() > tile3.getNumber()) {
            s = tile1;
            tile1 = tile3;
            tile3 = s;
        }
        if (tile2.getNumber() > tile3.getNumber()) {
            s = tile2;
            tile2 = tile3;
            tile3 = s;
        }
        if (this.isMentsu = check(tile1, tile2, tile3)) {
            identifierTile = tile2;
        }
    }

    /**
     * 順子かどうかの判定を行ないます
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @param tile3 3枚目
     * @return 順子であればtrue 順子でなければfalse
     */
    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {

        //Typeが違う場合false
        if (tile1.getType() != tile2.getType() || tile2.getType() != tile3.getType()) {
            return false;
        }

        //字牌だった場合false
        if (tile1.getNumber() == 0 || tile2.getNumber() == 0 || tile3.getNumber() == 0) {
            return false;
        }

        //ソートする
        MahjongTile s;
        if (tile1.getNumber() > tile2.getNumber()) {
            s = tile1;
            tile1 = tile2;
            tile2 = s;
        }
        if (tile1.getNumber() > tile3.getNumber()) {
            s = tile1;
            tile1 = tile3;
            tile3 = s;
        }
        if (tile2.getNumber() > tile3.getNumber()) {
            s = tile2;
            tile2 = tile3;
            tile3 = s;
        }

        //判定
        return tile1.getNumber() + 1 == tile2.getNumber() && tile2.getNumber() + 1 == tile3.getNumber();
    }

    public MahjongTile getIdentifierTile() {
        return identifierTile;
    }

    public boolean getIsMentsu() {
        return isMentsu;
    }

    public boolean getIsOpen() {
        return isOpen;
    }


}
