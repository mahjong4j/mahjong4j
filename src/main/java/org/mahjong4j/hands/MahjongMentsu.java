package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * 面子に関するインターフェイスです
 * 順子・刻子・槓子を扱います
 *
 * @author yu1ro
 */
public interface MahjongMentsu {
    // TODO: 符計算用に符をかえすようにしたい

    MahjongTile getIdentifierTile();

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     */
    boolean getIsMentsu();

    /**
     * 食い下がり判定用です
     * 公開している場合はtrueですが、
     * 暗槓の場合はfalseです。
     */
    boolean getIsOpen();
}
