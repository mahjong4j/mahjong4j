package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MahjongHands;

/**
 * @author yu1ro
 *         役判定のためのインターフェイスです。
 *         役を判定するクラスを実装します
 *         和了判定は別です。
 */
public interface NormalYakuResolver {

    int getHan();

    boolean isMatch(MahjongHands hands);
}
