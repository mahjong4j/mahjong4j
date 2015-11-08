package org.mahjong4j.mahjong;

/**
 * @author yu1ro
 *         <p>
 *         役判定のためのインターフェイスです。
 *         役を判定するクラスを実装します
 *         <p>
 *         和了判定は別です。
 */

public interface MahjongResolver {
    int howHan();
}
