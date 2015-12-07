package org.mahjong4j.hands;

import org.mahjong4j.tile.MahjongTile;

/**
 * 面子に関するインターフェイスです
 * 順子・刻子・槓子・対子を扱います
 *
 * @author yu1ro
 */
public interface MahjongMentsu {
    // TODO: 符計算用に符をかえすようにしたい

    /**
     * 順子の場合は2番目の牌です
     *
     * @return どの牌で面子となっているか
     */
    MahjongTile getTile();

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     *
     * @return 面子として成立しているか
     */
    boolean getIsMentsu();

    /**
     * 食い下がり判定用です
     * 鳴いている場合はtrueですが、
     * 暗槓の場合はfalseです。
     *
     * @return 鳴いているか
     */
    boolean getIsOpen();
}
