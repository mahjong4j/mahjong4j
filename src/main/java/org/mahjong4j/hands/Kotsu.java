package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public class Kotsu implements MahjongMentsu {
    private MahjongTile centerTile;
    private boolean isMentsu;
    private boolean isOpen;

    public Kotsu(boolean isOpen, MahjongTile centerTile) {
        this.centerTile = centerTile;
        this.isOpen = isOpen;
        this.isMentsu = true;
    }

    public Kotsu(boolean isOpen, MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
        this.isOpen = isOpen;
        this.isMentsu = check(tile1, tile2, tile3);
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

    public static boolean check(MahjongTile tile1, MahjongTile tile2, MahjongTile tile3) {
        return tile1 == tile2 && tile2 == tile3;
    }
}
