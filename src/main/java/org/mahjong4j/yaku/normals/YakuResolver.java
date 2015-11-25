package org.mahjong4j.yaku.normals;

/**
 * @author yu1ro
 *         役判定のためのインターフェイスです。
 *         役を判定するクラスを実装します
 *         和了判定は別です。
 *         TODO:判定するメソッドも共通にするべきかもしれない
 */
public interface YakuResolver {
    int getHan();

    boolean isMatch();
}
