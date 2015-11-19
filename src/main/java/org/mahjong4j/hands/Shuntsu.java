package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public class Shuntsu implements MahjongMentsu {
    private MahjongTile centerTile;
    private boolean isMentsu;
    private boolean isOpen;

    /**
     * 順子であることがわかっている場合に利用します
     *
     * @param isOpen     明順子ならばtrue 暗順子ならばfalse
     * @param centerTile 順子の組の二番目
     */
    public Shuntsu(boolean isOpen, MahjongTile centerTile) {
        // TODO: centerTileがありえない数ならthrow
        this.centerTile = centerTile;
        this.isOpen = isOpen;
        this.isMentsu = true;
    }

    /**
     * 順子であるかの判定も伴います
     *
     * @param isOpen 明順子ならばtrue 暗順子ならばfalse
     * @param tile1
     * @param tile2
     * @param tile3
     */
    public Shuntsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
        this.isOpen = isOpen;
    }

    public MahjongTile getCenterTile() {
        return centerTile;
    }

    public boolean getIsMentsu() {
        return isMentsu;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * @param tile1
     * @param tile2
     * @param tile3
     * @return
     */
    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {

        //Typeが違えば無条件でfalse
        if (tile1.getType() != tile2.getType() || tile2.getType() != tile3.getType()) {
            return false;
        }

        //字牌だったら絶対false
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

        //ソート後に判定
        return tile1.getNumber() + 1 == tile2.getNumber() && tile2.getNumber() + 1 == tile3.getNumber();
    }
}
