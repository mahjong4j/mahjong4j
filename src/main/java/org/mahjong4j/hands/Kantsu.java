package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public class Kantsu implements MahjongMentsu {
    private MahjongTile centerTile;
    private boolean isMentsu;
    private boolean isOpen;

    /**
     *
     * @param isOpen
     * @param centerTile
     */
    public Kantsu(boolean isOpen, MahjongTile centerTile) {
        this.isOpen = isOpen;
        this.centerTile = centerTile;
        this.isMentsu = true;
    }

    /**
     *
     * @param isOpen
     * @param tile1
     * @param tile2
     * @param tile3
     * @param tile4
     */
    public Kantsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3, MahjongTile tile4) {
        this.isOpen = isOpen;
        this.isMentsu = check(tile1, tile2, tile3, tile4);
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

    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3, MahjongTile tile4) {
        return tile1 == tile2 && tile2 == tile3 && tile3 == tile4;
    }
}
