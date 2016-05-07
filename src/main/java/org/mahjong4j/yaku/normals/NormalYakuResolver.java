package org.mahjong4j.yaku.normals;

/**
 * 役判定のためのインターフェイスです。
 * 役を判定するクラスを実装します
 * 和了判定は別です。
 *
 * @author yu1ro
 */
public interface NormalYakuResolver {

    /**
     * このメソッドは役判定を行わないので
     * 先にisMatchを実行して判定して利用します
     *
     * @return それぞれの役のEnum
     */
    NormalYaku getNormalYaku();

    /**
     * 実際に役判定をおこないます
     *
     * @return その役がつくかどうか
     */
    boolean isMatch();
}
