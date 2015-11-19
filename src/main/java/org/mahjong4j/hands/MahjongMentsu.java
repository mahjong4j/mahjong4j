package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * @author yu1ro
 */
public interface MahjongMentsu {
    MahjongTile getCenterTile();
    boolean getIsMentsu();
    boolean getIsOpen();
}
